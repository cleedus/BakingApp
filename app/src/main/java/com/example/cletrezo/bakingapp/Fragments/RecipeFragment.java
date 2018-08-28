package com.example.cletrezo.bakingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cletrezo.bakingapp.R;
import com.example.cletrezo.bakingapp.utils.RecipeDataSource;
import com.example.cletrezo.bakingapp.Adapters.RecipeRecyclerViewAdapter;

import java.io.IOException;
import java.io.InputStream;


public class RecipeFragment extends Fragment{
    RecipeDataSource recipeDataSource;

    public RecipeFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecipeRecyclerViewAdapter recipeRecyclerViewAdapter;

        //[reference]
//https://stackoverflow.com/questions/19945411/android-java-how-can-i-parse-a-local-json-file-from-assets-folder-into-a-listvi
        String json = null;
        try {
            InputStream inputStream = getActivity().getAssets().open("recipeJson");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //pass the String
        recipeDataSource = new RecipeDataSource();
        recipeDataSource.JsonParser(json);
        final View rootView = inflater.inflate(R.layout.default_launch_layout,container,false );

         //recipe recyclerView
         recyclerView = rootView.findViewById(R.id.recipe_recyclerView);
         recyclerView.setHasFixedSize(true);

         boolean deviceType = getResources().getBoolean(R.bool.isTablet);

         //checking device size
         if(deviceType){
             recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));


         }else {
             recyclerView.setLayoutManager(linearLayoutManager);
         }
        // Log.v("size", String.valueOf(recipeDataSource.getRecipeModelList().size()));
        recipeRecyclerViewAdapter = new RecipeRecyclerViewAdapter(getContext(),recipeDataSource.getRecipeModelList());
        recyclerView.setAdapter(recipeRecyclerViewAdapter);

         return  rootView;



    }



}
