package com.example.mybakingapp;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    String resultString;
    ArrayList<String> bakingItemsName;
    JSONArray bakingItemsNamesJson;

    RecyclerView BakingItems;

    BakingItemsAdapter Adapter;

    String nameBakingItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.replace(R.id.master_list_fragment, new ListOfItemsFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();

        /*bakingItemsName = new ArrayList<>();

        BakingItems = (RecyclerView) findViewById(R.id.recycler_view);

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
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //String url = jsonobject.getString("url");
            Log.v("baking item", nameBakingItem);
            bakingItemsName.add(nameBakingItem);
        }

        Adapter = new BakingItemsAdapter(bakingItemsName);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, spanCount);



        BakingItems.setLayoutManager(mLayoutManager);
        BakingItems.setAdapter(Adapter); */

    }

}













