package com.example.mybakingapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import static com.example.mybakingapp.IngredientFragment.anna;
import static com.example.mybakingapp.IngredientFragment.ingredientsWidget;

/**
 * Implementation of App Widget functionality.
 */
public class IngridientWidgetProvider extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        //CharSequence widgetText = context.getString(R.string.appwidget_text);


        String start = "Add baking item's ingridients to widget ";

        //Load the layout resource file into a RemoteViews object//

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingridient_widget_provider);
        views.setTextViewText(R.id.appwidget_text, start);

        //Tell the AppWidgetManager about the updated RemoteViews object//

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

//Define the onUpdate lifecycle method//

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

//appWidgetIds is an array of IDs that identifies every instance of your widget, so this
//particular onUpdate() method will update all instances of our application widget//

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {


        super.onReceive(context, intent);

        String myString = intent.getStringExtra("extra_value");
        RemoteViews views = new RemoteViews(context.getPackageName(),
                R.layout.ingridient_widget_provider);
        views.setTextViewText(R.id.appwidget_text, myString);
        AppWidgetManager.getInstance(context).updateAppWidget(
                new ComponentName(context, IngridientWidgetProvider.class),views);



    }

    @Override

//Define the onEnabled lifecycle method//

    public void onEnabled(Context context) {

        //To do//
    }

    @Override

//Define the onDisabled method//

    public void onDisabled(Context context) {

//To do//

    }


    }




