package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.name.model.GroupChatRoom;
import com.example.name.model.GroupOnlineUsers;
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

public class MainActivity28 extends AppCompatActivity {
    @BindView(R.id.list)
    ListView list;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef;
    private GroupChatRoom groupChatRoom;
    private String GroupId, groupName;
    ArrayList<String> ar = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main28);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        GroupId = bundle.getString("GroupId");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chatRooms/group/" + GroupId + "/members/");
        ArrayAdapter adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1,ar);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ar.clear();
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    ar.add(child.child("displayName").getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        list.setAdapter(adapter);
    }
}