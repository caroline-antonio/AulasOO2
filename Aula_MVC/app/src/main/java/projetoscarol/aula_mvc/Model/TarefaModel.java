package projetoscarol.aula_mvc.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import projetoscarol.aula_mvc.BD.DataBaseHelper;

/**
 * Created by Carol on 11/05/2016.
 */
public class TarefaModel {

    private SQLiteDatabase sqLite;

    public TarefaModel(Context context){
        sqLite = new DataBaseHelper(context).getWritableDatabase();
    }

    public void inserir(ContentValues valores) throws Exception{
        sqLite.insert("Tarefas", null, valores);
    }

    public Cursor listar(){
        String[] colunas = new String[] {"_id", "TAREFA"};
        Cursor cursor = sqLite.query("Tarefas", colunas, null, null, null, null, null);
        return cursor;
    }

    public void deletar(String argumento)throws Exception{
        String where = "_id=?";
        String[] argumentos = new String[]{argumento};
        sqLite.delete("Tarefas", where, argumentos);
    }

}
