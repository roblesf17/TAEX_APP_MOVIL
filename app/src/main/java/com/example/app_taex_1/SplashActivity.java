package com.example.app_taex_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    private int time_loading=3000;
    String codigopr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Window window=getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //Init Firebase
        // final FirebaseDatabase database = FirebaseDatabase.getInstance();
        // final DatabaseReference table_alumno = database.getReference("Alumno");

       // codigopr = getFromSharedPreferences("codigo");

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if(codigopr==null || codigopr.equals("")){
                    Intent home = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(home);
                    finish();
                }
                else{
                    table_alumno.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Alumno alumno = dataSnapshot.child(codigopr).getValue(Alumno.class);
                            Intent homeIntent = new Intent(SplashActivity.this, MenuPrincipalActivity.class);
                            Common.currentUser = alumno;
                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(homeIntent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }
        }, time_loading);*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home = new Intent(SplashActivity.this, MenuPrincipalActivity.class);
                startActivity(home);
                finish();
            }
        }, time_loading);
    }

    public void guardarPreferences(String codigo, String password){
        SharedPreferences prefs = getSharedPreferences("preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("codigo", codigo);
        editor.putString("contrasena", password);
        editor.apply();
    }
    public String getFromSharedPreferences(String Key){
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("preferences", Context.MODE_PRIVATE);
        return sharedPref.getString(Key,"");
    }

}
