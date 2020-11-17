package com.example.incidenciesfragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.incidenciesfragment.IncidenciaDBHelper.IncidenciaDBHelper;

import java.util.ArrayList;

public class llistar extends Fragment {
    RecyclerView recyclerView;
    ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
    IncidenciaDBHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_llistar);
        incidencias = db.getAllIncidencies(db.getReadableDatabase());

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

}