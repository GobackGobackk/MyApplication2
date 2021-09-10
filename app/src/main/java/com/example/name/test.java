package com.example.name;

import android.os.Bundle;
import android.util.Log;
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

public class test extends AppCompatActivity {

    private static final String TAG = "test";

    //    String temp;
    String key; //位置
    TextView com_title; //標題
    TextView com_prize; //總獎金
    TextView com_maxprize; //最高獎金
    TextView com_starttime; //活動時間
    TextView com_endtime; //截止時間
    TextView com_winner; //得獎公布
    TextView organizer; //主辦單位
    TextView organizerEmail; //主辦信箱
    TextView organizerLocation; //主辦地點
    TextView organizerPhone; //主辦電話

    String temp;
    private ImageView pic;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.competition);
        Log.d(TAG, "onCreate: started.");


        getIncomingIntent();

        com_title = (TextView)findViewById(R.id.com_title);
        com_prize = (TextView)findViewById(R.id.com_prize);
        com_maxprize = (TextView)findViewById(R.id.com_maxprize);
        com_starttime = (TextView)findViewById(R.id.com_starttime);
        com_endtime = (TextView)findViewById(R.id.com_endtime);
        com_winner = (TextView)findViewById(R.id.com_winner);
        organizer = (TextView)findViewById(R.id.organizer);
        organizerEmail = (TextView)findViewById(R.id.organizerEmail);
        organizerPhone = (TextView)findViewById(R.id.organizerPhone);
        organizerLocation = (TextView)findViewById(R.id.organizerLocation);
        pic = (ImageView)findViewById(R.id.com_pic);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("competition").child(key);
        reference.addValueEventListener(new ValueEventListener(){

            public void onDataChange(DataSnapshot dataSnapshot) {

//                temp =
                com_title.setText(dataSnapshot.child("title").getValue().toString());
                com_prize.setText(dataSnapshot.child("prize").getValue().toString());
                com_maxprize.setText(dataSnapshot.child("MaxPrize").getValue().toString());
                com_starttime.setText(dataSnapshot.child("submissionStart").getValue().toString());
                com_endtime.setText(" ~ " + dataSnapshot.child("dead_line").getValue().toString());
                com_winner.setText(dataSnapshot.child("winner").getValue().toString());
                organizer.setText(dataSnapshot.child("organizer").getValue().toString());
                organizerEmail.setText(dataSnapshot.child("organizerEmail").getValue().toString());
                organizerLocation.setText(dataSnapshot.child("organizerLocation").getValue().toString());
                organizerPhone.setText(dataSnapshot.child("organizerPhone").getValue().toString());

                temp = dataSnapshot.child("pic").getValue().toString();
                Glide.with(test.this).load(temp).into(pic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getIncomingIntent(){
        Log.d(TAG,"getIncomingIntent:");
        if(getIntent().hasExtra("url")){
            Log.d(TAG,"Found");

            String url = getIntent().getStringExtra("url");
            setUrl(url);

        }
    }

    private void setUrl(String url){
        Log.d(TAG, "setUrl to widgets.");

        key = url;
    }


}
