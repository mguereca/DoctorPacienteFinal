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
import com.example.manuel.doctorpaciente.controllers.adapters.DocPaAdapter;
import com.example.manuel.doctorpaciente.models.databases.Doc_Pa_DataSource;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CitasActivity extends AppCompatActivity {

    @Bind(R.id.recyclerview)
    RecyclerView recyclerView;
    @Bind(R.id.ivNuevo)
    ImageView ivNuevo;
    @Bind(R.id.ivEditar) ImageView ivEditar;
    @Bind(R.id.ivEliminar) ImageView ivEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);
        ButterKnife.bind(this);
        mostrarInformacion();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Doc_Pa_DataSource doc_pa_dataSource = new Doc_Pa_DataSource(this);
        DocPaAdapter docPaAdapter = new DocPaAdapter(this,doc_pa_dataSource.mostrarCitas2());
        recyclerView.setAdapter(docPaAdapter);
    }

    private void mostrarInformacion() {
        Doc_Pa_DataSource doc_pa_dataSource = new Doc_Pa_DataSource(this);
        DocPaAdapter docPaAdapter = new DocPaAdapter(this,doc_pa_dataSource.mostrarCitas2());
        recyclerView.setAdapter(docPaAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

    }


    @OnClick(R.id.ivNuevo)
    public void nuevoDoctor(){
        Intent intent = new Intent(this,NuevaCitaActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.ivEliminar)
    public void eliminaCita(){
        Doc_Pa_DataSource docPaDS = new Doc_Pa_DataSource(this);
        int id = docPaDS.obtenerID(docPaDS.mostrarCitas2(),DocPaAdapter.posicion);
        docPaDS.eliminarCita(id);
        DocPaAdapter docPaAdapter = new DocPaAdapter(this,docPaDS.mostrarCitas2());
        recyclerView.setAdapter(docPaAdapter);
        Toast.makeText(this, "Cita Elimanda correctamente", Toast.LENGTH_LONG).show();

    }


    @OnClick(R.id.ivEditar)
    public void modificarCita(){
        Doc_Pa_DataSource docPaDS = new Doc_Pa_DataSource(this);
        int id = docPaDS.obtenerID(docPaDS.mostrarCitas2(),DocPaAdapter.posicion);
        NuevaCitaActivity.id_cita = id;
        Intent intent = new Intent(this,NuevaCitaActivity.class);
        startActivity(intent);
    }

}
