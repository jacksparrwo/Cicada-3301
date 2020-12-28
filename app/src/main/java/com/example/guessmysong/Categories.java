package com.example.guessmysong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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

    public void onClickLove(View view) {

    }

    public void onClickShower(View view) {

    }

    public void onClickParty(View view) {

    }

    public void onClickSummer(View view) {

    }

    public void onClickOldie(View view) {

    }

    public void onClickKids(View view) {

    }

    public void onClickSad(View view) {

    }

}