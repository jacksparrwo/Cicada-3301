package com.example.guessmysong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainPage extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }

    public void onClickSettings(View view){
        openSettings();
    }
    public void onClickCategories(View view){openCategories();}


    public void openSettings(){
        Intent settings = new Intent(this, SettingsPage.class);
        startActivity(settings);
    }

    public void openCategories(){
        Intent categories = new Intent(this, Categories.class);
        startActivity(categories);
    }
}