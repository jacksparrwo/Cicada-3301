package com.example.guessmysong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guessmysong.firebase.storage.EMusicTypes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class Categories extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        final TextView lvl = (TextView)findViewById(R.id.levelLayoutText);
        MainActivity.UserRef.child("level").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lvl.setText("Level: " + snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onClickXmas(View view){
        Intent xmas = new Intent(this, SinglePlayerSong.class);
        xmas.putExtra("category", EMusicTypes.CHRISTMAS.getName());
        startActivity(xmas);
    }

    public void onClickLove(View view) {
        Intent love = new Intent(this, SinglePlayerSong.class);
        love.putExtra("category", EMusicTypes.LOVE.getName());
        startActivity(love);
    }

    public void onClickShower(View view) {
        Intent shower = new Intent(this, SinglePlayerSong.class);
        shower.putExtra("category", EMusicTypes.SHOWER.getName());
        startActivity(shower);
    }

    public void onClickParty(View view) {
        Intent party = new Intent(this, SinglePlayerSong.class);
        party.putExtra("category", EMusicTypes.PARTY.getName());
        startActivity(party);
    }

    public void onClickSummer(View view) {
        Intent summer = new Intent(this, SinglePlayerSong.class);
        summer.putExtra("category", EMusicTypes.SUMMER.getName());
        startActivity(summer);
    }

    public void onClickOldie(View view) {
        Intent oldie = new Intent(this, SinglePlayerSong.class);
        oldie.putExtra("category", EMusicTypes.OLDIE.getName());
        startActivity(oldie);
    }

    public void onClickKids(View view) {
        Intent kids = new Intent(this, SinglePlayerSong.class);
        kids.putExtra("category", EMusicTypes.KIDS.getName());
        startActivity(kids);
    }

    public void onClickSad(View view) {
        Intent sad = new Intent(this, SinglePlayerSong.class);
        sad.putExtra("category", EMusicTypes.SAD.getName());
        startActivity(sad);
    }

}