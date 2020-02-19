package com.enes.moviesapp.ui.view;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.enes.moviesapp.R;
import com.enes.moviesapp.model.YouTubeVideoItem;
import com.enes.moviesapp.service.YoutubeService;
import com.enes.moviesapp.ui.OnTaskCompletedListener;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoPopupActivity extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener{

    private YouTubePlayerView youTubePlayerView;
    private YouTubePlayer mYouTubePlayer;

    private YoutubeService youtubeService = new YoutubeService(this);

    private YouTubeVideoItem youTubeVideoItem = new YouTubeVideoItem();

    private String searchParam;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_popup_activity);

        searchParam = getIntent().getStringExtra("trailer");

        youTubePlayerView = findViewById(R.id.youtube_player_view);

        new Thread(){
            public void run(){
                youTubeVideoItem = youtubeService.search(searchParam, new OnTaskCompletedListener() {
                    @Override
                    public void onTaskCompleted() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                youTubePlayerView.initialize(youtubeService.getApi(),VideoPopupActivity.this);
                            }
                        });
                    }
                });
            }
        }.start();


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        this.mYouTubePlayer = youTubePlayer;


        if (!b){

            mYouTubePlayer.loadVideo(youTubeVideoItem.getId());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

}
