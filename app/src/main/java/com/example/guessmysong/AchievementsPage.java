package com.example.guessmysong;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class AchievementsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        final TextView userData = (TextView) findViewById(R.id.userData2);
        final TextView christmasAchiev = (TextView) findViewById(R.id.christmasAchievementText);
        final TextView kidsAchiev = (TextView) findViewById(R.id.kidsAchievementsText);
        final TextView loveAchiev = (TextView) findViewById(R.id.loveAchievementsText);
        final TextView oldieAchiev = (TextView) findViewById(R.id.oldieAchievementsText);
        final TextView partyAchiev = (TextView) findViewById(R.id.partyAchievementsText);
        final TextView sadAchiev = (TextView) findViewById(R.id.sadAchievementsText);
        final TextView showerAchiev = (TextView) findViewById(R.id.showerAchievementsText);
        final TextView summerAchiev = (TextView) findViewById(R.id.summerAchievementsText);

        MainActivity.UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userData.setText("Achievements of user: " + snapshot.getKey());

                for(DataSnapshot child : snapshot.getChildren()) {
                    if(child.getKey().equals("achievements")) {
                        for(DataSnapshot type : child.getChildren()) {
                            if(type.getKey().equals("christmas")) {
                                christmasAchiev.setText("Christmas: " + type.getValue().toString());
                            } else if(type.getKey().equals("kids")) {
                                kidsAchiev.setText("Kids: " + type.getValue().toString());
                            } else if(type.getKey().equals("love")) {
                                loveAchiev.setText("Love: " + type.getValue().toString());
                            } else if(type.getKey().equals("oldie")) {
                                oldieAchiev.setText("Oldie: " + type.getValue().toString());
                            } else if(type.getKey().equals("party")) {
                                partyAchiev.setText("Party: " + type.getValue().toString());
                            } else if(type.getKey().equals("sad")) {
                                sadAchiev.setText("Sad: " + type.getValue().toString());
                            } else if(type.getKey().equals("shower")) {
                                showerAchiev.setText("Shower: " + type.getValue().toString());
                            } else if(type.getKey().equals("summer")) {
                                summerAchiev.setText("Summer: " + type.getValue().toString());
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void onClickBack(View view) {
        finish();
    }
}
