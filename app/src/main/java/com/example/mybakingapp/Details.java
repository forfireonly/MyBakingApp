package com.example.mybakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.example.mybakingapp.ListOfItemsFragment.ID;
import static com.example.mybakingapp.ListOfItemsFragment.resultString;

public class Details extends AppCompatActivity {
    String definitions, videoURL, thumbnailURL, nameBakingItem;
    TextView definitionTextView, videoURLTextView, thumbnailURLTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // get the Intent that started this Activity
        Intent in = getIntent();

        // get the Bundle that stores the data of this Activity
        Bundle b = in.getExtras();

        // getting data from bundle
        definitions = b.getString("description");
        videoURL = b.getString("videoURL");
        thumbnailURL = b.getString("thumbnailURL");

        JSONArray jsonarray = null;
        try {
            jsonarray = new JSONArray(resultString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonobject = null;
        try {
            jsonobject = jsonarray.getJSONObject(ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
           nameBakingItem = jsonobject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setTitle(nameBakingItem + " - Cooking Steps");

        definitionTextView = (TextView)findViewById(R.id.description);
        videoURLTextView = (TextView) findViewById(R.id.video_URL);
        thumbnailURLTextView = (TextView) findViewById(R.id.thumbnail_URL);

        definitionTextView.setText(definitions);
        videoURLTextView.setText(videoURL);
        thumbnailURLTextView.setText(thumbnailURL);



    }
}
