package com.example.manuel.doctorpaciente.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.manuel.doctorpaciente.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpcionesActivity extends AppCompatActivity {

    @Bind(R.id.btnDoctores)
    Button btnDoctores;

    @Bind(R.id.btnPacientes) Button btnPacientes;
    @Bind(R.id.btnDoctorPaciente) Button btnDoctorPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnDoctores)
    public void abrirDoctores(){
        Intent intent = new Intent(this,DoctoresActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnPacientes)
    public void abrirPacientes(){

        Intent intent = new Intent(this,PacientesActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btnDoctorPaciente)
    public void abrirDocPa(){

        Intent intent = new Intent(this,CitasActivity.class);
        startActivity(intent);
    }

}
