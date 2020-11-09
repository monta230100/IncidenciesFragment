package com.example.incidenciesfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;

public class GeneralActivity extends AppCompatActivity {
    ArrayList<Incidencia> incidencies = new ArrayList<Incidencia>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general);
    }
}