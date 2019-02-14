package com.tosto.federico.mangiaebive.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tosto.federico.mangiaebive.R;
import com.tosto.federico.mangiaebive.datamodels.Item;
import com.tosto.federico.mangiaebive.datamodels.Restaurant;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private ArrayList<Item> item;
    private OnQuantityChangedListener onQuantityChangedListener;

    public OnQuantityChangedListener getOnQuantityChangedListener() {
        return onQuantityChangedListener;
    }

    public void setOnQuantityChangedListener(OnQuantityChangedListener onQuantityChangedListener) {
        this.onQuantityChangedListener = onQuantityChangedListener;
    }

    public ItemAdapter(Context context, ArrayList<Item> item) {
        this.inflater = LayoutInflater.from(context);
        this.item = item;
    }

    public void setData(ArrayList<Item> data) {
        this.item = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_checkout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.nome.setText(item.get(position).getNome());
        itemViewHolder.quantita.setText(String.valueOf(item.get(position).getQuantita()));
        itemViewHolder.prezzo.setText(String.valueOf(item.get(position).getPrezzo()));
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nome;
        public TextView quantita;
        public TextView prezzo;
        public Button tastopiu;
        public Button tastomeno;

        public ItemViewHolder(View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.item_name);
            quantita = itemView.findViewById(R.id.item_quantity_text);
            prezzo = itemView.findViewById(R.id.item_price);
            tastopiu = itemView.findViewById(R.id.item_quantity_piu_btn);
            tastomeno = itemView.findViewById(R.id.item_quantity_meno_btn);

            tastopiu.setOnClickListener(this);
            tastomeno.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Item product = item.get(getAdapterPosition());

            if (view.getId() == R.id.item_quantity_piu_btn) {
                product.increaseQuantita();
                notifyItemChanged(getAdapterPosition());
                onQuantityChangedListener.onChange(product.getPrezzo());
            } else if (view.getId() == R.id.item_quantity_meno_btn) {
                if(product.getQuantita() == 0)return;
                product.decreaseQuantita();
                notifyItemChanged(getAdapterPosition());
                onQuantityChangedListener.onChange(product.getPrezzo()*-1);
            }

            notifyItemChanged(getAdapterPosition());
        }
    }

    public interface OnQuantityChangedListener {
        void onChange(float price);
    }

}
