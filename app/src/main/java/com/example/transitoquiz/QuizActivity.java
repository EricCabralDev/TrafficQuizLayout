package com.example.transitoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class QuizActivity extends AppCompatActivity {

    private int questao_atual = 0;
    private int pontuacao = 0;

    private int[] imagens_placas = {
            R.drawable.placa_1, // PARE
            R.drawable.placa_2, // Limite 60
            R.drawable.placa_3, // Proibido Estacionar
            R.drawable.placa_4, // Atenção
            R.drawable.placa_5  // Proibido Ultrapassar
    };

    private String[][] texto_opcoes = {
            {"Pare e dê preferência.", "Proibido estacionar.", "Limite de velocidade de 60 km/h.", "Parada obrigatória para verificação."},
            {"Velocidade mínima de 60 km/h.", "Velocidade máxima permitida de 60 km/h.", "Limite de velocidade para caminhões.", "Área de radar."},
            {"Estacionamento permitido por 30 minutos.", "Estacionamento proibido em todo o local.", "Estacionamento permitido apenas para residentes.", "Estacionamento permitido com pagamento."},
            {"Reduzir a velocidade e estar atento a perigos.", "Parar completamente e verificar o tráfego.", "Ultrapassar outros veículos com cuidado.", "Seguir em frente sem alterações na velocidade."},
            {"Proibido ultrapassar outros veículos.", "Proibido parar na via.", "Proibido dirigir acima de 80 km/h.", "Proibido estacionar em ambos os lados da via."}
    };

    private int[] resp_certas = {R.id.btn_opcao4, R.id.btn_opcao2, R.id.btn_opcao2, R.id.btn_opcao1, R.id.btn_opcao1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final ImageView imgv_placa = findViewById(R.id.imgv_placa);
        final RadioGroup rdgp_opcoes = findViewById(R.id.rdgp_opcoes);
        final Button btn_responder = findViewById(R.id.btn_responder);

        pularPergunta();

        btn_responder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int opcao_selecionada = rdgp_opcoes.getCheckedRadioButtonId();
                if (opcao_selecionada == resp_certas[questao_atual]) {
                    pontuacao++;
                }
                questao_atual++;
                if (questao_atual < imagens_placas.length) {
                    pularPergunta();
                } else {
                    Intent intent = new Intent(QuizActivity.this, ResultActivty.class);
                    intent.putExtra("pontuacao", pontuacao);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void pularPergunta() {
        ImageView imgv_placa = findViewById(R.id.imgv_placa);
        RadioGroup rdgp_opcoes = findViewById(R.id.rdgp_opcoes);

        if (imgv_placa != null) {
            imgv_placa.setImageResource(imagens_placas[questao_atual]);
        }

        if (rdgp_opcoes != null) {
            for (int i = 0; i < rdgp_opcoes.getChildCount(); i++) {
                View view = rdgp_opcoes.getChildAt(i);
                if (view instanceof RadioButton) {
                    ((RadioButton) view).setChecked(false);
                }
            }

            String[] currentOptions = texto_opcoes[questao_atual];
            for (int i = 0; i < currentOptions.length; i++) {
                RadioButton option = findViewById(getResources().getIdentifier("btn_opcao" + (i + 1), "id", getPackageName()));
                if (option != null) {
                    option.setText(currentOptions[i]);
                }
            }
        }
    }
}
