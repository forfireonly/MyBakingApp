package com.example.mybakingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class BakingItemsAdapter extends RecyclerView.Adapter<BakingItemsAdapter.BakingItemHolder> {

    private List<String> listOfItems;

    public BakingItemsAdapter(List<String> listOfItems) {
        this.listOfItems = listOfItems;
    }
    @NonNull
    @Override
    public BakingItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.baking_item, viewGroup, false);
        return new BakingItemHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BakingItemHolder holder, int i) {
       String name;
       name = listOfItems.get(i);

        holder.itemName.setText(name);

    }

    @Override
    public int getItemCount() {
        int a ;

        if(listOfItems != null && !listOfItems.isEmpty()) {

            a = listOfItems.size();
        }
        else {

            a = 0;

        }
        return a;
    }


    class BakingItemHolder extends RecyclerView.ViewHolder {

        ImageView frame;
        TextView itemName;
        public BakingItemHolder(@NonNull View itemView) {
            super(itemView);


            itemName = (TextView) itemView.findViewById(R.id.name_of_item);
        }
    }
}