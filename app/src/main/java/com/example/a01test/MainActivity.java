package com.example.a01test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.utils.Constantes;
import com.example.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre;
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        setActionBarIcon();

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString();
                if (!nombre.isEmpty()) {
                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    i.putExtra(Constantes.KEY_NOMBRE, nombre);
                    startActivity(i);
                } else {
                    Utils.showToast(MainActivity.this, "Ingrese su nombre",
                            Toast.LENGTH_LONG);
                }
            }
        });
    }

    private void setActionBarIcon(){
        getSupportActionBar().setLogo(R.drawable.ic_tubes_32);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initComponents() {
        txtNombre = findViewById(R.id.txtNombre);
        btnContinuar = findViewById(R.id.btnContinuar);
    }
}
