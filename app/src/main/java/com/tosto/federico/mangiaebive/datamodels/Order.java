package com.tosto.federico.mangiaebive.datamodels;

import java.util.ArrayList;

public class Order {

    private Restaurant restaurant;
    private ArrayList<Item> products;
    private float total;

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<Item> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Item> products) {
        this.products = products;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}