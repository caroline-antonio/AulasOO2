package projetoscarol.aula_mvc.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Carol on 11/05/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TAREFA";//nome da base de dados
    private static final int DATABASE_VERSION = 1;//versão da base de dados
    private static String TABLE_TAREFAS = "CREATE TABLE TAREFAS" +
            "(_id INTEGER PRIMARY KEY AUTOINCREMENT, TAREFA TEXT);";//script para criação da tabela de contatos

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME, null,DATABASE_VERSION );//envia o nome da base de dados e versão para o contrutor da classe pai "SQLiteOpenHelper" para criação do banco
    }


    public void onCreate (SQLiteDatabase db){
        db.execSQL(TABLE_TAREFAS); //executa o SQL de criação da tabela
    }

    //o método onUpgrade é chamado qunado é identificado que a versão da base de dados foi incrementada
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2){
        db.execSQL("DROP TABLE TAREFAS;");//executar comandos necessários para atualização da base
        onCreate(db);
    }
}

