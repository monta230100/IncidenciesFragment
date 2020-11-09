package com.example.incidenciesfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Button entrar = findViewById(R.id.login);
        entrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String usuario=((EditText) findViewById(R.id.user)).getText().toString();
                String contraseña=((EditText) findViewById(R.id.password)).getText().toString();

                if (usuario.equals("admin")&& contraseña.equals("admin")) {
                    startActivity(new Intent(MainActivity.this, GeneralActivity.class));
                }
                else
                    Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

    }
}