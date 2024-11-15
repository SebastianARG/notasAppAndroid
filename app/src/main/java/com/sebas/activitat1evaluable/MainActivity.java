package com.sebas.activitat1evaluable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button[]botones;
    private int[] indicesBotones;
    private Button btnAddNote;
    private TextView textView;
    private ActivityResultLauncher<Intent> startForResultCrear;
    private ActivityResultLauncher<Intent> startForResultVer;
    private Notas notas;
    private Nota nota;
    private int cantNotas = 0;
    private int index ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("ACT1", "onCreate");
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
        notas = new Notas();
        nota = new Nota();
        btnAddNote = findViewById(R.id.btnAddNote);
        setInvisible();
            indicesBotones = new int[botones.length];
            for (int i = 0; i < botones.length; i++) {
                indicesBotones[i] = i;
            }
            for (int i = 0; i < botones.length; i++) {
                int num = i;
                botones[i].setOnClickListener(v -> {

                  Intent intent = new Intent(this,VerActivity.class);
                  nota = notas.get(indicesBotones[num]);
                  intent.putExtra("nota", this.nota);
                  this.startForResultVer.launch(intent);
                });
            }
        startForResultCrear = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    cantNotas = notas.size();
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        notas = (Notas) result.getData().getSerializableExtra("not");
                        Log.i("ACT1", "regreso");
                        Log.i("ACT1", notas.toString());
                        if(notas.size()>cantNotas){
                            for (Button boton : botones) {
                                if (boton.getVisibility() == View.INVISIBLE) {
                                    setVisible(boton);
                                    boton.setText(notas.getLast().getTitulo() + "\n\n"+notas.getLast().getFecha());
                                    break;
                                }
                            }
                        }
                    }
                });


        startForResultVer = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        nota = (Nota) result.getData().getSerializableExtra("nota");
                        if(!nota.getTitulo().equals("back")){
                            Log.i("ACT1", "regreso de ver pa borrar");
                            Log.i("ACT1", nota.toString());
                            setInvisibleAndEmpty();
                            index = notas.getIndexOf(nota);
                            Log.i("ACT1", "Índice de la nota a eliminar: " + index);
                            Log.i("ACT1", "Lista de notas antes de eliminar: " + notas.toString());
                            if(index != -1 && index < notas.size()){
                                notas.remove(index);
                            }
                            Log.i("ACT1", notas.toString());//aqui deberia borrarse la nota
                            for(int i = 0; i < notas.size(); i++){
                                setVisible(botones[i]);
                                botones[i].setText(notas.get(i).getTitulo() + "\n\n"+notas.get(i).getFecha());
                                Log.i("ACT1","actualizo botones");
                            }
                        }else{
                            Log.i("ACT1", "regreso de ver sin borrar");
                            Log.i("ACT1", nota.toString());
                        }
                    }
                });
        //Añadir una nota cuando presionemos el boton de añadir
//        btnAddNote.setOnClickListener(v -> {
//            for (Button boton : botones) {
//                if (boton.getVisibility() == View.INVISIBLE) {
//                    setVisible(boton);
//                    break;
//                }
//            }
//        });

    }

    public void crearNota(View view) {
        Intent intent = new Intent(this,CrearActivity.class);
        intent.putExtra("not", this.notas);
        this.startForResultCrear.launch(intent);

    }

    public void setInvisible(Button boton){
        boton.setVisibility(View.INVISIBLE);
    }



    public void setVisible(Button boton){
        boton.setVisibility(View.VISIBLE);
    }

    private void setInvisibleAndEmpty(){
        for (Button boton : botones) {
            boton.setVisibility(View.INVISIBLE);
            boton.setText("");
        }
    }
    private void setInvisible(){
        for (Button boton : botones) {
            boton.setVisibility(View.INVISIBLE);
        }
    }
}