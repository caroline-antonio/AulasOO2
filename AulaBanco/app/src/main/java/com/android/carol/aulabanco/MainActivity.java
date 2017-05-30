package com.android.carol.aulabanco;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtTelefone;

    public String operacao = "novo";//indica se ao salvar será um novo registro ou edição

    Contatos contatos;
    ContatosDB contatosDB;

    //criando os objetos referentes aos botões de texto que existem na activity_main
    public Button btnNovo;
    public Button btnSalvar;
    public Button btnListar;
    public Button btnExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vincula os objetos criados aos botões presentes na activity_main
        edtNome = (EditText)findViewById(R.id.edtNome);
        edtTelefone = (EditText) findViewById(R.id.edtTelefone);

        //vincula os objetos criados aos botões presentes na activity_main
        btnNovo = (Button) findViewById(R.id.buttonNovo);
        btnSalvar = (Button)findViewById(R.id.buttonSalvar);
        btnListar =(Button)findViewById(R.id.buttonListar);
        btnExcluir =(Button)findViewById(R.id.buttonExcluir);

        contatosDB = new ContatosDB(this);

        //cria um objeto do tipo intent para receber os dados que possam ter vindo na chamada da activity
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();//cria um objeto bundle para receber os dados extras que possam ter sido enviados na  chamada da activity
            if (bundle != null) {//se houverem extras entra no if (em caso de edição são enviados os dados do contato )

                contatos = new Contatos();//instancia o objeto contato

                //seta os dados do contato com os dados enviados
                contatos.set_id(bundle.getInt("id"));
                contatos.setNome(bundle.getString("nome"));
                contatos.setTelefone(bundle.getString("telefone"));

                //preenche os campos da tela da activity_main com os dados do contato
                edtNome.setText(contatos.getNome());
                edtTelefone.setText(contatos.getTelefone());

                operacao = "editar";//seta a operação como edição
            }
        }
    }

    //função que salva o registro do contato
    //esta função deve ser indicada no evento onClick do botão Salvar da activity_main
    public  void Salvar(View v){

        try {
            if (operacao.equals("editar")){//ao salvar verifica se é edição
                //seta os novos valores no objeto contato
                contatos.setNome(edtNome.getText().toString());
                contatos.setTelefone(edtTelefone.getText().toString());

                contatosDB.atualizar(contatos);

            }else{//se for novo
                contatos = new Contatos();//instancia novo objeto contato
                //seta os dados do contato
                contatos.setNome(edtNome.getText().toString());
                contatos.setTelefone(edtTelefone.getText().toString());

                contatosDB.inserir(contatos);//adiciona o objeto contato na lista

            }
            Toast.makeText(MainActivity.this, "Contato salvo com sucesso", Toast.LENGTH_LONG).show();
            configuraBotoes("salvar");//chama as configurações do botão

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

    //função para excluiro registro de um contato
    //esta função deve ser indicada no evento onClick do botão Excluir da activity_main
    public void Excluir(final View view) {

        //criando uma mensagem do tipo AlertDialog com dois botões "Sim" e "Cancelar" para confirmar a exclusão
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Exclusão de Contato");
        alertDialog.setMessage("Tem certeza que deseja excluir o contato: " + contatos.getNome() + "?");
        alertDialog.setPositiveButton("Sim",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //chama o método de exclusão
                        try {
                            contatosDB.deletar(contatos);
                            Novo(view);
                            Toast.makeText(MainActivity.this, "Contato Excluido com Sucesso!", Toast.LENGTH_SHORT).show();//mensagem curta

                        }catch (Exception e){
                            Toast.makeText(MainActivity.this, "Ocorreu um problema ao excluir o contrato!", Toast.LENGTH_SHORT).show();//mensagem curta
                        }

                    }
                });

        alertDialog.setNeutralButton("Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();//comando para exibir a mensagem
    }

    //função que limpa os campos e prepara a tela para que um novo contato seja inserido
    //esta função deve ser indicada no evento onClick do botão Novo da activity_main
    public void Novo(View view){
        //limpa os campos de texto
        edtNome.setText("");
        edtTelefone.setText("");
        operacao ="novo";//seta a operação para novo
        configuraBotoes("novo");//chama as configurações de botão
    }
    //função para configurar os botões da tela para que somente fiquem habilitados quando
    //necessário de acordo com a operação solicitada pelo usuário (novo, salvar, listar ou excluir)
    public void configuraBotoes (String operacao){
        switch (operacao){//verifica o tipo de operação
            case "novo":
                btnNovo.setEnabled(false);
                btnSalvar.setEnabled(true);
                btnListar.setEnabled(false);
                btnExcluir.setEnabled(false);
                break;

            case "salvar":
                btnNovo.setEnabled(true);
                btnSalvar.setEnabled(false);
                btnListar.setEnabled(true);
                btnExcluir.setEnabled(true);
                break;

            case "excluir":
                btnNovo.setEnabled(true);
                btnSalvar.setEnabled(false);
                btnListar.setEnabled(true);
                btnExcluir.setEnabled(false);
                break;
        }

    }

}
