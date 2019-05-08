package com.example.mybakingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BakingItemsAdapter extends RecyclerView.Adapter<BakingItemsAdapter.BakingItemHolder> {

    private List<NameServing> listOfItems;

    public BakingItemsAdapter(ArrayList<NameServing> listOfItems) {
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
       String serving;
       name = listOfItems.get(i).getName();
       serving = listOfItems.get(i).getServing();
        holder.itemName.setText(name);
        holder.servings.setText("Servings: "+serving);

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
        TextView servings;
        public BakingItemHolder(@NonNull View itemView) {
            super(itemView);

            servings = (TextView) itemView.findViewById(R.id.servings);
            itemName = (TextView) itemView.findViewById(R.id.name_of_item);
        }
    }
}