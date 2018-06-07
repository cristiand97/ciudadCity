package com.cristian.ciudadcity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText txtEmail,txtClave;
    private Button btnRegister , btnLogin , btnOlvido;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEmail = findViewById(R.id.txtEmail);
        txtClave= findViewById(R.id.txtClave);
        btnRegister =findViewById(R.id.btnRegistroDatos);
        btnLogin = findViewById(R.id.btnLogin);
        btnOlvido = findViewById(R.id.btnOlvido);
        mAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(txtEmail.getText().toString().trim(),txtClave.getText().toString().trim()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"usuario registrado",Toast.LENGTH_SHORT).show();
                            Intent inte = new Intent(MainActivity.this,RegisterDatosActivity.class);
                            startActivity(inte);
                            finish();
                        }else{
                            Toast.makeText(MainActivity.this,"usuario no registrado",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btnOlvido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.sendPasswordResetEmail(txtEmail.getText().toString().trim());
                Toast.makeText(MainActivity.this,"correo enviado",Toast.LENGTH_SHORT).show();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(txtEmail.getText().toString().trim(),txtClave.getText().toString().trim()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"usuario logeado",Toast.LENGTH_SHORT).show();
                            Intent inte = new Intent(MainActivity.this,NavigatorActivity.class);
                            startActivity(inte);
                            finish();
                        }else{
                            Toast.makeText(MainActivity.this,"usuario no logeado",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
