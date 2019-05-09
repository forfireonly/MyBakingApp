package com.example.mybakingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.CustomViewHolder> {

    private List<Ingredients> ingredients;
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView ingredientName, measure;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredientName = (TextView)itemView.findViewById(R.id.name_of_ingredient);
            measure = (TextView)itemView.findViewById(R.id.measurment);
        }}

        public IngredientAdapter(List<Ingredients>ingredients) {
        this.ingredients=ingredients;
        }


        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.ingredients, parent, false);

            return new CustomViewHolder(itemView);
        }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Ingredients ingredient = ingredients.get(position);
        holder.ingredientName.setText("• "+ingredient.getIngredient());
        holder.measure.setText(ingredient.getQuantity()+" "+ingredient.getMeasure());

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }
    }

