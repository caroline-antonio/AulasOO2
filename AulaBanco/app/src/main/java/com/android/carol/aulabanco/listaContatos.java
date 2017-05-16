package com.android.carol.aulabanco;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class listaContatos extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ContatosDB contatosDB = new ContatosDB(this);

        setListAdapter(new ContatosAdapter(this, contatosDB.listar()));
    }
}
