package com.example.guessmysong;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainPage extends AppCompatActivity {




    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Intent intent = getIntent();
        TextView u = findViewById(R.id.welcome);
        u.setText("Let's sing!" , TextView.BufferType.NORMAL);


    }

    public void onClickSettings(View view){
        openSettings();
    }
    public void onClickCategories(View view){openCategories();}
    public void onClickLogout(View view){ logout();}


    public void openSettings(){
        Intent settings = new Intent(this, SettingsPage.class);
        startActivity(settings);
    }

    public void openCategories(){
        Intent categories = new Intent(this, Categories.class);
        startActivity(categories);
    }
    public void logout(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

}