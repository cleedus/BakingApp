package com.example.cletrezo.bakingapp.utils;

import android.util.Log;

import com.example.cletrezo.bakingapp.model.Ingredients;
import com.example.cletrezo.bakingapp.model.RecipeModel;
import com.example.cletrezo.bakingapp.model.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

public class RecipeDataSource {
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

    private  List<RecipeModel> recipeModelList = new ArrayList<>();

    public List<RecipeModel> getRecipeModelList() {
        return recipeModelList;
    }

    public  void JsonParser(String json){



    try {
        JSONArray entireJson = new JSONArray(json);
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
               /* if(stepsObject.optString(VIDEO_URL).isEmpty()){
                    String videoURL = "NO VIDEOURL";
                }*/
                String videoURL = stepsObject.optString(VIDEO_URL);
                Steps stepsModel = new Steps(id1, shortDescription, description, videoURL);
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
