package com.example.cletrezo.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.cletrezo.bakingapp.model.Ingredients;
import com.example.cletrezo.bakingapp.model.RecipeModel;
import com.example.cletrezo.bakingapp.model.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RecipeRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private Intent intent;
    String name;
    private final static String RECIPE_ID = "id";
    private final static String RECIPE_NAME = "name";
    private final static String INGREDIENTS = "ingredients";
    private final static String QUANTITY = "quantity";
    private final static String MEASURE = "measure";
    private final static String INGREDIENT = "ingredient";
    private final static String STEPS = "steps";
    private final static String STEPS_ID = "id";
    private final static String SHORT_DESCRIPTION = "shortDescription";
    private final static String DESCRIPTION = "description";
    private final static String VIDEO_URL ="videoURL";
    private final static String SERVINGS = "servings";
    private final static String THUMBNAILURL= "thumbnailURL";
    private static List<RecipeModel> recipeModelList = new ArrayList<>();

    public static List<RecipeModel> getRecipeModelList() {
        return recipeModelList;
    }


    private List<Ingredients> ingredientsListMain = new ArrayList<>();

    public RecipeRemoteViewsFactory(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;

    }


    @Override
    public void onCreate() {


        name = intent.getStringExtra("name");

        if (name.equals("Yellow Cake")){

            getData();
            ingredientsListMain = RecipeRemoteViewsFactory.getRecipeModelList().get(2).getIngredientsList();
            }
        if(name.equals("Cheesecake")){

            getData();
            ingredientsListMain = RecipeRemoteViewsFactory.getRecipeModelList().get(3).getIngredientsList();


        }
        if(name.equals("Brownies")){

            getData();
            ingredientsListMain = RecipeRemoteViewsFactory.getRecipeModelList().get(1).getIngredientsList();

        }
        if (name.equals("Nutella Pie")) {
            getData();
            ingredientsListMain = RecipeRemoteViewsFactory.getRecipeModelList().get(0).getIngredientsList();


        }
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {


        return ingredientsListMain.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews row = new RemoteViews(context.getPackageName(),R.layout.widget_list_layout);
        row.setTextViewText(R.id.ingredient_widget_textview,ingredientsListMain.get(position).getIngredient());
        row.setTextViewText(R.id.quantity_widget_textview, String.valueOf(ingredientsListMain.get(position).getQuantity()));
        row.setTextViewText(R.id.measure_widget_textview, ingredientsListMain.get(position).getMeasure());

        return row;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    private String json(){

        String json = null;
        try {
            InputStream inputStream = context.getAssets().open("recipeJson");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;

    }

    private void getData(){

            try {
                JSONArray entireJson = new JSONArray(json());
                for(int i=0; i< entireJson.length(); i++){

                    List<Ingredients> ingredientsList = new ArrayList<>();
                    List<Steps> stepsList = new ArrayList<>();

                    JSONObject jsonObject = entireJson.getJSONObject(i);
                    int id = jsonObject.optInt(RECIPE_ID);
                    String name = jsonObject.optString(RECIPE_NAME);
                    //get ingredients array
                    JSONArray ingredientArray = jsonObject.getJSONArray(INGREDIENTS);
                    for(int j=0; j<ingredientArray.length(); j++){
                        JSONObject ingredientObject = ingredientArray.getJSONObject(j);
                        int quantity = ingredientObject.optInt(QUANTITY);
                        String measure = ingredientObject.optString(MEASURE);
                        String ingredient = ingredientObject.optString(INGREDIENT);
                        Ingredients ingredientsModel = new Ingredients(quantity, measure, ingredient);
                        ingredientsList.add(ingredientsModel);
                    }
                    // get steps array
                    JSONArray stepsArray = jsonObject.getJSONArray(STEPS);
                    for(int k=0; k<stepsArray.length(); k++){
                        JSONObject stepsObject = stepsArray.getJSONObject(k);
                        int id1 = stepsObject.optInt(STEPS_ID);
                        String shortDescription = stepsObject.optString(SHORT_DESCRIPTION);
                        String description = stepsObject.optString(DESCRIPTION);
                        String videoURL = stepsObject.optString(VIDEO_URL);
                        String thumbnailUrl= stepsObject.getString(THUMBNAILURL);
                        Steps stepsModel = new Steps(id1, shortDescription, description, videoURL,thumbnailUrl);
                        stepsList.add(stepsModel);
                    }
                    int servingSize = jsonObject.optInt(SERVINGS);
                    //create full recipe with name, ingredients and steps
                    RecipeModel recipeModel = new RecipeModel(id, name, ingredientsList, stepsList, servingSize);
                    recipeModelList.add(recipeModel); // add each recipe created to a list
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

    }
}
