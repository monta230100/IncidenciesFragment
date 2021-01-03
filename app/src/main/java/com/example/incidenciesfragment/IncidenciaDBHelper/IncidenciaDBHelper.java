package com.example.incidenciesfragment.IncidenciaDBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.incidenciesfragment.Incidencia;
import com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaContract.*;

import java.util.ArrayList;
import java.util.Date;

import static com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaContract.IncidenciaEntry.COLUMN_DATE;
import static com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaContract.IncidenciaEntry.COLUMN_DESCRIPCION;
import static com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaContract.IncidenciaEntry.COLUMN_ESTAT;
import static com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaContract.IncidenciaEntry.COLUMN_NAME_TITLE;
import static com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaContract.IncidenciaEntry.COLUMN_PRIORITAT;
import static com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaContract.IncidenciaEntry.TABLE_NAME;

public class IncidenciaDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "incidencies.db";
    ContentValues values = new ContentValues();

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + IncidenciaEntry.TABLE_NAME + "(" + IncidenciaEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            IncidenciaEntry.COLUMN_NAME_TITLE + " TEXT," +
            IncidenciaEntry.COLUMN_PRIORITAT + " TEXT," +
            IncidenciaEntry.COLUMN_DESCRIPCION + " TEXT," +
            IncidenciaEntry.COLUMN_ESTAT + " TEXT," +
            IncidenciaEntry.COLUMN_DATE + " TEXT);";

    public IncidenciaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertIncidencia(SQLiteDatabase db, Incidencia i){
        //Check the db is open
        if (db.isOpen()){
//Creation of the register for insert object with the content values

            //Insert the incidence getting all values
            values.put(IncidenciaEntry.COLUMN_NAME_TITLE, i.getIncidencia());
            values.put(IncidenciaEntry.COLUMN_PRIORITAT, i.getPrioritat());
            values.put(IncidenciaEntry.COLUMN_DESCRIPCION, i.getDescripcion());
            values.put(IncidenciaEntry.COLUMN_ESTAT, i.getEstat());
            values.put(IncidenciaEntry.COLUMN_DATE, i.getDate());

            db.insert(IncidenciaEntry.TABLE_NAME, null, values);
        }else{
            Log.d("sql","Database is closed");
        }
    }
    public ArrayList<Incidencia> getAllIncidencies(SQLiteDatabase db) {
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        Cursor res;
        res = db.rawQuery("select * from "+TABLE_NAME,null);

        while(res.moveToNext()) {
            String titol = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_NAME_TITLE));
            String prioritat = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_PRIORITAT));
            String descripcion = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_DESCRIPCION));
            String estat = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_ESTAT));
            String date = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_DATE));

            int id = res.getInt(res.getColumnIndex(IncidenciaEntry.ID));

            Incidencia incidencia = new Incidencia(titol, prioritat, descripcion, estat, date);
            incidencia.setId(id);
            incidencias.add(incidencia);
        }
        res.close();
        return incidencias;
    }
    public ArrayList<Incidencia> getSelectedIncidencies(SQLiteDatabase db, String nivell) {
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        Cursor res;
        res = db.rawQuery("select * from "+TABLE_NAME+" WHERE "+COLUMN_PRIORITAT+" = "+nivell,null);

        while(res.moveToNext()) {
            String titol = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_NAME_TITLE));
            String prioritat = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_PRIORITAT));
            String descripcion = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_DESCRIPCION));
            String estat = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_ESTAT));
            String date = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_DATE));

            int id = res.getInt(res.getColumnIndex(IncidenciaEntry.ID));

            Incidencia incidencia = new Incidencia(titol, prioritat, descripcion, estat, date);
            incidencia.setId(id);
            incidencias.add(incidencia);
        }
        res.close();
        return incidencias;
    }
    public void deleteAll(SQLiteDatabase db){
        db.execSQL("DELETE FROM "+TABLE_NAME);
    }
    public void deleteOne(SQLiteDatabase db, int id){ db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE ID="+id);}

    public void dropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }
    public void UpdateEstat(SQLiteDatabase db,String estat,int row_id) {
        if (db.isOpen()){
            values.put(COLUMN_ESTAT, estat);
            String sql = "UPDATE " + IncidenciaEntry.TABLE_NAME + " SET estat='" + estat + "' WHERE id=" + row_id;
            db.execSQL(sql);
            Log.i("prova", sql);
           // db.update(TABLE_NAME, values, "id=?", new String[]{row_id});
        } else {
            Log.d("sql","Database is closed");
        }
    }

    public Incidencia getIncidencia(SQLiteDatabase db, int contador){
        Cursor res = db.rawQuery("SELECT * FROM " + IncidenciaEntry.TABLE_NAME + " WHERE id=" + contador, null);

        Incidencia incidencia = null;

        //Iteration on the cursor results and fill the array
        while (res.moveToNext()) {
            String titol = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_NAME_TITLE));
            String prioritat = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_PRIORITAT));
            String descripcion = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_DESCRIPCION));
            String estat = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_ESTAT));
            String date = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_DATE));

            int id = res.getInt(res.getColumnIndex(IncidenciaEntry.ID));

            incidencia = new Incidencia(titol, prioritat, descripcion, estat, date);
            incidencia.setId(id);
        }

        res.close();

        return incidencia;

    }
}
