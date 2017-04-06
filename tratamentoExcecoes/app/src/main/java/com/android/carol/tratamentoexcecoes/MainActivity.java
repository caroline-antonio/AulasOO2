package com.android.carol.tratamentoexcecoes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        textView = (TextView)findViewById(R.id.textView);
    }

    public void erro(View v){

        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.radioButton:
                textView.setText("Opção A selecionada");
                break;
            case R.id.radioButton2:
                textView.setText("Opção B selecionada");
                break;

        }
    }
}
