package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.name.model.Calendarrr;
import com.example.name.model.GroupChatRoom;
import com.example.name.model.GroupOnlineUsers;
import com.example.name.model.Habit;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity6 extends AppCompatActivity {
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bu;

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        ButterKnife.bind(this);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        userId = preferences.getString("UserId", "");
//        Intent intent = this.getIntent();
//        Bundle bundle = intent.getExtras();
//        userId = bundle.getString("UserId");

        CalendarView calendarView=(CalendarView) findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                String y = String.valueOf(year);
                int mo = month+1;
                String str_month = String.format("%02d", mo);
                String str_day = String.format("%02d", dayOfMonth);
                String date = y + "-" + str_month + "-" +str_day;

                new AlertDialog.Builder(MainActivity6.this)
                        .setTitle(date)
                        .setPositiveButton("新增活動", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity6.this, MainActivity7.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("UserId", userId);
                        bundle.putString("hihi", date);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }}).show();

            }
        });
        bu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.competition:
                        Intent intent = new Intent(MainActivity6.this, MainActivity24.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("UserId", userId);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calendar:
                        return true;
                    case R.id.schedule:
                        Intent intent3 = new Intent(MainActivity6.this, MainActivity9.class);
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("UserId", userId);
                        intent3.putExtras(bundle3);
                        startActivity(intent3);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.chatroom:
                        Intent intent4 = new Intent(MainActivity6.this, MainActivity25.class);
                        Bundle bundle4 = new Bundle();
                        bundle4.putString("UserId", userId);
                        intent4.putExtras(bundle4);
                        startActivity(intent4);
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        Intent intent5 = new Intent(MainActivity6.this, MainActivity5.class);
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_time_calculate) {
            Intent intent = new Intent(MainActivity6.this, MainActivity17.class);
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
        getMenuInflater().inflate(R.menu.second_menu, menu);
        return true;
    }
}