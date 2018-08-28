package com.example.cletrezo.bakingapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.cletrezo.bakingapp.Fragments.IngredientsFragment;
import com.example.cletrezo.bakingapp.Adapters.IngredientListRecyclerViewAdapter;
import com.example.cletrezo.bakingapp.Fragments.ListOfIngredientsFragment;
import com.example.cletrezo.bakingapp.Fragments.RecipeInstructionFragment;
import com.example.cletrezo.bakingapp.Fragments.RecipeStepsDetailFragment;
import com.example.cletrezo.bakingapp.Fragments.RecipeStepsFragment;
import com.example.cletrezo.bakingapp.Fragments.RecipeVideoFragment;
import com.example.cletrezo.bakingapp.model.RecipeModel;
import com.google.android.exoplayer2.ui.PlayerView;

import java.util.Objects;

public class RecipeDetailActivity extends AppCompatActivity implements CommunicationChannel{

    RecipeModel recipeModel;
    CardView cardView;
    FragmentManager manager;
    FragmentTransaction fragmentTransaction;
    ListOfIngredientsFragment listOfIngredientsFragment;
    public static boolean deviceType;
    public View view2;
    RecipeVideoFragment recipeVideoFragment;
    RecipeInstructionFragment recipeInstructionFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        final RecipeStepsFragment recipeStepsFragment = new RecipeStepsFragment();
        recipeVideoFragment = new RecipeVideoFragment();
        recipeInstructionFragment = new RecipeInstructionFragment();



        final TextView textViewIngredient = findViewById(R.id.ingredient_textView);

        listOfIngredientsFragment = new ListOfIngredientsFragment();
        manager = getSupportFragmentManager();

        recipeModel = getIntent().getParcelableExtra(MainActivity.RECIPE_IN_CURRENT_CLICKED_POSITION);


        Objects.requireNonNull(getSupportActionBar()).setTitle((CharSequence) recipeModel.getName());

        // get tablet reference
         deviceType = getResources().getBoolean(R.bool.isTablet);
        // if tablet
        if(deviceType){
            final Bundle bundle = new Bundle();
            bundle.putParcelable("recipe", recipeModel);
            recipeStepsFragment.setArguments(bundle);
            recipeVideoFragment.setArguments(bundle);
            recipeInstructionFragment.setArguments(bundle);

            final FragmentTransaction fragmentTransaction1 = manager.beginTransaction();
            fragmentTransaction1.add(R.id.frag_container_2, recipeStepsFragment, "TabletStepsFragment");
            fragmentTransaction1.add(R.id.video_frag, recipeVideoFragment,"tabletRecipeVideoFrag");
            fragmentTransaction1.add(R.id.instructions_frag, recipeInstructionFragment, "tabletInstructionFrag");
            //fragmentTransaction1.addToBackStack(null);
            fragmentTransaction1.commit();

            textViewIngredient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listOfIngredientsFragment.setArguments(bundle);
                    FragmentTransaction fragmentTransaction2 = manager.beginTransaction();
                    fragmentTransaction2.replace(R.id.video_frag,listOfIngredientsFragment);
                    fragmentTransaction2.addToBackStack("add2");
                    fragmentTransaction2.commit();
                }
            });





        }else {

            final TextView textViewSteps= findViewById(R.id.steps_textView);
            cardView = findViewById(R.id.single_recipe_cardView);



            //set Action bar title
            Objects.requireNonNull(getSupportActionBar()).setTitle(recipeModel.getName());


            final Bundle bundle = new Bundle();
            bundle.putParcelable("data",recipeModel );
            recipeStepsFragment.setArguments(bundle);


            fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.add(R.id.frag_container_2, recipeStepsFragment,"RecipeStepsFrag" );
            //fragmentTransaction.addToBackStack("add3");
            fragmentTransaction.commit();

            textViewIngredient.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listOfIngredientsFragment.setArguments(bundle);
                    fragmentTransaction = manager.beginTransaction();

                    if(listOfIngredientsFragment.isVisible()){
                        textViewIngredient.setText("List of Ingredients Needed");
                        textViewSteps.setText("STEPS");
                        fragmentTransaction.replace(R.id.frag_container_2,recipeStepsFragment);
                        fragmentTransaction.addToBackStack("add4");
                        fragmentTransaction.commit();
                    }else {
                        textViewIngredient.setText("STEPS TO MAKE RECIPE");
                        textViewSteps.setText("INGREDIENTS");
                        fragmentTransaction.replace(R.id.frag_container_2,listOfIngredientsFragment );
                        fragmentTransaction.addToBackStack("add5");
                        fragmentTransaction.commit();
                    }


                }
            });

        }

    }



    @Override
    public void passData(String url, String description, int position) {
        View view = findViewById(R.id.instructions_frag);
        RecipeStepsDetailFragment recipeStepsDetailFragment = new RecipeStepsDetailFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("des", description);
        recipeStepsDetailFragment.setArguments(bundle);
        view.setVisibility(View.GONE);
        transaction.replace(R.id.video_frag, recipeStepsDetailFragment,"p");
        transaction.addToBackStack("add6");
        transaction.commit();





    }



    }
