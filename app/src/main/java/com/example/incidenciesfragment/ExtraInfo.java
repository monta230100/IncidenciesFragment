package com.example.incidenciesfragment;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

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
    TextView desc, dat;
    int contador, num = 1;
    Date curDate;
    String estatAnterior = "PENDIENTE";


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
                    dbHelper.UpdateEstat(db,"PENDIENTE", count);
                    estat.setText("PENDIENTE");
                    estat.setBackgroundColor(Color.RED);
                }
                if (num == 2){
                    estatAnterior = "PENDIENTE";
                    dbHelper.UpdateEstat(db, "ASIGNADO", count);
                    estat.setText("ASIGNADO");
                    estat.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.Orange));
                }
                if (num == 3){
                    estatAnterior = "ASIGNADO";
                    dbHelper.UpdateEstat(db,"REALIZADO", count);
                    estat.setText("REALIZADO");
                    estat.setBackgroundColor(Color.GREEN);
                }
            }
        });
        return ExtraInfo;
    }
}