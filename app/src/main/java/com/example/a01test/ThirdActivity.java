package com.example.a01test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.utils.Constantes;

public class ThirdActivity extends AppCompatActivity {

    private Button btnSaludo;
    private TextView lblSaludo;
    private Button btnCompartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        initComponents();
        setActionBarIcon();
        enableBackOption();

        Bundle bundle = getIntent().getExtras();
        final String nombre = bundle.getString(Constantes.KEY_NOMBRE);
        final String saludo = bundle.getString(Constantes.KEY_SALUDO);
        final String edad = bundle.get(Constantes.KEY_EDAD).toString();
        final String saludoEnviado = saludo.equals(Constantes.OPCION_HOLA)
                ? "Hola " + nombre + " ¿cómo van esos " + edad + " años?"
                : "Espero verte pronto " + nombre + ", antes de que pases los " + edad + " años =)";

        btnSaludo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lblSaludo.setText(saludoEnviado);
                btnCompartir.setVisibility(View.VISIBLE);
                btnSaludo.setVisibility(View.INVISIBLE);
            }
        });

        btnCompartir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, saludoEnviado);
                startActivity(Intent.createChooser(i, "Elige por donde quieres compartir"));
            }
        });
    }

    private void setActionBarIcon(){
        getSupportActionBar().setLogo(R.drawable.ic_tubes_32);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void enableBackOption(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initComponents () {
        btnSaludo = findViewById(R.id.btnSaludo);
        lblSaludo = findViewById(R.id.lblSaludo);
        btnCompartir = findViewById(R.id.btnCompartir);

        //Ocultando botones
        btnCompartir.setVisibility(View.INVISIBLE);
    }
}
