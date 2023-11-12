package com.example.a2trabalho;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2trabalho.R;
import com.example.a2trabalho.datasource.DownloadImagem;

public class DetalhesActivity extends AppCompatActivity {

    TextView txtNome;
    ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        txtNome = findViewById(R.id.txtNome);
        imagem = findViewById(R.id.imagem);

        Intent caminhoRecebido = getIntent();

        if (caminhoRecebido != null) {
            Bundle parametros = caminhoRecebido.getExtras();

            if (parametros != null) {
                txtNome.setText(parametros.getString("nome"));
                new DownloadImagem(imagem).execute(parametros.getString("imagem"));
            }
        }
    }
}
