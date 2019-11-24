package com.example.a01test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.Constantes;
import com.example.utils.Utils;

public class SecondActivity extends AppCompatActivity {

    private RadioButton rbtSaludoHola;
    private RadioButton rbtSaludoChau;
    private SeekBar sekEdad;
    private TextView lblEdad;
    private Button btnContinuar;

    private String saludo;
    private int edad;
    private static final int EDAD_MINIMA = 16;
    private static final int EDAD_MAXIMA = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        initComponents();
        setActionBarIcon();
        enableBackOption();

        final String nombre = getIntent().getExtras().getString(Constantes.KEY_NOMBRE);

        /* Evento para el SeekBar */
        sekEdad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                edad = progress;
                lblEdad.setText(String.valueOf(edad));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(edad > EDAD_MAXIMA) {
                    btnContinuar.setVisibility(View.INVISIBLE);
                    Utils.showToast(SecondActivity.this, "La edad máxima es 60",
                            Toast.LENGTH_LONG);
                } else if(edad < EDAD_MINIMA) {
                    btnContinuar.setVisibility(View.INVISIBLE);
                    Utils.showToast(SecondActivity.this, "La edad mínima es 16",
                            Toast.LENGTH_LONG);
                } else {
                    btnContinuar.setVisibility(View.VISIBLE);
                }
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saludo = rbtSaludoHola.isChecked() ? Constantes.OPCION_HOLA : Constantes.OPCION_CHAU;
                if(!saludo.isEmpty()){
                    if(edad < EDAD_MAXIMA && edad > EDAD_MINIMA){
                        Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                        i.putExtra(Constantes.KEY_NOMBRE, nombre);
                        i.putExtra(Constantes.KEY_SALUDO, saludo);
                        i.putExtra(Constantes.KEY_EDAD, edad);
                        startActivity(i);
                    } else {
                        Utils.showToast(SecondActivity.this,
                                "La edad debe estar entre 16 y 60 años", Toast.LENGTH_LONG);
                    }
                } else {
                    Utils.showToast(SecondActivity.this, "Seleccione saludo",
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

    private void enableBackOption(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initComponents() {
        rbtSaludoHola = findViewById(R.id.rbtSaludoHola);
        rbtSaludoChau = findViewById(R.id.rbtSaludoChau);
        sekEdad = findViewById(R.id.sekEdad);
        lblEdad = findViewById(R.id.lblEdad);
        btnContinuar = findViewById(R.id.btnContinuar);
    }
}
