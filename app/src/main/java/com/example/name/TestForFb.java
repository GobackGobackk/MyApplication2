package com.example.name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.name.model.FBFriends;
import com.example.name.model.UserHelperClass;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TestForFb extends AppCompatActivity {

    Button jumpbutton;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference, FriendReference;
    String id, FBname, FBemail, gender, fbId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_for_fb);

        //Initialize Firebase;
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        reference = database.getReference("chatRooms/userProfiles/");
        id = mUser.getUid();
        FriendReference = database.getReference("chatRooms/userProfiles/"+id+"/fbFriends");

        jumpbutton = findViewById(R.id.jump);
        jumpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TestForFb.this, fbtest.class));
            }
        });

        if(mUser != null) {
            id = mUser.getUid();
            //FBemail = mUser.getEmail();
            GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            Log.d("demo", object.toString());
                            try {
                                //id = object.getString("id");
                                FBname = object.getString("name");
                                FBemail = object.getString("email");
//                            FBemail = mUser.getEmail();
                                gender =  object.getString("gender");
                                fbId = object.getString("id");
                                UserHelperClass helperClass = new UserHelperClass(id, FBname, FBemail, gender, fbId);
                                reference.child(id).setValue(helperClass);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            //實立化一個 Bundle
            Bundle bundle = new Bundle();
            //儲存資料　第一個為參數key，第二個為Value
            bundle.putString("fields", "gender, name, id, email, first_name, last_name");
            graphRequest.setParameters(bundle);
            //An asynchronous call does not block the program from code execution.
            // In other word, it is running on a separate thread in the background.
            // Therefore, this Graph API request will be occuring in the background.
            graphRequest.executeAsync();

            //retrieve FB friends
            GraphRequest graphRequestFriends = GraphRequest.newMyFriendsRequest(AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONArrayCallback() {
                        @Override
                        public void onCompleted(JSONArray objects, GraphResponse response) {
                            Log.d("Demo", objects.toString());
                            //Log.d("demo", String.valueOf(objects.length())); objects.length()可以成功顯示該用戶有多少位朋友。
                            ArrayList<FBFriends> fbFriends = new ArrayList<>();
                            for (int i = 0; i < objects.length(); i++) {
                                try {
                                    JSONObject object = objects.getJSONObject(i);
                                    String FacebookId = object.getString("id");
                                    fbFriends.add(new FBFriends(object.getString("name"), FacebookId));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
//                            FriendReference.addListenerForSingleValueEvent();
                            Log.d("Demoo", String.valueOf(fbFriends));
                            FriendReference.setValue(fbFriends);
                        }
                    });
            Bundle bundleFriend = new Bundle();
            bundleFriend.putString("fields", "id, name");
            graphRequestFriends.setParameters(bundleFriend);
            graphRequestFriends.executeAsync();
        }
    }
//    AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
//        @Override
//        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
//            if (currentAccessToken == null) {
//                LoginManager.getInstance().logOut();
//            }
//        }
//    };
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
}