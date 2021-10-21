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

public class MainActivity33 extends AppCompatActivity {
    @BindView(R.id.textView87)
    TextView text;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.textView89)
    TextView text2;

    private FirebaseDatabase database;
    private DatabaseReference myRef, myRef2, inviteRef, inviteRef2;
    private String GroupId, userId;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main33);
        ButterKnife.bind(this);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        GroupId = bundle.getString("GroupId");
        database = FirebaseDatabase.getInstance();
        inviteRef = database.getReference("chatRooms/invite/");
        myRef = database.getReference("chatRooms/userProfiles/");
        inviteRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.child(GroupId).child("members").getChildren()){
                    if(!child.getKey().equals(userId)){
                        text.setText(child.child("displayName").getValue().toString());
                        text2.setText(child.getKey());
                        inviteRef2 = database.getReference("chatRooms/userProfiles/"+child.getKey());
                        inviteRef2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                if(snapshot.child("gender").getValue().toString().equals("Male") || snapshot.child("gender").getValue().toString().equals("male")){
                                    image.setImageResource(R.drawable.study_school_jugyou_man);
                                }else image.setImageResource(R.drawable.study_school_jugyou_woman);
                            }

                            @Override
                            public void onCancelled(@NonNull @NotNull DatabaseError error) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
    @OnClick(R.id.textView88)
    public void mm(View view){
        myRef.child(userId).child("friends").child(text2.getText().toString()).child("userId").setValue(text2.getText().toString());
        myRef.child(text2.getText().toString()).child("friends").child(userId).child("userId").setValue(userId);
        inviteRef.child(GroupId).removeValue();
        Intent intent = new Intent(MainActivity33.this, MainActivity25.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.textView90)
    public void ll(View view){
        inviteRef.child(GroupId).removeValue();
        Intent intent = new Intent(MainActivity33.this, MainActivity25.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.login5)
    public void nn(View view){
        Intent intent = new Intent(MainActivity33.this, MainActivity25.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}