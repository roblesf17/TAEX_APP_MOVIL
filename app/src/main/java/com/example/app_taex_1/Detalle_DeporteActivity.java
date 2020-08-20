package com.example.app_taex_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Detalle_DeporteActivity extends AppCompatActivity {

    TextView mTitleDocente, mDesTaller, mNombreDocente, mDesDocente, mCantLimite, mCantInscritos, mEstado, mDia, mLugar, mHora, mPrecio;
    ImageView mImgDocente, mImgTaller;
    Toolbar toolbar;
    Button buttonIncribirse;
    CollapsingToolbarLayout collapsingToolBar;

    FirebaseDatabase database;
    DatabaseReference tallerdetalle;
    String TipoTaller="Taller Deportivo",TipoTaller2="TallerDeportivo";
    String TallerId="";
    Taller currentTaller;

    String nacimiento,direccion, celular, correo;
    ArrayList<InscripcionTaller> listacontar;
    DatabaseReference reference;
    TextView textViewMensaje2;
    int canttaller;

    String number;
    String mensajecosto;
    String costotaller;
    int codigoiden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle__deporte);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //Firebase
        database = FirebaseDatabase.getInstance();
        tallerdetalle=database.getReference("TallerDeportivo");

        mTitleDocente = findViewById(R.id.txt_nombre_docente);
        mDesTaller = findViewById(R.id.txt_descripcion_talleres);
        mDia=findViewById(R.id.txt_dia);
        mHora=findViewById(R.id.txt_hora);
        mLugar=findViewById(R.id.txt_lugar);
        mPrecio=findViewById(R.id.txt_precio);
        mNombreDocente =findViewById(R.id.txt_nombre_docente);
        mDesDocente=findViewById(R.id.txt_descripcion_docente);

        mCantLimite=findViewById(R.id.txt_cupos_disponibles);
        mCantInscritos=findViewById(R.id.txt_cantidad_inscritos);
        mEstado=findViewById(R.id.txt_estado);

        mImgTaller=findViewById(R.id.tool_photo);
        mImgDocente=findViewById(R.id.img_docente);

        //buttonIncribirse=findViewById(R.id.btnIncribirseD);
        textViewMensaje2=findViewById(R.id.txt_mensajeD);

        collapsingToolBar =(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout);
        collapsingToolBar.setExpandedTitleTextAppearance(R.style.ExpandeAppBarDesign);

        /*********** OBTENER DATOS PARA VALIDAR LA INSCRIPCION ***************/
        /*nacimiento=Common.currentUser.fecha_nacimiento_alumno;
        direccion=Common.currentUser.domicilio_alumno;
        celular=Common.currentUser.celular_alumno;
        correo=Common.currentUser.email_alumno;*/
        /***************************/

        /*********** LISTA LOS DATOS DEL TALLER *************/
        if(getIntent() !=null)
            TallerId = getIntent().getStringExtra("TallerId");
        if(!TallerId.isEmpty()){
            getTallerDetalle(TallerId);
        }
        /************************/

        /**************** DESHABILITAR EL BOTON DE INSCRIBIRSE SI YA HIZO ESE TALLER ******************
        String codigo = Common.currentUser.codigo;
        listacontar = new ArrayList<InscripcionTaller>();

        reference = FirebaseDatabase.getInstance().getReference("Inscripcion");
        Query query = FirebaseDatabase.getInstance().getReference("Inscripcion")
                .orderByChild("codigo_alumnoI").equalTo(codigo);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        InscripcionTaller inscripcionTaller = snapshot.getValue(InscripcionTaller.class);
                        String nombtaller = inscripcionTaller.getNombre_taller();
                        if(nombtaller.equals(currentTaller.getNombre_taller())){
                            buttonIncribirse.setVisibility(View.INVISIBLE);
                            textViewMensaje2.setText("Ya se ha inscrito a este curso anteriormente, no se puede inscribir de nuevo.");
                        }
                        listacontar.add(inscripcionTaller);
                        canttaller = listacontar.size();
                    }
                }
                else {
                    String tallerestado = currentTaller.getEstado();
                    String generotaller = currentTaller.getGenero();
                    if(tallerestado.equals("Cerrado")){
                        buttonIncribirse.setVisibility(View.INVISIBLE);
                        textViewMensaje2.setText("El taller ya se ha completado con la cantidad de alumnos requeridos. Inscribase en el siguiente semestre.");
                    }else
                    if (generotaller.equals("Femenino") && Common.currentUser.genero_alumno.equals("Masculino")){
                        buttonIncribirse.setVisibility(View.INVISIBLE);
                        textViewMensaje2.setText("Existe un taller Masculino de este deporte.");
                    }else if(generotaller.equals("Masculino") && Common.currentUser.genero_alumno.equals("Femenino")){
                        buttonIncribirse.setVisibility(View.INVISIBLE);
                        textViewMensaje2.setText("Existe un taller Femenino de este deporte.");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /********************************************************************
        number = Common.currentUser.codigo;
        String codigonumero="";
        String[] nums = number.split("");
        int numero = nums.length;
        for(int i=0;i<5;i++){
            String valor = nums[i]+"";
            codigonumero= codigonumero + valor;
        }
        codigoiden = Integer.parseInt(codigonumero);

        /******************************************/
        /*buttonIncribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nacimiento.isEmpty() || nacimiento.equals("") || direccion.equals("") || direccion.isEmpty() || celular.equals("") ||
                        celular.isEmpty() || correo.isEmpty() || correo.equals("")){
                    Toast.makeText(Detalle_DeporteActivity.this, "Complete sus datos en MI PERFIL para realizar la inscripcion ", Toast.LENGTH_LONG).show();
                }
                else  if(canttaller>=2){
                    AlertDialog.Builder Confirm = new AlertDialog.Builder(Detalle_DeporteActivity.this);
                    Confirm.setMessage("Ya se ha inscrito a 2 talleres antes, no es necesario que se inscriba a otro. \n¿Desea continuar con la inscrpción?").setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(codigoiden==2016 || codigoiden==2017 || codigoiden>2017){
                                        mensajecosto="Costo del Taller: S/. 125.00";
                                        costotaller="125.00";
                                    }else if(codigoiden<2016){
                                        mensajecosto="Costo del Taller: S/. 125.00";
                                        costotaller="125.00";
                                    }
                                    Intent intent = new Intent (Detalle_DeporteActivity.this, InscripcionActivity.class);
                                    intent.putExtra("IDTaller", TallerId);
                                    intent.putExtra("NombreTaller", currentTaller.getNombre_taller());
                                    intent.putExtra("TipoTaller", TipoTaller);
                                    intent.putExtra("TipoTaller2", TipoTaller2);
                                    intent.putExtra("PrecioTaller", currentTaller.getPrecio_taller());
                                    intent.putExtra("CantidaddeTalleres", canttaller);

                                    //Datos para Correo de Inscripción
                                    intent.putExtra("DocenteTaller", currentTaller.getNombre_docente());
                                    intent.putExtra("DiaTaller", currentTaller.getDia_taller());
                                    intent.putExtra("HoraTaller", currentTaller.getHora_taller());
                                    intent.putExtra("LugarTaller", currentTaller.getLugar());

                                    intent.putExtra("CostoMensaje", mensajecosto);
                                    intent.putExtra("Costo", costotaller);

                                    startActivity(intent);

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog titulo = Confirm.create();
                    titulo.setTitle("Importante");
                    titulo.show();
                }
                else {

                    if(codigoiden==2016 || codigoiden==2017 ){
                        mensajecosto="Costo del Taller: S/. 125.00";
                        costotaller="125.00";
                    }else if(codigoiden<2016){
                        mensajecosto="Costo del Taller: S/. 125.00";
                        costotaller="125.00";
                    }else if(codigoiden>2017){
                        mensajecosto="Costo del Taller: S/. 0.00";
                        costotaller="0.00";
                    }

                    Intent intent = new Intent (Detalle_DeporteActivity.this, InscripcionActivity.class);
                    intent.putExtra("IDTaller", TallerId);
                    intent.putExtra("NombreTaller", currentTaller.getNombre_taller());
                    intent.putExtra("TipoTaller", TipoTaller);
                    intent.putExtra("TipoTaller2", TipoTaller2);
                    intent.putExtra("PrecioTaller", currentTaller.getPrecio_taller());
                    intent.putExtra("CantidaddeTalleres", canttaller);

                    //Datos para Correo de Inscripción
                    intent.putExtra("DocenteTaller", currentTaller.getNombre_docente());
                    intent.putExtra("DiaTaller", currentTaller.getDia_taller());
                    intent.putExtra("HoraTaller", currentTaller.getHora_taller());
                    intent.putExtra("LugarTaller", currentTaller.getLugar());

                    intent.putExtra("CostoMensaje", mensajecosto);
                    intent.putExtra("Costo", costotaller);
                    startActivity(intent);
                }
            }
        });*/

    }
    public void getTallerDetalle(final String TallerId){
        tallerdetalle.child(TallerId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                currentTaller = dataSnapshot.getValue(Taller.class);

                //Set cosas
                toolbar.setTitle(currentTaller.getNombre_taller());
                setSupportActionBar(toolbar);

                Picasso.get().load(currentTaller.getPhoto_taller()).into(mImgTaller);
                mDesTaller.setText(currentTaller.getDescripcion_taller());
                mDia.setText(currentTaller.getDia_taller());
                mHora.setText(currentTaller.getHora_taller());
                mLugar.setText(currentTaller.getLugar());
                mPrecio.setText(currentTaller.getPrecio_taller());

                mNombreDocente.setText(currentTaller.getNombre_docente());
                mDesDocente.setText(currentTaller.getDescripcion_docente());
                Picasso.get().load(currentTaller.getFoto_docente()).into(mImgDocente);

                int cantIn = Integer.parseInt(currentTaller.getCant_Inscritos());
                int cantt = Integer.parseInt(currentTaller.getCant_Limite_Cupos());
                mCantInscritos.setText(currentTaller.getCant_Inscritos());
                int cuposlibres = cantt -cantIn;
                mCantLimite.setText(String.valueOf(cuposlibres));
                mEstado.setText(currentTaller.getEstado());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
