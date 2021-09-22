package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity30 extends AppCompatActivity {
    @BindView(R.id.recyclerViewChat)
    RecyclerView recyclerView;
    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef, myRef2;
    private GroupChatRoom groupChatRoom;
    private String grpId, groupName, userId, userName;
    ArrayList<GroupChatRoom> ar = new ArrayList<GroupChatRoom>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main30);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        userName = bundle.getString("UserName");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("tryChat/");


        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ar.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.child("members").hasChild(userId)) {
                        GroupChatRoom group = child.getValue(GroupChatRoom.class);

                        for(DataSnapshot child2 : child.child("members").getChildren()){
                            if(!userName.equals(child2.child("displayName").getValue().toString())){//group.getGroupName惡魔貓男
                                group.setGroupName(child2.child("displayName").getValue().toString());
                            }
                        }

                        ar.add(group);
                        keys.add(child.getKey());
                        new config().setConfig(recyclerView, MainActivity30.this, ar, keys, userId);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}