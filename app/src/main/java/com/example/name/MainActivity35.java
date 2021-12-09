package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.name.Notifications.FcmNotificaionSender;
import com.example.name.config.config;
import com.example.name.config.config3;
import com.example.name.config.config4;
import com.example.name.model.GroupChatRoom;
import com.example.name.model.Msg;
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

public class MainActivity35 extends AppCompatActivity {
    @BindView(R.id.recyclerViewChat)
    RecyclerView recyclerView;
    @BindView(R.id.textView91)
    TextView text;
    private FirebaseDatabase database;
    private DatabaseReference myRef, gRef, myRef2;
    private GroupChatRoom groupChatRoom;
    private String grpId, groupName, userId, userName;
    ArrayList<Msg> ar = new ArrayList<Msg>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main35);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
//        userName = bundle.getString("UserName");
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference("chatRooms/ring/");

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                ar.clear();
//                List<String> keys = new ArrayList<>();
//                for(DataSnapshot child2 : snapshot.getChildren()){
//                    for(DataSnapshot child3 : snapshot.child("members").getChildren()){
//                        if(child3.hasChild(userId)){
//                            for(DataSnapshot child : child2.getChildren()){
//                                if(!child.getKey().equals("members")){
//                                    text.setText(child.getKey());
////                                Msg group = child.getValue(Msg.class);
////                                ar.add(group);
////                                keys.add(child.getKey());
////                                new config4().setConfig4(recyclerView, MainActivity35.this, ar, keys, userId);
//                                }
//                            }
//                        }
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                ar.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot child : snapshot.getChildren()){
                    if(child.child("members").hasChild(userId)) {
                        for (DataSnapshot child2 : child.getChildren()) {
                            if (!child2.getKey().equals("members")) {
                                Msg group = child2.getValue(Msg.class);
                                ar.add(group);
                                keys.add(child2.getKey());
                                new config4().setConfig4(recyclerView, MainActivity35.this, ar, keys, userId);
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    @OnClick(R.id.login13)
    public void jj(View view){

        Intent intent = new Intent(MainActivity35.this, MainActivity25.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }
}