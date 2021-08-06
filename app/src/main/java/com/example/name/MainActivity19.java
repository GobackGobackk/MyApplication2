package com.example.name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.name.model.Timeee;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity19 extends AppCompatActivity {
    @BindView(R.id.editTextTextPersonName2)
    EditText happyName;
    @BindView(R.id.editTextNumberDecimal3)
    EditText happyHour;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.textView46)
    TextView percent;
    private FirebaseDatabase database;
    private DatabaseReference myRef, dateRef;
    private String sel, userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main19);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String p = String.valueOf(progress);
                percent.setText(p);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    @OnClick(R.id.button4)
    public void jlkjsa(View view) {
        myRef = database.getReference("chatRooms/time/"+userId);
        Timeee time = new Timeee();
        time.setEventName(happyName.getText().toString());
        time.setLastlong(happyHour.getText().toString()+":00:00");
        String createdOn = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        time.setCreatedOn(createdOn);
        time.setCategory("玩樂");
        time.setPlayFun(percent.getText().toString());
        sel = myRef.push().getKey();
        myRef.child(sel).setValue(time);
        Intent intent = new Intent(MainActivity19.this, MainActivity9.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}