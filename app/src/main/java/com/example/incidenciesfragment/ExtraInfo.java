package com.example.incidenciesfragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
    Incidencia incidencia;
    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;
    TextView desc, dat, list;
    int contador, num = 1;
    String estatAnterior = "PENDIENTE", estatActual;

    int color;

    public ExtraInfo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View ExtraInfo = inflater.inflate(R.layout.fragment_extra_info, container, false);
        dbHelper = new IncidenciaDBHelper(this.getContext());
        db = dbHelper.getWritableDatabase();
        incidencia = dbHelper.getIncidencia(db, contador);

        Log.i("prova", "" + contador);

        desc = ExtraInfo.findViewById(R.id.Descripcion);
        dat = ExtraInfo.findViewById(R.id.Date);
        desc.setText(incidencia.getDescripcion());
        dat.setText(incidencia.getDate());

        final Button estat = ExtraInfo.findViewById(R.id.btnEstat);
        switch (incidencia.getEstat()){
            case "ASIGNADO":
                estat.setText(incidencia.getEstat());
                estat.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.asignado));
                break;
            case "PENDIENTE":
                estat.setText(incidencia.getEstat());
                estat.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.pendiente));
                break;
            case "REALIZADO":
                estat.setText(incidencia.getEstat());
                estat.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.resuelto));
        }
        estat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (incidencia.getEstat()){
                    case "ASIGNADO":
                        estatActual = "REALIZADO";
                        color = ContextCompat.getColor(getContext(), R.color.resuelto);
                        break;

                    case "PENDIENTE":
                        estatActual = "ASIGNADO";
                        color = ContextCompat.getColor(getContext(), R.color.asignado);
                        break;

                    case "REALIZADO":
                        estatActual = "PENDIENTE";
                        color = ContextCompat.getColor(getContext(), R.color.pendiente);
                        break;
                    default:

                }

                dbHelper.UpdateEstat(db,estatActual, incidencia.getId());
                estat.setText(estatActual);
                estat.setBackgroundColor(color);
                incidencia.setEstat(estatActual);
            }
        });
        return ExtraInfo;
    }

}