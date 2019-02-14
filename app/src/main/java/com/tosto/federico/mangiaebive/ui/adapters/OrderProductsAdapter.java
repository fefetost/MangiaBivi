package com.tosto.federico.mangiaebive.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.tosto.federico.mangiaebive.R;
import com.tosto.federico.mangiaebive.datamodels.Item;


import java.util.ArrayList;

public class OrderProductsAdapter extends RecyclerView.Adapter<OrderProductsAdapter.OrderProductViewHolder> {

    private ArrayList<Item> dataSet;
    private Context context;
    private LayoutInflater inflater;
    private float miniumOrder;


    public  OrderProductsAdapter(Context context, ArrayList<Item> dataSet,float miniumOrder){

        this.dataSet = dataSet;
        this.context = context;
        this.miniumOrder = miniumOrder;
        inflater = LayoutInflater.from(context);
    }

    public interface onItemRemovedListener{
        void onItemRemoved(float subtotal);
    }
    private onItemRemovedListener onItemRemovedListener;


    public OrderProductsAdapter.onItemRemovedListener getOnItemRemovedListener() {
        return onItemRemovedListener;
    }

    public void setOnItemRemovedListener(OrderProductsAdapter.onItemRemovedListener onItemRemovedListener) {
        this.onItemRemovedListener = onItemRemovedListener;
    }


    @NonNull
    @Override
    public OrderProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new OrderProductViewHolder(inflater.inflate(R.layout.item_shopping_cart, viewGroup, false));
    }

    private void removeItem(int index){
        dataSet.remove(index);
        notifyItemRemoved(index);

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
            AlertDialog.Builder removeAlert = new AlertDialog.Builder(context);
            removeAlert.setTitle(R.string.be_careful)
                    .setMessage(R.string.remove_title)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onItemRemovedListener.onItemRemoved(dataSet.get(getAdapterPosition()).getSubtotal());
                            removeItem(getAdapterPosition());

                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .create()
                    .show();

        }
    }
}