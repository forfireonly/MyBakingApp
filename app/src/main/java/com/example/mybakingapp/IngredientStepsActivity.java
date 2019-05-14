package com.example.mybakingapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import static com.example.mybakingapp.IngredientFragment.ingredientsWidget;
import static com.example.mybakingapp.ListOfItemsFragment.ID;
import static com.example.mybakingapp.ListOfItemsFragment.resultString;


public class IngredientStepsActivity extends AppCompatActivity {
    TextView placeHolder;
    Integer newString;
    String nameBakingItem;

    Button widgetButton;

    Boolean isClicked;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_steps);

        widgetButton =(Button) findViewById(R.id.widget_button);
        newString = ID;
        isClicked = false;

        widgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClicked = true;
                Log.v("is clicked", "true");
                Intent intent = new Intent(IngredientStepsActivity.this, IngridientWidgetProvider.class);
                intent.putExtra("extra_value", ingredientsWidget);
                getApplication().sendBroadcast(intent);

            }
        });

        if (!isClicked){
            Intent intent = new Intent(IngredientStepsActivity.this, IngridientWidgetProvider.class);
            intent.putExtra("extra_value", "Add ingridients to Widget");
            getApplication().sendBroadcast(intent);
        }

      JSONArray jsonarray = null;
        try {
            jsonarray = new JSONArray(resultString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject   jsonobject = null;
        try {
            jsonobject = jsonarray.getJSONObject(newString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            nameBakingItem = jsonobject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }

            setTitle(nameBakingItem);



     /*   if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getInt("STRING_I_NEED");
            }
    }*/
        //  Log.v("String I need", String.valueOf(newString));
       IngredientFragment fragment1 = new IngredientFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ID", newString);
        bundle.putString("name", nameBakingItem);
        bundle.putBoolean("clicked", isClicked);
        fragment1.setArguments(bundle);

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.replace(R.id.recycler_view_steps, fragment1);
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();


       StepsFragment fragment2= new StepsFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("ID", newString);
        fragment2.setArguments(bundle2);

        // Begin the transaction
        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft2.replace(R.id.ingridients_step_fragment, fragment2);
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft2.commit();
    }

}
