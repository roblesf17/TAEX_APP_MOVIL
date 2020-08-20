package com.example.app_taex_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText editTextCodigo, editTextPassword;
    TextView textViewRegistrarse;
    Button btningresar;

    String codigotexto="", passwordtexto="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textViewRegistrarse = (TextView)findViewById(R.id.tv_registrarse);
        editTextCodigo =(EditText)findViewById(R.id.txt_codigo);
        editTextPassword=(EditText)findViewById(R.id.txt_password);
        btningresar =(Button)findViewById(R.id.btn_ingresarlogin);

        //Init Firebase
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_alumno = database.getReference("Alumno");

        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codigotexto = editTextCodigo.getText().toString();
                passwordtexto = editTextPassword.getText().toString();
                final String codigopref = getFromSharedPreferences("Codigo");
                    if (!codigotexto.isEmpty() && !passwordtexto.isEmpty()){

                            final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
                            mDialog.setMessage("Porfavor espere");
                            mDialog.show();
                            table_alumno.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    //Verificar si el usuario existe
                                    if (dataSnapshot.child(editTextCodigo.getText().toString()).exists()) {
                                        //Obtener Informacion del usuario
                                        mDialog.dismiss();
                                        Alumno alumno = dataSnapshot.child(codigotexto).getValue(Alumno.class);

                                        if (alumno.getContrasena_alumno().equals(passwordtexto)) {

                                            Intent homeIntent = new Intent(MainActivity.this, ConfirmLoginActivity.class);
                                            Common.currentUser = alumno;
                                            homeIntent.putExtra("CodigoLogin", codigotexto);
                                            homeIntent.putExtra("passlogin", passwordtexto);
                                            homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(homeIntent);

                                            finish();
                                        } else {
                                            Toast.makeText(MainActivity.this, "Inicio de Sesion Fallido", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        mDialog.dismiss();
                                        Toast.makeText(MainActivity.this, "Usuario no existe", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                       // }

                    }
                    else {
                        Toast.makeText(MainActivity.this, "Complete los campos.", Toast.LENGTH_SHORT).show();
                    }
            }
        });

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
