package com.example.guessmysong;

import android.os.Bundle;

import com.example.guessmysong.firebase.storage.EMusicTypes;
import com.example.guessmysong.firebase.storage.StorageHandler;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YTSingleSong extends YouTubeBaseActivity {

    private YouTubePlayerView mBouncySongYTPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_song);
        mBouncySongYTPlayer = this.<YouTubePlayerView>findViewById(R.id.bouncySong);

        StorageHandler st = StorageHandler.getInstance();
        st.getMusicByType(EMusicTypes.ROCK);

        //YouTube.Search();
        // prepare the listener
        YouTubePlayer.OnInitializedListener onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("KGD2N5hJ2e0");
                youTubePlayer.setManageAudioFocus(true);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        // initialize video
        mBouncySongYTPlayer.initialize("AIzaSyBxzCAg00LCJFtVOPd-X0Rw6g_OeidRppQ", onInitializedListener);
    }
}
