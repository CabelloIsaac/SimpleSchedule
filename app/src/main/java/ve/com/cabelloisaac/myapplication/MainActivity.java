package ve.com.cabelloisaac.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import static ve.com.cabelloisaac.myapplication.Database.DIA;
import static ve.com.cabelloisaac.myapplication.Database.HORA;
import static ve.com.cabelloisaac.myapplication.Database.HORA_END;
import static ve.com.cabelloisaac.myapplication.Database.MINUTO;
import static ve.com.cabelloisaac.myapplication.Database.MINUTO_END;
import static ve.com.cabelloisaac.myapplication.Database.SEMANA;

public class MainActivity extends AppCompatActivity implements com.borax12.materialdaterangepicker.time.TimePickerDialog.OnTimeSetListener {

    private static final String TAG = "MainActivity";
    private ListView lvDias;
    private ArrayList<Dias> dias = new ArrayList<>();
    private SQLiteDatabase db;
    private DiasAdapter diasAdapter;
    private String diaSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this, Database.DB_NAME, null, Database.DB_VERSION).getWritableDatabase();
        lvDias = findViewById(R.id.lvDias);
        diasAdapter = new DiasAdapter(this, dias);

        lvDias.setAdapter(diasAdapter);

        cargarHorario();

        lvDias.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                diaSeleccionado = Integer.toString(dias.get(position).getDia());

                Log.d(TAG, "dia: "+diaSeleccionado);
                Calendar now = Calendar.getInstance();
                TimePickerDialog tpd = TimePickerDialog.newInstance(
                        MainActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        false
                );
                tpd.show(getFragmentManager(), "Timepickerdialog");
                return false;
            }
        });
    }

    private void cargarHorario() {
        dias.clear();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SEMANA, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int dia = cursor.getInt(1);
                int hora = cursor.getInt(2);
                int minuto = cursor.getInt(3);
                int horaEnd = cursor.getInt(4);
                int minutoEnd = cursor.getInt(5);

                Log.d(TAG, "cargarHorario: "+dia+" "+hora+":"+minuto);
                dias.add(new Dias(id, dia, hora, minuto, horaEnd, minutoEnd));
            } while (cursor.moveToNext());
            diasAdapter.notifyDataSetChanged();
        }
    }

    private void actualizarHorarioDia(int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(HORA, hourOfDay);
        contentValues.put(MINUTO, minute);
        contentValues.put(HORA_END, hourOfDayEnd);
        contentValues.put(MINUTO_END, minuteEnd);
        db.update(SEMANA, contentValues, "dia=?", new String[]{diaSeleccionado});
        cargarHorario();
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {

        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        String hourStringEnd = hourOfDayEnd < 10 ? "0" + hourOfDayEnd : "" + hourOfDayEnd;
        String minuteStringEnd = minuteEnd < 10 ? "0" + minuteEnd : "" + minuteEnd;
        String time = "You picked the following time: From - " + hourString + ":" + minuteString + " To - " + hourStringEnd + ":" + minuteStringEnd;

        Log.d(TAG, "onTimeSet: " + time);

        actualizarHorarioDia(hourOfDay, minute, hourOfDayEnd, minuteEnd);
    }
}
