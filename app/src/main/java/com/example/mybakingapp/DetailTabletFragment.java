package com.example.mybakingapp;

import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.view.View.VISIBLE;
import static com.example.mybakingapp.ListOfItemsFragment.ID;
import static com.example.mybakingapp.ListOfItemsFragment.resultString;
import static com.example.mybakingapp.R.id.exoplayer;
import static com.example.mybakingapp.R.id.parent;
import static com.example.mybakingapp.StepsFragment.descriptionTablet;

public class DetailTabletFragment extends Fragment {

    SimpleExoPlayerView playerView;

    ImageView noVideo;

    String definitions, videoURL;

    TextView definitionTextView;

    String stepsBeforeParsing;

    String idStep;
    String shortDescription;
    String description;

    ExoPlayer player;

    String descriptionForTablet, urlForTablet;




    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            descriptionForTablet = bundle.getString("Description");
            urlForTablet = bundle.getString("URL");
           // Log.v("DescrDetailTabletFrag", descriptionForTablet);
        }



        definitionTextView = (TextView) parent.findViewById(R.id.description);
        playerView = (SimpleExoPlayerView) parent.findViewById(R.id.exoplayer);
        noVideo = (ImageView) parent.findViewById(R.id.no_video);
        //noVideo.setVisibility(View.VISIBLE);

        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_details, parent, false);



        definitionTextView.setText(descriptionTablet);



        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!urlForTablet.isEmpty()){
            initializePlayer(urlForTablet);}
        else {
            playerView.setVisibility(View.GONE);
            noVideo.setVisibility(View.VISIBLE);
          //  playerView.setDefaultArtwork();
           // playerView.setUseArtwork(R.drawable.no_video);

        }

    }

    private void initializePlayer(String videoURL){
        // Create a default TrackSelector
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);

        //Initialize the player
        player = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);



        //Initialize simpleExoPlayerView
        SimpleExoPlayerView simpleExoPlayerView = playerView.findViewById(R.id.exoplayer);

        simpleExoPlayerView.setPlayer(player);

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "CloudinaryExoplayer"));

        // Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();

        // This is the MediaSource representing the media to be played.
        Uri videoUri = Uri.parse(videoURL);
        MediaSource videoSource = new ExtractorMediaSource(videoUri,
                dataSourceFactory, extractorsFactory, null, null);

        // Prepare the player with the source.
        player.prepare(videoSource);


    }

    @Override
    public void onPause() {
        super.onPause();
        if (player!=null) {
            player.release();
            player = null;
        }


    }




    }

