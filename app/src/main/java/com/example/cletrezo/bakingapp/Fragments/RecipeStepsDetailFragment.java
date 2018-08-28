package com.example.cletrezo.bakingapp.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cletrezo.bakingapp.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.squareup.picasso.Picasso;

public class RecipeStepsDetailFragment extends Fragment {

    public SimpleExoPlayer simpleExoPlayer1;
    public PlayerView playerView1;
    TextView textView;
    MediaSource mediaSource;
    ImageView imageView;
    String url;
    String desc;
    public RecipeStepsDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View rootView = inflater.inflate(R.layout.tablet_recipe_steps_detail, container, false);




        playerView1 = rootView.findViewById(R.id.tablet_video_view);
        textView = rootView.findViewById(R.id.tablet_instructions);
        imageView = rootView.findViewById(R.id.tablet_no_video_view);

        if (getArguments() != null) {
             url = getArguments().getString("url");
            desc = getArguments().getString("des");
        }
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
        simpleExoPlayer1 = ExoPlayerFactory.newSimpleInstance(getContext(),trackSelector );

        // if video has no url replace with no video image view
        if( url.isEmpty()){
            playerView1.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        }


        Uri videoUri = Uri.parse(url);

        DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory("recipe_video");
        mediaSource = new ExtractorMediaSource.Factory(defaultHttpDataSourceFactory).createMediaSource(videoUri);


        playerView1.setPlayer(simpleExoPlayer1);
        simpleExoPlayer1.prepare(mediaSource);
        simpleExoPlayer1.setPlayWhenReady(true);

        textView.setText(desc);


        return  rootView;
    }

    @Override
    public void onPause() {
        super.onPause();
        simpleExoPlayer1.setPlayWhenReady(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        simpleExoPlayer1.stop();
        simpleExoPlayer1.release();
    }

    @Override
    public void onResume() {
        super.onResume();
        simpleExoPlayer1.setPlayWhenReady(true);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("position", simpleExoPlayer1.getCurrentPosition());
    }


    }




