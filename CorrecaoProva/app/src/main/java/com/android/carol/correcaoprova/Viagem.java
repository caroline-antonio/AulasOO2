package com.android.carol.correcaoprova;

/**
 * Created by Carol on 03/05/2017.
 */

public class Viagem {
    private String destino;
    private double km;
    private double consumo;

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    @Override
    public String toString() {
        return this.getDestino()+ this.getKm() + "Km's";
    }
}
