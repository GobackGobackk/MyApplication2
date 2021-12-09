package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.name.model.FBFriends;
import com.example.name.model.User;
import com.example.name.model.UserHelperClass;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity23 extends AppCompatActivity {
    @BindView(R.id.seekBar1A)
    SeekBar a1;
    @BindView(R.id.seekBar1B)
    SeekBar b1;
    @BindView(R.id.seekBar2A)
    SeekBar a2;
    @BindView(R.id.seekBar2B)
    SeekBar b2;
    @BindView(R.id.seekBar3A)
    SeekBar a3;
    @BindView(R.id.seekBar3B)
    SeekBar b3;
    @BindView(R.id.seekBar4A)
    SeekBar a4;
    @BindView(R.id.seekBar4B)
    SeekBar b4;
    @BindView(R.id.seekBar5A)
    SeekBar a5;
    @BindView(R.id.seekBar5B)
    SeekBar b5;
    @BindView(R.id.seekBar6A)
    SeekBar a6;
    @BindView(R.id.seekBar6B)
    SeekBar b6;
    @BindView(R.id.seekBar7A)
    SeekBar a7;
    @BindView(R.id.seekBar7B)
    SeekBar b7;
    @BindView(R.id.seekBar8A)
    SeekBar a8;
    @BindView(R.id.seekBar8B)
    SeekBar b8;
    @BindView(R.id.seekBar9A)
    SeekBar a9;
    @BindView(R.id.seekBar9B)
    SeekBar b9;
    @BindView(R.id.seekBar10A)
    SeekBar a10;
    @BindView(R.id.seekBar10B)
    SeekBar b10;
    @BindView(R.id.seekBar11A)
    SeekBar a11;
    @BindView(R.id.seekBar11B)
    SeekBar b11;
    @BindView(R.id.seekBar12A)
    SeekBar a12;
    @BindView(R.id.seekBar12B)
    SeekBar b12;
    @BindView(R.id.seekBar13A)
    SeekBar a13;
    @BindView(R.id.seekBar13B)
    SeekBar b13;
    @BindView(R.id.seekBar14A)
    SeekBar a14;
    @BindView(R.id.seekBar14B)
    SeekBar b14;
    @BindView(R.id.seekBar15A)
    SeekBar a15;
    @BindView(R.id.seekBar15B)
    SeekBar b15;
    @BindView(R.id.seekBar16A)
    SeekBar a16;
    @BindView(R.id.seekBar16B)
    SeekBar b16;
    @BindView(R.id.seekBar17A)
    SeekBar a17;
    @BindView(R.id.seekBar17B)
    SeekBar b17;
    @BindView(R.id.seekBar18A)
    SeekBar a18;
    @BindView(R.id.seekBar18B)
    SeekBar b18;
    @BindView(R.id.textview_score_1A)
    TextView ta1;
    @BindView(R.id.textview_score_1B)
    TextView tb1;
    @BindView(R.id.textview_score_2A)
    TextView ta2;
    @BindView(R.id.textview_score_2B)
    TextView tb2;
    @BindView(R.id.textview_score_3A)
    TextView ta3;
    @BindView(R.id.textview_score_3B)
    TextView tb3;
    @BindView(R.id.textview_score_4A)
    TextView ta4;
    @BindView(R.id.textview_score_4B)
    TextView tb4;
    @BindView(R.id.textview_score_5A)
    TextView ta5;
    @BindView(R.id.textview_score_5B)
    TextView tb5;
    @BindView(R.id.textview_score_6A)
    TextView ta6;
    @BindView(R.id.textview_score_6B)
    TextView tb6;
    @BindView(R.id.textview_score_7A)
    TextView ta7;
    @BindView(R.id.textview_score_7B)
    TextView tb7;
    @BindView(R.id.textview_score_8A)
    TextView ta8;
    @BindView(R.id.textview_score_8B)
    TextView tb8;
    @BindView(R.id.textview_score_9A)
    TextView ta9;
    @BindView(R.id.textview_score_9B)
    TextView tb9;
    @BindView(R.id.textview_score_10A)
    TextView ta10;
    @BindView(R.id.textview_score_10B)
    TextView tb10;
    @BindView(R.id.textview_score_11A)
    TextView ta11;
    @BindView(R.id.textview_score_11B)
    TextView tb11;
    @BindView(R.id.textview_score_12A)
    TextView ta12;
    @BindView(R.id.textview_score_12B)
    TextView tb12;
    @BindView(R.id.textview_score_13A)
    TextView ta13;
    @BindView(R.id.textview_score_13B)
    TextView tb13;
    @BindView(R.id.textview_score_14A)
    TextView ta14;
    @BindView(R.id.textview_score_14B)
    TextView tb14;
    @BindView(R.id.textview_score_15A)
    TextView ta15;
    @BindView(R.id.textview_score_15B)
    TextView tb15;
    @BindView(R.id.textview_score_16A)
    TextView ta16;
    @BindView(R.id.textview_score_16B)
    TextView tb16;
    @BindView(R.id.textview_score_17A)
    TextView ta17;
    @BindView(R.id.textview_score_17B)
    TextView tb17;
    @BindView(R.id.textview_score_18A)
    TextView ta18;
    @BindView(R.id.textview_score_18B)
    TextView tb18;
    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef, eventRef;
    private String grpId, groupName, userId;
    private FirebaseAuth mAuth;
    //胡新加的
    private DatabaseReference reference, FriendReference, UserReference;
    String id, FBname, FBemail, gender, fbId;
    //分隔線

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main23);
        ButterKnife.bind(this);

