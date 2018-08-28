package com.example.cletrezo.bakingapp.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cletrezo.bakingapp.R;
import com.example.cletrezo.bakingapp.model.RecipeModel;

public class RecipeInstructionFragment extends Fragment {
    TextView textView;
    String description;
    public RecipeInstructionFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.recipe_instruction, container, false);
        textView = rootView.findViewById(R.id.instructions);

        boolean deviceType = getResources().getBoolean(R.bool.isTablet);
        if(deviceType){
            RecipeModel recipeModel = null;
            if (getArguments() != null) {
                recipeModel = getArguments().getParcelable("recipe");
            }
            if (recipeModel != null) {
                description = recipeModel.getStepsList().get(0).getDescription();
            }

        }else {
            if (getArguments() != null) {
                description = getArguments().getString("desc");

            }


        }


        textView.setText(description);




        return rootView;
    }


}
