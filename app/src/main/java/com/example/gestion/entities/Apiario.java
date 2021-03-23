package com.example.gestion.entities;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Apiario {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String establecimiento;

    private String direccion;

    private String numero_de_colmenas;

    private String trabajo;

    private String fecha_de_la_visita;

    private String proxima_visita;

    private String gps;

    private String observaciones;

    public Apiario(int id,
                   String establecimiento,
                   String direccion,
                   String numero_de_colmenas,
                   String trabajo,
                   String fecha_de_la_visita,
                   String proxima_visita,
                   String gps,
                   String observaciones) {
        this.id = id;
        this.establecimiento = establecimiento;
        this.direccion = direccion;
        this.numero_de_colmenas = numero_de_colmenas;
        this.trabajo = trabajo;
        this.fecha_de_la_visita = fecha_de_la_visita;
        this.proxima_visita = proxima_visita;
        this.gps = gps;
        this.observaciones = observaciones;
    }

    public Apiario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero_de_colmenas() {
        return numero_de_colmenas;
    }

    public void setNumero_de_colmenas(String numero_de_colmenas) {
        this.numero_de_colmenas = numero_de_colmenas;
    }

    public String getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(String trabajo) {
        this.trabajo = trabajo;
    }

    public String getFecha_de_la_visita() {
        return fecha_de_la_visita;
    }

    public void setFecha_de_la_visita(String fecha_de_la_visita) {
        this.fecha_de_la_visita = fecha_de_la_visita;
    }

    public String getProxima_visita() {
        return proxima_visita;
    }

    public void setProxima_visita(String proxima_visita) {
        this.proxima_visita = proxima_visita;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    //
    public void observe(View.OnClickListener onClickListener, Observer<String> stringObserver) {
    }
    //
}
