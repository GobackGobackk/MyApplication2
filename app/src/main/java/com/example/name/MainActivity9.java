package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.name.model.Habit;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity9 extends AppCompatActivity {
    @BindView(R.id.editTextTextPersonName2)
    EditText habitName;
    @BindView(R.id.radioGroup2)
    RadioGroup genderRadioGroup;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bu;
    private FirebaseDatabase database;
    private DatabaseReference myRef, dateRef;
    private String sel, userId;
    private RadioButton radioButton;
    private Habit habit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        bu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.competition:
                        Intent intent = new Intent(MainActivity9.this, MainActivity24.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("UserId", userId);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calendar:
                        Intent intent2 = new Intent(MainActivity9.this, MainActivity6.class);
//                        Bundle bundle2 = new Bundle();
//                        bundle2.putString("UserId", userId);
//                        intent2.putExtras(bundle2);
                        startActivity(intent2);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.schedule:
                        return true;
                    case R.id.chatroom:
                        Intent intent4 = new Intent(MainActivity9.this, MainActivity25.class);
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("UserId", userId);
                        intent4.putExtras(bundle4);
                        startActivity(intent4);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        Intent intent5 = new Intent(MainActivity9.this, MainActivity5.class);
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

    @OnClick(R.id.button4)
    public void asd(View view) {
        myRef = database.getReference("chatRooms/task/"+userId);
        int checkedRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(checkedRadioButtonId);
        habit = new Habit();
        habit.setHabitName(habitName.getText().toString());
        habit.setMethod(radioButton.getText().toString());
        sel = myRef.push().getKey();
        myRef.child(sel).setValue(habit);
        if(radioButton.getText().toString().equals("設定休息次數")){
            Intent intent = new Intent(MainActivity9.this, MainActivity10.class);
            Bundle bundle = new Bundle();
            bundle.putString("UserId", userId);
            bundle.putString("radio", radioButton.getText().toString());
            bundle.putString("sel", sel);
            bundle.putString("habitName", habitName.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }else{
            Intent intent = new Intent(MainActivity9.this, MainActivity12.class);
            Bundle bundle = new Bundle();
            bundle.putString("UserId", userId);
            bundle.putString("radio", radioButton.getText().toString());
            bundle.putString("sel", sel);
            bundle.putString("habitName", habitName.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }

    }
    @OnClick(R.id.textView41)//跳到玩樂
    public void onlkj(View view) {
        Intent intent = new Intent(MainActivity9.this, MainActivity19.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_play) {
            Intent intent = new Intent(MainActivity9.this, MainActivity19.class);
            Bundle bundle = new Bundle();
            bundle.putString("UserId", userId);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.seventh_menu, menu);
        return true;
    }
}