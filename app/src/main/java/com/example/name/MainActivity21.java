package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.name.model.GroupChatRoom;
import com.example.name.model.GroupOnlineUsers;
import com.example.name.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity21 extends AppCompatActivity {
    @BindView(R.id.textView57)
    TextView sun1;
    @BindView(R.id.textView58)
    TextView sun2;
    @BindView(R.id.textView59)
    TextView mon1;
    @BindView(R.id.textView60)
    TextView mon2;
    @BindView(R.id.textView61)
    TextView tue1;
    @BindView(R.id.textView62)
    TextView tue2;
    @BindView(R.id.textView63)
    TextView wed1;
    @BindView(R.id.textView64)
    TextView wed2;
    @BindView(R.id.textView65)
    TextView thr1;
    @BindView(R.id.textView66)
    TextView thr2;
    @BindView(R.id.textView67)
    TextView fri1;
    @BindView(R.id.textView68)
    TextView fri2;
    @BindView(R.id.textView69)
    TextView sat1;
    @BindView(R.id.textView70)
    TextView sat2;
    private FirebaseDatabase database;
    private DatabaseReference myRef, calendarRef, userRef, groupRef;
    private GroupChatRoom groupChatRoom;
    private String grpId, groupName, userId, calId, goal;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main21);
        ButterKnife.bind(this);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
//        goal = bundle.getString("goal");
//        groupName = bundle.getString("groupName");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chatRooms/userProfiles/"+userId);
        calendarRef = database.getReference("chatRooms/calendar/"+userId);
        userRef = database.getReference("chatRooms/userProfiles/"+userId);
        groupRef = database.getReference("chatRooms/group/");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
