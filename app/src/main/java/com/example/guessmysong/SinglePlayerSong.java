package com.example.guessmysong;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessmysong.firebase.storage.StorageHandler;


public class SinglePlayerSong extends AppCompatActivity {

    private StorageHandler mStorage = StorageHandler.getInstance();
    EditText songName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmas);
        mStorage.PlayRandomSong(getIntent().getStringExtra("category"));

        songName = findViewById(R.id.songName);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mStorage.ResetMediaPlayer();
    }

    public void onClickCheck(View v){
        String song = songName.getText().toString().toLowerCase().trim();
        boolean res = mStorage.CheckSong(song);

        if(true == res) {
            Toast.makeText(SinglePlayerSong.this, "You got this song!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SinglePlayerSong.this, "Not correct!", Toast.LENGTH_SHORT).show();
        }
    }

    public void changeMusicOnClick(View v) {

    }
}