package com.example.mybakingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import static com.example.mybakingapp.ListOfItemsFragment.ID;

public class IngredientStepsActivity extends AppCompatActivity {
TextView placeHolder;
    Integer newString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient_steps);

        newString = ID;


     /*   if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getInt("STRING_I_NEED");
            }
    }*/
      //  Log.v("String I need", String.valueOf(newString));
        IngredientFragment fragment= new IngredientFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("ID", newString);
        fragment.setArguments(bundle);

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.replace(R.id.ingridients_list_fragment, fragment);
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
}}
