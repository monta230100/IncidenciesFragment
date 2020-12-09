package com.example.incidenciesfragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaDBHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ExtraInfo extends Fragment {
    ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;
    TextView desc, dat, list;
    int contador, num = 1;
    String estatAnterior = "PENDIENTE", estatActual;



    public ExtraInfo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View ExtraInfo = inflater.inflate(R.layout.fragment_extra_info, container, false);
        dbHelper = new IncidenciaDBHelper(this.getContext());
        db = dbHelper.getWritableDatabase();
        incidencias = dbHelper.getAllIncidencies(db);

        desc = ExtraInfo.findViewById(R.id.Descripcion);
        dat = ExtraInfo.findViewById(R.id.Date);
        desc.setText(incidencias.get(contador).getDescripcion());
        dat.setText(incidencias.get(contador).getDate());
        final String count = Integer.toString(contador);



        final Button estat = ExtraInfo.findViewById(R.id.btnEstat);
        estat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num = num + 1;
                if (num<1 || num>3){
                    num = 1;
                }
                if (num == 1){
                    estatAnterior = "ASIGNADO";
                    estatActual = "PENDIENTE";
                    dbHelper.UpdateEstat(db,"PENDIENTE", count);
                    estat.setText("PENDIENTE");
                    estat.setBackgroundColor(Color.RED);
                    incidencias.get(contador).setIncidencia(estatActual);

                }
                if (num == 2){
                    estatAnterior = "PENDIENTE";
                    estatActual = "ASIGNADO";
                    dbHelper.UpdateEstat(db, "ASIGNADO", count);
                    estat.setText("ASIGNADO");
                    estat.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.Orange));
                    incidencias.get(contador).setIncidencia(estatActual);
                }
                if (num == 3){
                    estatAnterior = "ASIGNADO";
                    estatActual = "REALIZADO";
                    dbHelper.UpdateEstat(db,"REALIZADO", count);
                    estat.setText("REALIZADO");
                    estat.setBackgroundColor(Color.GREEN);
                    incidencias.get(contador).setIncidencia(estatActual);
                }
            }
        });
        return ExtraInfo;
    }

}