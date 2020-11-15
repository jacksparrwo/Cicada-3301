package com.example.guessmysong;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.youtube.player.YouTubePlayerView;

public class YTSingleSong extends AppCompatActivity {

    private YouTubePlayerView yt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_song);
    }
}
