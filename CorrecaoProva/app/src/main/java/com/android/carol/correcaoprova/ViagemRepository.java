package com.android.carol.correcaoprova;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carol on 03/05/2017.
 */

public class ViagemRepository {

    //list statico para armazenar os dados de viagem
    static public List<Viagem> list = new ArrayList<>();

    //função para retornar a média de consumo das viagens
    static public double getMediaConsumo(){
        double totalKm = 0;
        double totalLitros = 0;
        //percorre o list para calcular a média de quilometragem de todas as viagens
        for (Viagem viagem:list){
            totalKm+=viagem.getKm();
            totalLitros+=viagem.getConsumo();
        }
        //verificar e o total em litros é diferente de 0 ,
        //caso positivo divide o total de Km por total de litros
        return totalLitros!=0? totalKm/totalLitros : 0;
    }
}
