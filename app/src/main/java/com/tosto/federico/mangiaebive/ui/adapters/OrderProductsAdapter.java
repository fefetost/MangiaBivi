package com.tosto.federico.mangiaebive.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tosto.federico.mangiaebive.R;
import com.tosto.federico.mangiaebive.datamodels.Item;


import java.util.ArrayList;

public class OrderProductsAdapter extends RecyclerView.Adapter<OrderProductsAdapter.OrderProductViewHolder> {

    private ArrayList<Item> dataSet;
    private Context context;
    private LayoutInflater inflater;


    public OrderProductsAdapter(Context context, ArrayList<Item> dataSet) {

        this.dataSet = dataSet;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public OrderProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new OrderProductViewHolder(inflater.inflate(R.layout.item_shopping_cart, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProductViewHolder orderProductViewHolder, int i) {
        Item product = dataSet.get(i);
        orderProductViewHolder.productNameTv.setText(product.getNome());
        orderProductViewHolder.quantityTv.setText(String.valueOf(product.getQuantita()));
        orderProductViewHolder.subtotalTv.setText(String.valueOf(product.getSubtotal()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class OrderProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView quantityTv, productNameTv, subtotalTv;
        public ImageButton removeBtn;


        public OrderProductViewHolder(@NonNull View itemView) {
            super(itemView);
            quantityTv = itemView.findViewById(R.id.quantity_tv);
            productNameTv = itemView.findViewById(R.id.product_name_tv);
            subtotalTv = itemView.findViewById(R.id.subtotal_tv);
            removeBtn = itemView.findViewById(R.id.remove_btn);
            removeBtn.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            //TODO alertDialog
            dataSet.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
        }
    }
}