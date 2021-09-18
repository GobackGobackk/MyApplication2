package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.name.config.config;
import com.example.name.model.GroupChatRoom;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity25 extends AppCompatActivity {
    @BindView(R.id.recyclerViewChat)
    RecyclerView recyclerView;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bu;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef;
    private GroupChatRoom groupChatRoom;
    private String grpId, groupName, userId;
    ArrayList<GroupChatRoom> ar = new ArrayList<GroupChatRoom>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main25);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chatRooms/group/");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ar.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot child : dataSnapshot.getChildren()) {
//                    GroupChatRoom group = new GroupChatRoom();
//                    group.setGroupId(child.child("groupId").getValue().toString());
//                    group.setGroupName(child.child("groupName").getValue().toString());
                    if (child.child("members").hasChild(userId)) {
//                        String value = snapshot.getValue(GroupChatRoom.class).yaa();
//                        String value = group.getGroupId();
                        GroupChatRoom group = child.getValue(GroupChatRoom.class);
                        ar.add(group);
                        keys.add(child.getKey());
                        new config().setConfig(recyclerView, MainActivity25.this, ar, keys, userId);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        bu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.competition:
                        Intent intent = new Intent(MainActivity25.this, MainActivity24.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("UserId", userId);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calendar:
                        Intent intent2 = new Intent(MainActivity25.this, MainActivity6.class);
//                        Bundle bundle2 = new Bundle();
//                        bundle2.putString("UserId", userId);
//                        intent2.putExtras(bundle2);
                        startActivity(intent2);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.schedule:
                        Intent intent3 = new Intent(MainActivity25.this, MainActivity9.class);
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("UserId", userId);
                        intent3.putExtras(bundle3);
                        startActivity(intent3);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.chatroom:
                        return true;
                    case R.id.profile:
                        Intent intent4 = new Intent(MainActivity25.this, MainActivity5.class);
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("UserId", userId);
                        intent4.putExtras(bundle4);
                        startActivity(intent4);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_sign_out) {
            mAuth.signOut();
            startActivity(new Intent(MainActivity25.this, MainActivity.class));
            finish();
        }
        if(item.getItemId() == R.id.menu_create_group) {
            Intent intent = new Intent(MainActivity25.this, MainActivity20.class);
            Bundle bundle = new Bundle();
            bundle.putString("UserId", userId);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
//            grpId = myRef.push().getKey();
//            groupChatRoom = new GroupChatRoom();
//            String createdOn = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(new Date());
//            groupChatRoom.setGroupId(grpId);
//            groupChatRoom.setGroupName(user.getName());
//            groupChatRoom.setCreatedOn(createdOn);
//            myRef.child(grpId).setValue(groupChatRoom);
//            GroupOnlineUsers groupOnlineUsers = new GroupOnlineUsers(user.getId(),
//                    user.getName());
//            myRef.child(grpId).child("members").child(user.getId()).setValue(groupOnlineUsers);
//            Toast.makeText(MainActivity2.this, "Group Created", Toast.LENGTH_LONG).show();
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}