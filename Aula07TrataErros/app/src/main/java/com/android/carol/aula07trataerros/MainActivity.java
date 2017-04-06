package com.android.carol.aula07trataerros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //vincula o objeto RadioGroup ao RadioGroup do arquivo XML
        radioGroup = (RadioGroup)findViewById(R.id.radioGroupErros);
    }

    public void erro(View v){

        try {
            //testa qual o ID do Radio Button que está checado no grupo
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.radioButtonArray:
                    //força um erro de ArrayIndezOutOfBouds
                    List<String> meuArray = new ArrayList<>();
                    meuArray.add("algo");

                    meuArray.get(-1);
                    break;
                case R.id.radioButtonAri:
                        //implementar demais exceptions
                    break;

            }
        }catch (ArrayIndexOutOfBoundsException e){
            //Exibe uma mensagem para o usuário com o texto padrão criado no arquivo String.XML concatenado ao texto que descreve o erro.
            Toast.makeText(this, getString(R.string.trataErro)+ "\n Índice da matriz fora dos limites.", Toast.LENGTH_SHORT).show();
        }
    }


}
