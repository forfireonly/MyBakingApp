package com.example.mybakingapp;

import android.content.Context;
import android.content.Intent;
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
    private RecyclerViewClickListener mListener;

    public BakingItemsAdapter(ArrayList<NameServing> listOfItems,RecyclerViewClickListener listener) {
        this.listOfItems = listOfItems;
        mListener = listener;
    }
    @NonNull
    @Override
    public BakingItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.baking_item, viewGroup, false);

        return new BakingItemHolder(itemView,mListener);
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


    class BakingItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView frame;
        TextView itemName;
        TextView servings;
        private RecyclerViewClickListener mListener;
        public BakingItemHolder(@NonNull View itemView,RecyclerViewClickListener listener) {
            super(itemView);


            servings = (TextView) itemView.findViewById(R.id.servings);
            itemName = (TextView) itemView.findViewById(R.id.name_of_item);
            mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v, getAdapterPosition());

            int position = getAdapterPosition();
            Intent myIntent = new Intent(v.getContext(), IngredientStepsActivity.class);
            myIntent.putExtra("STRING_I_NEED", String.valueOf(position));
            v.getContext().startActivity(myIntent);

        }
    }

    public interface RecyclerViewClickListener {

        void onClick(View view, int position);
    }
}