package com.example.a2trabalho.datasource;

import android.os.AsyncTask;

import com.example.a2trabalho.models.Rick;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class BuscarDadosApi extends AsyncTask<String, Void, ArrayList<Rick>> {

    protected ArrayList<Rick> doInBackground(String... strings) {
        ArrayList<Rick> listaDados = new ArrayList<>();

        try {
            String link = strings[0];

            URL url = new URL(link);

            URLConnection connection = url.openConnection();

            InputStream stream = connection.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(stream);

            BufferedReader reader = new BufferedReader(inputStreamReader);

            String dados ="";
            String linha;

            while ((linha = reader.readLine()) != null) {
                dados += linha;
            }

            JSONObject json = new JSONObject(dados);

            JSONArray lista = new JSONArray(json.getString("content"));

            for (int i = 0; i < lista.length(); i++) {
                JSONObject item = (JSONObject) lista.get(i);

                Rick rick = new Rick();
                Rick.nome = item.getString("name");
                Rick.url = item.getString("url");
                Rick.imagem = item.getString("image");

                listaDados.add(rick);
            }
        }
        catch (Exception ex) {

        }
        return listaDados;
    }
}
