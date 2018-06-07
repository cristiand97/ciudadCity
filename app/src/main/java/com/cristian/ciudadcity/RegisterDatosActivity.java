package com.cristian.ciudadcity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterDatosActivity extends AppCompatActivity {


    private EditText txtNombre,txtApellido,txtCedula,txtTelefono;
    private Button btnRegistroDatos;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_datos);
        mAuth = FirebaseAuth.getInstance();
        txtNombre =  findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtCedula= findViewById(R.id.txtCedula);
        txtTelefono = findViewById(R.id.txtTelefono);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Usuario");
        btnRegistroDatos = findViewById(R.id.btnRegistroDatos);
        btnRegistroDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario user = new Usuario();
                user.setNombre(txtNombre.getText().toString().trim());
                user.setApellido(txtApellido.getText().toString().trim());
                user.setCedula(txtCedula.getText().toString().trim());
                user.setTelefono(txtTelefono.getText().toString().trim());
                myRef.child(mAuth.getCurrentUser().getUid()).setValue(user);
                mAuth.signOut();
                Intent inte = new Intent(RegisterDatosActivity.this,MainActivity.class);
                startActivity(inte);
                finish();
            }
        });
    }
}