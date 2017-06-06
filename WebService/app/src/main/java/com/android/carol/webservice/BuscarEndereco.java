package com.android.carol.webservice;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BuscarEndereco {
    public final int TIMEOUT_MILLIS = 15000;

    //função apra obter o endereço no web service a partir de um cep
    public String obterEndereco(String cep) throws Exception {
        //cria um objteo de url e indica o endereço do webservice personalizado com o cep desejado
        java.net.URL url = new URL("https://viacep.com.br/ws/" + cep + "/json");

        //cria um objeto de HttpURLConnection
        HttpURLConnection connection = null;
        String sJson = null;

        try {
            //seta o objeto HttpURLConnection com o retorno do metodo openConnection da url
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");//seta o tipo de requisição, neste caso com GET
            connection.setConnectTimeout(TIMEOUT_MILLIS);//seta o tempo de timeout de conexão
            connection.setReadTimeout(TIMEOUT_MILLIS);//seta o tempo de timeout de leitura
            connection.connect();//chama o metodo para conectar com o webservice

            InputStream in = null;
            int status = connection.getResponseCode();//pega o código de resporta do web service

            if (status >= HttpURLConnection.HTTP_BAD_REQUEST) {//verificar se o código de resposta é de erro
                in = connection.getErrorStream();//se for, pega o retorno de erro

            } else {
                in = connection.getInputStream();//se for sucesso, pega o retorno do web service que será com os dados do endereço em formato Json
            }
                sJson = getStringFromInputStream(in);//chama o método getStringFromInputStream para converter o objeto InputStream com os dados de endereço em uma string
            in.close();//fecha o objeto InputStream

        }catch (IOException e){
            throw e;
        }finally {
            if(connection!=null){
                connection.disconnect();//indepente de ter sucesso ou falha na conexão com o web service, é preciso verificar se a conexão está aberta e fechar
            }
        }

            return sJson;//retorna uma string com os dados de endereço

    }

    //metodo que recebe uma string com os dados de endereço para parsear em um objeto Json e a partir dele, um objeto de endereço
    public Endereco parseJson(String json){
        //cria um objeto de endereço
        Endereco objEndereco = new Endereco();
        try {
            //cria um objeto Json enviando a string com os dados de endereço
            JSONObject endereco = new JSONObject(json);

            //seta os valores do objeto de endereço com o conteudo do objeto Json
            objEndereco.setCep(endereco.optString("cep"));
            objEndereco.setLogradouro(endereco.optString("logradouro"));
            objEndereco.setComplemento(endereco.optString("complemento"));
            objEndereco.setBairro(endereco.optString("bairro"));
            objEndereco.setLocalidade(endereco.optString("localidade"));
            objEndereco.setUf(endereco.optString("uf"));
            objEndereco.setUnidade(endereco.optString("unidade"));
            objEndereco.setIbge(endereco.optString("ibge"));
            objEndereco.setGia(endereco.optString("gia"));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return objEndereco;//retorna o objeto de endereço
    }

    //função para onverter um InputStream em uma string
    public String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            //instancia o objeto de leiura  BufferedReader passando o InputStream recebido por parâmetro
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {//enquanto puder ler a próxima linha continua
                sb.append(line);//adiciona a linha lida no objeto de StringBuilder
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();//independente de ter dado erro, precisa fechar o objeto BufferedReader
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();//retorna uma string do StringBuilder
    }


}
