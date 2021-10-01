package com.example.name;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.name.model.GroupChatRoom;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class user_ability extends AppCompatActivity {

    private static final String  TAG = "ability";

    String this_uid; //使用者id
    String this_pic; //使用者pic
    String this_name; //使用者name
    String this_url;
    String myName;
    String myUserId;
    private RecyclerView mRecyclerView; //container來存放所有子view

    private TextView username;
    private ImageView userpic;
    private TextView ui_focus;
    private TextView ui_complete;
    private TextView ui_ontime;
    private Button know;
    private Button try_chat;

    private Boolean build = false;

    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef;
    private GroupChatRoom groupChatRoom;

    String focus;
    String complete;
    String ontime;

    int focus_integer = 10;
    int complete_integer=10;
    int ontime_integer=10;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_ability);

        username = (TextView) findViewById(R.id.ability_name);
        userpic = (ImageView) findViewById(R.id.ability_pic);
        ui_complete = (TextView) findViewById(R.id.complete);
        ui_ontime = (TextView) findViewById(R.id.ontime);
        ui_focus = (TextView) findViewById(R.id.focus);
        know = (Button) findViewById(R.id.know);
        try_chat = (Button) findViewById(R.id.try_chat);

        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("url", this_url);
                intent.setClass(user_ability.this, inventer.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//設定不要重新整理將要跳到的介面
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//它可以關掉所要到的介面中間的activity
                intent.putExtra("url", this_url);
                intent.putExtra("myName", myName);
                intent.putExtra("myUserId", myUserId);
                startActivity(intent);
            }
        });

        try_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("tryChat/");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot child : dataSnapshot.getChildren()){
                            if(child.hasChild("members")){
                                if(child.child("members").hasChild(this_uid) && child.child("members").hasChild(myUserId)){
                                    build = false;
                                    break;
                                }
                                else{
                                    build = true;
                                }
                            }
                        }
                        if(build == true){
                            String grpId = myRef.push().getKey();
                            myRef.child(grpId).child("groupId").setValue(grpId);
                            myRef.child(grpId).child("groupName").setValue(this_name);
                            myRef.child(grpId).child("pic").setValue("Male");
                            myRef.child(grpId).child("members").child(this_uid).child("displayName").setValue(this_name);
                            myRef.child(grpId).child("members").child(this_uid).child("userId").setValue(this_uid);
                            myRef.child(grpId).child("members").child(myUserId).child("displayName").setValue(myName);
                            myRef.child(grpId).child("members").child(myUserId).child("userId").setValue(myUserId);
                            Toast.makeText(v.getContext(),"成功建立個別聊天室", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(v.getContext(), "此人已在聊天室名單裡", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("TAG", "Failed to read value.", error.toException());
                    }
                });
            }
        });

        getIncomingIntent();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("chatRooms/userProfiles/");

        reference.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot){

                String temp = "";

                for(DataSnapshot snap : dataSnapshot.getChildren()) {
                    if(snap.hasChild("id")){
                        temp = snap.child("id").getValue().toString();
//                        Toast.makeText(user_ability.this, ""+temp+" and " +this_uid, Toast.LENGTH_SHORT).show();

                        if(temp.equals(this_uid)){
//                            Toast.makeText(user_ability.this, "bingo", Toast.LENGTH_SHORT).show();
                            focus = snap.child("ability").child("focus").getValue().toString();
                            focus = focus.substring(0, focus.length()-1);
                            ontime = snap.child("ability").child("ontime").getValue().toString();
                            ontime = ontime.substring(0, ontime.length()-1);
                            complete = snap.child("ability").child("complete").getValue().toString();
                            complete = complete.substring(0, complete.length()-1);

                            init();

                            focus_integer = Integer.parseInt(focus);
                            complete_integer = Integer.parseInt(complete);
                            ontime_integer = Integer.parseInt(ontime);

//                            Toast.makeText(user_ability.this, focus_integer, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                ui_complete.setText(complete);
                ui_ontime.setText(ontime);
                ui_focus.setText(focus);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        username.setText(this_name);
        Glide.with(user_ability.this).load(this_pic).into(userpic);
    }

    private void getIncomingIntent(){
        Log.d(TAG,"getIncomingIntent:");
        if(getIntent().hasExtra("uid")){

            Log.d(TAG,"Found");
            String url = getIntent().getStringExtra("url");
            String uid = getIntent().getStringExtra("uid");
            String pic = getIntent().getStringExtra("pic");
            String name = getIntent().getStringExtra("name");
            String myName = getIntent().getStringExtra("myName");
            String myUserId = getIntent().getStringExtra("myUserId");
            setUrl(uid, pic, name, url, myName, myUserId);

        }
    }

    private void setUrl(String uid, String pic, String name, String url, String Name, String UserId){
        Log.d(TAG, "setUid to widgets.");
        this_uid = uid;
        this_name = name;
        this_pic = pic;
        this_url = url;
        myName = Name;
        myUserId = UserId;

    }

    private void init() {
        LinearLayout layout=(LinearLayout) findViewById(R.id.test);
        final DrawView view=new DrawView(this);
        view.setMinimumHeight(500);
        view.setMinimumWidth(300);
        //通知view元件重繪
        view.invalidate();
        layout.addView(view);

    }

    public class DrawView extends View {
        public DrawView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // 建立畫筆
            Paint p = new Paint();
            p.setColor(Color.GRAY);//
            p.setStyle(Paint.Style.FILL);
            canvas.drawRect(0, 40, 500, 75, p);
            canvas.drawRect(0, 115, 500, 150, p);
            canvas.drawRect(0, 190, 500, 225, p);

            Paint p2 = new Paint();
            p2.setColor(Color.rgb(195, 80, 78));
            p2.setStyle(Paint.Style.FILL);
            canvas.drawRect(0, 40, focus_integer*5, 75, p2);
            canvas.drawRect(0, 115, ontime_integer*5, 150, p2);
            canvas.drawRect(0, 190, complete_integer*5, 225, p2);
        }
    }
}
