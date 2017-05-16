package com.android.carol.aulabanco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carol on 10/05/2017.
 */

public class ContatosDB {

    private SQLiteDatabase sqLiteDB;//cria um objeto de acesso a banco de dados

    public ContatosDB(Context context){
        sqLiteDB = new DataBaseHelper(context).getWritableDatabase();//instacia a classe DataBaseHelper e chama o método getWritableDatabase para retornar uma DataBase com permissão de escrita
    }

    //método para inserir dados na tabela de contatos
    public void inserir(Contatos contatos) throws Exception{
        ContentValues valores = new ContentValues();

        valores.put("nome", contatos.getNome());
        valores.put("telefone", contatos.getTelefone());
        sqLiteDB.insert("contatos",null, valores);
    }

    //método para listar dados da tabela contato
    public List<Contatos> listar(){
        List <Contatos> list = new ArrayList<>();

        String[] colunas = new String []{"_id", "nome", "telefone"};

        Cursor cursor = sqLiteDB.query("contatos", colunas, null, null, null, null, "nome ASC");

        if (cursor.getCount()>0){
            cursor.moveToFirst();

            do {
                Contatos contato = new Contatos();
                contato.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
                contato.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                contato.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                list.add(contato);
            }while (cursor.moveToNext());

        }

        return list;
    }

}
