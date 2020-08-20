package com.example.app_taex_1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterPersonal extends RecyclerView.Adapter<MyAdapterPersonal.MyViewHolder> implements Filterable {


    Context context;
    ArrayList<Taller> tallerarray;
    ArrayList<Taller> filterList;

    CustomFilterP filter;

    public MyAdapterPersonal(Context c, ArrayList<Taller> t) {
        context = c;
        tallerarray = t;
        this.filterList = t;
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter=new CustomFilterP(filterList,this);
        }
        return filter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyAdapterPersonal.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.rowartistico, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Taller task = tallerarray.get(position);

        holder.txttitulotaller.setText(tallerarray.get(position).getNombre_taller());
        holder.txtdocentetaller.setText(task.getNombre_docente());
        Picasso.get().load(tallerarray.get(position).getPhoto_taller()).into(holder.imgTaller);

        holder.setItemclicklistener(new itemclicklistener() {
            @Override
            public void onItemClickListener(View v, int position) {
                String gIdTaller = task.getId_taller();
                Intent detalleTaller = new Intent(context, Detalle_PersonalActivity.class);
                detalleTaller.putExtra("TallerId", gIdTaller);
                context.startActivity(detalleTaller);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tallerarray.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txttitulotaller, txtdocentetaller;
        ImageView imgTaller;
        itemclicklistener itemclicklistener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitulotaller =(TextView)itemView.findViewById(R.id.taller_titulo);
            txtdocentetaller=(TextView)itemView.findViewById(R.id.taller_docente);
            imgTaller =(ImageView)itemView.findViewById(R.id.taller_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemclicklistener.onItemClickListener(v,getLayoutPosition());
        }
        public void setItemclicklistener (itemclicklistener ic){

            this.itemclicklistener = ic;
        }
    }
}
