package com.example.guessmysong;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessmysong.firebase.storage.EMusicTypes;
import com.example.guessmysong.firebase.storage.StorageHandler;


public class Xmas extends AppCompatActivity {

    private StorageHandler mStorage = StorageHandler.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmas);
        mStorage.PlayRandomSong(EMusicTypes.CHRISTMAS);
    }

    public void onClickCheck(View v){

    }

    public void changeMusicOnClick(View v){

    }
}