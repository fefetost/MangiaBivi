package com.tosto.federico.mangiaebive.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tosto.federico.mangiaebive.R;
import com.tosto.federico.mangiaebive.datamodels.Restaurant;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private ArrayList<Restaurant> restaurant;
    private Context context;
    private boolean isGridMode;


    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurant, boolean isGridMode) {
        this.inflater = LayoutInflater.from(context);
        this.restaurant = restaurant;
        this.isGridMode = isGridMode;
    }
    public RestaurantAdapter(Context context, boolean isGridMode){
        this.inflater = LayoutInflater.from(context);
        this.restaurant = new ArrayList<>();
        this.context = context;
        this.isGridMode = isGridMode;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (!isGridMode) {
            view = inflater.inflate(R.layout.item_restaurant, parent, false);
        } else {
            view = inflater.inflate(R.layout.item_restaurant_grid, parent, false);
        }
        return new RestaurantViewHolder(view);
    }

    public void setGridMode(boolean isGridMode) {
        this.isGridMode = isGridMode;
    }

    public boolean isGridMode() {
        return isGridMode;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RestaurantViewHolder restaurantViewHolder = (RestaurantViewHolder) holder;
        restaurantViewHolder.nome.setText(restaurant.get(position).getNome());
        restaurantViewHolder.via.setText(restaurant.get(position).getVia());
        restaurantViewHolder.prezzo.setText("" + restaurant.get(position).getPrezzo());
        Glide.with(context).load(restaurant.get(position).getUrl()).into(restaurantViewHolder.immagine);
    }

    @Override
    public int getItemCount() {
        return restaurant.size();
    }

    public void setData(ArrayList<Restaurant> data) {
        this.restaurant = data;
        notifyDataSetChanged();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public TextView nome;
        public TextView via;
        public TextView prezzo;
        public ImageView immagine;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.textcard1);
            via = itemView.findViewById(R.id.textcard2);
            prezzo = itemView.findViewById(R.id.textcard3);
            immagine = itemView.findViewById(R.id.imagecard);

        }
    }
}
