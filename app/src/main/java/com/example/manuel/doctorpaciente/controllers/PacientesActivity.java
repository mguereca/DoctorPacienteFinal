package com.example.manuel.doctorpaciente.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.manuel.doctorpaciente.R;
import com.example.manuel.doctorpaciente.controllers.adapters.PacienteAdapter;
import com.example.manuel.doctorpaciente.models.databases.PacienteDataSource;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PacientesActivity extends AppCompatActivity {


    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.ivNuevo)
    ImageView ivNuevo;
    @Bind(R.id.ivEliminar) ImageView ivEliminar;

    //Ultima Clase
    @Bind(R.id.ivEditar) ImageView ivEditar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);

        ButterKnife.bind(this);
        mostrarInformacion();

    }

    @Override
    protected void onResume() {
        super.onResume();
        PacienteDataSource pacienteDS = new PacienteDataSource(this);
        PacienteAdapter pacienteAdapter = new PacienteAdapter(this,pacienteDS.mostrarPacientes());
        recyclerView.setAdapter(pacienteAdapter);
    }

    public void mostrarInformacion(){
        PacienteDataSource pacienteDS = new PacienteDataSource(this);
        PacienteAdapter pacienteAdapter = new PacienteAdapter(this,pacienteDS.mostrarPacientes());
        recyclerView.setAdapter(pacienteAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
    }

    @OnClick(R.id.ivNuevo)
    public void nuevoAlumno(){
        Intent intent = new Intent(this,NuevoPacienteActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ivEliminar)
    public void eliminaPaciente(){
        PacienteDataSource pacienteDS = new PacienteDataSource(this);
        int id = pacienteDS.obtenerID(pacienteDS.mostrarPacientes(),PacienteAdapter.posicion);
        pacienteDS.eliminarPaciente(id);
        PacienteAdapter pacienteAdapter = new PacienteAdapter(this,pacienteDS.mostrarPacientes());
        recyclerView.setAdapter(pacienteAdapter);
        Toast.makeText(this, "Paciente Elimando correctamente", Toast.LENGTH_LONG).show();

    }

    //Ultima Clase

    @OnClick(R.id.ivEditar)
    public void modificarPaciente(){
        PacienteDataSource pacienteDS = new PacienteDataSource(this);
        int id = pacienteDS.obtenerID(pacienteDS.mostrarPacientes(),PacienteAdapter.posicion);
        NuevoPacienteActivity.id_paciente = id;
        Intent intent = new Intent(this,NuevoPacienteActivity.class);
        startActivity(intent);
    }

}
