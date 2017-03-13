package com.android.carol.exemploaula01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtPrimeiro;
    EditText edtSegundo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtPrimeiro = (EditText)findViewById(R.id.editTextPrimeiro);
        edtSegundo = (EditText)findViewById(R.id.editTextSegundo);
    }

    public void copiar(View v){
        String texto = "";

        texto = edtPrimeiro.getText().toString();
        edtSegundo.setText(texto);
    }

}
