package com.android.carol.aulabanco;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Carol on 10/05/2017.
 */

public class ContatosAdapter extends BaseAdapter{
    private Context context;
    private List<Contatos> list;

    public ContatosAdapter(Context context, List<Contatos> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).get_id();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final int auxPosition = position;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final RelativeLayout layout = (RelativeLayout)inflater.inflate(R.layout.lista_contatos, null);

        TextView txtNome = (TextView)layout.findViewById(R.id.txtNomeContato);
        txtNome.setText(list.get(position).getNome());

        txtNome.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View arg0) {//cria uma ação para o evento onClickListener do botão
                Intent intent = new Intent(context, MainActivity.class);//cria uma intent para chamar a tela de cadastro de contatos
                intent.putExtra("nome", list.get(auxPosition).getNome());//envia o nome do contato para o MainActivity
                intent.putExtra("telefone", list.get(auxPosition).getTelefone());//envia o telefone do contato para a MainActivity
                intent.putExtra("id", list.get(auxPosition).get_id());//envia o id do contato para a MainActivity
                context.startActivity(intent);//starta a activity
            }
        });

        return layout;
    }
}
