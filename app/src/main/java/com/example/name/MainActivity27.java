package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.TypefaceCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.name.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity27 extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef, goalRef;
    private String grpId, groupName, userId;
    private User user;
    @BindView(R.id.busyTime)
    LinearLayout busyTime;
    @BindView(R.id.textView58)
    TextView active;
    @BindView(R.id.goal)
    LinearLayout goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main27);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("chatRooms/userProfiles/"+ userId);
        goalRef = database.getReference("chatRooms/userProfiles/" + userId + "/" + "goal");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                if(dataSnapshot.hasChild("busyTime")){
                    if(dataSnapshot.child("busyTime").hasChild("星期日")){
                        LinearLayout llay = new LinearLayout(MainActivity27.this);
                        llay.setOrientation(LinearLayout.HORIZONTAL);
                        llay.setWeightSum(12.0f);
                        LinearLayout.LayoutParams llp0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        llp0.setMargins(0, 30, 0, 0);
                        llay.setLayoutParams(llp0);
                        TextView text = new TextView(MainActivity27.this);
                        text.setText("SUN");
                        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 6.0f);
                        text.setLayoutParams(llp);
                        TextView text1 = new TextView(MainActivity27.this);
                        text1.setText(dataSnapshot.child("busyTime").child("星期日").child("start").getValue().toString());
                        LinearLayout.LayoutParams llp1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f);
                        text1.setLayoutParams(llp1);
                        TextView text2 = new TextView(MainActivity27.this);
                        text2.setText(dataSnapshot.child("busyTime").child("星期日").child("end").getValue().toString());
                        text2.setLayoutParams(llp1);
//                        text.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text1.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text2.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
                        text.setTypeface(getTypefaceBold());
                        text1.setTypeface(getTypeface());
                        text2.setTypeface(getTypeface());
                        text.setGravity(Gravity.CENTER);
                        text1.setGravity(Gravity.CENTER);
                        text2.setGravity(Gravity.CENTER);
                        llay.addView(text);
                        llay.addView(text1);
                        llay.addView(text2);
                        busyTime.addView(llay);
                    }
                    if(dataSnapshot.child("busyTime").hasChild("星期一")){
                        LinearLayout llay = new LinearLayout(MainActivity27.this);
                        llay.setOrientation(LinearLayout.HORIZONTAL);
                        llay.setWeightSum(12.0f);
                        LinearLayout.LayoutParams llp0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        llp0.setMargins(0, 30, 0, 0);
                        llay.setLayoutParams(llp0);
                        TextView text = new TextView(MainActivity27.this);
                        text.setText("MON");
                        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 6.0f);
                        text.setLayoutParams(llp);
                        TextView text1 = new TextView(MainActivity27.this);
                        text1.setText(dataSnapshot.child("busyTime").child("星期一").child("start").getValue().toString());
                        LinearLayout.LayoutParams llp1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f);
                        text1.setLayoutParams(llp1);
                        TextView text2 = new TextView(MainActivity27.this);
                        text2.setText(dataSnapshot.child("busyTime").child("星期一").child("end").getValue().toString());
                        text2.setLayoutParams(llp1);
//                        text.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text1.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text2.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
                        text.setTypeface(getTypefaceBold());
                        text1.setTypeface(getTypeface());
                        text2.setTypeface(getTypeface());
                        text.setGravity(Gravity.CENTER);
                        text1.setGravity(Gravity.CENTER);
                        text2.setGravity(Gravity.CENTER);
                        llay.addView(text);
                        llay.addView(text1);
                        llay.addView(text2);
                        busyTime.addView(llay);
                    }
                    if(dataSnapshot.child("busyTime").hasChild("星期二")){
                        LinearLayout llay = new LinearLayout(MainActivity27.this);
                        llay.setOrientation(LinearLayout.HORIZONTAL);
                        llay.setWeightSum(12.0f);
                        LinearLayout.LayoutParams llp0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        llp0.setMargins(0, 30, 0, 0);
                        llay.setLayoutParams(llp0);
                        TextView text = new TextView(MainActivity27.this);
                        text.setText("TUE");
                        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 6.0f);
                        text.setLayoutParams(llp);
                        TextView text1 = new TextView(MainActivity27.this);
                        text1.setText(dataSnapshot.child("busyTime").child("星期二").child("start").getValue().toString());
                        LinearLayout.LayoutParams llp1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f);
                        text1.setLayoutParams(llp1);
                        TextView text2 = new TextView(MainActivity27.this);
                        text2.setText(dataSnapshot.child("busyTime").child("星期二").child("end").getValue().toString());
                        text2.setLayoutParams(llp1);
