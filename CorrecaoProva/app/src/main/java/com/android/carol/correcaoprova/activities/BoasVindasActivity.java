package com.android.carol.correcaoprova.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.carol.correcaoprova.R;
import com.android.carol.correcaoprova.Viagem;
import com.android.carol.correcaoprova.ViagemRepository;

public class BoasVindasActivity extends AppCompatActivity {

    Bundle b;
    TextView tvGlobinho;
    private EditText edtTextDestino;
    private EditText edtTextQuilometragem;
    private EditText edtTextConsumo;
    private Button botaoSalvar;
    private Button botaoListar;
    private Button botaoCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boas_vindas);

        tvGlobinho = (TextView) findViewById(R.id.tvGlobinho);
        //verifica se há infromações extras sendo enviadas para a intent
        if(getIntent().hasExtra("fullname")){
            //se tiver extras, seta no textView
            tvGlobinho.setText(getIntent().getStringExtra("fullname")
            );
        }else{
            finish();
        }

        edtTextDestino = (EditText) findViewById(R.id.edt_text_destino);
        edtTextQuilometragem = (EditText) findViewById(R.id.edt_text_quilometragem);
        edtTextConsumo = (EditText) findViewById(R.id.edt_text_consumo);
        botaoSalvar = (Button)findViewById(R.id.botao_salvar);
        botaoListar = (Button)findViewById(R.id.botao_listar);
        botaoCalcular = (Button)findViewById(R.id.botao_calcular_consumo);

        //cria o evento onClickListener para o botaoSalvar
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //instancia o objeto viagem e seta os dados
                Viagem viagem = new Viagem();
                viagem.setDestino(edtTextDestino.getText().toString());
                viagem.setKm(Double.parseDouble(edtTextQuilometragem.getText().toString()));
                viagem.setConsumo(Double.parseDouble(edtTextConsumo.getText().toString()));

                //adicionar o objeto viagem ao list
                ViagemRepository.list.add(viagem);

                //exibe mensagem de confirmação
                Toast.makeText(BoasVindasActivity.this, "Viagem Cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                limpaFields();
            }
        });

        //evento click do botão listar chama
        botaoListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BoasVindasActivity.this, ListaViagensActivity.class));
            }
        });

        //evento click do botao calcular
        botaoCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BoasVindasActivity.this, "A media de consumo é: " + ViagemRepository.getMediaConsumo(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //método para retornar a intent e passar as informações extras do nome do usuário
    public static Intent getActivtyIntent(Context context, String fullname){
        return new Intent(context,
                BoasVindasActivity.class)
                .putExtra("fullname",fullname);
    }

    //limpar os campos
    public void limpaFields(){
        edtTextDestino.setText("");
        edtTextConsumo.setText("");
        edtTextQuilometragem.setText("");
    }
}
