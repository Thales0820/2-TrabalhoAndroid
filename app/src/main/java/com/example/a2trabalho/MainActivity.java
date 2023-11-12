package com.example.a2trabalho;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.a2trabalho.datasource.BuscarDadosApi;
import com.example.a2trabalho.models.Rick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends ListActivity {

    private ArrayList<Rick> listaDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            listaDados = new BuscarDadosApi().execute(Config.link).get();

            ListAdapter adapter = new SimpleAdapter(this,
                dadostoMap(listaDados),
                R.layout.listview_modelo,
                new String[] { "nome" },
                new int[] {R.id.txtNome}
            );

            setListAdapter(adapter);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<HashMap<String,String>> dadostoMap(ArrayList<Rick> listaDados) {
        List<HashMap<String,String>> lista = new ArrayList<>();

        for (int i = 0; i < listaDados.size(); i++) {
            HashMap<String,String> item = new HashMap<>();
            item.put("id", String.valueOf(listaDados.get(i).id()));
            item.put("nome", listaDados.get(i).nome);
            item.put("imagem", listaDados.get(i).imagem);

            lista.add(item);
        }
        return lista;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Rick rick = listaDados.get(position);

        Intent tela = new Intent(MainActivity.this, DetalhesActivity.class);

        Bundle parametros = new Bundle();
        parametros.putString("nome", rick.nome);
        parametros.putString("imagem", rick.imagem);

        tela.putExtras(parametros);

        startActivity(tela);
    }
}