package projetoscarol.aula_mvc.View;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import projetoscarol.aula_mvc.Controller.TarefaController;
import projetoscarol.aula_mvc.Model.Tarefa;
import projetoscarol.aula_mvc.R;

public class MainActivity extends AppCompatActivity {
   //declara os objetos que serão linkados com os componentes da tela activity_main
    private ListView lvTarefa;
    private Button btNovaTarefa;
    private EditText etNovaTarefa;

    //cria um objeto controller da Tarefa
    private TarefaController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.controller = new TarefaController(this);//instancia o objeto controller

        //vincula os objetos aos componentes de tela
        this.lvTarefa = (ListView) findViewById(R.id.lvTarefa);
        this.btNovaTarefa = (Button) findViewById(R.id.btNovaTarefa);
        this.etNovaTarefa = (EditText) findViewById(R.id.etNovaTarefa);

        //chama o método populaTarefas para mostrar na tela as tarefas salva
        this.populaTarefas();
    }


    //este método busca as tarefas salvas em banco e popula o listView da tela de activity_main
    private void populaTarefas()
    {

        String[] nomeCampos={"TAREFA"};//vetor contendo a lista de campos a serem populados (indicar nome das colunas que serão retornadas do banco)
        int[] idView = new int[]{R.id.textViewTarefa};//vetor que armazena o id dos campos, contidos em tela, que irão receber os dados
        final Cursor cursor=controller.listarTarefas();//cursor que recebe o resultado do método listarTarefas

        // criando um adaptador para popular o listView em tela. Por parâmetros, são informados o contexto, o listView da tela, o cursor com o resultados
        //da consulta em banco, a lista de campos e lista de ids.
        SimpleCursorAdapter adaptador = new SimpleCursorAdapter(this, R.layout.list_tarefas,cursor,nomeCampos,idView,0);

        lvTarefa=(ListView)findViewById(R.id.lvTarefa);//linka o objeto de listView com o listView da tela
        lvTarefa.setAdapter(adaptador);//seta o Adapter do listView com o adapter que foi criado

        //Setando o evento OnItemClickListener do listView para excluir o item quando clicado nele
        this.lvTarefa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cursor.moveToPosition(position);//move o cursor para a posição clicada no list
                try {
                    //chama o método deletarTarefa do controller passando o ID da tarefa como parâmetro
                    controller.deletarTarefa(cursor.getInt(cursor.getColumnIndex("_id")));
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Ocorreu um erro ao Excluir a tarefa! \n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
                populaTarefas();
            }
        });
    }

    //metodo acionado quado clicado no botão de nova tarefa na tela
    public void novaTarefa(View v){
        Tarefa tarefa  = new Tarefa();//criando um novo objeto de Tarefa
        tarefa.setTarefa(etNovaTarefa.getText().toString());//seta a tarefa com a descrição da tarefa cotida no editText etNovaTarefa
        try {
            this.controller.inserirTarefa(tarefa);//chama o método de inserirTarefa do controller

            this.populaTarefas();//chama o metodo populaTarefa para que a nova tarefa apareça na lista em tela
        } catch (Exception e){
            Toast.makeText(MainActivity.this, "Ocorreu um erro ao Salvar a tarefa! \n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

    }

}
