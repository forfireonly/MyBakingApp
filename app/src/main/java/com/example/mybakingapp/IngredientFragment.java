package com.example.mybakingapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.zip.Inflater;

import static com.example.mybakingapp.ListOfItemsFragment.resultString;


public class IngredientFragment extends Fragment {

    Integer id;
    String ingredientBeforeParsing;

    String quantity;
    String measure;
    String ingredient;


    IngredientAdapter mAdapter;
    ArrayList <Ingredients> ingridientsToDisplay;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
             id = bundle.getInt("ID");}
            //Log.v("id",id);
        //}

       /* if (id != null) {
            try {
                idInt = Integer.parseInt(id);
            } catch(NumberFormatException e) {
                // Deal with the situation like
                idInt = 0;
            }
        }*/

       // Log.v("id", String.valueOf(id));

        ingridientsToDisplay = new ArrayList<Ingredients>();


        View rootView= inflater
                .inflate(R.layout.list_of_steps_fragment, parent, false);

        RecyclerView IngredientsForBaking = (RecyclerView) rootView.findViewById(R.id.recycler_view_steps);

       // Log.v("ResultS ingridientF",  resultString);





        JSONArray jsonarray3= null;
        try {
            jsonarray3 = new JSONArray(resultString);

            Log.v("JSON Array ingridients", String.valueOf(jsonarray3.length()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonobject = null;
        try {
            jsonobject =jsonarray3.getJSONObject(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            ingredientBeforeParsing = jsonobject.getString("ingredients");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonarray2= null;
        try {
            jsonarray2 = new JSONArray(ingredientBeforeParsing);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonarray2.length(); i++){
            JSONObject jsonobject2 = null;
            try {
                jsonobject2 = (JSONObject) jsonarray2.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                quantity = jsonobject2.getString("quantity");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                measure = jsonobject2.getString("measure");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                ingredient = jsonobject2.getString("ingredient");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.v("Ingredient", ingredient);
            Ingredients ingredientToDisplay = new Ingredients(quantity, measure, ingredient);

            ingridientsToDisplay.add(ingredientToDisplay);
        }


        mAdapter = new IngredientAdapter(ingridientsToDisplay);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setAutoMeasureEnabled(true);
        IngredientsForBaking.setLayoutManager(mLayoutManager);
        IngredientsForBaking.setNestedScrollingEnabled(false);
        IngredientsForBaking.setHasFixedSize(false);

        IngredientsForBaking.setAdapter(mAdapter);

        return rootView;
    }

}
