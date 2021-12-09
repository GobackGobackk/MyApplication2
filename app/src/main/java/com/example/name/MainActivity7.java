package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.name.model.Calendarrr;
import com.example.name.model.Detail;
import com.example.name.model.GroupChatRoom;
import com.example.name.model.Spinnerrr;
import com.example.name.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity7 extends AppCompatActivity {
    @BindView(R.id.editTextTextPersonName)
    EditText event;
    @BindView(R.id.textView35)
    TextView startDay;
    @BindView(R.id.textView36)
    TextView startTime;
    @BindView(R.id.textView37)
    TextView finishDay;
    @BindView(R.id.textView38)
    TextView finishTime;
    @BindView(R.id.textView49)
    TextView allFinishDay;
    @BindView(R.id.spinner)
    Spinner list;
    @BindView(R.id.spinner4)
    Spinner repeat;
    @BindView(R.id.textView)
    TextView text;
    @BindView(R.id.button)
    Button btn;
    @BindView(R.id.textView72)
    TextView text1;
    @BindView(R.id.button3)
    Button submit;
    @BindView(R.id.textView73)
    TextView text2;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef, groupRef, detailRef;
    private String grpId, groupName, userId, calId;
    private User user;
    private Calendarrr asd;
    ArrayList<Spinnerrr> ar = new ArrayList<Spinnerrr>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        ButterKnife.bind(this);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        calId = bundle.getString("hihi");

        String[] dayofweek = new String[]{"星期日","星期一","星期二","星期三", "星期四", "星期五", "星期六"};
        ArrayAdapter<String> adapterday = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, dayofweek);
        adapterday.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repeat.setAdapter(adapterday);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chatRooms/calendar");
        detailRef = database.getReference("chatRooms/detail/"+userId);
        groupRef = database.getReference("chatRooms/group/");
        ArrayAdapter<Spinnerrr> adapter = new ArrayAdapter<Spinnerrr>(
                this, android.R.layout.simple_spinner_dropdown_item, ar
        );
        groupRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ar.clear();
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.child("members").hasChild(userId)) {
                        ar.add(new Spinnerrr(child.child("groupName").getValue().toString(), child.child("groupId").getValue().toString()));
                    }
                }
                if(ar.size()==0){//沒有群組的話 強制去聊天室建群組
                    btn.setVisibility(View.VISIBLE);
                    text1.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.INVISIBLE);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        list.setAdapter(adapter);
        startDay.setText(calId);
        finishDay.setText(calId);

    }
    @OnClick(R.id.textView35)
    public void hihi(View view){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        new DatePickerDialog(MainActivity7.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String str_month = String.format("%02d", month+1);
                String str_day = String.format("%02d", day);
                String dateTime = String.valueOf(year)+"-"+str_month+"-"+str_day;
                startDay.setText(dateTime);
            }

        }, year, month, day).show();
    }
    @OnClick(R.id.textView37)
    public void hi(View view){

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(MainActivity7.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String str_month = String.format("%02d", month+1);
                String str_day = String.format("%02d", day);
                String dateTime = String.valueOf(year)+"-"+str_month+"-"+str_day;
                finishDay.setText(dateTime);
            }

        }, year, month, day).show();

    }
    @OnClick(R.id.textView36)
    public void sayhihi(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity7.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                startTime.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView38)
    public void say(View view){
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        new TimePickerDialog(MainActivity7.this, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String str_hour = String.format("%02d", hourOfDay);
                String str_min = String.format("%02d", minute);
                finishTime.setText(str_hour + ":" + str_min);
            }
        }, hour, minute, false).show();
    }
    @OnClick(R.id.textView49)
    public void hjai(View view){

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        new DatePickerDialog(MainActivity7.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String str_month = String.format("%02d", month+1);
                String str_day = String.format("%02d", day);
                String dateTime = String.valueOf(year)+"-"+str_month+"-"+str_day;
                allFinishDay.setText(dateTime);
            }

        }, year, month, day).show();

    }
    @OnClick(R.id.button3)
    public void asd(View view) {
        for(Spinnerrr arr : ar){
            if(arr.getSpinnerrr_name().equals(list.getSelectedItem().toString())){
                text2.setText(arr.getSpinnerrr_id());
            }
        }
        String gf = myRef.push().getKey();
        Calendarrr cal = new Calendarrr(event.getText().toString(), startDay.getText().toString(), finishDay.getText().toString(), startTime.getText().toString(), finishTime.getText().toString(), text2.getText().toString());
        cal.setDayofweek(repeat.getSelectedItem().toString());
        cal.setAllFinishDay(allFinishDay.getText().toString());
        myRef.child(userId).child(gf).setValue(cal);

        String dateString = startDay.getText().toString();
        String dateString2 = "";

        Date uiop = null;
        try {
            uiop = convertStringToDate(startDay.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long jkll = uiop.getTime();//開始日期的毫秒數
        Detail detail = new Detail();

        detail.setEventName(event.getText().toString());
        if(getWeek(startDay.getText().toString()).equals(repeat.getSelectedItem().toString())){ //星期 一樣
            detail.setDatee(startDay.getText().toString());
        }
        else {
            while(!getWeek(dateString).equals(repeat.getSelectedItem().toString())){
                jkll = jkll + 60 * 60 * 24 * 1000;
                dateString = new SimpleDateFormat("yyyy-MM-dd").format(new Date(jkll));
            }
            detail.setDatee(dateString);
        }
        detail.setTimee(startTime.getText().toString());
        String detailId = myRef.push().getKey();//第一個
        detailRef.child(detailId).setValue(detail);
        try {
            Date jllj = convertStringToDate(allFinishDay.getText().toString());
            long asdd = jllj.getTime();//結束日期
            jkll = jkll + 60 * 60 * 24 * 1000 * 7;

            while(asdd > jkll || asdd == jkll){
                dateString2 = new SimpleDateFormat("yyyy-MM-dd").format(new Date(jkll));
                Detail detail2 = new Detail(event.getText().toString(), dateString2, startTime.getText().toString());
                String detailId2 = myRef.push().getKey();//未來每個禮拜
                detailRef.child(detailId2).setValue(detail2);
                jkll = jkll + 60 * 60 * 24 * 1000 * 7;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(MainActivity7.this, MainActivity6.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.button)
    public void asdf(View view) {
        Intent intent = new Intent(MainActivity7.this, MainActivity25.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    @OnClick(R.id.login12)
    public void kkk(View view) {
        Intent intent = new Intent(MainActivity7.this, MainActivity6.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("UserId", userId);
//        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    String formatDate(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // HH是24小時制，hh是12小時制
        return sdf.format(date);
    }
    public Date convertStringToDate(String time) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(time);
        return date;
    }
    public Calendar convertStringToCalendar(String time) throws ParseException {

        Date date = convertStringToDate(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    public static String getWeek(String time) {
        String Week = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int wek=c.get(Calendar.DAY_OF_WEEK);
        if (wek == 1) {
            Week  = "星期日";
        }
        if (wek == 2) {
            Week  = "星期一";
        }
        if (wek == 3) {
            Week  = "星期二";
        }
        if (wek == 4) {
            Week  = "星期三";
        }
        if (wek == 5) {
            Week  = "星期四";
        }
        if (wek == 6) {
            Week  = "星期五";
        }
        if (wek == 7) {
            Week  = "星期六";
        }
        return Week;
    }
}