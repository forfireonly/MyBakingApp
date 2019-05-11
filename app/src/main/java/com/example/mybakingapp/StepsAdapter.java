package com.example.mybakingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.CustomViewHolder>{

 private List <Steps> steps;
    private RecyclerViewClickListener mListener;

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView numberStep, stepDescriptionShort;
        private RecyclerViewClickListener mListener;

        public CustomViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {

            super(itemView);

            numberStep = (TextView) itemView.findViewById(R.id.name_of_ingredient);
            stepDescriptionShort = (TextView) itemView.findViewById(R.id.measurment);

            mListener = listener;
            itemView.setOnClickListener(this);}

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());

        }
    }

    public StepsAdapter(List<Steps> steps, RecyclerViewClickListener listener){
        this.steps = steps;
        mListener = listener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredients, parent, false);

        return new CustomViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Steps step = steps.get(position);
        if (position!=0)
        {   String correctStep = "STEP " + step.getNumber();
            holder.numberStep.setText(correctStep);}
        holder.stepDescriptionShort.setText(step.getShortDescription());

    }

    @Override
    public int getItemCount() {
        Log.v("Size of steps", String.valueOf(steps.size()));
        return steps.size();
    }

    public interface RecyclerViewClickListener {

        void onClick(View view, int position);
    }
}
