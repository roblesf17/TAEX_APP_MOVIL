package com.example.app_taex_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MisTalleresActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<InscripcionTaller> list;
    MyAdapterTalleres adapterTalleres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_talleres);

        String codigo = Common.currentUser.codigo;
        recyclerView =(RecyclerView)findViewById(R.id.rv_talleres);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<InscripcionTaller>();

        reference = FirebaseDatabase.getInstance().getReference("Inscripcion");
        Query query = FirebaseDatabase.getInstance().getReference("Inscripcion")
                .orderByChild("codigo_alumnoI").equalTo(codigo);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.removeAll(list);
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        InscripcionTaller inscripcionTaller = snapshot.getValue(InscripcionTaller.class);
                        list.add(inscripcionTaller);

                    }
                    adapterTalleres = new MyAdapterTalleres(MisTalleresActivity.this, list);
                    recyclerView.setAdapter(adapterTalleres);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
