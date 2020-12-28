package com.example.guessmysong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessmysong.firebase.database.UserModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class MainMenu extends AppCompatActivity {

    FirebaseUser fUser;
    public Bundle getBundle = null;
    UserModel mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        TextView u = findViewById(R.id.name);
        getBundle = this.getIntent().getExtras();

        if(fUser != null){
            for(UserInfo userInfo : fUser.getProviderData()){
                if(userInfo.getProviderId().equals("facebook.com")){
                    String name = getBundle.getString("User");
                    u.setText(name, TextView.BufferType.NORMAL);
                    // String facebookUser = getIntent().getStringExtra("User");
                    // u.setText(facebookUser, TextView.BufferType.NORMAL);
                }
                else{
                    String userName = getIntent().getStringExtra("User");
                    u.setText(mUser.getUsername() , TextView.BufferType.NORMAL);
                }
            }
        }
    }

    public void startOnClick(View view) {
        startActivity(new Intent(MainMenu.this, GameModes.class));
    }

    public void achievementsOnClick(View view) {
    }

    public void optionsOnClick(View view) {
        Intent settings = new Intent(this, SettingsPage.class);
        startActivity(settings);
    }

    public void exitOnClick(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
