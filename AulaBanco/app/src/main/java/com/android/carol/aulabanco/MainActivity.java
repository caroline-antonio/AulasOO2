package com.android.carol.aulabanco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtTelefone;

    Contatos contatos;
    ContatosDB contatosDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vincula os objetos criados aos botões presentes na activity_main
        edtNome = (EditText)findViewById(R.id.edtNome);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);

        contatosDB = new ContatosDB(this);
    }

    //função que salva o registro do contato
    //esta função deve ser indicada no evento onClick do botão Salvar da activity_main
    public  void Inserir(View v){

        try {
            contatos = new Contatos();

            contatos.setNome(edtNome.getText().toString());
            contatos.setTelefone(edtTelefone.getText().toString());

            contatosDB.inserir(contatos);
            Toast.makeText(MainActivity.this, "Contato salvo com sucesso", Toast.LENGTH_LONG).show();

        } catch (Exception e){
            Toast.makeText(MainActivity.this, "Ocorreu um problema ao salvar o contato!", Toast.LENGTH_LONG).show();
        }
    }
    //função que chama a listagem de contatos
    //esta função deve ser indicada no evento onClick do botão Listar da activity_main
    public void Listar(View v){
        Intent intent = new Intent(this, listaContatos.class);
        startActivity(intent);
    }
}
