package com.example.cletrezo.bakingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cletrezo.bakingapp.Adapters.IngredientListRecyclerViewAdapter;
import com.example.cletrezo.bakingapp.R;
import com.example.cletrezo.bakingapp.model.RecipeModel;

public class ListOfIngredientsFragment extends Fragment {

    RecipeModel recipeModel;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    IngredientListRecyclerViewAdapter ingredientListRecyclerViewAdapter;
    public ListOfIngredientsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_list_of_ingredients, container, false);
        recyclerView = rootView.findViewById(R.id.list_of_ingredients_recylerView);

        boolean deviceType = getResources().getBoolean(R.bool.isTablet);
        if(deviceType){
            if (getArguments() != null) {
                recipeModel = getArguments().getParcelable("recipe");
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                ingredientListRecyclerViewAdapter = new IngredientListRecyclerViewAdapter(getContext(), recipeModel.getIngredientsList());
                recyclerView.setAdapter(ingredientListRecyclerViewAdapter);

            }


        }else {

            if (getArguments() != null) {
                recipeModel = getArguments().getParcelable("data");
            }


            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            ingredientListRecyclerViewAdapter = new IngredientListRecyclerViewAdapter(getContext(), recipeModel.getIngredientsList());
            recyclerView.setAdapter(ingredientListRecyclerViewAdapter);

        }

        return rootView;



    }
}
