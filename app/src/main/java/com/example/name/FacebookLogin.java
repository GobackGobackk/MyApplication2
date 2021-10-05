package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
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

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Arrays;

public class FacebookLogin extends MainActivity {

    private CallbackManager callbackManager;
    FirebaseAuth mAuth;
    //private LoginButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_facebook_login);

        //loginButton = findViewById(R.id.login_button);
        mAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList("user_gender, user_friend"));
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

//        //這邊用在登入時可以詢問用戶是否提供該資料
//        loginButton.setPermissions(Arrays.asList("user_gender, user_friends"));
//        //為了確認登入的回應結果
//        // The FacebookCallback is the login callback that will be
//        // called on login completion.
//        // This part of code is not actually needed to log in but it is
//        // a way to monitor if login was successful, canceled, or an error occurred.
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                Log.d("Demo", "Login Successful!");
//            }
//
//            @Override
//            public void onCancel() {
//                Log.d("Demo", "Login canceled.");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Log.d("Demo", "Login error");
//            }
//        });
    }
    private void handleFacebookAccessToken(AccessToken token) {


        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateFBUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(FacebookLogin.this,  ""+task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void updateFBUI(FirebaseUser user) {
        Intent intent = new Intent(FacebookLogin.this, TestForFb.class);
        startActivity(intent);
    }
    // The method of onActivityResult() is called when an activity you launch exits.
    // For example, your app starts a camera activity and receives the captured photo as a result in the method.
    //call callbackManager onActivityResult()
    //to pass the login results to the login manager through the callback manager.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        //To request data from Facebook, we use the GraphRequest class.
        // A newMeRequest() is used to retrieve a user's own proofile.
        // To do this, we pass the access token and a GraphJSONObjectCallback
        // which is a callback for requests that result in a JSON object.
//        GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
//                new GraphRequest.GraphJSONObjectCallback() {
//                    @Override
//                    public void onCompleted(JSONObject object, GraphResponse response) {
//                        Log.d("demo", object.toString());
//                    }
//                });
//        //實立化一個 Bundle
//        Bundle bundle = new Bundle();
//        //儲存資料　第一個為參數key，第二個為Value
//        bundle.putString("fields", "gender, name, id, email, first_name, last_name");
//        graphRequest.setParameters(bundle);
//        //An asynchronous call does not block the program from code execution.
//        // In other word, it is running on a separate thread in the background.
//        // Therefore, this Graph API request will be occuring in the background.
//        graphRequest.executeAsync();
    }


}