package com.tosto.federico.mangiaebive.ui.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import com.tosto.federico.mangiaebive.R;
import com.tosto.federico.mangiaebive.datamodels.Item;
import com.tosto.federico.mangiaebive.datamodels.Restaurant;
import com.tosto.federico.mangiaebive.ui.adapters.ItemAdapter;



public class CheckoutActivity extends AppCompatActivity implements ItemAdapter.OnQuantityChangedListener {

    // UI components
    private TextView shopNameTv, shopAddress, totalTxtView;
    private Button checkout;
    private ProgressBar progressBar;
    private ImageView restaurantIv;

    // RecyclerView components
    private RecyclerView productRv;
    private LinearLayoutManager layoutManager;
    private ItemAdapter adapter;


    // data model
    private Restaurant restaurant;


    private float total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        shopNameTv = findViewById(R.id.shopnametc);
        shopAddress = findViewById(R.id.shopaddresstc);
        totalTxtView = findViewById(R.id.totale);
        restaurantIv = findViewById(R.id.imagecard);

        checkout = findViewById(R.id.checkout_btn);
        progressBar = findViewById(R.id.progress_bar);
        productRv = findViewById(R.id.recycleview_checkout);

        binData();
        layoutManager = new LinearLayoutManager(this);
        adapter = new ItemAdapter(this, restaurant.getProducts());
        adapter.setOnQuantityChangedListener(this);
        productRv.setLayoutManager(layoutManager);
        ((SimpleItemAnimator) productRv.getItemAnimator()).setSupportsChangeAnimations(false);
        productRv.setAdapter(adapter);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckoutActivity.this, ShoppingCartActivity.class));
            }
        });

        enableBttuon();

    }

    //TODO there is some hardcoding inside
    private void binData() {

        restaurant = getRestaurant();
        restaurant.setUri(R.drawable.burger_king);
        restaurant.setProducts(getData());
        shopNameTv.setText(restaurant.getNome());
        shopAddress.setText(restaurant.getVia());
        Glide.with(this).load(restaurant.getUri()).into(restaurantIv);
        progressBar.setMax((int) restaurant.getPrezzo() * 100);

    }

    //TODO hardcoded
    private ArrayList<Item> getData() {
        ArrayList<Item> arrayList = new ArrayList<>();
        Item Panin1 = new Item("Hamburger", 0, 8.0F);
        Item Panin2 = new Item("Hamburger", 0, 2.0F);
        Item Panin3 = new Item("Hamburger", 0, 6.0F);
        Item Panin4 = new Item("Hamburger", 0, 3.0F);
        arrayList.add(Panin1);
        arrayList.add(Panin2);
        arrayList.add(Panin3);
        arrayList.add(Panin4);

        return arrayList;

    }
    //TODO hardcoded
    private Restaurant getRestaurant() {
        return new Restaurant("Burger King", "Via Tiburtina", 10, R.drawable.burger_king);
    }

    void UpdateProgressBar(int progress) {
        progressBar.setProgress(progress);
    }

    void UpdateTotal(float price) {
        total = total + price;
        totalTxtView.setText(String.valueOf(total).concat(" €"));
    }


    private void enableBttuon() {
        checkout.setEnabled(total >= restaurant.getPrezzo());
    }

    @Override
    public void onChange(float price) {
        UpdateTotal(price);
        UpdateProgressBar((int) total * 100);
        enableBttuon();
    }
}