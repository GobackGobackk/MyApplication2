package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.name.config.config;
import com.example.name.model.GroupChatRoom;
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
import butterknife.OnClick;

public class MainActivity34 extends AppCompatActivity {
    @BindView(R.id.recyclerViewChat)
    RecyclerView recyclerView;
    private FirebaseDatabase database;
    private DatabaseReference myRef, otherRef;
    private GroupChatRoom groupChatRoom;
    private String grpId, groupName, userId, userName;
    ArrayList<GroupChatRoom> ar = new ArrayList<GroupChatRoom>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main34);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chatRooms/userProfiles/"+userId+"/friends/");


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ar.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    GroupChatRoom group = new GroupChatRoom();
                    group.setGroupId(child.child("userId").getValue().toString());
                    otherRef = database.getReference("chatRooms/userProfiles/"+group.getGroupId());
                    otherRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            group.setGroupName(snapshot.child("name").getValue().toString());
                            if(snapshot.child("gender").getValue().toString().equals("Male")) group.setPic("Male");
                            else group.setPic("Female");
                            ar.add(group);
                            keys.add(group.getGroupId());
                            new config().setConfig(recyclerView, MainActivity34.this, ar, keys, userId);
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    @OnClick(R.id.login6)
    public void vv(View view){
        Intent intent = new Intent(MainActivity34.this, MainActivity22.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}