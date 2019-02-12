package com.tosto.federico.mangiaebive.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tosto.federico.mangiaebive.R;
import com.tosto.federico.mangiaebive.datamodels.Item;
import com.tosto.federico.mangiaebive.datamodels.Order;
import com.tosto.federico.mangiaebive.datamodels.Restaurant;
import com.tosto.federico.mangiaebive.ui.adapters.OrderProductsAdapter;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView restaturantTv, restaurantAdress, totalTv, restaurantminord;
    private RecyclerView productRv;
    private Button payBtn;
    private LinearLayoutManager layoutManager;
    private OrderProductsAdapter adapter;
    private ImageView restaurantIv;

    private Order order;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shopping_cart);

        restaturantTv = findViewById(R.id.shopnametc);
        restaurantAdress = findViewById(R.id.shopaddresstc);
        restaurantminord = findViewById(R.id.minimumordertc);
        totalTv = findViewById(R.id.total_tv);
        productRv = findViewById(R.id.product_rvr);
        payBtn = findViewById(R.id.pay_btn);
        restaurantIv = findViewById(R.id.imagecard);

        order = getOrder();

        layoutManager = new LinearLayoutManager(this);
        productRv.setLayoutManager(layoutManager);
        adapter = new OrderProductsAdapter(this, order.getProducts());
        productRv.setAdapter(adapter);
        payBtn.setOnClickListener(this);

        bindData();

    }


    private void bindData() {
        restaturantTv.setText(order.getRestaurant().getNome());
        restaurantAdress.setText(order.getRestaurant().getVia());
        restaurantminord.setText(String.valueOf(order.getRestaurant().getPrezzo()));
        totalTv.setText(String.valueOf(order.getTotal()));
        Glide.with(this).load(order.getRestaurant().getUri()).into(restaurantIv);

    }


    //TODO hardcoded
    private Order getOrder() {

        Order mockOrder = new Order();
        mockOrder.setProducts(getProducts());
        mockOrder.setRestaurant(getRestaurant());
        mockOrder.setTotal(30.00f);

        return mockOrder;
    }


    private Restaurant getRestaurant() {
        return new Restaurant("Burger King","Via Tiburtina",20,R.drawable.burger_king);
    }

    //TODO hardcoded
    private ArrayList<Item> getProducts() {
        ArrayList<Item> products = new ArrayList<>();
        products.add(new Item("McMenu", 7,8));
        products.add(new Item("McMenu", 7,8));
        products.add(new Item("McMenu", 7,8));
        products.add(new Item("McMenu", 7,8));
        products.add(new Item("McMenu", 7,8));
        products.add(new Item("McMenu", 7,8));
        return products;

    }

    @Override
    public void onClick(View view) {
        //TODO Click
    }
}