package com.example.incidenciesfragment;

import java.io.Serializable;
import java.util.ArrayList;

public class Incidencia implements Serializable {
    protected String incidencia;
    protected String prioritat;
    protected String descripcion;
    protected String estat;
    protected String date;

    public Incidencia(String titol, String prioritat, String descripcion, String estat, String date) {
        this.incidencia = titol;
        this.prioritat = prioritat;
        this.descripcion = descripcion;
        this.estat = estat;
        this.date = date;
    }

    public String getIncidencia() {
        return incidencia;
    }
    public String getPrioritat() {
        return prioritat;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getEstat() {
        return estat;
    }
    public String getDate() {
        return date;
    }


    public void setIncidencia(String incidencia) {
        this.incidencia = incidencia;
    }
    public void setPrioritat(String prioritat) {
        this.prioritat = prioritat;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setEstat(String estat) {this.estat = estat;}
    public void setDate(String date) {
        this.date = date;
    }

}
