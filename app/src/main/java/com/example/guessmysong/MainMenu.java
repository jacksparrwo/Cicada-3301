package com.example.guessmysong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }

    public void startOnClick(View view) {
        startActivity(new Intent(MainMenu.this, MainPage.class));
    }

    public void achievementsOnClick(View view) {
    }

    public void optionsOnClick(View view) {
    }

    public void exitOnClick(View view) {
    }
}
