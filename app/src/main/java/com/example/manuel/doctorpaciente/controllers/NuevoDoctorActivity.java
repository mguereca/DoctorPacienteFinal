package com.example.manuel.doctorpaciente.controllers;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.manuel.doctorpaciente.R;
import com.example.manuel.doctorpaciente.models.Doctor;
import com.example.manuel.doctorpaciente.models.databases.DoctorDataSource;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NuevoDoctorActivity extends AppCompatActivity {

    public static int id_doctor = 0;

    @Bind(R.id.btnGuardar)
    Button btnGuardar;
    @Bind(R.id.etNombre)
    EditText etNombre;
    @Bind(R.id.etPaterno) EditText etPaterno;
    @Bind(R.id.etMaterno) EditText etMaterno;
    @Bind(R.id.etConsultorio) EditText etConsultorio;
    @Bind(R.id.etEspecialidad) EditText etEspecialidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_doctor);
        ButterKnife.bind(this);

        if (id_doctor > 0){
            DoctorDataSource doctorDS = new DoctorDataSource(this);
            Cursor cursor = doctorDS.obtenerDoctor(id_doctor);
            if (cursor.moveToFirst()){
                do {
                    etNombre.setText(cursor.getString(1));
                    etPaterno.setText(cursor.getString(2));
                    etMaterno.setText(cursor.getString(3));
                    etEspecialidad.setText(cursor.getString(4));
                    etConsultorio.setText(cursor.getString(5));
                } while (cursor.moveToNext());
            }
        }
    }

    @OnClick(R.id.btnGuardar)
    public void guardarDoctor(){
        Doctor doctor = new Doctor();
        doctor.setNombre(etNombre.getText().toString());
        doctor.setPaterno(etPaterno.getText().toString());
        doctor.setMaterno(etMaterno.getText().toString());
        doctor.setEspecialidad(etEspecialidad.getText().toString());
        doctor.setConsultorio(etConsultorio.getText().toString());

        DoctorDataSource doctorDS = new DoctorDataSource(this);
        if (id_doctor > 0) {
            doctorDS.actualizarDoctor(doctor,id_doctor);
        } else {
            doctorDS.insertarDoctor(doctor);
        }
        id_doctor = 0;
        finish();
    }

}
