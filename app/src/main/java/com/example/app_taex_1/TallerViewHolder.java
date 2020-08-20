package com.example.app_taex_1;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TallerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView txttitulotaller, txtdocentetaller;
    ImageView imgTaller;
    itemclicklistener itemclicklistener;

    public TallerViewHolder(@NonNull View itemView) {
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

    public void setItemclicklistener(com.example.app_taex_1.itemclicklistener itemclicklistener) {
        this.itemclicklistener = itemclicklistener;
    }
}
