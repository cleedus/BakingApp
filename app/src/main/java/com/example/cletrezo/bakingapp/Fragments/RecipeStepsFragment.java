package com.example.cletrezo.bakingapp.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.cletrezo.bakingapp.Adapters.RecipeStepsRecyclerViewAdapter;
import com.example.cletrezo.bakingapp.R;
import com.example.cletrezo.bakingapp.model.RecipeModel;

public class RecipeStepsFragment extends Fragment {
    RecipeModel model;

    public RecipeStepsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.recipe_steps_recycler,container ,false );

        RecyclerView recyclerView;
        recyclerView = rootView.findViewById(R.id.recipe_steps_recyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        RecipeStepsRecyclerViewAdapter recipeStepsRecyclerViewAdapter;

        boolean deviceType = getResources().getBoolean(R.bool.isTablet);


        if(deviceType){

            if (getArguments() != null) {
                model = getArguments().getParcelable("recipe");
            }
            if (model != null) {
                Log.v("sizInFrag", String.valueOf(model.getStepsList().size()));
            } else {
                Log.v("TAG", "model tab is null");
            }
            recyclerView.setLayoutManager(linearLayoutManager);
             recipeStepsRecyclerViewAdapter = new RecipeStepsRecyclerViewAdapter(getContext(), model.getStepsList());
             recyclerView.setAdapter(recipeStepsRecyclerViewAdapter);



        }else{

            if (getArguments() != null) {
                model = getArguments().getParcelable("data");
            } else {
                Log.v("emptyModel", "empty");
            }

            if (model != null) {
                Log.v("sizInFrag", String.valueOf(model.getStepsList().size()));
            } else {
                Log.v("TAG", "model is null");
            }


            //Toast.makeText(getContext(), "I got your you" + data, Toast.LENGTH_SHORT).show();
            recyclerView.setLayoutManager(linearLayoutManager);
            recipeStepsRecyclerViewAdapter = new RecipeStepsRecyclerViewAdapter(getContext(), model.getStepsList());
            recyclerView.setAdapter(recipeStepsRecyclerViewAdapter);
        }

      return  rootView;

    }









    }

