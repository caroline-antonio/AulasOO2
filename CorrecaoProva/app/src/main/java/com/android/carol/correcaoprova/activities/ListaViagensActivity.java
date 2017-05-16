package com.android.carol.correcaoprova.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.carol.correcaoprova.R;
import com.android.carol.correcaoprova.ViagemRepository;
import com.android.carol.correcaoprova.adapters.ViagemAdapter;

public class ListaViagensActivity extends AppCompatActivity {

    private ListView lvViagens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_viagens);

        lvViagens = (ListView) findViewById(R.id.lvViagens);
        ViagemAdapter adapter = new ViagemAdapter(ListaViagensActivity.this, R.layout.activity_lista_viagens, ViagemRepository.list);
        lvViagens.setAdapter(adapter);
    }
}
