package com.example.incidenciesfragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences login = this.getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = login.edit();
        String User = login.getString("User", "");
        String Password = login.getString("password" , "");

        if( User != "" && Password != ""){
            if (User.equals("admin")&& Password.equals("admin")) {
                startActivity(new Intent(MainActivity.this, GeneralActivity.class));
            }else {
                Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
        if(User == "" && Password == ""){
            final Button entrar = findViewById(R.id.login);
            entrar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String usuario;
                    String contrasena;
                    usuario = ((EditText) findViewById(R.id.user)).getText().toString();
                    contrasena = ((EditText) findViewById(R.id.password)).getText().toString();
                    if (usuario.equals("admin")&& contrasena.equals("admin")) {
                        editor.putString("User", usuario);
                        editor.putString("password", contrasena);
                        editor.commit();
                        startActivity(new Intent(MainActivity.this, GeneralActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}