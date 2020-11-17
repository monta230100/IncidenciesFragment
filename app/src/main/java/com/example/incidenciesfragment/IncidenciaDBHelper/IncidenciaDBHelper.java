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

import static com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaContract.IncidenciaEntry.COLUMN_NAME_TITLE;
import static com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaContract.IncidenciaEntry.TABLE_NAME;

public class IncidenciaDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "incidencies.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + "(" + IncidenciaEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_NAME_TITLE + " TEXT)";

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
        //Check the bd is open
        if (db.isOpen()){
//Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();

            //Insert the incidence getting all values
            values.put(COLUMN_NAME_TITLE, i.getIncidencia());

            db.insert(TABLE_NAME, null, values);
        }else{
            Log.d("sql","Database is closed");
        }
    }
    public ArrayList<Incidencia> getAllIncidencies(SQLiteDatabase db) {
        ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);

        while(res.moveToNext()) {
            String titol = res.getString(res.getColumnIndex(IncidenciaEntry.COLUMN_NAME_TITLE));
            String prioritat = res.getString(res.getColumnIndex(IncidenciaEntry.TABLE_NAME));
            Incidencia incidencia = new Incidencia(titol, prioritat);
            incidencias.add(incidencia);
        }
        res.close();

        return incidencias;
    }


}
