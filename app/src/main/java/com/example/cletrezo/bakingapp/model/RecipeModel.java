package com.example.cletrezo.bakingapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class RecipeModel implements Parcelable {
    private  int id;
    private String name;
    private List<Ingredients> ingredientsList = new ArrayList<>();
    private List<Steps> stepsList = new ArrayList<>();
    private int servingSize;

    public RecipeModel() {
    }

    public RecipeModel(int id, String name, List<Ingredients> ingredientsList, List<Steps> stepsList, int servingSize) {
        this.id = id;
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.stepsList = stepsList;
        this.servingSize = servingSize;
    }


    protected RecipeModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredientsList = in.createTypedArrayList(Ingredients.CREATOR);
        stepsList = in.createTypedArrayList(Steps.CREATOR);
        servingSize = in.readInt();
    }

    public static final Creator<RecipeModel> CREATOR = new Creator<RecipeModel>() {
        @Override
        public RecipeModel createFromParcel(Parcel in) {
            return new RecipeModel(in);
        }

        @Override
        public RecipeModel[] newArray(int size) {
            return new RecipeModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredients> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Ingredients> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public List<Steps> getStepsList() {
        return stepsList;
    }

    public void setStepsList(List<Steps> stepsList) {
        this.stepsList = stepsList;
    }

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeTypedList(ingredientsList);
        dest.writeTypedList(stepsList);
        dest.writeInt(servingSize);
    }
}
