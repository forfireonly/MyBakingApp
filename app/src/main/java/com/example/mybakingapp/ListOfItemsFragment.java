package com.example.mybakingapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class ListOfItemsFragment extends Fragment {

    String resultString;
    ArrayList<NameServing> bakingItemsName;
    RecyclerView BakingItems;
    BakingItemsAdapter Adapter;
    String nameBakingItem;
    String servings;
    View rootView;

    public static Integer ID;



    public ListOfItemsFragment(){};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                             Bundle savedInstanceState) {



        bakingItemsName = new ArrayList<>();

        // rootView = inflater.inflate(R.layout.lits_of_items_fragment, parent, false);

        View rootView=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lits_of_items_fragment, parent, false);

        RecyclerView BakingItems = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        int spanCount = getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? 2 : 1;

        GetBakingItems gettingBakingItems = new GetBakingItems();

        try {
            resultString = gettingBakingItems.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (resultString == null) {
            Log.v("Result string", "No result string");
        } else {
            Log.v("Result string", resultString);


        }

        JSONArray jsonarray = null;
        try {
            jsonarray = new JSONArray(resultString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonarray.length(); i++) {
            JSONObject jsonobject = null;
            try {
                jsonobject = jsonarray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                nameBakingItem = jsonobject.getString("name");
                servings = jsonobject.getString("servings");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //String url = jsonobject.getString("url");
            Log.v("baking item", nameBakingItem);
            NameServing item = new NameServing(nameBakingItem, servings);
            bakingItemsName.add(item);
        }


        BakingItemsAdapter.RecyclerViewClickListener listener = new BakingItemsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(getContext(), IngredientStepsActivity.class);
                //myIntent.putExtra("STRING_I_NEED", position);

                ID = position;
                startActivity(myIntent);
            }
        };

        Adapter = new BakingItemsAdapter(bakingItemsName, listener);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), spanCount);
       // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        BakingItems.setLayoutManager(mLayoutManager);

        BakingItems.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));


        BakingItems.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.HORIZONTAL));

        BakingItems.setAdapter(Adapter);

        return rootView;
                //inflater.inflate(R.layout.lits_of_items_fragment, parent, false);

}
}
