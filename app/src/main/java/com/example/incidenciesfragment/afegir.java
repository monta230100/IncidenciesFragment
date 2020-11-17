package com.example.incidenciesfragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaDBHelper;

public class afegir extends Fragment  {
    Spinner spinner;
    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;

    public View afegirVista;

    public void afegirIncidencia() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        afegirVista= inflater.inflate(R.layout.fragment_afegir_incidencia, container, false);

        dbHelper = new IncidenciaDBHelper(this.getContext());
        db = dbHelper.getWritableDatabase();

        spinner = afegirVista.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.Urgencia, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this.getContext());

        final Button guardar = afegirVista.findViewById(R.id.guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                EditText nom = afegirVista.findViewById(R.id.titol);
                String nombre = nom.getText().toString();
                String nivell = spinner.getSelectedItem().toString();
                Incidencia incidencia = new Incidencia(nombre, nivell);
                dbHelper.insertIncidencia(db, incidencia);
            }
        });

        return afegirVista;
    }


}