package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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
import butterknife.OnClick;

public class MainActivity28 extends AppCompatActivity {
    @BindView(R.id.list)
    ListView list;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef, myRef2;
    private GroupChatRoom groupChatRoom;
    private String GroupId, userId;
    ArrayList<String> ar = new ArrayList<String>();
    ArrayList<String> ar2 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main28);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        GroupId = bundle.getString("GroupId");
        userId = bundle.getString("UserId");
        database = FirebaseDatabase.getInstance();
        if(GroupId.length()>6){
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
        }else{
            myRef = database.getReference("dicuess/" + GroupId);
            myRef2 = database.getReference("chatRooms/userProfiles/");

            ArrayAdapter adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_list_item_1,ar2);

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    ar.clear();
                    for(DataSnapshot child : dataSnapshot.getChildren()) {
                        ar.add(child.child("user").getValue().toString());//gPoHCK7F...
                    }
                    myRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            ar2.clear();
                            for(DataSnapshot child : snapshot.getChildren()) {
                                if (ar.contains(child.getKey())) {
                                    ar2.add(child.child("name").getValue().toString());//惡魔貓男
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }
                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });

                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
            list.setAdapter(adapter);
        }

    }
    @OnClick(R.id.login3)
    public void asd(View view){
        if(GroupId.length()>6){
            Intent intent = new Intent(MainActivity28.this, MainActivity25.class);
            Bundle bundle = new Bundle();
            bundle.putString("UserId", userId);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(MainActivity28.this, MainActivity29.class);
            Bundle bundle = new Bundle();
            bundle.putString("UserId", userId);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }
}