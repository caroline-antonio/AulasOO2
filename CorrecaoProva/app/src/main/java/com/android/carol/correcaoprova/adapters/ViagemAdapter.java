package com.android.carol.correcaoprova.adapters;

import android.app.Activity;
import android.content.Context;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.carol.correcaoprova.R;
import com.android.carol.correcaoprova.Viagem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carol on 03/05/2017.
 */

public class ViagemAdapter extends ArrayAdapter{

    private Activity activity;
    private List<Viagem> viagens;
    private LayoutInflater inflater = null;

    public ViagemAdapter(@NonNull Activity activity, @LayoutRes int resource, List<Viagem> viagens) {
        super(activity, resource, viagens);
        try {
            this.activity = activity;
            this.viagens = viagens;//list de viagens
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }catch (Exception e){

        }
    }

    @Override
    public int getCount() {
        return viagens.size();
    }

    @Nullable
    @Override
    public Viagem getItem(int position) {
        return viagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{
        public TextView tvConsumo;
        public TextView tvKm;
        public TextView tvDestino;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        try {
            if(convertView == null){
                s
                view = inflater.inflate(R.layout.list_viagem_item, null);
                holder = new ViewHolder();

                holder.tvDestino = (TextView) view.findViewById(R.id.tvDestino);
                holder.tvKm = (TextView) view.findViewById(R.id.tvKm);
                holder.tvConsumo = (TextView) view.findViewById(R.id.tvConsumo);

                view.setTag(view);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            holder.tvDestino.setText(viagens.get(position).getDestino());
            holder.tvConsumo.setText(String.valueOf(viagens.get(position).getConsumo()) + "Litros");
            holder.tvKm.setText(String.valueOf(viagens.get(position).getKm()) + "Km");

        }catch (Exception e){

        }
        return view;
    }

}
