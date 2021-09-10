package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.name.model.Competition;
import com.example.name.model.FirebaseDatabaseHelper;
import com.example.name.model.RecyclerView_config;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity24 extends AppCompatActivity {
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bu;
    @BindView(R.id.recyclerview_competitions)
    RecyclerView mRecyclerView;
    @BindView(R.id.textView79)
    TextView text;
    private String grpId, groupName, userId, name, ev123, ssdd;
    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main24);
        ButterKnife.bind(this);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chatRooms/userProfiles/"+userId);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                text.setText(snapshot.child("name").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        new FirebaseDatabaseHelper().readCompetition(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsloaded(List<Competition> competitions, List<String> keys) {
                new RecyclerView_config().setConfig(mRecyclerView, MainActivity24.this, competitions, keys, userId, text.getText().toString());
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
        bu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.competition:
                        return true;
                    case R.id.calendar:
                        Intent intent2 = new Intent(MainActivity24.this, MainActivity6.class);
//                        Bundle bundle2 = new Bundle();
//                        bundle2.putString("UserId", userId);
//                        intent2.putExtras(bundle2);
                        startActivity(intent2);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.schedule:
                        Intent intent3 = new Intent(MainActivity24.this, MainActivity9.class);
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("UserId", userId);
                        intent3.putExtras(bundle3);
                        startActivity(intent3);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.chatroom:
                        Intent intent4 = new Intent(MainActivity24.this, MainActivity25.class);
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("UserId", userId);
                        intent4.putExtras(bundle4);
                        startActivity(intent4);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        Intent intent5 = new Intent(MainActivity24.this, MainActivity5.class);
                        Bundle bundle5 = new Bundle();
                        bundle5.putString("UserId", userId);
                        intent5.putExtras(bundle5);
                        startActivity(intent5);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}