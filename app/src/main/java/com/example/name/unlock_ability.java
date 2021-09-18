package com.example.name;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class unlock_ability  extends AppCompatActivity {
    private static final String  TAG = "unlock";
    String key; //key
    Integer u_position; //位置
    private TextView username;
    private ImageView userpic;
    private ImageView accept;
    private ImageView cancel;

    String competition_name = ""; //比賽名稱
    String name = ""; //使用者名稱
    String pic = ""; //使用者照片
    String uid = ""; //使用者id

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unlock);

        getIncomingIntent();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("join_competition/");
//        Toast.makeText( unlock_ability.this, "text"+u_position, Toast.LENGTH_SHORT).show();
        username = (TextView) findViewById(R.id.user_name);
        userpic = (ImageView) findViewById(R.id.user_pic);

        accept = (ImageView) findViewById(R.id.imageView2);
        cancel = (ImageView) findViewById(R.id.imageView3);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(unlock_ability.this,"click", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent( unlock_ability.this, user_ability.class);
                intent.putExtra("url", competition_name);
                intent.putExtra("uid", uid);
                intent.putExtra("name", name);
                intent.putExtra("pic", pic);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(unlock_ability.this, inventer.class);
//                intent.putExtra("url", competition_name);
//                startActivity(intent);
                unlock_ability.this.finish();
            }
        });

        reference.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot){

                for(DataSnapshot snap : dataSnapshot.getChildren()) {
                    if(snap.hasChild(key)){
                        competition_name = snap.getKey();
                        name = snap.child(key).child("name").getValue().toString();
                        pic = snap.child(key).child("pic").getValue().toString();
                        uid = snap.child(key).child("user").getValue().toString();
                    }
                }
                username.setText(name);
                Glide.with(unlock_ability.this).load(pic).into(userpic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getIncomingIntent(){
        Log.d(TAG,"getIncomingIntent:");
        String user = getIntent().getStringExtra("user");
        Integer position = getIntent().getIntExtra("user_position", 0);
        setUrl(user, position);
    }

    private void setUrl(String user, Integer position){
        Log.d(TAG, "setUser");
        key = user;
        u_position = position;
//        num = Integer.parseInt(u_position);
    }
}
