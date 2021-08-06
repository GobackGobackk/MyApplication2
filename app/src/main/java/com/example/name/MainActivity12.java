package com.example.name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.name.model.Habit;
import com.example.name.model.Timeee;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity12 extends AppCompatActivity {
    private String userId, radio;
    @BindView(R.id.textView29)
    TextView text;
    @BindView(R.id.editTextNumberDecimal)
    EditText execute;
    @BindView(R.id.editTextNumberDecimal2)
    EditText rest;
    private FirebaseDatabase database;
    private DatabaseReference myRef, timeRef;
    private Habit habit;
    private String sel, habitName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        radio = bundle.getString("radio");
        sel = bundle.getString("sel");
        habitName = bundle.getString("habitName");
        text.setText(radio);
    }
    @OnClick(R.id.button5)
    public void asd(View view) {
        myRef = database.getReference("chatRooms/task/"+userId);

        habit = new Habit();
        habit.setMethod(radio);
        habit.setExecute(execute.getText().toString());
        habit.setRest(rest.getText().toString());
        habit.setHabitName(habitName);
        myRef.child(sel).setValue(habit);

        timeRef = database.getReference("chatRooms/time/" + userId);

        int hour = Integer.parseInt(execute.getText().toString());
        hour = hour * 60;
        Timeee timeee = new Timeee();
        int s = (int) (hour%60);
        int m = (int) (hour/60%60);
        int h = (int) (hour/60/60%24);
        String ssss = "";
        if(h>0)
            // 顯示 時:分:秒
            ssss = String.format("%d", h) + ":" + String.format("%02d", m) + ":" + String.format("%02d", s);
        else
        if(m>0)
            // 顯示 分:秒
            ssss = String.format("%02d", m) + ":" + String.format("%02d", s);
        else
            // 顯示 秒
            ssss = String.format("%02d",0) +":" + String.format("%02d", s);
        String createdOn = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        timeee.setLastlong(ssss);
        timeee.setEventName(habitName);
        timeee.setCreatedOn(createdOn);
        String jkslk = timeRef.push().getKey();
        timeRef.child(jkslk).setValue(timeee);

        Intent intent = new Intent(MainActivity12.this, MainActivity14.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        bundle.putString("eventName", habitName);
        bundle.putInt("hour", Integer.parseInt(execute.getText().toString()));
        bundle.putInt("rest", Integer.parseInt(rest.getText().toString()));
        bundle.putString("key", jkslk);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}