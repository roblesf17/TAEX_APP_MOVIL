package com.example.app_taex_1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PerfilActivity extends AppCompatActivity {

    EditText editTextnombre, editTextapellido, editTextfechanac,
            editTextdireccion, editTextcelular, editTextemail, editTextfacultad, editTextescuela, editTextContras1, editTextContra2;
    Button buttonCerrar, buttonGuardarDatos, buttonGuardarContraseña;

    private static final String CERO = "0";
    private static final String BARRA = "/";
    String number;

    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();

    //Variables para obtener la fecha
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextnombre =(EditText)findViewById(R.id.txtnombre1);
        editTextapellido =(EditText)findViewById(R.id.txtapellidos1);
        editTextfacultad =(EditText)findViewById(R.id.txtfacultad1);
        editTextescuela =(EditText)findViewById(R.id.txtcarrera1);

        editTextfechanac =(EditText)findViewById(R.id.txtfechanacimiento1);
        editTextdireccion =(EditText)findViewById(R.id.txtdireccion1);
        editTextcelular =(EditText)findViewById(R.id.txtcelular1);
        editTextemail =(EditText)findViewById(R.id.txtemail1);

        buttonCerrar =(Button)findViewById(R.id.btncerrar);
        buttonGuardarDatos =(Button)findViewById(R.id.btneditardatos);
        buttonGuardarContraseña =(Button)findViewById(R.id.btneditarcontra);
        editTextContras1=(EditText)findViewById(R.id.txtcontra1);
        editTextContra2=(EditText)findViewById(R.id.txtcontras2);

        editTextnombre.setText(Common.currentUser.getNombre_alumno());
        editTextapellido.setText(Common.currentUser.getApellido_alummo());
        editTextfechanac.setText(Common.currentUser.getFecha_nacimiento_alumno());
        editTextdireccion.setText(Common.currentUser.getDomicilio_alumno());

        editTextemail.setText(Common.currentUser.getEmail_alumno());
        editTextfacultad.setText(Common.currentUser.getFacultad_alumno());
        editTextescuela.setText(Common.currentUser.getEscuela_alumno());

        number = Common.currentUser.celular_alumno;
        String codigonumero="";
        String[] nums = number.split("");
        int numero = nums.length;
        for(int i=numero-9;i<numero;i++){
            String valor = nums[i]+"";
            codigonumero= codigonumero + valor;
        }
        editTextcelular.setText(codigonumero);


        editTextfechanac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerFecha();
            }
        });

        buttonCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder signout = new AlertDialog.Builder(PerfilActivity.this);
                signout.setMessage("¿Desea cerrar sesión?").setCancelable(false)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final String codigo ="";
                                final String password="";
                                guardarPreferences(codigo,password);
                                Intent signIn = new Intent(PerfilActivity.this, MainActivity.class);
                                signIn.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(signIn);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo = signout.create();
                titulo.setTitle("Salida");
                titulo.show();
            }
        });

        buttonGuardarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alumno m = new Alumno();
                m.setNombre_alumno(Common.currentUser.nombre_alumno);
                m.setApellido_alummo(Common.currentUser.apellido_alummo);
                m.setContrasena_alumno(Common.currentUser.contrasena_alumno);
                m.setCodigo(Common.currentUser.codigo);
                m.setFacultad_alumno(Common.currentUser.facultad_alumno);
                m.setEscuela_alumno(Common.currentUser.escuela_alumno);
                m.setGenero_alumno(Common.currentUser.genero_alumno);
                m.setFecha_nacimiento_alumno(editTextfechanac.getText().toString().trim());
                m.setDomicilio_alumno(editTextdireccion.getText().toString().trim());
                String actnumero = editTextcelular.getText().toString().trim();
                actnumero = "+51"+actnumero;
                m.setCelular_alumno(actnumero);
                m.setEmail_alumno(editTextemail.getText().toString().trim());
                databaseReference.child("Alumno").child(Common.currentUser.getCodigo()).setValue(m);
                Toast.makeText(PerfilActivity.this, "Datos Actualizados", Toast.LENGTH_SHORT).show();
            }
        });

        buttonGuardarContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contraactual =editTextContras1.getText().toString().trim();
                String contranueva = editTextContra2.getText().toString().trim();

                if( contraactual.equals(Common.currentUser.contrasena_alumno)){
                    Alumno m = new Alumno();
                    m.setNombre_alumno(Common.currentUser.nombre_alumno);
                    m.setApellido_alummo(Common.currentUser.apellido_alummo);
                    m.setContrasena_alumno(contranueva);
                    m.setCodigo(Common.currentUser.codigo);
                    m.setFacultad_alumno(Common.currentUser.facultad_alumno);
                    m.setEscuela_alumno(Common.currentUser.escuela_alumno);
                    m.setGenero_alumno(Common.currentUser.genero_alumno);
                    m.setFecha_nacimiento_alumno(editTextfechanac.getText().toString().trim());
                    m.setDomicilio_alumno(editTextdireccion.getText().toString().trim());
                    m.setCelular_alumno(editTextcelular.getText().toString().trim());
                    m.setEmail_alumno(editTextemail.getText().toString().trim());
                    databaseReference.child("Alumno").child(Common.currentUser.getCodigo()).setValue(m);
                    Toast.makeText(PerfilActivity.this, "Contraseña Actualizada", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(PerfilActivity.this,"Su contraseña actual es incorrecta, ingrese de nuevo", Toast.LENGTH_SHORT).show();
                    editTextContras1.setText("");
                    editTextContra2.setText("");
                }
            }
        });

    }

    private void obtenerFecha(){
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                final int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? CERO + (dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? CERO + (mesActual):String.valueOf(mesActual);
                editTextfechanac.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);


            }
        },anio, mes, dia);
        recogerFecha.show();
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
