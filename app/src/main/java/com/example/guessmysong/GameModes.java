package com.example.guessmysong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GameModes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemodes);
    }

    public void singlePlayerOnClick(View view) {
        startActivity(new Intent(this, Categories.class));
    }

    public void playWithFriendsOnClick(View view) {

    }
}
