package com.tosto.federico.mangiaebive.datamodels;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.annotation.DrawableRes;

import com.tosto.federico.mangiaebive.ui.adapters.ItemAdapter;

public class Restaurant {
    private String via;
    private String nome;
    private float prezzo;
    private String url;
    @DrawableRes
    private int uri;


    private ArrayList<Item> products = new ArrayList<Item>();
    ItemAdapter itemAdapter;


    public Restaurant(String nome, String via, float prezzo, @DrawableRes int uri) {
        this.via = via;
        this.nome = nome;
        this.prezzo = prezzo;
        this.uri = uri;

    }

    public Restaurant(JSONObject jsonRestaurant) throws JSONException {
        nome = jsonRestaurant.getString("name");
        via = jsonRestaurant.getString("address");
        prezzo = Float.valueOf(jsonRestaurant.getString("min_order"));
        url = jsonRestaurant.getString("image_url");
        //products = jsonRestaurant.getJSONArray("products");
        try {
            JSONArray itemJsonArray = jsonRestaurant.getJSONArray("products");
            for (int i = 0; i < itemJsonArray.length(); i++) {
                Item item = new Item(itemJsonArray.getJSONObject(i));
                products.add(item);
            }
            itemAdapter.setData(products);

        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUri(int uri) {
        this.uri = uri;
    }

    public ArrayList<Item> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Item> products) {

        this.products = products;
    }
}
