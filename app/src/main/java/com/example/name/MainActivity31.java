package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.name.model.GroupChatRoom;
import com.example.name.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity31 extends AppCompatActivity {
    @BindView(R.id.textView87)
    TextView text;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.textView89)
    TextView text2;

    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef, userRef2;
    private String grpId, query, userId;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main31);
        ButterKnife.bind(this);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        query = bundle.getString("query");
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("chatRooms/userProfiles/");
        userRef2 = database.getReference("chatRooms/userProfiles/"+userId);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){
                    if(child.child("name").getValue().toString().equals(query)){
                        text.setText(query);
                        text2.setText(child.child("id").getValue().toString());
                        if(child.child("gender").getValue().toString().equals("Male")||child.child("gender").getValue().toString().equals("male")) image.setImageResource(R.drawable.study_school_jugyou_man);
                        else image.setImageResource(R.drawable.study_school_jugyou_woman);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }
    @OnClick(R.id.textView88)
    public void mm(View view){
        userRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                myRef = database.getReference("chatRooms/invite/");
                String groupId = myRef.push().getKey();
                myRef.child(groupId).child("groupId").setValue(groupId);
                myRef.child(groupId).child("groupName").setValue(query);
                myRef.child(groupId).child("members").child(text2.getText().toString()).child("displayName").setValue(query);
                myRef.child(groupId).child("members").child(text2.getText().toString()).child("userId").setValue(text2.getText().toString());
                myRef.child(groupId).child("members").child(userId).child("displayName").setValue(snapshot.child("name").getValue().toString());
                myRef.child(groupId).child("members").child(userId).child("userId").setValue(userId);
                myRef.child(groupId).child("pic").setValue("Male");
                Toast.makeText(MainActivity31.this, "成功傳送交友邀請",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    @OnClick(R.id.login5)
    public void nn(View view){
        Intent intent = new Intent(MainActivity31.this, MainActivity25.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}