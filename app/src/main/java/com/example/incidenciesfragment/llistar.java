package com.example.incidenciesfragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaDBHelper;

import java.util.ArrayList;

public class llistar extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
    private IncidenciaDBHelper dbHelper;
    private SQLiteDatabase db;

    public void llistar() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View llistar = inflater.inflate(R.layout.fragment_llistar, container, false);


        dbHelper = new IncidenciaDBHelper(this.getContext());
        db = dbHelper.getWritableDatabase();
        incidencias = dbHelper.getAllIncidencies(db);
        RecyclerView recyclerView = llistar.findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this.getContext(), incidencias);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this.getContext())));

        return llistar;
    }



}