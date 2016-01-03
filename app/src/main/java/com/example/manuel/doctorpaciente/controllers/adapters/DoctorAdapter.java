package com.example.manuel.doctorpaciente.controllers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manuel.doctorpaciente.R;
import com.example.manuel.doctorpaciente.models.Doctor;

import java.util.List;

/**
 * Created by manuel on 25/12/2015.
 */
public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    public static int posicion;
    private List<Doctor> doctores;
    private Context context;

    public DoctorAdapter(Context context,List<Doctor> doctores){
        this.doctores = doctores;
        this.context = context;
    }

    @Override
    public DoctorAdapter.DoctorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.doctor_detalle,viewGroup,false);
        DoctorViewHolder doctorViewHolder = new DoctorViewHolder(view);

        return doctorViewHolder;
    }

    @Override
    public void onBindViewHolder(DoctorAdapter.DoctorViewHolder doctorViewHolder, int i) {
        doctorViewHolder.enlazaDoctor(doctores.get(i));
    }

    @Override
    public int getItemCount() {
        return doctores.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvIDDetalle;
        public TextView tvNombre;
        public TextView tvEspecialidad;

        public DoctorViewHolder(View itemView) {
            super(itemView);

            tvIDDetalle = (TextView) itemView.findViewById(R.id.tvIDDetalle);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombreDetalle);
            tvEspecialidad = (TextView) itemView.findViewById(R.id.tvEspecialidadDetalle);

            itemView.setOnClickListener(this);

        }

        public void enlazaDoctor(Doctor doctor){
            tvIDDetalle.setText(String.valueOf(doctor.getId()));
            tvNombre.setText(doctor.getNombre() + " " + doctor.getPaterno() + " " + doctor.getMaterno());
            tvEspecialidad.setText(doctor.getEspecialidad());
        }

        @Override
        public void onClick(View v) {
            posicion = getAdapterPosition();
            //Toast.makeText(context,String.valueOf(posicion),Toast.LENGTH_LONG).show();
        }
    }
}
