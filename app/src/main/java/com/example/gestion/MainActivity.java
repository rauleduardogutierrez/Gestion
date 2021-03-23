package com.example.gestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText usuario;
    EditText password;
    Button entrar;
    Button cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrar = (Button)findViewById(R.id.entrar);
        cancelar = (Button)findViewById(R.id.cancelar);
        usuario = (EditText)findViewById(R.id.usuario);
        password = (EditText)findViewById(R.id.password);
    }
    public void onClick(View view){

        Intent miIntent = null;

        switch (view.getId()){
            case R.id.entrar:
                miIntent = new Intent(MainActivity.this, GestionPrincipal.class);
                break;

            case R.id.cancelar:
                System.exit (0);
                break;
        }
        startActivity(miIntent);
    }
}