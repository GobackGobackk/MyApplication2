package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.example.name.model.GroupChatRoom;
import com.example.name.model.GroupOnlineUsers;
import com.example.name.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.messaging.FirebaseMessaging;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity22 extends AppCompatActivity {
    @BindView(R.id.textView71)
    TextView text;
    private FirebaseDatabase database;
    private DatabaseReference myRef, myRef2, userRef, groupRef;
    private String grpId, groupName, userId, calId, goal;
    private User user;
    private GroupChatRoom groupChatRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        ButterKnife.bind(this);
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));

        }
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        groupName = bundle.getString("groupName");
//        goal = bundle.getString("goal");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chatRooms/userProfiles/"+userId);
        groupRef = database.getReference("chatRooms/group/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.child("gender").getValue().toString().equals("Male")){
                    text.setText("Male");
                }
                else {
                    text.setText("Female");
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    @OnClick(R.id.button2)//非好友創群
    public void ajk(View view){
        grpId = groupRef.push().getKey();
        groupChatRoom = new GroupChatRoom();
        String createdOn = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(new Date());
        groupChatRoom.setGroupId(grpId);
        groupChatRoom.setGroupName(groupName);
        groupChatRoom.setCreatedOn(createdOn);
        if(text.getText().toString().equals("Male")){
            groupChatRoom.setPic("Male");
        }
        else{
            groupChatRoom.setPic("Female");
        }
        groupRef.child(grpId).setValue(groupChatRoom);

        Python py = Python.getInstance();
        py.getModule("group").get("main").call(userId,grpId);
        groupRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    for(DataSnapshot child2 : child.child("members").getChildren()){
                        myRef2 = database.getReference("chatRooms/userProfiles/"+child2.getKey());
                        myRef2.child("group").child(grpId).child("groupId").setValue(grpId);
                        myRef2.child("group").child(grpId).child("groupName").setValue(groupName);
                        //將選到的 topic 進行 subscribe (這個topic屆時會替換成群組id)
                        FirebaseMessaging.getInstance().subscribeToTopic(grpId);
                        //如果訂閱成功，就同步更新資料庫的判斷
                        String HasSubscribed = "1";
                        myRef2.child("group").child(grpId).child("HasSubscribed").setValue(HasSubscribed);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });
        Intent intent = new Intent(MainActivity22.this, MainActivity25.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.button7)//好友創群
    public void jj(View view){
        grpId = groupRef.push().getKey();
        groupChatRoom = new GroupChatRoom();
        String createdOn = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(new Date());
        groupChatRoom.setGroupId(grpId);
        groupChatRoom.setGroupName(groupName);
        groupChatRoom.setCreatedOn(createdOn);
        if(text.getText().toString().equals("Male")){
            groupChatRoom.setPic("Male");
        }
        else{
            groupChatRoom.setPic("Female");
        }
        groupRef.child(grpId).setValue(groupChatRoom);
        Python py = Python.getInstance();
        py.getModule("t_3").get("main").call(userId,grpId);
//        groupRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
//                for(DataSnapshot child : snapshot.getChildren()){
//                    for(DataSnapshot child2 : child.child("members").getChildren()){
//                        myRef2 = database.getReference("chatRooms/userProfiles/"+child2.getKey());
//                        myRef2.child("group").child(grpId).child("groupId").setValue(grpId);
//                        myRef2.child("group").child(grpId).child("groupName").setValue(groupName);
//                        //將選到的 topic 進行 subscribe (這個topic屆時會替換成群組id)
//                        FirebaseMessaging.getInstance().subscribeToTopic(grpId);
//                        //如果訂閱成功，就同步更新資料庫的判斷
//                        String HasSubscribed = "1";
//                        myRef2.child("group").child(grpId).child("HasSubscribed").setValue(HasSubscribed);
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {
//
//            }
//        });
        Intent intent = new Intent(MainActivity22.this, MainActivity25.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fifth_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.group);

        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {


                Intent intent = new Intent(MainActivity22.this, MainActivity34.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId", userId);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

                return true;
            }
        });
        return true;
    }
    @OnClick(R.id.login10)
    public void nn(View view){
        Intent intent = new Intent(MainActivity22.this, MainActivity25.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}