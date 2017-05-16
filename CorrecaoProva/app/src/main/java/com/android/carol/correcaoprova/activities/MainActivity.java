package com.android.carol.correcaoprova.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.carol.correcaoprova.R;

public class MainActivity extends AppCompatActivity {

    Button btEnviar;
    EditText etFullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btEnviar = (Button) findViewById(R.id.btEnviar);
        etFullname = (EditText) findViewById(R.id.etFullname);

        //evento onClickListener do botao enviar
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //retornou o nome do campo fullname
                String fullname = etFullname.getEditableText().toString();

                //se o campo de nome estiver vazio, exibe mensagem e sai do método
                if (fullname.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Não pode estar vazio!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // se o nome não estvier vazio vai startar a activity boasVindas,
                // chamando o método  getActivtyIntent para retornar a intent passando o
                // fullname por parâmetro
                startActivity(BoasVindasActivity.getActivtyIntent(MainActivity.this,fullname));
            }
        });
    }
}
