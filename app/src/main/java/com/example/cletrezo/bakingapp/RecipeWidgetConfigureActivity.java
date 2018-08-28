package com.example.cletrezo.bakingapp;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cletrezo.bakingapp.utils.RecipeDataSource;


/**
 * The configuration screen for the {@link RecipeWidget RecipeWidget} AppWidget.
 */
public class RecipeWidgetConfigureActivity extends Activity {
    RecipeDataSource recipeDataSource = new RecipeDataSource();
    private static final String PREFS_NAME = "AppWidget";
    private static final String PREF_PREFIX_KEY = "appwidget";

    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);
        setContentView(R.layout.recipe_widget_configure);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }
        final ListView listView = findViewById(R.id.widget_options_list);
        TextView textView = findViewById(R.id.widget_configure_textView);

        String[] values = new String[]{
                "Nutella Pie",
                "Brownies",
                "Yellow Cake",
                "Cheesecake"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String widgetText = (String) listView.getItemAtPosition(position);
                switch (widgetText) {
                    case "Nutella Pie":

                        Toast.makeText(getApplicationContext(), "YOU Clicked" + widgetText + " " + "in" + " " + position, Toast.LENGTH_SHORT).show();
                        startWidget(widgetText);
                        break;
                    case "Brownies":
                        Toast.makeText(getApplicationContext(), "YOU Clicked" + widgetText + " " + "in" + " " + position, Toast.LENGTH_SHORT).show();
                        startWidget(widgetText);
                        break;
                    case "Yellow Cake":
                        Toast.makeText(getApplicationContext(), "YOU Clicked" + widgetText + " " + "in" + " " + position, Toast.LENGTH_SHORT).show();
                        startWidget(widgetText);
                        break;
                    case "Cheesecake":
                        Toast.makeText(getApplicationContext(), "YOU Clicked" + widgetText + " " + "in" + " " + position, Toast.LENGTH_SHORT).show();
                        startWidget(widgetText);
                }
            }
        });


    }


    private void startWidget(String widText) {
        final Context context = RecipeWidgetConfigureActivity.this;
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);

            saveTitlePref(context, mAppWidgetId, widText);

            RecipeWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);



        // Make sure we pass back the original appWidgetId and exit and config to show widget
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();

    }

    // Write the prefix to the SharedPreferences object for this widget
    static void saveTitlePref(Context context, int appWidgetId, String text) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(PREF_PREFIX_KEY + appWidgetId, text);
        prefs.apply();
    }

    static String loadTitlePref(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        String titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null);
        if (titleValue != null) {
            return titleValue;
        } else {
            return context.getString(R.string.appwidget_text);
        }
    }
}
