package com.example.manuel.doctorpaciente.controllers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.manuel.doctorpaciente.R;
import com.example.manuel.doctorpaciente.models.DocPa;

import java.util.List;

/**
 * Created by manuel on 29/12/2015.
 */
public class DocPaAdapter extends RecyclerView.Adapter<DocPaAdapter.DocPaViewHolder> {

    public static int posicion;
    private List<DocPa> docPas;
    private Context context;

    public DocPaAdapter(Context context,List<DocPa> docPas){
        this.docPas = docPas;
        this.context = context;
    }

    @Override
    public DocPaAdapter.DocPaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.citas_detalle,viewGroup,false);
        DocPaViewHolder docPaViewHolder = new DocPaViewHolder(view);

        return docPaViewHolder;
    }

    @Override
    public void onBindViewHolder(DocPaAdapter.DocPaViewHolder docPaViewHolder, int i) {
        docPaViewHolder.enlazaDocPa(docPas.get(i));
    }

    @Override
    public int getItemCount() {
        return docPas.size();
    }

    public class DocPaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvIDDetalle;
        public TextView tvDoctorDetalle;
        public TextView tvPacienteDetalle;
        public TextView tvFechaDetalle;

        public DocPaViewHolder(View itemView) {
            super(itemView);

            tvIDDetalle = (TextView) itemView.findViewById(R.id.tvIDDetalle);
            tvDoctorDetalle = (TextView) itemView.findViewById(R.id.tvDoctorDetalle);
            tvPacienteDetalle = (TextView) itemView.findViewById(R.id.tvPacienteDetalle);
            tvFechaDetalle = (TextView) itemView.findViewById(R.id.tvFechaDetalle);

            itemView.setOnClickListener(this);

        }

        public void enlazaDocPa(DocPa docPa){
            tvIDDetalle.setText(String.valueOf(docPa.getId()));
            tvDoctorDetalle.setText(String.valueOf(docPa.getNombre_doctor()));
            tvPacienteDetalle.setText(String.valueOf(docPa.getNombre_paciente()));
            tvFechaDetalle.setText(String.valueOf(docPa.getCita()));
        }


        @Override
        public void onClick(View v) {
            posicion = getAdapterPosition();
            //Toast.makeText(context,String.valueOf(posicion),Toast.LENGTH_LONG).show();
        }
    }


}
