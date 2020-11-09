package com.example.incidenciesfragment;

import java.io.Serializable;
import java.util.ArrayList;

public class Incidencia implements Serializable {
    private String incidencia;
    private String prioritat;


    public String getIncidencia() {
        return incidencia;
    }

    public String getPrioritat() {
        return prioritat;
    }

    public void setIncidencia(String incidencia) {
        this.incidencia = incidencia;
    }

    public void setPrioritat(String prioritat) {
        this.prioritat = prioritat;
    }
}
