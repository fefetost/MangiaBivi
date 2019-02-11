package com.tosto.federico.mangiaebive.ui.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.tosto.federico.mangiaebive.R;
import com.tosto.federico.mangiaebive.ui.adapters.RestaurantAdapter;
import com.tosto.federico.mangiaebive.datamodels.Restaurant;

import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity {

    private static final String view_key = "view_KEY";
    private static final String file_key = "com.tosto.federico.mangiaebive.key";
    RecyclerView recycler;
    RecyclerView.LayoutManager layoutManager;
    RestaurantAdapter adapter;
    ArrayList<Restaurant> arrayList;
    boolean grid = true;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        recycler = findViewById(R.id.recycleview);
        sharedPreferences = this.getSharedPreferences(file_key, MODE_PRIVATE);
        grid = sharedPreferences.getBoolean(view_key, false);
        adapter = new RestaurantAdapter(this, getData(), grid);
        setLayoutManager();
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(layoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_grid_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.switch_view) {
            if (getLayoutManager()) {
                item.setIcon(R.drawable.ic_view_list_black_24dp);
            } else {
                item.setIcon(R.drawable.ic_view_module_black_24dp);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Restaurant> getData() {
        Restaurant McDonald = new Restaurant("McDonald's", "Via Tiburtina n.8", 8.0F, R.drawable.mc_donalds);
        Restaurant BurgerKing = new Restaurant("Burger King", "Via Tiburtina n.50", 4.0F, R.drawable.burger_king);
        Restaurant KFC = new Restaurant("KFC", "Via Nomentana n.43", 14.0F, R.drawable.kfc);
        Restaurant RossoPomodoro = new Restaurant("Rosso Pomodoro", "Via Sandro Sandri n.90", 5.0F, R.drawable.rosso_pomodoro);

        arrayList = new ArrayList<>();
        arrayList.add(McDonald);
        arrayList.add(BurgerKing);
        arrayList.add(KFC);
        arrayList.add(RossoPomodoro);

        return arrayList;

    }

    private boolean getLayoutManager() {
        if (adapter.isGridMode()) {
            layoutManager = new LinearLayoutManager(this);
            grid = false;

        } else {
            layoutManager = new GridLayoutManager(this, 2);
            grid = true;
        }
        adapter.setGridMode(!adapter.isGridMode());
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);
        saveLayoutManager(grid);
        return grid;
    }

    private void setLayoutManager() {
        layoutManager = adapter.isGridMode() ? new GridLayoutManager(this, 2) : new LinearLayoutManager(this);
    }

    private void saveLayoutManager(boolean grid) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(view_key, grid);
        if (editor.commit()) {
            Utility.showToast(this, "Salvato");
        }
    }
}

