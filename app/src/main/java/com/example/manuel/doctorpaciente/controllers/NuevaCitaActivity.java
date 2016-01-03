package com.example.manuel.doctorpaciente.controllers;

import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.manuel.doctorpaciente.R;
import com.example.manuel.doctorpaciente.models.DocPa;
import com.example.manuel.doctorpaciente.models.Doctor;
import com.example.manuel.doctorpaciente.models.Paciente;
import com.example.manuel.doctorpaciente.models.databases.Doc_Pa_DataSource;
import com.example.manuel.doctorpaciente.models.databases.DoctorDataSource;
import com.example.manuel.doctorpaciente.models.databases.PacienteDataSource;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class NuevaCitaActivity extends AppCompatActivity {

    public static int id_cita = 0;
    public static int id_doc;
    public static int id_pa;
    public static String fecha;
    public static List<Paciente> pacientes;
    public static List<Doctor> doctores;

    @Bind(R.id.spDoctores)
    Spinner spDoctores;

    @Bind(R.id.spPacientes)
    Spinner spPacientes;

    @Bind(R.id.txtDate) EditText txtDate;
    @Bind(R.id.ivCalendar) ImageView ivCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_cita);
        ButterKnife.bind(this);



        if (id_cita > 0){

            Doc_Pa_DataSource doc_pa_DS = new Doc_Pa_DataSource(this);
            Cursor cursor = doc_pa_DS.obtenerCita(id_cita);
            if(cursor.moveToFirst()){
                do {
                    id_pa = cursor.getInt(1);
                    id_doc = cursor.getInt(2);
                    fecha = cursor.getString(3);
                }    while (cursor.moveToNext());
            }
            txtDate.setText(fecha);
            leerSpinnerDoctores(id_doc);
            leerSpinnerPacientes(id_pa);
            mostrarCalendario();



        }else {
            leerSpinnerDoctores(0);
            leerSpinnerPacientes(0);
            mostrarCalendario();
        }


    }

    @OnItemSelected(R.id.spDoctores)
    void onItemSelected(int position){
        id_doc = doctores.get(position).getId();

    }

    @OnItemSelected(R.id.spPacientes)
    void onItemSelected2(int position){
        id_pa = pacientes.get(position).getId();
    }



    @OnClick(R.id.btnGuardar)
    public void guardarCita(){

        DocPa docPa = new DocPa();
        docPa.setId_doctor(obtenerSpID(true));
        docPa.setId_paciente(obtenerSpID(false));
        docPa.setCita(txtDate.getText().toString());

        Doc_Pa_DataSource docPaDS = new Doc_Pa_DataSource(this);
        if(id_cita > 0){
            docPaDS.actualizarCita(docPa,id_cita);
        }else {
            docPaDS.insertarCitas(docPa);
        }

        id_cita = 0;
        finish();

    }

    public int obtenerSpID(boolean band){
        if(band){
            return id_doc;
        }else{
            return id_pa;
        }

    }


    public void mostrarCalendario(){

       txtDate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               DateDialog dialog = new DateDialog(view);
               FragmentTransaction ft = getFragmentManager().beginTransaction();
               dialog.show(ft, "DatePicker");
           }
       });

    }



    private void leerSpinnerPacientes(int p) {

        int pos;
        PacienteDataSource pacienteDataSource = new PacienteDataSource(this);
         pacientes = pacienteDataSource.spinnerPacientes();

        if(p > 0)
            pos = buscarPosicion(doctores,p);

        else
            pos = 0;

        ArrayAdapter doctorArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,pacientes);
        doctorArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPacientes.setAdapter(doctorArrayAdapter);
        spPacientes.setSelection(pos);
    }

    private void leerSpinnerDoctores(int p) {
        int pos;
        DoctorDataSource doctorDataSource = new DoctorDataSource(this);
         doctores = doctorDataSource.spinnerDoctores();
        if(p > 0)
            pos = buscarPosicion(doctores,p);

        else
            pos = 0;

        ArrayAdapter doctorArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,doctores);
        doctorArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDoctores.setAdapter(doctorArrayAdapter);
        spDoctores.setSelection(pos);
    }

    private int buscarPosicion(List<Doctor> doctorList, int id){
        int i;
        for( i = 0;i < doctorList.size();i++){
            if(doctorList.get(i).getId() == id){
                //return i;
                break;
            }
        }
        return i;
    }

}
