package com.example.mybakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
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


public class StepsFragment extends Fragment {

    ArrayList<Steps> stepsToTake;
    Integer id;
    String stepsBeforeParsing;

    String idStep;
    String shortDescription;
    String description;
    String videoURL;
    String thumbnailURL;

    String resultString;

    StepsAdapter mAdapter;

    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("ID");
            //Log.v("id",id);
        }

        stepsToTake = new ArrayList<>();

        View rootView1 = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lits_of_items_fragment, parent, false);

        RecyclerView stepsForBaking = (RecyclerView) rootView1.findViewById(R.id.recycler_view);
        //stepsForBaking.setNestedScrollingEnabled(false);

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

        JSONArray jsonarray3 = null;
        try {
            jsonarray3 = new JSONArray(resultString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonobject = null;
        try {
            jsonobject = jsonarray3.getJSONObject(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            stepsBeforeParsing = jsonobject.getString("steps");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONArray jsonarray2 = null;
        try {
            jsonarray2 = new JSONArray(stepsBeforeParsing);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonarray2.length(); i++) {

            JSONObject jsonobject2 = null;
            try {
                jsonobject2 = (JSONObject) jsonarray2.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                idStep = jsonobject2.getString("id");

            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                shortDescription = jsonobject2.getString("shortDescription");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                description = jsonobject2.getString("description");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                videoURL = jsonobject2.getString("videoURL");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                thumbnailURL = jsonobject2.getString("thumbnailURL");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Steps stepToBake = new Steps(idStep, shortDescription, description, videoURL, thumbnailURL);

            stepsToTake.add(stepToBake);

        }

        BakingItemsAdapter.RecyclerViewClickListener listener = new BakingItemsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(getContext(), "Position " + position, Toast.LENGTH_SHORT).show();

                // Creating Bundle object
                Bundle b = new Bundle();
                b.putString("description", stepsToTake.get(position).getDescription());
                b.putString("videoURL", stepsToTake.get(position).getVideoURL());
                b.putString("thumbnailURL", stepsToTake.get(position).getThumbnailURL());
                Intent myIntent = new Intent(view.getContext(), Details.class);
                myIntent.putExtras(b);
                view.getContext().startActivity(myIntent);
            }
        };

        mAdapter = new StepsAdapter(stepsToTake, listener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());

        stepsForBaking.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        stepsForBaking.setLayoutManager(mLayoutManager);

        stepsForBaking.setAdapter(mAdapter);

            return rootView1;


        }
    }
