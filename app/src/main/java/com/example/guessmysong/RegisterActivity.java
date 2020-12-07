package com.example.guessmysong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
                ref = FirebaseDatabase.getInstance().getReference().child("credentials");
                ref.child("usernames").child(username);
                ref.child("usernames").child(username).child("password").setValue(password);
                ref.child("usernames").child(username).child("email").setValue(email);

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