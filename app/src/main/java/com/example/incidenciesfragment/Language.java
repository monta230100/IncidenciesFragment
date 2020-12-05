package com.example.incidenciesfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Locale;

public class Language extends Fragment {
    public View afegirVista;
    SharedPreferences lang;
    SharedPreferences.Editor langeditor;
    String lange;

    public Language() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        afegirVista = inflater.inflate(R.layout.fragment_language, container, false);
        lang = getActivity().getSharedPreferences("Language", Context.MODE_PRIVATE);
        langeditor = lang.edit();
        lange = lang.getString("Language", "");

        final Button spanish = afegirVista.findViewById(R.id.esp);
        spanish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                changeLocale("es");
            }
        });
        final Button english = afegirVista.findViewById(R.id.eng);
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                changeLocale("en");
            }
        });
        final Button french = afegirVista.findViewById(R.id.fra);
        french.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                changeLocale("fr");
            }
        });
        final Button deutsch = afegirVista.findViewById(R.id.ger);
        deutsch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                changeLocale("de");
            }
        });
        return afegirVista;
    }
    public void changeLocale(String lang) {

        if (lang.equalsIgnoreCase(""))

            return;

        Locale myLocale = new Locale(lang);//Set Selected Locale

        savelangDetails(lang);//Save the selected locale

        Locale.setDefault(myLocale);//set new locale as default

        Configuration config = new Configuration();//get Configuration

        config.locale = myLocale;//set config locale as selected locale

        getActivity().getResources().updateConfiguration(config, getActivity().getResources().getDisplayMetrics());//Update the config

    }
    public void savelangDetails(String lang) {
        if (lange == null) {
            langeditor.putString("Language", lang).commit();
        }
        if (lange != null) {
            langeditor.clear().commit();
            langeditor.putString("Language", lang).commit();
        }
    }
}