//                        text.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text1.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text2.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
                        text.setTypeface(getTypefaceBold());
                        text1.setTypeface(getTypeface());
                        text2.setTypeface(getTypeface());
                        text.setGravity(Gravity.CENTER);
                        text1.setGravity(Gravity.CENTER);
                        text2.setGravity(Gravity.CENTER);
                        llay.addView(text);
                        llay.addView(text1);
                        llay.addView(text2);
                        busyTime.addView(llay);
                    }
                    if(dataSnapshot.child("busyTime").hasChild("星期三")){
                        LinearLayout llay = new LinearLayout(MainActivity27.this);
                        llay.setOrientation(LinearLayout.HORIZONTAL);
                        llay.setWeightSum(12.0f);
                        LinearLayout.LayoutParams llp0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        llp0.setMargins(0, 30, 0, 0);
                        llay.setLayoutParams(llp0);
                        TextView text = new TextView(MainActivity27.this);
                        text.setText("WED");
                        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 6.0f);
                        text.setLayoutParams(llp);
                        TextView text1 = new TextView(MainActivity27.this);
                        text1.setText(dataSnapshot.child("busyTime").child("星期三").child("start").getValue().toString());
                        LinearLayout.LayoutParams llp1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f);
                        text1.setLayoutParams(llp1);
                        TextView text2 = new TextView(MainActivity27.this);
                        text2.setText(dataSnapshot.child("busyTime").child("星期三").child("end").getValue().toString());
                        text2.setLayoutParams(llp1);
//                        text.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text1.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text2.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
                        text.setTypeface(getTypefaceBold());
                        text1.setTypeface(getTypeface());
                        text2.setTypeface(getTypeface());
                        text.setGravity(Gravity.CENTER);
                        text1.setGravity(Gravity.CENTER);
                        text2.setGravity(Gravity.CENTER);
                        llay.addView(text);
                        llay.addView(text1);
                        llay.addView(text2);
                        busyTime.addView(llay);
                    }
                    if(dataSnapshot.child("busyTime").hasChild("星期四")){
                        LinearLayout llay = new LinearLayout(MainActivity27.this);
                        llay.setOrientation(LinearLayout.HORIZONTAL);
                        llay.setWeightSum(12.0f);
                        LinearLayout.LayoutParams llp0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        llp0.setMargins(0, 30, 0, 0);
                        llay.setLayoutParams(llp0);
                        TextView text = new TextView(MainActivity27.this);
                        text.setText("THR");
                        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 6.0f);
                        text.setLayoutParams(llp);
                        TextView text1 = new TextView(MainActivity27.this);
                        text1.setText(dataSnapshot.child("busyTime").child("星期四").child("start").getValue().toString());
                        LinearLayout.LayoutParams llp1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f);
                        text1.setLayoutParams(llp1);
                        TextView text2 = new TextView(MainActivity27.this);
                        text2.setText(dataSnapshot.child("busyTime").child("星期四").child("end").getValue().toString());
                        text2.setLayoutParams(llp1);
