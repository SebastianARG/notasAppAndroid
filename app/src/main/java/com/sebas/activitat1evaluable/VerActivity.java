package com.sebas.activitat1evaluable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerActivity extends AppCompatActivity {


    private TextView fechaTD;
    private EditText contenido;
    private TextView titulo;
    Nota nota;
    private static Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.fechaTD = findViewById(R.id.fechaTD);
        this.contenido = findViewById(R.id.contenido_id);
        this.titulo = findViewById(R.id.tituloTV);

        Intent intent = this.getIntent();
        this.nota = (Nota) intent.getSerializableExtra("not");
        this.fechaTD.setText(this.nota.getFecha());
        this.contenido.setText(this.nota.getContenido());
        this.titulo.setText(this.nota.getTitulo());
    }

    public void goBack(View view){
        Nota n = new Nota();
        n.setTitulo("back");
        intent.putExtra("not",n);
        this.setResult(Activity.RESULT_OK, intent);
        this.finish();
    }
    public void eliminar(View view){
        intent.putExtra("not", this.nota);
        this.setResult(Activity.RESULT_OK, intent);
        this.finish();
    }



}