//                Toast.makeText(MainActivity2.this, user.getName()+user.getGender()+user.getCity() , Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
    @OnClick(R.id.textView57)
    public void sayhihi(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                sun1.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView58)
    public void s(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                sun2.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView59)
    public void sa(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                mon1.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView60)
    public void say(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                mon2.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView61)
    public void sayh(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                tue1.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView62)
    public void sayhii(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                tue2.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView63)
    public void sayhih(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                wed1.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView64)
    public void sayhihii(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                wed2.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView65)
    public void qwe(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                thr1.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView66)
    public void jl(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                thr2.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView67)
    public void mvb(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                fri1.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView68)
    public void gfh(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                fri2.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView69)
    public void pi(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                sat1.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView70)
    public void cvxx(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity21.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                sat2.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.button6)
    public void jmm(View view) {
        if (!sun1.getText().toString().equals("start") && !sun2.getText().toString().equals("finish")) {
            myRef.child("busyTime").child("星期日").child("start").setValue(sun1.getText().toString());
            myRef.child("busyTime").child("星期日").child("end").setValue(sun2.getText().toString());
        }
        if (!mon1.getText().toString().equals("start") && !mon2.getText().toString().equals("finish")) {
            myRef.child("busyTime").child("星期一").child("start").setValue(mon1.getText().toString());
            myRef.child("busyTime").child("星期一").child("end").setValue(mon2.getText().toString());
        }
        if (!tue1.getText().toString().equals("start") && !tue2.getText().toString().equals("finish")) {
            myRef.child("busyTime").child("星期二").child("start").setValue(tue1.getText().toString());
            myRef.child("busyTime").child("星期二").child("end").setValue(tue2.getText().toString());
        }
        if (!wed1.getText().toString().equals("start") && !wed2.getText().toString().equals("finish")) {
            myRef.child("busyTime").child("星期三").child("start").setValue(wed1.getText().toString());
            myRef.child("busyTime").child("星期三").child("end").setValue(wed2.getText().toString());
        }
        if (!thr1.getText().toString().equals("start") && !thr2.getText().toString().equals("finish")) {
            myRef.child("busyTime").child("星期四").child("start").setValue(thr1.getText().toString());
            myRef.child("busyTime").child("星期四").child("end").setValue(thr2.getText().toString());
        }
        if (!fri1.getText().toString().equals("start") && !fri2.getText().toString().equals("finish")) {
            myRef.child("busyTime").child("星期五").child("start").setValue(fri1.getText().toString());
            myRef.child("busyTime").child("星期五").child("end").setValue(fri2.getText().toString());
        }
        if (!sat1.getText().toString().equals("start") && !sat2.getText().toString().equals("finish")) {
            myRef.child("busyTime").child("星期六").child("start").setValue(sat1.getText().toString());
            myRef.child("busyTime").child("星期六").child("end").setValue(sat2.getText().toString());
        }
//        calendarRef.addValueEventListener(new ValueEventListener() { //以行事曆為優先
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                for(DataSnapshot child : snapshot.getChildren()){
//                    if (child.child("dayofweek").getValue().toString().equals("星期日")) {
//                        myRef.child("busyTime").child("星期日").child("start").setValue(child.child("eventStartTime").getValue().toString());
//                        myRef.child("busyTime").child("星期日").child("end").setValue(child.child("eventFinishTime").getValue().toString());
//                    }
//                    if (child.child("dayofweek").getValue().toString().equals("星期一")) {
//                        myRef.child("busyTime").child("星期一").child("start").setValue(child.child("eventStartTime").getValue().toString());
//                        myRef.child("busyTime").child("星期一").child("end").setValue(child.child("eventFinishTime").getValue().toString());
//                    }
//                    if (child.child("dayofweek").getValue().toString().equals("星期二")) {
//                        myRef.child("busyTime").child("星期二").child("start").setValue(child.child("eventStartTime").getValue().toString());
//                        myRef.child("busyTime").child("星期二").child("end").setValue(child.child("eventFinishTime").getValue().toString());
//                    }
//                    if (child.child("dayofweek").getValue().toString().equals("星期三")) {
//                        myRef.child("busyTime").child("星期三").child("start").setValue(child.child("eventStartTime").getValue().toString());
//                        myRef.child("busyTime").child("星期三").child("end").setValue(child.child("eventFinishTime").getValue().toString());
//                    }
//                    if(child.child("dayofweek").getValue().toString().equals("星期四")){
//                        myRef.child("busyTime").child("星期四").child("start").setValue(child.child("eventStartTime").getValue().toString());
//                        myRef.child("busyTime").child("星期四").child("end").setValue(child.child("eventFinishTime").getValue().toString());
//                    }
//                    if (child.child("dayofweek").getValue().toString().equals("星期五")) {
//                        myRef.child("busyTime").child("星期五").child("start").setValue(child.child("eventStartTime").getValue().toString());
//                        myRef.child("busyTime").child("星期五").child("end").setValue(child.child("eventFinishTime").getValue().toString());
//                    }
//                    if (child.child("dayofweek").getValue().toString().equals("星期六")) {
//                        myRef.child("busyTime").child("星期六").child("start").setValue(child.child("eventStartTime").getValue().toString());
//                        myRef.child("busyTime").child("星期六").child("end").setValue(child.child("eventFinishTime").getValue().toString());
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//            }
//        });
//        grpId = groupRef.push().getKey();
//        groupChatRoom = new GroupChatRoom();
//        String createdOn = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(new Date());
//        groupChatRoom.setGroupId(grpId);
//        groupChatRoom.setGroupName(groupName);
//        groupChatRoom.setCreatedOn(createdOn);
//        groupRef.child(grpId).setValue(groupChatRoom);
        //創沒有member的群組

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("UserId", userId);
        editor.apply();
        Intent intent = new Intent(MainActivity21.this, MainActivity6.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("UserId", userId);
//        bundle.putString("GroupId", grpId);
//        bundle.putString("goal", goal);
//        intent.putExtras(bundle);
        startActivity(intent);
        finish();

    }
}