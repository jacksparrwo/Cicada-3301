package com.example.guessmysong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    CardView login;
    SharedPreferences sp;
//    EditText userField = (EditText)findViewById(R.id.username);
  //  EditText passwordField = (EditText)findViewById(R.id.password);

   // String user = userField.getText().toString();
    //String password =passwordField.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (CardView) findViewById(R.id.loginButton);
        sp = getSharedPreferences("login",MODE_PRIVATE);

        if(sp.getBoolean("logged",false)){
            goToMainActivity();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainActivity();
                sp.edit().putBoolean("logged", true).apply();
            }
        });
    }

    public void goToMainActivity(){
        startActivity(new Intent(this, MainPage.class));
    }


}