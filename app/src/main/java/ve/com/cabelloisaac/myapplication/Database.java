package ve.com.cabelloisaac.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * En esta clase se especifican todas las constantes que se usarán en la base de datos
 * En las clases donde se haga uso de la base de datos del sistema, se hará uso de estas constantes
 * para mantener la consistencia en las palabras clave y evitar errores de consulta.
 */

public class Database extends SQLiteOpenHelper {

    public static final String ID = "id";
    public static final String DIA = "dia";
    public static final String HORA = "hora";
    public static final String MINUTO = "minuto";
    public static final String HORA_END = "hora_end";
    public static final String MINUTO_END = "minuto_end";

    public static final String DB_NAME = "db_transformadores";
    public static final int DB_VERSION = 1;

    //TABLES
    public static final String SEMANA = "semana";

    /*SENTENCIAS DE CREACIÓN DE TABLAS*/

    String TABLE_ALBUMS = "CREATE TABLE '" + SEMANA + "' ( " +
            " '" + ID + "' INTEGER PRIMARY KEY AUTOINCREMENT, " +
            " '" + DIA + "' INTEGER, " +
            " '" + HORA + "' TEXT, " +
            " '" + MINUTO + "' TEXT, " +
            " '" + HORA_END + "' TEXT, " +
            " '" + MINUTO_END + "' TEXT" +
            ");";

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Se ejecuta al ser instanciada por primera vez en la aplicación y luego no se ejecuta más.
     * Aquí se crean las tablas y entradas que deben de estar presentes al inicio de la aplicación.
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_ALBUMS);

        ContentValues contentValues = new ContentValues();
        contentValues.put(DIA, 1);
        contentValues.put(HORA, 0);
        contentValues.put(MINUTO, 0);
        contentValues.put(HORA_END, 0);
        contentValues.put(MINUTO_END, 0);
        db.insert(SEMANA, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put(DIA, 2);
        contentValues.put(HORA, 0);
        contentValues.put(MINUTO, 0);
        contentValues.put(HORA_END, 0);
        contentValues.put(MINUTO_END, 0);
        db.insert(SEMANA, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put(DIA, 3);
        contentValues.put(HORA, 0);
        contentValues.put(MINUTO, 0);
        contentValues.put(HORA_END, 0);
        contentValues.put(MINUTO_END, 0);
        db.insert(SEMANA, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put(DIA, 4);
        contentValues.put(HORA, 0);
        contentValues.put(MINUTO, 0);
        contentValues.put(HORA_END, 0);
        contentValues.put(MINUTO_END, 0);
        db.insert(SEMANA, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put(DIA, 5);
        contentValues.put(HORA, 0);
        contentValues.put(MINUTO, 0);
        contentValues.put(HORA_END, 0);
        contentValues.put(MINUTO_END, 0);
        db.insert(SEMANA, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put(DIA, 6);
        contentValues.put(HORA, 0);
        contentValues.put(MINUTO, 0);
        contentValues.put(HORA_END, 0);
        contentValues.put(MINUTO_END, 0);
        db.insert(SEMANA, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put(DIA, 7);
        contentValues.put(HORA, 0);
        contentValues.put(MINUTO, 0);
        contentValues.put(HORA_END, 0);
        contentValues.put(MINUTO_END, 0);
        db.insert(SEMANA, null, contentValues);

    }

    /**
     * Se llama para actualizar la base de datos sin tener que crearla de nuevo
     *
     * @param sqLiteDatabase la base de datos a actuqlizar
     * @param i              la version actual de la base de datos
     * @param i1             la nueva version de la base de datos
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

}
