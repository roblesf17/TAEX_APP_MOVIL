package com.example.app_taex_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class PersonalActivity extends AppCompatActivity {

    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Taller> list;

    MyAdapterPersonal adapter;
    SharedPreferences preferences;
    String KeyValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        //Agregando el botón de Regreso en el Action Bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ActionBar actionBar = getSupportActionBar();
        String titulo = "TALLERES PERSONALES";
        actionBar.setTitle(titulo);

        /**************************************/

        preferences = this.getSharedPreferences("My_Pref", MODE_PRIVATE);
        recyclerView =(RecyclerView)findViewById(R.id.rv_personal);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Taller>();

        getDatos();

    }

    public void getDatos(){

        reference = FirebaseDatabase.getInstance().getReference().child("TallerPersonal");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.removeAll(list);
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Taller t = dataSnapshot1.getValue(Taller.class);
                    String KeyValue = dataSnapshot1.getKey();
                    t.setId_taller(KeyValue);
                    list.add(t);
                }
                adapter = new MyAdapterPersonal(PersonalActivity.this, list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PersonalActivity.this, "Opps.. Something wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {

        int id = item.getItemId();
        if(id ==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void sortDailog(){
        String[] options={"A-Z","Z-A"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Orden de");
        builder.setIcon(R.drawable.ic_action_ssort);

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort","A-Z");
                    editor.apply();
                }
                if(which == 1){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Sort","Z-A");
                    editor.apply();
                }
            }
        });
        builder.create().show();
    }
}
