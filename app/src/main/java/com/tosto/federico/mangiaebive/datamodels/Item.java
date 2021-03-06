package com.tosto.federico.mangiaebive.datamodels;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Item {
    private int quantita;
    private String nome;
    private float prezzo;

    public Item(String nome, int quantita, float prezzo) {
        this.quantita = quantita;
        this.nome = nome;
        this.prezzo = prezzo;
    }
    public Item(JSONObject jsonRestaurant) throws JSONException {
        nome = jsonRestaurant.getString("name");
        quantita = 0;
        prezzo = Float.valueOf(jsonRestaurant.getString("price"));
    }

    public int getQuantita() {
        return quantita;
    }

    public void setquantita(int quantita) {
        this.quantita = quantita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public void increaseQuantita() {
        quantita++;
    }

    public void decreaseQuantita() {
        if (quantita == 0) return;
        quantita--;
    }

    public float getSubtotal() {
        return quantita * prezzo;
    }
}
