package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.name.model.Calendarrr;
import com.example.name.model.Profile;
import com.example.name.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class MainActivity5 extends AppCompatActivity {
    @BindView(R.id.textView4)
    TextView text;
    @BindView(R.id.imageButton6)
    ImageButton event;
    @BindView(R.id.textView7)
    TextView eventName;
    @BindView(R.id.textView13)
    TextView lastdays;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bu;
    @BindView(R.id.ivUser3)
    ImageView image;
    private List<String> eventList = new ArrayList<>();
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef, eventRef;
    private String grpId, groupName, userId;

    private User user;
    private Profile profile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("chatRooms/userProfiles/"+ userId);
        eventRef = database.getReference("chatRooms/calendar/" + userId);
        myRef = database.getReference("chatRooms/time");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                text.setText(user.getName());
                if(user.getGender().equals("Male")) image.setImageResource(R.drawable.study_school_jugyou_man);
                else image.setImageResource(R.drawable.study_school_jugyou_woman);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        bu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.competition:
                        Intent intent = new Intent(MainActivity5.this, MainActivity24.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("UserId", userId);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calendar:
                        Intent intent2 = new Intent(MainActivity5.this, MainActivity6.class);
//                        Bundle bundle2 = new Bundle();
//                        bundle2.putString("UserId", userId);
//                        intent2.putExtras(bundle2);
                        startActivity(intent2);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.schedule:
                        Intent intent3 = new Intent(MainActivity5.this, MainActivity9.class);
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("UserId", userId);
                        intent3.putExtras(bundle3);
                        startActivity(intent3);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.chatroom:
                        Intent intent4 = new Intent(MainActivity5.this, MainActivity25.class);
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("UserId", userId);
                        intent4.putExtras(bundle4);
                        startActivity(intent4);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        return true;
                }
                return false;
            }
        });
    }

//    public void onStart() {
//        super.onStart();
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                if(!snapshot.hasChild(userId)){
//                    AlertDialog.Builder alertDialog =
//                            new AlertDialog.Builder(MainActivity5.this);
//                    alertDialog.setTitle("目前沒有時間紀錄");
//                    alertDialog.setMessage("請先計時再進入此頁面");
//                    alertDialog.setPositiveButton("行事曆", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent = new Intent(MainActivity5.this, MainActivity6.class);
////                            Bundle bundle = new Bundle();
////                            bundle.putString("UserId", userId);
////                            intent.putExtras(bundle);
//                            startActivity(intent);
//                            finish();
//                        }
//                    });
//                    alertDialog.setNegativeButton("排程",(dialog, which) -> {
//                        Intent intent = new Intent(MainActivity5.this, MainActivity9.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("UserId", userId);
//                        intent.putExtras(bundle);
//                        startActivity(intent);
//                        finish();
//                    });
//                    alertDialog.setCancelable(false);
//                    alertDialog.show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });
//    }
    @OnClick(R.id.imageButton6)
    public void onClickk(View view) {
        eventRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList.clear();
                for(DataSnapshot val : dataSnapshot.getChildren()){
                    Calendarrr cal = new Calendarrr();
                    cal.setEventName(val.child("eventName").getValue().toString());
                    eventList.add(cal.getEventName());
                }
                final String[] Animals = eventList.toArray(new String[eventList.size()]);
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity5.this);
                dialogBuilder.setTitle("活動列表")
                        .setItems(Animals, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
//                                 Toast.makeText(MainActivity5.this, "你選的是" + Animals[item], Toast.LENGTH_SHORT).show();
                                eventName.setText(Animals[item]);
                                Intent intent = new Intent(MainActivity5.this, MainActivity8.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("UserId", userId);
                                bundle.putString("eventName", Animals[item]);
//                                bundle.putString("startDay", profile.getCount());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                //Show the dialog
                dialogBuilder.show();
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}