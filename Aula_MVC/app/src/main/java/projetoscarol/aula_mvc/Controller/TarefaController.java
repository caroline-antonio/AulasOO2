package projetoscarol.aula_mvc.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import projetoscarol.aula_mvc.Model.Tarefa;
import projetoscarol.aula_mvc.Model.TarefaModel;

/**
 * Created by Carol on 11/05/2016.
 */
public class TarefaController {
    private TarefaModel tarefaModel;

    public TarefaController(Context context){
        tarefaModel = new TarefaModel(context);
    }

    public void inserirTarefa(Tarefa tarefa) throws Exception{
        final ContentValues valores = new ContentValues();
        valores.put("tarefa", tarefa.getTarefa());
        tarefaModel.inserir(valores);
    }
    public void deletarTarefa(int ID) throws Exception{
        tarefaModel.deletar("" + ID);
    }

    public Cursor listarTarefas(){
        Cursor c = tarefaModel.listar();
        return c;
    }
}
