package com.example.cletrezo.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public final static String RECIPE_IN_CURRENT_CLICKED_POSITION = "theRecipeInCurrentClickedPosition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}



