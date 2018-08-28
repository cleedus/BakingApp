package com.example.cletrezo.bakingapp;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cletrezo.bakingapp.Adapters.RecipeStepsRecyclerViewAdapter;
import com.example.cletrezo.bakingapp.Fragments.RecipeInstructionFragment;
import com.example.cletrezo.bakingapp.Fragments.RecipeStepsFragment;
import com.example.cletrezo.bakingapp.Fragments.RecipeVideoFragment;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class RecipeVideoInstructionActivity extends AppCompatActivity {
    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    ImageView imageView;
    TextView textView;
    String description;
    ImageButton imageButton_Previous;
    ImageButton imageButton_Next;
    RecipeStepsRecyclerViewAdapter recipeStepsRecyclerViewAdapter = new RecipeStepsRecyclerViewAdapter();
    int position;
    MediaSource mediaSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_video_instruction);


        String videoUrl = getIntent().getStringExtra("VIDEOURL");
        description = getIntent().getStringExtra("DESCRIPTION");
        position = getIntent().getIntExtra("position", 0);

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

}