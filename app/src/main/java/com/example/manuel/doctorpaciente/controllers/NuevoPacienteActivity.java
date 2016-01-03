package com.example.manuel.doctorpaciente.controllers;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.manuel.doctorpaciente.R;
import com.example.manuel.doctorpaciente.models.Paciente;
import com.example.manuel.doctorpaciente.models.databases.PacienteDataSource;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NuevoPacienteActivity extends AppCompatActivity {

    public static int id_paciente = 0;

    @Bind(R.id.btnGuardar)
    Button btnGuardar;
    @Bind(R.id.etNombre)
    EditText etNombre;
    @Bind(R.id.etPaterno) EditText etPaterno;
    @Bind(R.id.etMaterno) EditText etMaterno;
    @Bind(R.id.etTelefono) EditText etTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_paciente);
        ButterKnife.bind(this);

        if (id_paciente > 0){
            PacienteDataSource pacienteDS = new PacienteDataSource(this);
            Cursor cursor = pacienteDS.obtenerPaciente(id_paciente);
            if (cursor.moveToFirst()){
                do {
                    etNombre.setText(cursor.getString(1));
                    etPaterno.setText(cursor.getString(2));
                    etMaterno.setText(cursor.getString(3));
                    etTelefono.setText(cursor.getString(4));
                } while (cursor.moveToNext());
            }
        }
    }//onCreate

    @OnClick(R.id.btnGuardar)
    public void guardarPaciente(){
        Paciente paciente = new Paciente();
        paciente.setNombre(etNombre.getText().toString());
        paciente.setPaterno(etPaterno.getText().toString());
        paciente.setMaterno(etMaterno.getText().toString());
        paciente.setTelefono(etTelefono.getText().toString());

        PacienteDataSource pacienteDS = new PacienteDataSource(this);
        if (id_paciente > 0) {
            pacienteDS.actualizarPaciente(paciente, id_paciente);
        } else {
            pacienteDS.insertarPacientes(paciente);
        }
        id_paciente = 0;
        finish();
    }
}
