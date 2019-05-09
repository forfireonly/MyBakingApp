package com.example.mybakingapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.CustomViewHolder>{

 private List <Steps> steps;

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        public TextView numberStep, stepDescriptionShort;

        public CustomViewHolder(@NonNull View itemView) {

            super(itemView);

            numberStep = (TextView) itemView.findViewById(R.id.name_of_ingredient);
            stepDescriptionShort = (TextView) itemView.findViewById(R.id.measurment);

        }
    }

    public StepsAdapter(List<Steps> steps){
        this.steps = steps;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Steps step = steps.get(position);
        holder.numberStep.setText("STEP " + (Integer.parseInt(step.getNumber())+1));
        holder.stepDescriptionShort.setText(step.getShortDescription());

    }

    @Override
    public int getItemCount() {
        return steps.size();
    }
}
