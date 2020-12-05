package com.example.incidenciesfragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaDBHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class afegir extends Fragment  {
    protected Spinner spinner;
    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;

    public View afegirVista;

    public void afegir() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        afegirVista = inflater.inflate(R.layout.fragment_afegir, container, false);

        dbHelper = new IncidenciaDBHelper(this.getContext());
        db = dbHelper.getWritableDatabase();
        // Canvi estructura db
        dbHelper.dropTable(db);
        dbHelper.onCreate(db);

        spinner = afegirVista.findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, getActivity().getResources().getStringArray(R.array.Urgencia));
        spinner.setAdapter(adapter);


        final Button guardar = afegirVista.findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                EditText nom = afegirVista.findViewById(R.id.titol);
                String nombre = nom.getText().toString();
                String nivell = spinner.getSelectedItem().toString();
                EditText desc = afegirVista.findViewById(R.id.description);
                String descripcion = desc.getText().toString();
                String estat = "PENDIENTE";
                SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date now = new Date();
                String date = sdfDate.format(now);

                Incidencia incidencia = new Incidencia(nombre, nivell, descripcion, estat, date);

                dbHelper.insertIncidencia(db, incidencia);

            }
        });

        return afegirVista;
    }


}