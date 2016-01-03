package com.example.manuel.doctorpaciente.models;

/**
 * Created by manuel on 16/12/2015.
 */
public class Doctor {

    private int id;
    private String nombre;
    private String paterno;
    private String materno;
    private String especialidad;
    private String consultorio; //Por si un usuario desea porner una letra en vez de número y viceverza
    private String nombre_completo;


    @Override
    public String toString(){
        return  nombre_completo;
    }


    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(String consultorio) {
        this.consultorio = consultorio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
