package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TP3_DR3_Q05_ApiRequest {
    public static void main(String[] args) {
        try {
            // URL da API pública informada pela questão 5
            String apiUrl = "https://api.publicapis.org/entries";

            // Fazendo a requisição GET
            Resposta resposta = fazerRequisicao(apiUrl);

            // Imprimindo a resposta
            System.out.println(resposta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Resposta fazerRequisicao(String apiUrl) throws Exception {
        // Criando a URL da API
        URL url = new URL(apiUrl);

        // Abrindo a conexão HTTP
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        // Configurando a requisição
        conexao.setRequestMethod("GET");

        // Lendo a resposta
        BufferedReader leitor = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
        StringBuilder respostaJson = new StringBuilder();
        String linha;

        while ((linha = leitor.readLine()) != null) {
            respostaJson.append(linha);
        }

        // Fechando recursos
        leitor.close();
        conexao.disconnect();

        // Criando e retornando a instância da classe Resposta
        return new Resposta(respostaJson.toString());
    }
}

class Resposta {
    private String respostaJson;

    public Resposta(String respostaJson) {
        this.respostaJson = respostaJson;
    }

    @Override
    public String toString() {
        return "Resposta{" +
                "respostaJson='" + respostaJson + '\'' +
                '}';
    }
}