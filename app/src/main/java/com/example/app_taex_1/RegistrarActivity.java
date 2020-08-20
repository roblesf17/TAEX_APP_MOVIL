package com.example.app_taex_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrarActivity extends AppCompatActivity {

    EditText editTextcodigo, editTextnombre, editTextapellido, editTextdni, editTextfechanac,
            editTextdireccion, editTextcelular, editTextemail, editTextcontraseña;
    Spinner spinerGenero,spinnerFacultad, spinnerEscuela;
    String valToSet, valTFacultad;
    Button btnregistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

       /*Spinner spinner = (Spinner) findViewById(R.id.genero_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.genero_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        editTextcodigo =(EditText)findViewById(R.id.txtcodigor);
        editTextnombre =(EditText)findViewById(R.id.txtnombre);
        editTextapellido =(EditText)findViewById(R.id.txtapellidos);
        editTextfechanac =(EditText)findViewById(R.id.txtfechanacimiento);
        editTextcelular =(EditText)findViewById(R.id.txtcelular);
        editTextemail =(EditText)findViewById(R.id.txtemail);
        editTextdireccion =(EditText) findViewById(R.id.txtdireccion);
        editTextcontraseña =(EditText)findViewById(R.id.txt_contrasenare);
        btnregistrarse =(Button)findViewById(R.id.btnregistrasetaex);

        /***************** SPINERS ********************/
        Spinner spinner2 = (Spinner) findViewById(R.id.facultad_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.facultad_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("FACEM")){
                    valTFacultad="FACEM";
                    Spinner facemspinner = (Spinner) findViewById(R.id.escuela_spinner);
                    ArrayAdapter<CharSequence> adapterfacem = ArrayAdapter.createFromResource(RegistrarActivity.this, R.array.facem_array, android.R.layout.simple_spinner_item);
                    adapterfacem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    facemspinner.setAdapter(adapterfacem);
                    facemspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            valToSet = parent.getItemAtPosition(position).toString();
                            Toast.makeText(parent.getContext(), "Escuela: "+valToSet,Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if(parent.getItemAtPosition(position).equals("FAING")){
                    valTFacultad="FAING";
                    Spinner faingsinner = (Spinner) findViewById(R.id.escuela_spinner);
                    ArrayAdapter<CharSequence> adapterfaing = ArrayAdapter.createFromResource(RegistrarActivity.this, R.array.faing_array, android.R.layout.simple_spinner_item);
                    adapterfaing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    faingsinner.setAdapter(adapterfaing);
                    faingsinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            valToSet = parent.getItemAtPosition(position).toString();
                            Toast.makeText(parent.getContext(), "Escuela: "+valToSet,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if(parent.getItemAtPosition(position).equals("FAEDCOH")){
                    valTFacultad="FAEDCOH";
                    Spinner faedcohspinner = (Spinner) findViewById(R.id.escuela_spinner);
                    ArrayAdapter<CharSequence> adapterfaedcoh = ArrayAdapter.createFromResource(RegistrarActivity.this, R.array.faedcoh_array, android.R.layout.simple_spinner_item);
                    adapterfaedcoh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    faedcohspinner.setAdapter(adapterfaedcoh);
                    faedcohspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            valToSet = parent.getItemAtPosition(position).toString();
                            Toast.makeText(parent.getContext(), "Escuela: "+valToSet,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if(parent.getItemAtPosition(position).equals("FACSA")){
                    valTFacultad="FACSA";
                    Spinner facsaspinner = (Spinner) findViewById(R.id.escuela_spinner);
                    ArrayAdapter<CharSequence> adapterfacsa = ArrayAdapter.createFromResource(RegistrarActivity.this, R.array.facsa_array, android.R.layout.simple_spinner_item);
                    adapterfacsa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    facsaspinner.setAdapter(adapterfacsa);
                    facsaspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            valToSet = parent.getItemAtPosition(position).toString();
                            Toast.makeText(parent.getContext(), "Escuela: "+valToSet,Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if(parent.getItemAtPosition(position).equals("FAU")){
                    valTFacultad="FAU";
                    Spinner fauspinner = (Spinner) findViewById(R.id.escuela_spinner);
                    ArrayAdapter<CharSequence> adapterfau= ArrayAdapter.createFromResource(RegistrarActivity.this, R.array.fau_array, android.R.layout.simple_spinner_item);
                    adapterfau.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    fauspinner.setAdapter(adapterfau);
                    fauspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            valToSet = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if(parent.getItemAtPosition(position).equals("FADE")){
                    valTFacultad="FADE";
                    Spinner fadespinner = (Spinner) findViewById(R.id.escuela_spinner);
                    ArrayAdapter<CharSequence> adapterfade = ArrayAdapter.createFromResource(RegistrarActivity.this, R.array.fade_array, android.R.layout.simple_spinner_item);
                    adapterfade.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    fadespinner.setAdapter(adapterfade);
                    fadespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            valToSet = parent.getItemAtPosition(position).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerFacultad=(Spinner) findViewById(R.id.facultad_spinner);
        spinnerEscuela=(Spinner)findViewById(R.id.escuela_spinner);
        spinerGenero=(Spinner)findViewById(R.id.genero_spinner);

        String text = spinnerFacultad.getSelectedItem().toString();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_alumno= database.getReference("Alumno");

        btnregistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(RegistrarActivity.this);
                mDialog.setMessage("Porfavor espere");
                mDialog.show();

                table_alumno.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //Verificar si ya existe el usuario
                        if(dataSnapshot.child(editTextcodigo.getText().toString()).exists()){
                            mDialog.dismiss();
                            Toast.makeText(RegistrarActivity.this, "Ya existe este usu44ario", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            mDialog.dismiss();
                            Alumno user = new Alumno(
                                    editTextcodigo.getText().toString().trim(),
                                    editTextnombre.getText().toString().trim(),
                                    editTextapellido.getText().toString().trim(),
                                    editTextfechanac.getText().toString().trim(),
                                    spinerGenero.getSelectedItem().toString(),
                                    editTextdireccion.getText().toString().trim(),
                                    editTextcelular.getText().toString().trim(),
                                    editTextemail.getText().toString().trim(),
                                    spinnerFacultad.getSelectedItem().toString(),
                                    spinnerEscuela.getSelectedItem().toString(),
                                    editTextcontraseña.getText().toString().trim());
                            table_alumno.child(editTextcodigo.getText().toString()).setValue(user);

                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
