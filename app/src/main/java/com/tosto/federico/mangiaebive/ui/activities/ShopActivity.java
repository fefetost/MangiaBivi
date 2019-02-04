package com.tosto.federico.mangiaebive.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tosto.federico.mangiaebive.R;
import com.tosto.federico.mangiaebive.ui.adapters.RestaurantAdapter;
import com.tosto.federico.mangiaebive.datamodels.Restaurant;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    RecyclerView recycler;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        recycler= findViewById(R.id.recycleview);
        layoutManager = new LinearLayoutManager(this);
        adapter= new RestaurantAdapter(this,getData());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(layoutManager);
    }

    private ArrayList<Restaurant> getData(){
        Restaurant McDonald= new Restaurant("McDonald's","Via Tiburtina n.8",8.0F, R.drawable.mc_donalds);
        Restaurant BurgerKing= new Restaurant("Burger King","Via Tiburtina n.50",4.0F,R.drawable.burger_king);
        Restaurant KFC= new Restaurant("KFC","Via Nomentana n.43",14.0F, R.drawable.kfc);
        Restaurant RossoPomodoro = new Restaurant("Rosso Pomodoro","Via Sandro Sandri n.90",5.0F,R.drawable.rosso_pomodoro);

        arrayList= new ArrayList<>();
        arrayList.add(McDonald);
        arrayList.add(BurgerKing);
        arrayList.add(KFC);
        arrayList.add(RossoPomodoro);

        return arrayList;

    }
}
