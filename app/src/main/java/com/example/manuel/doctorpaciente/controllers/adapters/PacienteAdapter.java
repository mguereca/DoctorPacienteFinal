package com.example.manuel.doctorpaciente.controllers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.manuel.doctorpaciente.R;
import com.example.manuel.doctorpaciente.models.Paciente;

import java.util.List;

/**
 * Created by manuel on 26/12/2015.
 */
public class PacienteAdapter extends RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder> {

    public static int posicion;
    private List<Paciente> pacientes;
    private Context context;

    public PacienteAdapter(Context context,List<Paciente> pacientes){
            this.pacientes = pacientes;
            this.context = context;
            }

    @Override
    public PacienteAdapter.PacienteViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.paciente_detalle,viewGroup,false);
            PacienteViewHolder pacienteViewHolder = new PacienteViewHolder(view);

            return pacienteViewHolder;
            }

    @Override
    public void onBindViewHolder(PacienteAdapter.PacienteViewHolder pacienteViewHolder, int i) {
            pacienteViewHolder.enlazaPaciente(pacientes.get(i));
            }

    @Override
    public int getItemCount() {
            return pacientes.size();
            }

    public class PacienteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvIDDetalle;
        public TextView tvNombre;
        public TextView tvTelefono;

        public PacienteViewHolder(View itemView) {
            super(itemView);

            tvIDDetalle = (TextView) itemView.findViewById(R.id.tvIDDetalle);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombreDetalle);
            tvTelefono = (TextView) itemView.findViewById(R.id.tvTelefonoDetalle);

            itemView.setOnClickListener(this);

        }

        public void enlazaPaciente(Paciente paciente){
            tvIDDetalle.setText(String.valueOf(paciente.getId()));
            tvNombre.setText(paciente.getNombre() + " " + paciente.getPaterno() + " " + paciente.getMaterno());
            tvTelefono.setText(paciente.getTelefono());
        }

        @Override
        public void onClick(View v) {
            posicion = getAdapterPosition();
            //Toast.makeText(context,String.valueOf(posicion),Toast.LENGTH_LONG).show();
        }
    }
}