//                        text.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text1.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text2.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
                        text.setTypeface(getTypefaceBold());
                        text1.setTypeface(getTypeface());
                        text2.setTypeface(getTypeface());
                        text.setGravity(Gravity.CENTER);
                        text1.setGravity(Gravity.CENTER);
                        text2.setGravity(Gravity.CENTER);
                        llay.addView(text);
                        llay.addView(text1);
                        llay.addView(text2);
                        busyTime.addView(llay);
                    }
                    if(dataSnapshot.child("busyTime").hasChild("星期五")){
                        LinearLayout llay = new LinearLayout(MainActivity27.this);
                        llay.setOrientation(LinearLayout.HORIZONTAL);
                        llay.setWeightSum(12.0f);
                        LinearLayout.LayoutParams llp0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        llp0.setMargins(0, 30, 0, 0);
                        llay.setLayoutParams(llp0);
                        TextView text = new TextView(MainActivity27.this);
                        text.setText("FRI");
                        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 6.0f);
                        text.setLayoutParams(llp);
                        TextView text1 = new TextView(MainActivity27.this);
                        text1.setText(dataSnapshot.child("busyTime").child("星期五").child("start").getValue().toString());
                        LinearLayout.LayoutParams llp1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f);
                        text1.setLayoutParams(llp1);
                        TextView text2 = new TextView(MainActivity27.this);
                        text2.setText(dataSnapshot.child("busyTime").child("星期五").child("end").getValue().toString());
                        text2.setLayoutParams(llp1);
//                        text.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text1.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text2.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
                        text.setTypeface(getTypefaceBold());
                        text1.setTypeface(getTypeface());
                        text2.setTypeface(getTypeface());
                        text.setGravity(Gravity.CENTER);
                        text1.setGravity(Gravity.CENTER);
                        text2.setGravity(Gravity.CENTER);
                        llay.addView(text);
                        llay.addView(text1);
                        llay.addView(text2);
                        busyTime.addView(llay);
                    }
                    if(dataSnapshot.child("busyTime").hasChild("星期六")){
                        LinearLayout llay = new LinearLayout(MainActivity27.this);
                        llay.setOrientation(LinearLayout.HORIZONTAL);
                        llay.setWeightSum(12.0f);
                        LinearLayout.LayoutParams llp0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        llp0.setMargins(0, 30, 0, 0);
                        llay.setLayoutParams(llp0);
                        TextView text = new TextView(MainActivity27.this);
                        text.setText("SAT");
                        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 6.0f);
                        text.setLayoutParams(llp);
                        TextView text1 = new TextView(MainActivity27.this);
                        text1.setText(dataSnapshot.child("busyTime").child("星期六").child("start").getValue().toString());
                        LinearLayout.LayoutParams llp1 = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 3.0f);
                        text1.setLayoutParams(llp1);
                        TextView text2 = new TextView(MainActivity27.this);
                        text2.setText(dataSnapshot.child("busyTime").child("星期六").child("end").getValue().toString());
                        text2.setLayoutParams(llp1);
//                        text.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text1.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
//                        text2.setTypeface(Typeface.createFromAsset(getAssets(), "font/ubuntu_regular.ttf"));
                        text.setTypeface(getTypefaceBold());
                        text1.setTypeface(getTypeface());
                        text2.setTypeface(getTypeface());
                        text.setGravity(Gravity.CENTER);
                        text1.setGravity(Gravity.CENTER);
                        text2.setGravity(Gravity.CENTER);
                        llay.addView(text);
                        llay.addView(text1);
                        llay.addView(text2);
                        busyTime.addView(llay);
                    }
                }
                if(dataSnapshot.hasChild("active")){
                    active.setText(dataSnapshot.child("active").getValue().toString());
                    active.setTypeface(getTypefaceBold());
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        goalRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    LinearLayout llay = new LinearLayout(MainActivity27.this);
                    llay.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams llp0 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                    llp0.setMargins(0, 30, 0, 0);
                    llay.setLayoutParams(llp0);
                    TextView text = new TextView(MainActivity27.this);
                    text.setText(child.child("goal").getValue().toString());
                    LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    text.setLayoutParams(llp);
                    text.setTypeface(getTypeface());
                    text.setGravity(Gravity.CENTER);
                    llay.addView(text);
                    goal.addView(llay);
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
    private Typeface getTypeface() {
        @SuppressLint("RestrictedApi")
        Typeface typeface = TypefaceCompat.createFromResourcesFontFile(MainActivity27.this, getResources(), R.font.ubuntu_regular, "", 0);

        return typeface;
    }
    private Typeface getTypefaceBold() {
        @SuppressLint("RestrictedApi")
        Typeface typeface = TypefaceCompat.createFromResourcesFontFile(MainActivity27.this, getResources(), R.font.ubuntu_bold, "", 0);

        return typeface;
    }
}