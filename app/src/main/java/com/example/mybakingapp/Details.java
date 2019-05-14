package com.example.mybakingapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
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

import static com.example.mybakingapp.ListOfItemsFragment.ID;
import static com.example.mybakingapp.ListOfItemsFragment.resultString;

public class Details extends AppCompatActivity {
    String definitions, videoURL, thumbnailURL, nameBakingItem;
    TextView definitionTextView, videoURLTextView, thumbnailURLTextView;

    ImageView noVideo;

    SimpleExoPlayerView playerView;
    ExoPlayer player;

    boolean landscapeMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);




        playerView = (SimpleExoPlayerView) findViewById(R.id.exoplayer);

        noVideo = (ImageView) findViewById(R.id.no_video);
        // = (VideoView) findViewById(R.id.exoplayer);
        // get the Intent that started this Activity
        Intent in = getIntent();

        // get the Bundle that stores the data of this Activity
        Bundle b = in.getExtras();

        // getting data from bundle
        definitions = b.getString("description");
        videoURL = b.getString("videoURL");
        Log.v("URL", videoURL);
        thumbnailURL = b.getString("thumbnailURL");

        JSONArray jsonarray = null;
        try {
            jsonarray = new JSONArray(resultString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonobject = null;
        try {
            jsonobject = jsonarray.getJSONObject(ID);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
           nameBakingItem = jsonobject.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setTitle(nameBakingItem + " - Cooking Steps");

        // Determine if we are in landscape mode
        if(findViewById(R.id.description) == null) {

            landscapeMode = true;
            if(getSupportActionBar()!=null) {
                getSupportActionBar().hide();
            }

        }
        else {

        definitionTextView = (TextView)findViewById(R.id.description);
        definitionTextView.setText(definitions);}


    }
    @Override
    protected void onStart() {
        super.onStart();
        if (!videoURL.isEmpty()){
        initializePlayer(videoURL);}
        else {
            playerView.setVisibility(View.GONE);
            noVideo.setVisibility(View.VISIBLE);
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
        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector);

        //Initialize simpleExoPlayerView
        SimpleExoPlayerView simpleExoPlayerView = findViewById(R.id.exoplayer);
        simpleExoPlayerView.setPlayer(player);

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, Util.getUserAgent(this, "CloudinaryExoplayer"));

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
