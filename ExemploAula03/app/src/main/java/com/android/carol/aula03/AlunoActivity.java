package com.android.carol.aula03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AlunoActivity extends AppCompatActivity {

    //criando os objetos referentes aos campos de texto que existem na activity_aluno
    public TextView txtId;
    public EditText edtNome;
    public EditText edtCGU;
    public EditText edtMatricula;

    public Aluno aluno;//objeto da classe aluno
    public static int id = 0;//variável para incrementar um valor para o id do aluno

    public static List<Aluno> listaAlunos = new ArrayList<>();//lista de alunos


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        //vincula os objetos criados aos campos de texto presentes na activity_aluno
        edtNome = (EditText)findViewById(R.id.editTextNome);
        edtCGU = (EditText)findViewById(R.id.editTextCGU);
        edtMatricula = (EditText) findViewById(R.id.editTextMatricula);
        txtId = (TextView) findViewById(R.id.textViewValorID);

        txtId.setText(String.valueOf(id));
    }

    //função que salva o registro do aluno na lista de alunos
    //esta função deve ser indicada no evento onClick do botão Salvar da activity_aluno
    public void Salvar(View v){
        aluno = new Aluno();
        //seta os dados do aluno
        aluno.setId(Integer.parseInt(txtId.getText().toString()));
        aluno.setNome(edtNome.getText().toString());
        aluno.setCgu(edtCGU.getText().toString());
        aluno.setMatricula(edtMatricula.getText().toString());

        listaAlunos.add(aluno);//adiciona o objeto aluno na lista

        Toast.makeText(this, "Aluno salvo com sucesso", Toast.LENGTH_SHORT).show();//mensagem curta
    }

    //função que limpa os campos e prepara a tela para que um novo aluno seja inserido
    //esta função deve ser indicada no evento onClick do botão Novo da activity_aluno
    public void Novo(View V){
        id++;//incrementa o id
        //limpa os campos de texto
        txtId.setText(String.valueOf(id));
        edtNome.setText("");
        edtMatricula.setText("");
        edtCGU.setText("");
    }


}