//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        userId = preferences.getString("UserId", "");
        //上面是原本註解掉的
//        Intent intent = this.getIntent();
//        Bundle bundle = intent.getExtras();
//        userId = bundle.getString("UserId");
//        database = FirebaseDatabase.getInstance();
//        userRef = database.getReference("chatRooms/userProfiles/"+ userId);
//        eventRef = database.getReference("chatRooms/calendar/" + userId);
//        userRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//            }
//            @Override
//            public void onCancelled(DatabaseError error) {
//            }
//        });
        //胡新加的
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        FirebaseUser mUser = mAuth.getCurrentUser();
        reference = database.getReference("chatRooms/userProfiles/");
        id = mUser.getUid();
        FriendReference = database.getReference("chatRooms/userProfiles/"+id+"/fbFriends");
        UserReference = database.getReference("chatRooms/userProfiles/"+id);

//        if(mUser != null) {
//            GraphRequest graphRequest = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
//                    new GraphRequest.GraphJSONObjectCallback() {
//                        @Override
//                        public void onCompleted(JSONObject object, GraphResponse response) {
//                            Log.d("demo", object.toString());
//                            try {
//                                //id = object.getString("id");
//                                FBname = object.getString("name");
//                                FBemail = object.getString("email");
////                            FBemail = mUser.getEmail();
//                                gender =  object.getString("gender");
//                                fbId = object.getString("id");
//                                UserHelperClass helperClass = new UserHelperClass(id, FBname, FBemail, gender, fbId);
//                                reference.child(id).setValue(helperClass);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//            //實立化一個 Bundle
//            Bundle userbundle = new Bundle();
//            //儲存資料　第一個為參數key，第二個為Value
//            userbundle.putString("fields", "gender, name, id, email, first_name, last_name");
//            graphRequest.setParameters(userbundle);
//            //An asynchronous call does not block the program from code execution.
//            // In other word, it is running on a separate thread in the background.
//            // Therefore, this Graph API request will be occuring in the background.
//            graphRequest.executeAsync();
//
//            //retrieve FB friends
//            GraphRequest graphRequestFriends = GraphRequest.newMyFriendsRequest(AccessToken.getCurrentAccessToken(),
//                    new GraphRequest.GraphJSONArrayCallback() {
//                        @Override
//                        public void onCompleted(JSONArray objects, GraphResponse response) {
//                            Log.d("Demo", objects.toString());
//                            //Log.d("demo", String.valueOf(objects.length())); objects.length()可以成功顯示該用戶有多少位朋友。
//                            ArrayList<FBFriends> fbFriends = new ArrayList<>();
//                            for (int i = 0; i < objects.length(); i++) {
//                                try {
//                                    JSONObject object = objects.getJSONObject(i);
//                                    String FacebookId = object.getString("id");
//                                    fbFriends.add(new FBFriends(object.getString("name"), FacebookId));
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
////                            FriendReference.addListenerForSingleValueEvent();
//                            Log.d("Demoo", String.valueOf(fbFriends));
//                            FriendReference.setValue(fbFriends);
//                        }
//                    });
//            Bundle bundleFriend = new Bundle();
//            bundleFriend.putString("fields", "id, name");
//            graphRequestFriends.setParameters(bundleFriend);
//            graphRequestFriends.executeAsync();
//        }

        //分隔線
        a1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta1.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta2.setText(progress+"/ 3");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta3.setText(progress+"/ 3");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta4.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta5.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta6.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta7.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta8.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta9.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a10.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta10.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a11.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta11.setText(progress+"/ 3");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a12.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta12.setText(progress+"/ 3");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a13.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta13.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a14.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta14.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a15.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta15.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a16.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta16.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a17.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta17.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        a18.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ta18.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb1.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb2.setText(progress+"/ 3");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb3.setText(progress+"/ 3");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb4.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb5.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb6.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb7.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb8.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b9.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb9.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b10.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb10.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b11.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb11.setText(progress+"/ 3");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b12.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb12.setText(progress+"/ 3");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b13.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb13.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b14.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb14.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b15.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb15.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b16.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb16.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b17.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb17.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        b18.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tb18.setText(progress+"/ 3");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @OnClick(R.id.textViewExit)
    public void jkl(View view){
        Intent intent = new Intent(MainActivity23.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.image_back)
    public void jkll(View view){
        Intent intent = new Intent(MainActivity23.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.SubmitButton)
    public void jlk(View view) {
        String ata1 = ta1.getText().toString().replace(" ", "").split("/")[0];
        int ita1 = Integer.valueOf(ata1);
        String ata2 = ta2.getText().toString().replace(" ", "").split("/")[0];
        int ita2 = Integer.valueOf(ata2);
        String ata3 = ta3.getText().toString().replace(" ", "").split("/")[0];
        int ita3 = Integer.valueOf(ata3);
        String ata4 = ta4.getText().toString().replace(" ", "").split("/")[0];
        int ita4 = Integer.valueOf(ata4);
        String ata5 = ta5.getText().toString().replace(" ", "").split("/")[0];
        int ita5 = Integer.valueOf(ata5);
        String ata6 = ta6.getText().toString().replace(" ", "").split("/")[0];
        int ita6 = Integer.valueOf(ata6);
        String ata7 = ta7.getText().toString().replace(" ", "").split("/")[0];
        int ita7 = Integer.valueOf(ata7);
        String ata8 = ta8.getText().toString().replace(" ", "").split("/")[0];
        int ita8 = Integer.valueOf(ata8);
        String ata9 = ta9.getText().toString().replace(" ", "").split("/")[0];
        int ita9 = Integer.valueOf(ata9);
        String ata10 = ta10.getText().toString().replace(" ", "").split("/")[0];
        int ita10 = Integer.valueOf(ata10);
        String ata11 = ta11.getText().toString().replace(" ", "").split("/")[0];
        int ita11 = Integer.valueOf(ata11);
        String ata12 = ta12.getText().toString().replace(" ", "").split("/")[0];
        int ita12 = Integer.valueOf(ata12);
        String ata13 = ta13.getText().toString().replace(" ", "").split("/")[0];
        int ita13 = Integer.valueOf(ata13);
        String ata14 = ta14.getText().toString().replace(" ", "").split("/")[0];
        int ita14 = Integer.valueOf(ata14);
        String ata15 = ta15.getText().toString().replace(" ", "").split("/")[0];
        int ita15 = Integer.valueOf(ata15);
        String ata16 = ta16.getText().toString().replace(" ", "").split("/")[0];
        int ita16 = Integer.valueOf(ata16);
        String ata17 = ta17.getText().toString().replace(" ", "").split("/")[0];
        int ita17 = Integer.valueOf(ata17);
        String ata18 = ta18.getText().toString().replace(" ", "").split("/")[0];
        int ita18 = Integer.valueOf(ata18);
        String atb1 = tb1.getText().toString().replace(" ", "").split("/")[0];
        int itb1 = Integer.valueOf(atb1);
        String atb2 = tb2.getText().toString().replace(" ", "").split("/")[0];
        int itb2 = Integer.valueOf(atb2);
        String atb3 = tb3.getText().toString().replace(" ", "").split("/")[0];
        int itb3 = Integer.valueOf(atb3);
        String atb4 = tb4.getText().toString().replace(" ", "").split("/")[0];
        int itb4 = Integer.valueOf(atb4);
        String atb5 = tb5.getText().toString().replace(" ", "").split("/")[0];
        int itb5 = Integer.valueOf(atb5);
        String atb6 = tb6.getText().toString().replace(" ", "").split("/")[0];
        int itb6 = Integer.valueOf(atb6);
        String atb7 = tb7.getText().toString().replace(" ", "").split("/")[0];
        int itb7 = Integer.valueOf(atb7);
        String atb8 = tb8.getText().toString().replace(" ", "").split("/")[0];
        int itb8 = Integer.valueOf(atb8);
        String atb9 = tb9.getText().toString().replace(" ", "").split("/")[0];
        int itb9 = Integer.valueOf(atb9);
        String atb10 = tb10.getText().toString().replace(" ", "").split("/")[0];
        int itb10 = Integer.valueOf(atb10);
        String atb11 = tb11.getText().toString().replace(" ", "").split("/")[0];
        int itb11 = Integer.valueOf(atb11);
        String atb12 = tb12.getText().toString().replace(" ", "").split("/")[0];
        int itb12 = Integer.valueOf(atb12);
        String atb13 = tb13.getText().toString().replace(" ", "").split("/")[0];
        int itb13 = Integer.valueOf(atb13);
        String atb14 = tb14.getText().toString().replace(" ", "").split("/")[0];
        int itb14 = Integer.valueOf(atb14);
        String atb15 = tb15.getText().toString().replace(" ", "").split("/")[0];
        int itb15 = Integer.valueOf(atb15);
        String atb16 = tb16.getText().toString().replace(" ", "").split("/")[0];
        int itb16 = Integer.valueOf(atb16);
        String atb17 = tb17.getText().toString().replace(" ", "").split("/")[0];
        int itb17 = Integer.valueOf(atb17);
        String atb18 = tb18.getText().toString().replace(" ", "").split("/")[0];
        int itb18 = Integer.valueOf(atb18);

        if(ita1+itb1==3 && ita2+itb2==3 && ita3+itb3==3 && ita4+itb4==3 && ita5+itb5==3 && ita6+itb6==3 && ita7+itb7==3 && ita8+itb8==3 && ita9+itb9==3 && ita10+itb10==3
        && ita11+itb11==3 && ita12+itb12==3 && ita13+itb13==3 && ita14+itb14==3 && ita15+itb15==3 && ita16+itb16==3 && ita17+itb17==3 && ita18+itb18==3 ) {
            int o,g,d,i, result = 0;
            o=ita1+itb3+ita5+itb7+ita9+itb11+ita13+itb15+ita17;
            g=itb1+ita3+itb5+ita7+itb9+ita11+itb13+ita15+itb17;
            d=itb2+ita4+itb6+ita8+itb10+ita12+itb14+ita16+itb18;
            i=ita2+itb4+ita6+itb8+ita10+itb12+ita14+itb16+ita18;
//            userRef.child("active").child("O").setValue(o);
//            userRef.child("active").child("G").setValue(g);
//            userRef.child("active").child("D").setValue(d);
//            userRef.child("active").child("I").setValue(i);

            //胡新加的
            UserReference.child("active").child("O").setValue(o);
            UserReference.child("active").child("G").setValue(g);
            UserReference.child("active").child("D").setValue(d);
            UserReference.child("active").child("I").setValue(i);
            //分隔線
//            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString("UserId", userId);
//            editor.apply();
            Intent intent = new Intent(MainActivity23.this, MainActivity26.class);
//            Bundle bundle = new Bundle();
//            bundle.putString("UserId", userId);
//            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }


}