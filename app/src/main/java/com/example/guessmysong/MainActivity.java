package com.example.guessmysong;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private LoginButton loginButton;
    private CircleImageView circleImageView;
    private TextView txtName, txtPassword;
    private MainActivity crtInstance;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        crtInstance = this;
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.facebookLogin);
        txtName = findViewById(R.id.username);
        txtPassword= findViewById(R.id.password);
        circleImageView = findViewById(R.id.facebookLogo);

        callbackManager = CallbackManager.Factory.create();
        checkLoginStatus();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            if(currentAccessToken == null){
                txtName.setText("");
                txtPassword.setText("");
                circleImageView.setImageResource(0);
                Toast.makeText(MainActivity.this,"User logged out", Toast.LENGTH_LONG).show();
            }
            else{
                openMainPage(currentAccessToken);
            }
        }
    };


    public  void openMainPage(AccessToken newAccessToken) {

        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                /*try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String password = object.getString("password");
                    String id = object.getString("id");

                    txtName.setText(first_name + " "+ last_name);
                    txtPassword.setText(password);

                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();



                } catch (JSONException e){
                    e.printStackTrace();
                }*/


                // bosa test here
                Intent ytTest = new Intent(crtInstance, YTSingleSong.class);
                startActivity(ytTest);

            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name, last_name, password, id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void checkLoginStatus(){
        if(AccessToken.getCurrentAccessToken()!=null){
            openMainPage(AccessToken.getCurrentAccessToken());
        }
    }
}