package com.tosto.federico.mangiaebive.datamodels;


import android.net.Uri;
import android.support.annotation.DrawableRes;

public class Restaurant {
    private String via;
    private String nome;
    private float prezzo;
    @DrawableRes
    public int uri;

    public Restaurant(String nome, String via, float prezzo , @DrawableRes int uri) {
        this.via = via;
        this.nome = nome;
        this.prezzo = prezzo;
        this.uri = uri;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
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

    public int getUri() {
        return uri;
    }

    public void setUri(int uri) {
        this.uri = uri;
    }
}
