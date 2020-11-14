package com.example.guessmysong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import de.hdodenhof.circleimageview.CircleImageView;

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