package com.example.a2trabalho.models;

import com.example.a2trabalho.Config;

public class Rick {
    public static String nome;

    public static String url;

    public static String imagem;

    public int id(){
        if (url != null){
            String codigo = url.replace(Config.link, "") .replace("/", "");
            return Integer.parseInt(codigo);
        }
        return 0;
    }
}
