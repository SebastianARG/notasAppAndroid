package com.sebas.activitat1evaluable;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button[]botones;
    private Button btnAddNote;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.textView);
        botones = new Button[]{
                findViewById(R.id.Nota1),
                findViewById(R.id.Nota2),
                findViewById(R.id.Nota3),
                findViewById(R.id.Nota4),
                findViewById(R.id.Nota5),
                findViewById(R.id.Nota6)
        };
        btnAddNote = findViewById(R.id.btnAddNote);
        setInvisible();
        //Añadir una nota cuando presionemos el boton de añadir
        btnAddNote.setOnClickListener(v -> {
            for (Button boton : botones) {
                if (boton.getVisibility() == View.INVISIBLE) {
                    setVisible(boton);
                    break;
                }
            }
        });

    }

    public void setVisible(Button boton){
        boton.setVisibility(View.VISIBLE);
    }
    private void setInvisible(){
        for (Button boton : botones) {
            boton.setVisibility(View.INVISIBLE);
        }
    }
}