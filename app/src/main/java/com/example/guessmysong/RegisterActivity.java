package com.example.guessmysong;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText sEmail, sUsername, sPassword;
    CardView sRegisterButton;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sEmail = findViewById(R.id.email);
        sUsername = findViewById(R.id.username);
        sPassword = findViewById(R.id.password);
        sRegisterButton = (CardView) findViewById(R.id.registerButton);

        fAuth = FirebaseAuth.getInstance();  //we are getting the current instance of the database
        progressBar = findViewById(R.id.progressBar);

        sRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = sEmail.getText().toString().trim();
                final String username = sUsername.getText().toString().trim();
                String password = sPassword.getText().toString().trim();
                String s = "";

                ref = FirebaseDatabase.getInstance().getReference().child("credentials");
                ref.child("usernames").child(username);
                ref.child("usernames").child(username).child("password").setValue(password);
                ref.child("usernames").child(username).child("email").setValue(email);
                ref.child("usernames").child(username).child("level").setValue(0);
                ref.child("usernames").child(username).child("experience").setValue(0);
                ref.child("usernames").child(username).child("achievements").child("christmas").setValue(s);
                ref.child("usernames").child(username).child("achievements").child("love").setValue(s);
                ref.child("usernames").child(username).child("achievements").child("shower").setValue(s);
                ref.child("usernames").child(username).child("achievements").child("party").setValue(s);
                ref.child("usernames").child(username).child("achievements").child("summer").setValue(s);
                ref.child("usernames").child(username).child("achievements").child("oldie").setValue(s);
                ref.child("usernames").child(username).child("achievements").child("kids").setValue(s);
                ref.child("usernames").child(username).child("achievements").child("sad").setValue(s);
                ref.child("usernames").child(username).child("songsguessed").setValue(0);
                ref.child("usernames").child(username).child("categorysongsguessed").child("christmas").setValue(0);
                ref.child("usernames").child(username).child("categorysongsguessed").child("love").setValue(0);
                ref.child("usernames").child(username).child("categorysongsguessed").child("shower").setValue(0);
                ref.child("usernames").child(username).child("categorysongsguessed").child("party").setValue(0);
                ref.child("usernames").child(username).child("categorysongsguessed").child("summer").setValue(0);
                ref.child("usernames").child(username).child("categorysongsguessed").child("oldie").setValue(0);
                ref.child("usernames").child(username).child("categorysongsguessed").child("kids").setValue(0);
                ref.child("usernames").child(username).child("categorysongsguessed").child("sad").setValue(0);
                ref.child("usernames").child(username).child("categorysongsguessednumbers").child("christmas").setValue(s);
                ref.child("usernames").child(username).child("categorysongsguessednumbers").child("love").setValue(s);
                ref.child("usernames").child(username).child("categorysongsguessednumbers").child("shower").setValue(s);
                ref.child("usernames").child(username).child("categorysongsguessednumbers").child("party").setValue(s);
                ref.child("usernames").child(username).child("categorysongsguessednumbers").child("summer").setValue(s);
                ref.child("usernames").child(username).child("categorysongsguessednumbers").child("oldie").setValue(s);
                ref.child("usernames").child(username).child("categorysongsguessednumbers").child("kids").setValue(s);
                ref.child("usernames").child(username).child("categorysongsguessednumbers").child("sad").setValue(s);
                ref.child("usernames").child(username).child("totalexperience").setValue(0);

                if(TextUtils.isEmpty(email)){
                    sEmail.setError("Email is required!");
                    return;
                }

                if(TextUtils.isEmpty(username)){
                    sUsername.setError("Username is required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    sPassword.setError("Password is required");
                    return;
                }

                if(password.length() < 6){
                    sPassword.setError("Password is too short!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                //Register the user in the FireBase

                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "User created: " + username, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                            intent.putExtra("Username", username);
                            startActivity(intent);

                        }else{
                            Toast.makeText(RegisterActivity.this, "Couldn't create user" + task.getException().getMessage() , Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });



    }
}