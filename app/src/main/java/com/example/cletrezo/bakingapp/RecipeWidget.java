package com.example.cletrezo.bakingapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import com.example.cletrezo.bakingapp.utils.RecipeDataSource;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link RecipeWidgetConfigureActivity RecipeWidgetConfigureActivity}
 */
public class RecipeWidget extends AppWidgetProvider {
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RecipeDataSource recipeDataSource = new RecipeDataSource();



        CharSequence widgetText = RecipeWidgetConfigureActivity.loadTitlePref(context, appWidgetId);


            //Toast.makeText(context, "equal" + " "+ widgetText, Toast.LENGTH_SHORT).show();
            Intent goToRemoteServiceIntent = new Intent(context, RecipeWidgetService.class);
            goToRemoteServiceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,appWidgetId);
            goToRemoteServiceIntent.putExtra("name", widgetText);
            goToRemoteServiceIntent.setData(Uri.parse(goToRemoteServiceIntent.toUri(Intent.URI_INTENT_SCHEME)));


            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
            rv.setTextViewText(R.id.appwidget_text, widgetText);

            rv.setRemoteAdapter(R.id.widget_listView ,goToRemoteServiceIntent );
            appWidgetManager.updateAppWidget(appWidgetId, rv);




    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

