package com.example.cletrezo.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import com.example.cletrezo.bakingapp.model.Ingredients;
import com.example.cletrezo.bakingapp.model.RecipeModel;
import com.example.cletrezo.bakingapp.utils.RecipeDataSource;

import java.util.ArrayList;
import java.util.List;

public class RecipeWidgetService extends RemoteViewsService {
    //RecipeDataSource recipeDataSource = new RecipeDataSource();
    //List<Ingredients> ingredients = new ArrayList<>();

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        //Toast.makeText(getApplicationContext(), "YOU Clicked"  +" " + "in" + " "+ "service" , Toast.LENGTH_SHORT).show();


       // int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
       /* String widgetText = intent.getStringExtra("checkText");
        switch (widgetText){

            case  "Nutella Pie":
                ingredients=recipeDataSource.getRecipeModelList().get(0).getIngredientsList();
                break;
            case "Brownies":
                ingredients=recipeDataSource.getRecipeModelList().get(1).getIngredientsList();
                break;
            case "Yellow Cake":
                ingredients=recipeDataSource.getRecipeModelList().get(2).getIngredientsList();
                break;
            case "Cheesecake":
                ingredients=recipeDataSource.getRecipeModelList().get(3).getIngredientsList();
        }*/



        return new RecipeRemoteViewsFactory(this.getApplication(),intent);


    }



}
