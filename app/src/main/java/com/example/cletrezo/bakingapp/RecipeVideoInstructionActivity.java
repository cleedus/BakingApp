package com.example.cletrezo.bakingapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.cletrezo.bakingapp.Fragments.RecipeInstructionFragment;
import com.example.cletrezo.bakingapp.Fragments.RecipeVideoFragment;

import java.util.Objects;

public class RecipeVideoInstructionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_video_instruction);


        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        String videoUrl = getIntent().getStringExtra("VIDEOURL");
        String description = getIntent().getStringExtra("DESCRIPTION");
        int position = getIntent().getIntExtra("position", 0);

        RecipeVideoFragment recipeVideoFragment = new RecipeVideoFragment();
        RecipeInstructionFragment recipeInstructionFragment = new RecipeInstructionFragment();
        Bundle bundle = new Bundle();
        bundle.putString("video", videoUrl);
        bundle.putString("desc", description);
        bundle.putInt("pos", position);
        recipeVideoFragment.setArguments(bundle);
        recipeInstructionFragment.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.frag_container_3, recipeVideoFragment, "videoFragment");
        fragmentTransaction.add(R.id.frag_container_4, recipeInstructionFragment, "stepsFragment");
        fragmentTransaction.commit();

       boolean deviceType = getResources().getBoolean(R.bool.isTablet);

       if(deviceType){
           Toast.makeText(this, "in tablet mode", Toast.LENGTH_SHORT).show();
       }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}