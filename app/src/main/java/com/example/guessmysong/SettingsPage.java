package com.example.guessmysong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;

public class SettingsPage extends AppCompatActivity {

    SeekBar seekBarVolume;
    Handler handler = new Handler();
    AudioManager audiomanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        seekBarVolume = findViewById(R.id.seekBarVolume);
        audiomanager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //get maxVolume
        int maxVolume = audiomanager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        //get currentVolume
        int currentVolume = audiomanager.getStreamVolume(AudioManager.STREAM_MUSIC);

        seekBarVolume.setMax(maxVolume);
        seekBarVolume.setProgress(currentVolume);
        seekBarVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audiomanager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}