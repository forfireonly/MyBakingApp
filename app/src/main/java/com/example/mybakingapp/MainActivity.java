package com.example.mybakingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.view.View.GONE;
import static com.example.mybakingapp.IngredientFragment.ingredientsWidget;
import static com.example.mybakingapp.ListOfItemsFragment.ID;
import static com.example.mybakingapp.ListOfItemsFragment.resultString;
import static com.example.mybakingapp.StepsFragment.idTablet;

public class MainActivity extends AppCompatActivity {

    String resultString;
    ArrayList<String> bakingItemsName;
    JSONArray bakingItemsNamesJson;

    RecyclerView BakingItems;

    BakingItemsAdapter Adapter;

    String nameBakingItem;

    ImageView noInternet;

    FrameLayout layout;

    // Track whether to display a two-pane or single-pane UI
    // A single-pane display refers to phone screens, and two-pane to larger tablet screens
    private boolean mTwoPane;

    boolean tabletSize;

    Integer newString;


    Button widgetButton;

    Boolean isClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabletSize = getResources().getBoolean(R.bool.isTablet);

        if (tabletSize) {
            // do something

            layout = (FrameLayout) findViewById(R.id.master_list_fragment);

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
            ft.replace(R.id.master_list_fragment, new ListOfItemsFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
            ft.commit();




        } else {
            // do something else


            noInternet = (ImageView) findViewById(R.id.no_internet);
            layout = (FrameLayout) findViewById(R.id.master_list_fragment);


            if (CheckNetwork.isInternetAvailable(MainActivity.this)) //returns true if internet available
            {
                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
                ft.replace(R.id.master_list_fragment, new ListOfItemsFragment());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
                ft.commit();
            } else {
                Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }
    }
  /*  @Override
    protected void onResume() {

        super.onResume();

    }*/

    public static class CheckNetwork {


        private static final String TAG = CheckNetwork.class.getSimpleName();



        public static boolean isInternetAvailable(Context context)
        {
            NetworkInfo info = (NetworkInfo) ((ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

            if (info == null)
            {
                Log.d(TAG,"no internet connection");
                return false;
            }
            else
            {
                if(info.isConnected())
                {
                    Log.d(TAG," internet connection available...");
                    return true;
                }
                else
                {
                    Log.d(TAG," internet connection");
                    return true;
                }

            }
        }
    }

}














