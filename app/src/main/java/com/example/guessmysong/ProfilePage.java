package com.example.guessmysong;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.guessmysong.firebase.database.UserRewardSystem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ProfilePage extends AppCompatActivity {

    private UserRewardSystem rewardSystem = UserRewardSystem.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        final TextView userName = (TextView) findViewById(R.id.userData);
        final TextView level = (TextView) findViewById(R.id.levelLayoutText2);
        final TextView experience = (TextView) findViewById(R.id.experienceLayoutText);
        final TextView totalExperience = (TextView) findViewById(R.id.totExperienceLayoutText);
        final TextView totalSongs = (TextView) findViewById(R.id.totalSongsGuessedText);
        final TextView christmasSongs = (TextView) findViewById(R.id.christmasSongText);
        final TextView kidsSongs = (TextView) findViewById(R.id.kidsSongText);
        final TextView loveSongs = (TextView) findViewById(R.id.loveSongText);
        final TextView oldieSongs = (TextView) findViewById(R.id.oldieSongText);
        final TextView partySongs = (TextView) findViewById(R.id.partySongText);
        final TextView sadSongs = (TextView) findViewById(R.id.sadSongText);
        final TextView showerSongs = (TextView) findViewById(R.id.showerSongText);
        final TextView summerSongs = (TextView) findViewById(R.id.summerSongText);

        MainActivity.UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userName.setText("Data of user: " + snapshot.getKey());

                for(DataSnapshot child : snapshot.getChildren()) {
                    int i=0;
                    i++;
                    if(child.getKey().equals("level")) {
                        level.setText("Level: " + child.getValue().toString());
                    } else if(child.getKey().equals("experience")) {
                        experience.setText("Experience: " + child.getValue().toString() + "/" + rewardSystem.GetUserMaxExpForLvl() + " XP");
                    } else if(child.getKey().equals("totalexperience")) {
                        totalExperience.setText("Total Experience: " + child.getValue().toString() + " XP");
                    } else if(child.getKey().equals("songsguessed")) {
                        totalSongs.setText("Total songs guessed: " + child.getValue().toString());
                    } else if(child.getKey().equals("categorysongsguessed")) {
                        for(DataSnapshot type : child.getChildren()) {
                            if(type.getKey().equals("christmas")) {
                                christmasSongs.setText("Christmas songs: " + type.getValue().toString());
                            } else if(type.getKey().equals("kids")) {
                                kidsSongs.setText("Kids songs: " + type.getValue().toString());
                            } else if(type.getKey().equals("love")) {
                                loveSongs.setText("Love songs: " + type.getValue().toString());
                            } else if(type.getKey().equals("oldie")) {
                                oldieSongs.setText("Oldie songs: " + type.getValue().toString());
                            } else if(type.getKey().equals("party")) {
                                partySongs.setText("Party songs: " + type.getValue().toString());
                            } else if(type.getKey().equals("sad")) {
                                sadSongs.setText("Sad songs: " + type.getValue().toString());
                            } else if(type.getKey().equals("shower")) {
                                showerSongs.setText("Shower songs: " + type.getValue().toString());
                            } else if(type.getKey().equals("summer")) {
                                summerSongs.setText("Summer songs: " + type.getValue().toString());
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
