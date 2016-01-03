package com.example.manuel.doctorpaciente.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.manuel.doctorpaciente.R;
import com.example.manuel.doctorpaciente.controllers.adapters.DoctorAdapter;
import com.example.manuel.doctorpaciente.models.databases.DoctorDataSource;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoctoresActivity extends AppCompatActivity {

    @Bind(R.id.recyclerview) RecyclerView recyclerView;
    @Bind(R.id.ivNuevo) ImageView ivNuevo;
    @Bind(R.id.ivEditar) ImageView ivEditar;
    @Bind(R.id.ivEliminar) ImageView ivEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctores);
        ButterKnife.bind(this);
        mostrarInformacion();

    }

    @Override
    protected void onResume() {
        super.onResume();
        DoctorDataSource doctorDS = new DoctorDataSource(this);
        DoctorAdapter doctorAdapter = new DoctorAdapter(this,doctorDS.mostrarDoctores());
        recyclerView.setAdapter(doctorAdapter);
    }

    private void mostrarInformacion() {
        DoctorDataSource doctorDS = new DoctorDataSource(this);
        DoctorAdapter doctorAdapter = new DoctorAdapter(this,doctorDS.mostrarDoctores());
        recyclerView.setAdapter(doctorAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);
    }


    @OnClick(R.id.ivNuevo)
    public void nuevoDoctor(){
        Intent intent = new Intent(this,NuevoDoctorActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ivEliminar)
    public void eliminaDoctor(){
        DoctorDataSource doctorDS = new DoctorDataSource(this);
        int id = doctorDS.obtenerID(doctorDS.mostrarDoctores(),DoctorAdapter.posicion);
        doctorDS.eliminarDoctor(id);
        DoctorAdapter doctorAdapter = new DoctorAdapter(this,doctorDS.mostrarDoctores());
        recyclerView.setAdapter(doctorAdapter);
        Toast.makeText(this, "Doctor Elimando correctamente", Toast.LENGTH_LONG).show();

    }


    @OnClick(R.id.ivEditar)
    public void modificarDoctor(){
        DoctorDataSource doctorDS = new DoctorDataSource(this);
        int id = doctorDS.obtenerID(doctorDS.mostrarDoctores(),DoctorAdapter.posicion);
        NuevoDoctorActivity.id_doctor = id;
        Intent intent = new Intent(this,NuevoDoctorActivity.class);
        startActivity(intent);
    }

}
