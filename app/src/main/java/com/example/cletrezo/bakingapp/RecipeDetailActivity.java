package com.example.cletrezo.bakingapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.cletrezo.bakingapp.Fragments.ListOfIngredientsFragment;
import com.example.cletrezo.bakingapp.Fragments.RecipeInstructionFragment;
import com.example.cletrezo.bakingapp.Fragments.RecipeStepsDetailFragment;
import com.example.cletrezo.bakingapp.Fragments.RecipeStepsFragment;
import com.example.cletrezo.bakingapp.Fragments.RecipeVideoFragment;
import com.example.cletrezo.bakingapp.model.RecipeModel;

import java.util.Objects;

//import com.example.cletrezo.bakingapp.Fragments.IngredientsFragment;

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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
                        textViewIngredient.setText(R.string.List_of_ingredients);
                        textViewSteps.setText(R.string.steps);
                        fragmentTransaction.replace(R.id.frag_container_2,recipeStepsFragment);
                        fragmentTransaction.addToBackStack("add4");
                        fragmentTransaction.commit();
                    }else {
                        textViewIngredient.setText(R.string.steps_to_make_recipe);
                        textViewSteps.setText(R.string.ingre);
                        fragmentTransaction.replace(R.id.frag_container_2,listOfIngredientsFragment );
                        fragmentTransaction.addToBackStack("add5");
                        fragmentTransaction.commit();
                    }


                }
            });

        }

    }



    @Override
    public void passData(String url, String description, String thumbnailUrl, int position) {
        View view = findViewById(R.id.instructions_frag);
        RecipeStepsDetailFragment recipeStepsDetailFragment = new RecipeStepsDetailFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("des", description);
        bundle.putString("thumbnail", thumbnailUrl);
        recipeStepsDetailFragment.setArguments(bundle);
        view.setVisibility(View.GONE);
        transaction.replace(R.id.video_frag, recipeStepsDetailFragment,"p");
        transaction.addToBackStack("add6");
        transaction.commit();





    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if(getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStack();
        }else {
            super.onBackPressed();
        }
    }
}
