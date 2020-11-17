package com.example.incidenciesfragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class fragment_menu extends Fragment {

    public fragment_menu() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View fMenu = inflater.inflate(R.layout.fragment_menu, container, false);

        Button afegir = fMenu.findViewById(R.id.btnAfegir);
        afegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                FragmentManager menuManager = getFragmentManager();
                FragmentTransaction menuTransaction = menuManager.beginTransaction();
                Fragment afegir = new afegir();

                menuTransaction.replace(R.id.fragmentLayout, afegir);

                menuTransaction.commit();
            }
        });

        Button llistar = fMenu.findViewById(R.id.btnLlistar);
        llistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                FragmentManager menuManager = getFragmentManager();
                FragmentTransaction menuTransaction = menuManager.beginTransaction();
                Fragment llistar = new llistar();

                menuTransaction.replace(R.id.fragmentLayout, llistar);

                menuTransaction.commit();
            }
        });

        Button eliminar = fMenu.findViewById(R.id.btnEliminar);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                FragmentManager menuManager = getFragmentManager();
                FragmentTransaction menuTransaction = menuManager.beginTransaction();
                Fragment eliminar = new eliminar();

                menuTransaction.replace(R.id.fragmentLayout, eliminar);

                menuTransaction.commit();
            }
        });


        return fMenu;
    }
}