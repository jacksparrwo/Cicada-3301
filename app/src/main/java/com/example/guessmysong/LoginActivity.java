package com.example.guessmysong;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.guessmysong.firebase.database.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText sEmail, sPassword;
    CardView loginButton;
    ProgressBar progressBar;
    private FirebaseAuth fAuth;
    private FirebaseDatabase database;
    private DatabaseReference ref;
    UserModel mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
     //   overridePendingTransition(0,0);

        sEmail = findViewById(R.id.email);
        sPassword = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        progressBar = findViewById(R.id.progressBar);
        fAuth = FirebaseAuth.getInstance();
        mUser = new UserModel();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = sEmail.getText().toString().trim();
                String password = sPassword.getText().toString().trim();

                if(TextUtils.isEmpty(password)){
                    sPassword.setError("Password is required");
                    return;
                }

                if(password.length() < 6){
                    sPassword.setError("Password is too short!");
                    return;
                }

                //Authenticate the user


                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            int index = email.indexOf('@');
                            String userName = email.substring(0,index);
                            mUser.setUsername(userName);
                            Toast.makeText(LoginActivity.this, "Logged in: " + mUser.getUsername(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                            intent.putExtra("Email", userName.trim());
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }

}