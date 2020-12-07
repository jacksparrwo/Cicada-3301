package com.example.guessmysong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

public class Categories extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
    }

    public void onClickXmas(View view){
        Intent xmas = new Intent(this, Xmas.class);
        startActivity(xmas);

    }
}