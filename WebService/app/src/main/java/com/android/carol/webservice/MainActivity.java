package com.android.carol.webservice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //objetos para vincular nos componentes de tela
    private EditText edtCep;
    private ProgressBar progresso;
    private TextView txtResultado;
    private String cep;//variável auxiliar para armazenar o valor de cep

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vinculando os objetos aos componentes da tela
        edtCep = (EditText) findViewById(R.id.edtCep);
        progresso = (ProgressBar) findViewById(R.id.progresso);
        txtResultado = (TextView) findViewById(R.id.txtResultado);

    }

    //classe extendida de AsyncTask para poder fazer a chamada do web service sem "travar" aplicativo
    //AsyncTask é uma pequena biblioteca que gerencia internamente as threads e handlers necessários para atualizar a interface.
    private class buscaCepTask extends AsyncTask<Void, Void, Void> {
        private String retorno;//váriave auxiliar para armazenar o retorno do webservice

        //o que é feito antes da execução da tarefa
        protected void onPreExecute() {
            super.onPreExecute();
            progresso.setVisibility(View.INVISIBLE);//seta a propriedade de visibilidade da barra de progresso para invisivel
        }


        @Override
        protected Void doInBackground(Void... params) {
            BuscarEndereco buscaEndereco = new BuscarEndereco();//criando objeto de BuscarEndereço, classe que contém o acesso ao webservice
            try {
                retorno = buscaEndereco.obterEndereco(cep);//chama o método de obter endereço enviar o cep digitado pelo usuário
            } catch (Exception e) {
                Log.i("Erro:", e.getMessage());
            }
            return null;
        }

        //após executar a tarefa
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progresso.setVisibility(View.INVISIBLE);//seta a propriedade de visibilidade da barra de progresso para invisivel

            BuscarEndereco buscaEndereco = new BuscarEndereco();
            //verifica se a função parseJson, utilizada para converter o retorno da string obtida do
            //webservice em um objeto Json e posteriormente em um objeto de endereço
            if (buscaEndereco.parseJson(retorno) != null) {
                Endereco endParse = buscaEndereco.parseJson(retorno);

                //seta o valor do txtResult com o endereço obtido no webservice
                txtResultado.setText(
                        "Endereço encontrado: \n" +
                                "\n Logradouro: " + endParse.getLogradouro() +
                                "\n Bairro: " + endParse.getBairro() +
                                "\n Cidade: " + endParse.getLocalidade() +
                                "\n UF: " + endParse.getUf()
                );

            } else {
                txtResultado.setText("Ocorreu um erro ao buscar a localidade.");
            }
        }
    }

    //função para disparar a tarefa para consulta do cep no webservice
    public void buscaCep(View v) {
        if (edtCep.getText().toString().length() == 8) {//checa se o cep digitado possui 8 caracteres
            cep = edtCep.getText().toString();//armazena o cep digitado na variavel auxiliar "cep"
            new buscaCepTask().execute();//cria um objeto de buscaCepTask e aciona o metodo de execute, para disparar a ação
        } else {
            Toast.makeText(this, "O campo deve ser preenchido corretamente.", Toast.LENGTH_SHORT).show();
        }
    }
}
