package com.android.carol.aulabanco;

import android.app.usage.ConfigurationStats;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Carol on 10/05/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Agenda";//NOME DA BASE DE DADOS
    private static final int DATABASE_VERSION = 1;//VERSÃO
    private static String TABLE_CONTATOS = "CREATE TABLE contatos(" +
            " _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome TEXT, telefone TEXT);"; //script para criação da tabela de contatos

    DataBaseHelper(Context context){
        //envia o nome da base de dados e versão para o contrutor da classe pai "SQLiteOpenHelper" para criação do banco
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //executa o SQL de criação da tabela
        sqLiteDatabase.execSQL(TABLE_CONTATOS);
    }

    @Override
    //o método onUpgrade é chamado qunado é identificado que a versão da base de dados foi incrementada
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE contatos");//executar comandos necessários para atualização da base
        onCreate(sqLiteDatabase);
    }
}
