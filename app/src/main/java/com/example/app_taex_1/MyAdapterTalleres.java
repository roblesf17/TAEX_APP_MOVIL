package com.example.app_taex_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterTalleres extends RecyclerView.Adapter<MyAdapterTalleres.TalleresViewHolder>{
    Context context;
    ArrayList<InscripcionTaller> tallerinscritoarray;

    public MyAdapterTalleres(Context context, ArrayList<InscripcionTaller> tallerinscritoarray) {
        this.context = context;
        this.tallerinscritoarray = tallerinscritoarray;
    }

    @NonNull
    @Override
    public TalleresViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyAdapterTalleres.TalleresViewHolder(LayoutInflater.from(context).inflate(R.layout.rowtalleres, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TalleresViewHolder holder, int position) {
        holder.textViewtaller.setText(tallerinscritoarray.get(position).getNombre_taller());
        holder.textViewestado.setText(tallerinscritoarray.get(position).getEstado_inscripcion());
        holder.textViewnota.setText(tallerinscritoarray.get(position).getNota_inscripcion());
    }

    @Override
    public int getItemCount() {
        return tallerinscritoarray.size();
    }

    public class TalleresViewHolder extends RecyclerView.ViewHolder {

        TextView textViewtaller, textViewnota, textViewestado;
        itemclicklistener itemclicklistener1;
        public TalleresViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewtaller =(TextView)itemView.findViewById(R.id.texto_dato_taller);
            textViewestado=(TextView)itemView.findViewById(R.id.texto_dato_estado);
            textViewnota=(TextView)itemView.findViewById(R.id.texto_dato_nota);
            //itemView.setOnClickListener(this);
        }
    }
}
