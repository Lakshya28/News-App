package com.example.roundedbutton.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.roundedbutton.NewsVideoItem;
import com.example.roundedbutton.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.menu.YouTubePlayerMenu;

public class LiveNewsDetailActivity extends AppCompatActivity {

    YouTubePlayerView youTubePlayerView;
    String videoId;
    ImageView channelIcon;
    TextView tvChannelName, tvChannelWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_news_detail);

        Intent intent = getIntent();
        NewsVideoItem newsVideoItem = intent.getParcelableExtra("newsVideoItem");

        Toolbar toolbar = findViewById(R.id.liveTVToolbar);
        toolbar.setTitle("Live News");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        videoId = newsVideoItem.getVideoId();

        channelIcon = findViewById(R.id.channelIcon);
        tvChannelName = findViewById(R.id.channelName);
        tvChannelWebsite = findViewById(R.id.visitWebsite);
        youTubePlayerView = findViewById(R.id.liveVideoPlayer);
        getLifecycle().addObserver(youTubePlayerView);


        Glide.with(this).load(newsVideoItem.getImageUrl()).into(channelIcon);
        tvChannelName.setText(newsVideoItem.getChannelName());

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);
                youTubePlayer.loadVideo(videoId, 0);

            }

            @Override
            public void onError(YouTubePlayer youTubePlayer, PlayerConstants.PlayerError error) {
                super.onError(youTubePlayer, error);
                Log.d("LakPlayerError", error.toString());
            }
        });

        tvChannelWebsite.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, WebViewActivity.class);
            intent1.putExtra("articleUrl", newsVideoItem.getWebsiteUrl());
            startActivity(intent1);
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putAll(youTubePlayerView);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}