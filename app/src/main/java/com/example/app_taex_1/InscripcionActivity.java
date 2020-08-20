package com.example.app_taex_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InscripcionActivity extends AppCompatActivity {

    //Para notificación a CORREO--------------------------------------------
    private EditText txt_email_receptor;
    private Button btn_inscribirse;
    private EditText Txt_inscripcion_Tipo_Taller, Txt_inscripcion_nombre_taller;
    private TextView textViewCosto;
    CheckBox checkBoxAceptarTaller;

    //-----------------------------------------------------------------------

    FirebaseDatabase database;
    DatabaseReference request;

    FirebaseDatabase database2;
    DatabaseReference tallerdetalle2, databaseReference;

    String mdocente, mdia, mhora, mlugar, mmensajecosto, mcosto;
    String TallerIdInscripcion="";
    Taller currentTaller2;
    String tipotaller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscripcion);

        /************** RECIBIR DATOS ****************/
        database = FirebaseDatabase.getInstance();
        request=database.getReference("Inscripcion");

        databaseReference = FirebaseDatabase.getInstance().getReference();
                //-----------------------------------------------------
        Intent intent = getIntent();
        final String mnombre = intent.getStringExtra("NombreTaller");
        final String mtaller = intent.getStringExtra("TipoTaller");
        final String mprecio = intent.getStringExtra("PrecioTaller");

        mdocente = intent.getStringExtra("DocenteTaller");
        mdia = intent.getStringExtra("DiaTaller");
        mhora = intent.getStringExtra("HoraTaller");
        mlugar = intent.getStringExtra("LugarTaller");
        mmensajecosto=intent.getStringExtra("CostoMensaje");

        mcosto = intent.getStringExtra("Costo");

        tipotaller = intent.getStringExtra("TipoTaller2");

        /********************************/

        //Para notificación a CORREO-------------------------------------------------------

        Txt_inscripcion_nombre_taller = (EditText)findViewById(R.id.txt_inscripcion_nombre_taller);
        Txt_inscripcion_Tipo_Taller = (EditText)findViewById(R.id.txt_inscripcion_tipo_taller);
        btn_inscribirse = (Button)findViewById(R.id.btn_inscribirse);
        checkBoxAceptarTaller =(CheckBox)findViewById(R.id.checkAceptar);
        textViewCosto=(TextView)findViewById(R.id.txt_mensajecosto);

        Txt_inscripcion_Tipo_Taller.setText(mtaller);
        Txt_inscripcion_nombre_taller.setText(mnombre);
        textViewCosto.setText(mmensajecosto);

        String codigo_alumno = Common.currentUser.codigo;
        final String nombre_alumno = Common.currentUser.nombre_alumno;
        String direccion_alumno = Common.currentUser.domicilio_alumno;
        String celular_alumno = Common.currentUser.celular_alumno;
        final String correo_alumno = Common.currentUser.email_alumno;
        String facultad_alummno =Common.currentUser.facultad_alumno;
        final String escuela_alumno =Common.currentUser.escuela_alumno;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();

        final String fecha = dateFormat.format(date);

        /*********** LISTA LOS DATOS DEL TALLER *************/
        //Firebase
        database2 = FirebaseDatabase.getInstance();
        tallerdetalle2=database.getReference(tipotaller);

        if(getIntent() !=null)
            TallerIdInscripcion = getIntent().getStringExtra("IDTaller");
        if(!TallerIdInscripcion.isEmpty()){
            tallerdetalle2.child(TallerIdInscripcion).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    currentTaller2=dataSnapshot.getValue(Taller.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        /***************/


        btn_inscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(checkBoxAceptarTaller.isChecked()==true){

                    AlertDialog.Builder inscrConfirm = new AlertDialog.Builder(InscripcionActivity.this);
                    inscrConfirm.setMessage("¿Desea realizar la inscripción?").setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    /*************** GUARDAR INSCRIPCIÓN ***************/
                                    InscripcionTaller inscripcion = new InscripcionTaller();
                                    inscripcion.setFecha_inscripcion(fecha);
                                    inscripcion.setTipo_taller(mtaller);
                                    inscripcion.setNombre_taller(mnombre);
                                    inscripcion.setNota_inscripcion("0");
                                    inscripcion.setEstado_inscripcion("Inscrito");
                                    inscripcion.setPrecio_inscripcion(mprecio);
                                    inscripcion.setNombre_alumnoI(Common.currentUser.nombre_alumno);
                                    inscripcion.setCodigo_alumnoI(Common.currentUser.codigo);
                                    inscripcion.setDomicilio_alumnoI(Common.currentUser.domicilio_alumno);
                                    inscripcion.setCelular_alumnoI(Common.currentUser.celular_alumno);
                                    inscripcion.setEmail_alumnoI(Common.currentUser.email_alumno);
                                    inscripcion.setFacultad_alumnoI(Common.currentUser.facultad_alumno);
                                    inscripcion.setEscuela_alumnoI(Common.currentUser.escuela_alumno);
                                    request.child(String.valueOf(System.currentTimeMillis())).setValue(inscripcion);


                                    int cantidad = Integer.parseInt(currentTaller2.getCant_Inscritos());
                                    cantidad =cantidad+1;
                                    String cantidadactual = String.valueOf(cantidad);

                                    String estadotaller;
                                    int valorlimite = Integer.parseInt(currentTaller2.getCant_Limite_Cupos());
                                    if(cantidad ==valorlimite){
                                        estadotaller = "Cerrado";
                                    }
                                    else{
                                        estadotaller = currentTaller2.getEstado();
                                    }
                                    /*********** ACTUALIZAR CANTIDAD **************/
                                    Taller t = new Taller();
                                    t.setId_taller(currentTaller2.getId_taller());
                                    t.setNombre_taller(currentTaller2.getNombre_taller());
                                    t.setDescripcion_taller(currentTaller2.getDescripcion_taller());
                                    t.setDia_taller(currentTaller2.getDia_taller());
                                    t.setLugar(currentTaller2.getLugar());
                                    t.setPrecio_taller(currentTaller2.getPrecio_taller());
                                    t.setEstado(estadotaller);
                                    t.setPhoto_taller(currentTaller2.getPhoto_taller());
                                    t.setGenero(currentTaller2.getGenero());
                                    t.setHora_taller(currentTaller2.getHora_taller());
                                    t.setCant_Inscritos(cantidadactual);
                                    t.setCant_Limite_Cupos(currentTaller2.getCant_Limite_Cupos());
                                    t.setId_docente(currentTaller2.getId_docente());
                                    t.setNombre_docente(currentTaller2.getNombre_docente());
                                    t.setDescripcion_docente(currentTaller2.getDescripcion_docente());
                                    t.setFoto_docente(currentTaller2.getFoto_docente());

                                    databaseReference.child(tipotaller).child(TallerIdInscripcion).setValue(t);
                                    Toast.makeText(InscripcionActivity.this, "Datos Taller Actualizados", Toast.LENGTH_SHORT).show();

                                    try {
                                        String titulo = "INSCRIPCIÓN EN TALLER EXTRACURRICULAR";

                                        String taller = Txt_inscripcion_nombre_taller.getText().toString();
                                        String apellido_alumno = Common.currentUser.apellido_alummo;

                                        new SimpleMail().enviarEmail(nombre_alumno, apellido_alumno, correo_alumno, titulo, taller, mdocente, mdia, mhora, mlugar, mcosto);
                                        Toast.makeText(InscripcionActivity.this, "Inscripción exitosa...", Toast.LENGTH_SHORT).show();
                                        Intent intent1 = new Intent(InscripcionActivity.this, MenuPrincipalActivity.class);
                                        startActivity(intent1);

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                    Intent intent1 = new Intent(InscripcionActivity.this, MenuPrincipalActivity.class);
                                    startActivity(intent1);
                                }
                            });

                    AlertDialog titulo = inscrConfirm.create();
                    titulo.setTitle("Mensaje de Confirmación");
                    titulo.show();
                }
                else
                {
                    Toast.makeText(InscripcionActivity.this, "Marque la casilla", Toast.LENGTH_SHORT).show();
                }


            }

        });


    }
}
