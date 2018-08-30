package com.example.cletrezo.bakingapp.Fragments;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.cletrezo.bakingapp.R;
import com.example.cletrezo.bakingapp.model.RecipeModel;
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

public class RecipeVideoFragment extends Fragment{
    public SimpleExoPlayer simpleExoPlayer;
    String videoUrl;
    PlayerView playerView;
    MediaSource mediaSource;
    ImageView imageView;
    View rootView;
    boolean deviceType;


    public RecipeVideoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       // Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        rootView = inflater.inflate(R.layout.recipe_video, container, false);
        playerView = rootView.findViewById(R.id.video_view);
        imageView = rootView.findViewById(R.id.no_video_view);
        deviceType = getResources().getBoolean(R.bool.isTablet);



        try{
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(),trackSelector );
            if(deviceType){
                if (getArguments() != null) {
                    if(getArguments().containsKey("vidUrl"))
                    {
                        videoUrl= getArguments().getString("vidUrl");
                    }else {
                        RecipeModel model = getArguments().getParcelable("recipe");
                        if (model != null) {
                            videoUrl =model.getStepsList().get(0).getVideoUrl(); // get first video for default display

                        }

                    }


                }

            }else {

                if (getArguments() != null) {
                    videoUrl = getArguments().getString("video");

                }

            }


            // if video has no url replace with no video image view
            if (videoUrl != null && videoUrl.isEmpty()) {
                playerView.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
            }


            Uri videoUri = Uri.parse(videoUrl);

            DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory("recipe_video");
            mediaSource = new ExtractorMediaSource.Factory(defaultHttpDataSourceFactory).createMediaSource(videoUri);


            playerView.setPlayer(simpleExoPlayer);
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(true);


        }catch (Exception e){
            e.printStackTrace();
        }

    return  rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        simpleExoPlayer.setPlayWhenReady(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.stop();
        simpleExoPlayer.release();
    }

    @Override
    public void onResume() {
        super.onResume();
        simpleExoPlayer.setPlayWhenReady(true);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("position", simpleExoPlayer.getCurrentPosition());
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(deviceType){
            playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
        }else {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
            } else {
                playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
            }
        }

        }

    }



