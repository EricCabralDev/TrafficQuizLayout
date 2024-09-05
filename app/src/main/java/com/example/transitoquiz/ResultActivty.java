package com.example.transitoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_activty);

        TextView txtv_resultado = findViewById(R.id.txtv_resultado);
        Button btn_reiniciar = findViewById(R.id.btn_reiniciar);
        Button btn_tela_principal = findViewById(R.id.btn_tela_principal);

        int pontuacao = getIntent().getIntExtra("pontuacao", 0);
        txtv_resultado.setText( pontuacao + " Pontos.");

        btn_reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivty.this, QuizActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_tela_principal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivty.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}