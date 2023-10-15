package com.example.posto;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // DECLARAÇÃO DE VARIÁVEIS PARA ELEMENTOS DE INTERFACE
    private TextInputLayout precoAlcoolLayout;
    private TextInputLayout precoGasolinaLayout;
    private TextInputEditText etPrecoAlcool;
    private TextInputEditText etPrecoGasolina;
    private Button btnCalcular;
    private Button btnLimpar;
    private TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // VINCULAÇÃO DE VARIÁVEIS POR ID
        precoAlcoolLayout = findViewById(R.id.precoAlcoolLayout);
        precoGasolinaLayout = findViewById(R.id.precoGasolinaLayout);
        etPrecoAlcool = findViewById(R.id.precoAlcool);
        etPrecoGasolina = findViewById(R.id.precoGasolina);
        btnCalcular = findViewById(R.id.calculaBt);
        btnLimpar = findViewById(R.id.limparBt);
        txtResultado = findViewById(R.id.resultado);

        // CONFIGURAÇÃO DO BOTÃO DE CALCULO
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularCombustivelViavel();
            }
        });

        // CONFIGURAÇÃO DO BOTÃO LIMPAR
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparDados();
            }
        });
    }

    // MÉTODO PARA CALCULAR O MELHOR COMBUSTÍVEL
    private void calcularCombustivelViavel() {
        // Obtendo os valores digitados pelo usuário
        String precoAlcoolStr = etPrecoAlcool.getText().toString();
        String precoGasolinaStr = etPrecoGasolina.getText().toString();

        // VERIFICAÇÃO DE PREENCHIMENTO DE CAMPOS
        if (!precoAlcoolStr.isEmpty() && !precoGasolinaStr.isEmpty()) {
            // CONVERSÃO DE VALORES PARA DECIMAL
            double precoAlcool = Double.parseDouble(precoAlcoolStr);
            double precoGasolina = Double.parseDouble(precoGasolinaStr);

            // CALCULO DE PORCENTAGEM DA VANTAGEM DO ALCOOL SOBRE A GASOLINA
            double percentual = (precoAlcool / precoGasolina) * 100;

            // DEFININDO RESULTADO COM BASE NO PERCENTUAL
            if (percentual <= 70) {
                txtResultado.setText("ABASTEÇA COM ÁLCOOL");
            } else {
                txtResultado.setText("ABASTEÇA COM GASOLINA");
            }

            // LIMPA OS ERROS ANTERIORES SE TIVER
            precoAlcoolLayout.setError(null);
            precoGasolinaLayout.setError(null);
        } else {
            txtResultado.setText("POR FAVOR, INSIRA OS DADOS!");

            // DEFINIE MENSAGENS DE ERRO PARA CAMPO VAZIO
            if (precoAlcoolStr.isEmpty()) {
                precoAlcoolLayout.setError("CAMPO OBRIGATÓRIO!");
            } else {
                precoAlcoolLayout.setError(null);
            }

            if (precoGasolinaStr.isEmpty()) {
                precoGasolinaLayout.setError("CAMPO OBRIGATÓRIO!");
            } else {
                precoGasolinaLayout.setError(null);
            }
        }
    }

    // MÉTODO PARA LIMPAR OS DADOS
    private void limparDados() {
        etPrecoAlcool.setText("");
        etPrecoGasolina.setText("");

        // TOAST
        Toast.makeText(this, "LIMPANDO DADOS", Toast.LENGTH_SHORT).show();
    }
}
