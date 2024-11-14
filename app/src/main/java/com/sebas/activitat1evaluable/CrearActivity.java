package com.sebas.activitat1evaluable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CrearActivity extends AppCompatActivity {

    private Notas notas;
    private EditText tituloET;
    private EditText contenidoET;
    private EditText caducidadET;
    private Button crear;
    private Button cancelar;
    private static Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        notas = new Notas();
        tituloET = findViewById(R.id.tituloPT);
        contenidoET = findViewById(R.id.contenidoET);
        caducidadET = findViewById(R.id.caducidadDate);
        crear = findViewById(R.id.crearButton);

        Intent intent = this.getIntent();
        this.notas = (Notas) intent.getSerializableExtra("not");

//        crear.setOnClickListener(v -> {
//
//        });
//        cancelar.setOnClickListener(v -> {
//
//        });
    }
    public void setVisible(Button boton){
        boton.setVisibility(View.VISIBLE);
    }
    public void crear(View view){
        Nota nota = new Nota();
        nota.setTitulo(tituloET.getText().toString());
        nota.setContenido(contenidoET.getText().toString());
        nota.setFecha(caducidadET.getText().toString());
        notas.add(nota);
        intent.putExtra("not", this.notas);
        this.setResult(Activity.RESULT_OK, intent);
        this.finish();
    }
    public void cancelar(View view){
        intent.putExtra("not", this.notas);
        this.setResult(Activity.RESULT_OK, intent);
        this.finish();
    }

}