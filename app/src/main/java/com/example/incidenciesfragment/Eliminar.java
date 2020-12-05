package com.example.incidenciesfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaDBHelper;

public class Eliminar extends Fragment {
    public View eliminarVista;
    protected SharedPreferences login;
    protected SharedPreferences language;

    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;

    public void Eliminar() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        eliminarVista = inflater.inflate(R.layout.fragment_eliminar, container, false);

        login = getContext().getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        language = getContext().getSharedPreferences("Language", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = login.edit();
        final SharedPreferences.Editor langeditor = language.edit();

        dbHelper = new IncidenciaDBHelper(this.getContext());
        db = dbHelper.getWritableDatabase();

        Button dropincidencias = (Button) eliminarVista.findViewById(R.id.btnDropIncidencias);
        dropincidencias.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dbHelper.deleteAll(db);
                Toast.makeText(getContext(), "Se ha borrado el contenido de la base de datos correctamente.", Toast.LENGTH_SHORT).show();
            }
        });
        final Button droppreferences = eliminarVista.findViewById(R.id.preferences);
        droppreferences.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                editor.clear().commit();
                langeditor.clear().commit();
                Toast.makeText(getContext(), "Se ha borrado el usuario, la contraseña y la configuración.", Toast.LENGTH_SHORT).show();
            }
        });
        return eliminarVista;
    }
}