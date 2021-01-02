package com.example.guessmysong;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CircleImageView circleImageView;
    private TextView txtName, txtPassword;
    private FirebaseAuth mAuth;
    private CallbackManager callbackManager;
    FirebaseAuth.AuthStateListener mAuthListener;

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mAuth.signOut();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    onStart();
                    finish();
                }
            }
        };

        loginButton = findViewById(R.id.facebookLogin);
        circleImageView = findViewById(R.id.facebookLogo);

        callbackManager = CallbackManager.Factory.create();

        loginButton.setPermissions("email", "public_profile", "user_gender");

      /*  AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if(isLoggedIn){
            onStart();
        } */



        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) { //successful login
                Log.d(TAG, "facebook:onSuccess: " + loginResult);
                startActivity(new Intent(MainActivity.this, MainMenu.class));
                finish();
            }

            @Override
            public void onCancel() {  //canceled login
                Log.d(TAG, "facebook:onCancel");

            }

            @Override
            public void onError(FacebookException error) { //error on login
                Log.d(TAG, "facebook:onError", error);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);



        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try{
                           String firstname = response.getJSONObject().getString("first_name");

                        } catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                });

        Bundle bundle = new Bundle();
        bundle.putString("fields", "gender, name, id, first_name, last_name");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
            if(accessTokenTracker == null){
                LoginManager.getInstance().logOut();

            }
        }
    };

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    public void onResume(){
        super.onResume();
        mAuth.addAuthStateListener(mAuthListener);

    }
    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    public void loginOnClick(View view){
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    public void registerOnClick(View view){
        startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        finish();
    }

    public void updateUI(FirebaseUser user){

        if (user != null) {
            int index = user.getEmail().indexOf('@');
            String displayName = user.getEmail().substring(0,index);
            Toast.makeText(this, displayName + " Signed in successfully", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), MainMenu.class);
            intent.putExtra("User",displayName);
            startActivity(intent);
            finish();

        } else{

        }
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(" ", "handleFacebookAccessToken: " + token);

        AuthCredential credential = FacebookAuthProvider
                .getCredential(token.getToken());
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithCredential:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                } else {
                    Log.w(TAG, "signInWithCredential: failure", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                    updateUI(null);
                }
            }
        });
    }


}