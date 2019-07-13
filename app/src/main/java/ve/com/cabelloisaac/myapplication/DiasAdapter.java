package ve.com.cabelloisaac.myapplication;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static ve.com.cabelloisaac.myapplication.Constantes.DOMINGO;
import static ve.com.cabelloisaac.myapplication.Constantes.JUEVES;
import static ve.com.cabelloisaac.myapplication.Constantes.LUNES;
import static ve.com.cabelloisaac.myapplication.Constantes.MARTES;
import static ve.com.cabelloisaac.myapplication.Constantes.MIERCOLES;
import static ve.com.cabelloisaac.myapplication.Constantes.SABADO;
import static ve.com.cabelloisaac.myapplication.Constantes.VIERNES;

/**
 * Created by cabel on 21/1/2018.
 */

public class DiasAdapter extends BaseAdapter {

    private static final String TAG = "Adapter";
    protected Activity activity;
    protected ArrayList<Dias> items;

    public DiasAdapter(Activity activity, ArrayList<Dias> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    public void clear() {
        items.clear();
    }

    public void addAll(ArrayList<Dias> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.model_dia, null);
        }

        //Crea elemento con datos
        Dias dia = items.get(position);

        //Inicializa componentes
        TextView tvID = v.findViewById(R.id.tvID);
        TextView tvNumeroDia = v.findViewById(R.id.tvNumeroDia);
        TextView tvNombreDia = v.findViewById(R.id.tvNombreDia);
        TextView tvHorario = v.findViewById(R.id.tvHorario);

        String diaDeLaSemana = "", horario;

        switch (dia.getDia()) {
            case DOMINGO:
                diaDeLaSemana = "Domingo";
                break;
            case LUNES:
                diaDeLaSemana = "Lunes";
                break;
            case MARTES:
                diaDeLaSemana = "Martes";
                break;
            case MIERCOLES:
                diaDeLaSemana = "Miércoles";
                break;
            case JUEVES:
                diaDeLaSemana = "Jueves";
                break;
            case VIERNES:
                diaDeLaSemana = "Viernes";
                break;
            case SABADO:
                diaDeLaSemana = "Sábado";
                break;
        }


        String minuto = dia.getMinuto() < 10 ? "0" + dia.getMinuto() : "" + dia.getMinuto();
        String minutoEnd = dia.getMinutoEnd() < 10 ? "0" + dia.getMinutoEnd() : "" + dia.getMinutoEnd();

        String ampm = dia.getHora() < 12 ? "am" : "pm";
        String ampmEnd = dia.getHoraEnd() < 12 ? "am" : "pm";

        //Convertimos de 24h a 12h
        int horaTemp = dia.getHora() > 12 ? dia.getHora() - 12 : dia.getHora();
        int horaEndTemp = dia.getHoraEnd() > 12 ? dia.getHoraEnd() - 12 : dia.getHoraEnd();

        //Agregamos 0 al principio si es necesario
        String hora = horaTemp < 10 ? "0" + horaTemp : "" + horaTemp;
        String horaEnd = horaEndTemp < 10 ? "0" + horaEndTemp : "" + horaEndTemp;

        horario = hora + ":" + minuto + " " + ampm + " - " + horaEnd + ":" + minutoEnd + " " + ampmEnd;

        //Asigna datos a los componentes
        tvID.setText(Integer.toString(dia.getId()));
        tvNumeroDia.setText(Integer.toString(dia.getDia()));
        tvNombreDia.setText(diaDeLaSemana);
        tvHorario.setText(horario);

        return v;

    }

}
