package com.example.app_taex_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConfirmLoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button buttonverificar, buttonrecibir;
    EditText editTextcode;
    TextView editTextnumber;
    ProgressBar progressBar;
    String CodeSend;
    String number;
    String codigointent, passwordintent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_login);

        mAuth = FirebaseAuth.getInstance();
        editTextcode = (EditText)findViewById(R.id.edt_code);
        editTextnumber=(TextView)findViewById(R.id.edt_number);
        progressBar =(ProgressBar)findViewById(R.id.progressbar);
        Intent intent = getIntent();
        codigointent = intent.getStringExtra("CodigoLogin");
        passwordintent = intent.getStringExtra("passlogin");

        number = Common.currentUser.celular_alumno;

        sendVerification(number);

        /*********3 ULTIMOS DIGITOS ********/
        String codigonumero="";
        String[] nums = number.split("");
        int numero = nums.length;
        for(int i=numero-3;i<numero;i++){
            String valor = nums[i]+"";
            codigonumero= codigonumero + valor;
        }
        editTextnumber.setText(codigonumero);
        /********************/
        buttonverificar =(Button)findViewById(R.id.btn_verificar2);
        buttonverificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextcode.getText().toString().trim();
                if(code.isEmpty() || code.length()<6){
                    editTextcode.setText("Invalido");
                    editTextcode.requestFocus();
                    return;
                }
                verifyCode(code);
            }
        });

    }

    public void sendVerification(String number){
        progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number, 60, TimeUnit.SECONDS,this, mCallbacks);
    }

    public PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code!=null){
                editTextcode.setText(code);
                progressBar.setVisibility(View.INVISIBLE);
                //verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                Toast.makeText(getApplicationContext(), "Numero Invalido", Toast.LENGTH_LONG);
            } else if (e instanceof FirebaseTooManyRequestsException) {
                Toast.makeText(getApplicationContext(), "El mensaje ha sido excedde", Toast.LENGTH_LONG);
            }
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            CodeSend = s;
        }
    };

    public void signInWithPhoneAuthCredential (PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            final String codigopref = getFromSharedPreferences("Codigo");
                            guardarPreferences(codigointent,passwordintent);
                            Intent intent = new Intent(ConfirmLoginActivity.this, MenuPrincipalActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(ConfirmLoginActivity.this,"No se pudo iniciar sesion", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void verifyCode(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(CodeSend,code);
        signInWithPhoneAuthCredential(credential);
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
