package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.TypefaceCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.name.model.Calendarrr;
import com.example.name.model.GroupChatRoom;
import com.example.name.model.Profile;
import com.example.name.model.Timeee;
import com.example.name.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity8 extends AppCompatActivity {
    @BindView(R.id.textView4)//名字
    TextView text;
    @BindView(R.id.textView7)//選擇的活動
    TextView eventName;
    @BindView(R.id.textView13)//堅持天數
    TextView lastdays;
    @BindView(R.id.textView16)//維持天數
    TextView maintain;
    @BindView(R.id.textView21)//全部時數 下面的
    TextView all;
    @BindView(R.id.textView14)//累積時間 活動
    TextView accumulate;
    @BindView(R.id.textView15)//平均一天
    TextView average;
    @BindView(R.id.textView25)//玩樂/做事
    TextView playanddo;
    @BindView(R.id.textView24)//準時
    TextView ontime;
    @BindView(R.id.textView22)//達成率
    TextView complete;
    @BindView(R.id.textView23)//專注率
    TextView focus;
    @BindView(R.id.textView52)//暫放
    TextView test;
    @BindView(R.id.ivUser3)//圖片
    ImageView image;

    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef, userRef2, eventRef, timeRef, alltimeRef, detailRef, lateRef, focusRef;
    private String grpId, groupName, userId, asd, ev123, ssdd;
    private User user;
    private Profile profile;
    private double dayDiffff, play, dayD;
    private HashMap<String, Integer> hashMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        ev123 = bundle.getString("eventName");
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("chatRooms/userProfiles/"+ userId);
        eventRef = database.getReference("chatRooms/calendar/" + userId);
        myRef = database.getReference("chatRooms/ui/" + userId);
        timeRef = database.getReference("chatRooms/time/");
        alltimeRef = database.getReference("chatRooms/time/");
        detailRef = database.getReference("chatRooms/detail/");
        lateRef = database.getReference("chatRooms/late/");
        focusRef = database.getReference("chatRooms/time/");


        profile = new Profile();//起始天和結束天
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                text.setText(user.getName());
                if(user.getGender().equals("Male")) image.setImageResource(R.drawable.study_school_jugyou_man);
                else image.setImageResource(R.drawable.study_school_jugyou_woman);
                if(dataSnapshot.hasChild("ability")){
                    complete.setText(dataSnapshot.child("ability").child("complete").getValue().toString());
                    focus.setText(dataSnapshot.child("ability").child("focus").getValue().toString());
                    ontime.setText(dataSnapshot.child("ability").child("ontime").getValue().toString());
                }else{
                    complete.setText("No data");
                    focus.setText("No data");
                    ontime.setText("No data");
                }
//                complete.setTypeface(getTypeface());
//                focus.setTypeface(getTypeface());
//                ontime.setTypeface(getTypeface());
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });


        eventName.setText(ev123);
        eventRef.addValueEventListener(new ValueEventListener() {//計算堅持天數和剩餘幾天 及 (目標達成率的分母) 這個改到 Main16算
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot val : snapshot.getChildren()){
                    Calendarrr cal = new Calendarrr();
                    cal.setEventName(val.child("eventName").getValue().toString());
                    cal.setEventStartDay(val.child("eventStartDay").getValue().toString());
                    cal.setEventFinishDay(val.child("allFinishDay").getValue().toString());
                    cal.setEventStartTime(val.child("eventStartTime").getValue().toString());
                    cal.setEventFinishTime(val.child("eventFinishTime").getValue().toString());
                    if(cal.getEventName().equals(ev123)){
                        profile.setStart(cal.getEventStartDay());
                        profile.setFinish(cal.getEventFinishDay());
                        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date now = new Date();//取得目前即時的時間
                            Date f = df2.parse(profile.getStart());
                            Date ff = df2.parse(profile.getFinish());
                            long aDayInMilliSecond = 60 * 60 * 24 * 1000; //一天的毫秒數
                            long dayDiff = (now.getTime() - f.getTime()) / aDayInMilliSecond;
                            long dayDiff2 = (ff.getTime() - now.getTime()) / aDayInMilliSecond;
                            myRef.child("countStart").setValue(String.valueOf(dayDiff));//今天距離起始天幾天
                            myRef.child("countFinish").setValue(String.valueOf(dayDiff2));//今天距離結束天幾天
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
//                    Double temp = ( Double.parseDouble(cal.getEventStartTime().split(":")[0]) * 60 + Double.parseDouble(cal.getEventStartTime().split(":")[1]));
//                    Double temp2 = ( Double.parseDouble(cal.getEventFinishTime().split(":")[0]) * 60 + Double.parseDouble(cal.getEventFinishTime().split(":")[1]));
//                    Double temp3 = temp2 - temp;//一周應執行分鐘  e.g. 60.0
//                    int ii = Integer.valueOf(temp3.intValue());// 60
//                    hashMap.put(cal.getEventName(), ii);// 背英文單字 60
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                lastdays.setText(snapshot.child("countStart").getValue().toString());
                maintain.setText(snapshot.child("countFinish").getValue().toString());
//                lastdays.setTypeface(getTypeface());
//                maintain.setTypeface(getTypeface());
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        dayDiffff = 0;
        timeRef.addValueEventListener(new ValueEventListener() {//單個活動計算時間 總時間和平均時間 上半部分
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(userId)){
                    for(DataSnapshot child : dataSnapshot.child(userId).getChildren()) {
                        if(child.child("eventName").getValue().toString().equals(ev123)) {
                            Timeee group = new Timeee();
                            Double temp;
                            group.setLastlong(child.child("lastlong").getValue().toString());
                            if(child.child("lastlong").getValue().toString().length()>5) {//時分秒
                                temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 3600 + Double.parseDouble(group.getLastlong().split(":")[1]) * 60 + Double.parseDouble(group.getLastlong().split(":")[2])) * 1000;
                            }else if(child.child("lastlong").getValue().toString().split(":")[0].equals("00")){
                                temp = (Double.parseDouble(group.getLastlong().split(":")[1])) * 1000;//秒
                            }else {//分秒
                                temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 60 + Double.parseDouble(group.getLastlong().split(":")[1])) * 1000;
                            }
                            dayDiffff = dayDiffff + temp;
                            Double uiop = Double.parseDouble(lastdays.getText().toString());
                            Double jlkljjj = dayDiffff/uiop;
                            int iSecond = (int) ((dayDiffff/1000)%60);
                            int iMinute = (int) (((dayDiffff/1000)/60) % 60);
                            int iHour = (int) (((dayDiffff/1000)/3600) % 24);
                            int iSecond2 = (int) ((jlkljjj/1000)%60);
                            int iMinute2 = (int) (((jlkljjj/1000)/60) % 60);
                            int iHour2 = (int) (((jlkljjj/1000)/3600) % 24);
                            String sVideoLen;
                            String sVideoLen2;
                            if(iHour>0){
                                // 顯示 時:分:秒
                                sVideoLen = String.format("%d", iHour) + ":" + String.format("%02d", iMinute) + ":" + String.format("%02d", iSecond);
                                sVideoLen2 = String.format("%d", iHour2) + ":" + String.format("%02d", iMinute2) + ":" + String.format("%02d", iSecond2);
                            }
                            else if(iMinute>0){
                                // 顯示 分:秒
                                sVideoLen = String.format("%02d", iMinute) + ":" + String.format("%02d", iSecond);
                                sVideoLen2 = String.format("%02d", iMinute2) + ":" + String.format("%02d", iSecond2);
                            }
                            else{
                                // 顯示 秒
                                sVideoLen = "00:" + String.format("%02d", iSecond);
                                sVideoLen2 = "00:" + String.format("%02d", iSecond2);
                            }
                            accumulate.setText(sVideoLen);
//                            accumulate.setTypeface(getTypeface());
                            average.setText(sVideoLen2);
//                            average.setTypeface(getTypeface());
                        }
                    }
                }
                else{
                    accumulate.setText("No data");
//                    accumulate.setTypeface(getTypeface());
                    average.setText("No data");
//                    average.setTypeface(getTypeface());
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        alltimeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dayD = 0;
                play = 0;
                if(dataSnapshot.hasChild(userId)){
                    for(DataSnapshot child : dataSnapshot.child(userId).getChildren()) {
                        if(!child.hasChild("category")) {//是做事
                            Timeee group = new Timeee();
                            group.setLastlong(child.child("lastlong").getValue().toString());
                            Double temp;
                            if (child.child("lastlong").getValue().toString().length() > 5) {
                                temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 3600 + Double.parseDouble(group.getLastlong().split(":")[1]) * 60 + Double.parseDouble(group.getLastlong().split(":")[2])) * 1000;
                            }else if(child.child("lastlong").getValue().toString().split(":")[0].equals("00")){
                                temp = Double.parseDouble(group.getLastlong().split(":")[1]) * 1000;
                            }else {
                                temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 60 + Double.parseDouble(group.getLastlong().split(":")[1])) * 1000;
                            }
                            dayD = dayD + temp;

                        }else {//是玩樂
                            Timeee group = new Timeee();
                            group.setLastlong(child.child("lastlong").getValue().toString());
                            Double temp;
                            if (child.child("lastlong").getValue().toString().length() > 5) {
                                temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 3600 + Double.parseDouble(group.getLastlong().split(":")[1]) * 60 + Double.parseDouble(group.getLastlong().split(":")[2])) * 1000;
                            }else if(child.child("lastlong").getValue().toString().split(":")[0].equals("00")){
                                temp = Double.parseDouble(group.getLastlong().split(":")[1]) * 1000;
                            }else {
                                temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 60 + Double.parseDouble(group.getLastlong().split(":")[1])) * 1000;
                            }
                            play = play + temp;
                        }
                    }
                    int iSecond = (int) ((dayD/1000)%60);
                    int iMinute = (int) (((dayD/1000)/60) % 60);
                    int iHour = (int) (((dayD/1000)/3600) % 24);
                    int playHour = (int) (((play/1000)/3600) % 24);
                    String sVideoLen = "";
                    if(iHour>0){
                        // 顯示 時:分:秒
                        sVideoLen = String.format("%d", iHour) + ":" + String.format("%02d", iMinute) + ":" + String.format("%02d", iSecond);
                    }
                    else if(iMinute>0){
                        // 顯示 分:秒
                        sVideoLen = String.format("%02d", iMinute) + ":" + String.format("%02d", iSecond);
                    }
                    else{
                        // 顯示 秒
                        sVideoLen = "00:" + String.format("%02d", iSecond);
                    }
                    all.setText(sVideoLen);
                    playanddo.setText(playHour + " hr / " + iHour + " hr");
//                    all.setTypeface(getTypeface());
//                    playanddo.setTypeface(getTypeface());
                }
                else{
                    all.setText("No data");
//                    all.setTypeface(getTypeface());
                    playanddo.setText("No data");
//                    playanddo.setTypeface(getTypeface());
                }
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

//        detailRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                if(snapshot.hasChild(userId)){
//                    String createdOn = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//                    String timeOn = new SimpleDateFormat("HH:mm").format(new Date());
//                    int count = 0, alltime = 0;
//                    SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                    for(DataSnapshot child : snapshot.child(userId).getChildren()) {
//                        String bind = child.child("datee").getValue().toString() + " " + child.child("timee").getValue().toString();
//                        try {
//                            Date now = new Date();//取得目前即時的時間
//                            Date f = df3.parse(bind);
//                            if(now.getTime() > f.getTime()){//以前的目標數量
//                                if(hashMap.containsKey(child.child("eventName").getValue().toString())){
//                                    test.setText(hashMap.get(child.child("eventName").getValue().toString())+"/");
//                                    int bb = Integer.valueOf(test.getText().toString().replace("/", ""));//取出之前存在hashMap裡的分鐘數
//                                    alltime += bb; //把分鐘數加在一起    應達成的時間
//                                }
//                                count++;
//                            }
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    String zxccv = all.getText().toString();
//                    Double temp = 0.0;//實際執行時間
//                    if(!zxccv.equals("No data")){
//                        if(zxccv.length()>5) temp = (Double.parseDouble(zxccv.split(":")[0])*60+Double.parseDouble(zxccv.split(":")[1]));
//                        else if(zxccv.split(":")[0].equals("00")) temp = 0.0;
//                        else temp = Double.parseDouble(zxccv.split(":")[0]);
//                    }
//                    NumberFormat nf = new DecimalFormat("#%");
//                    Double lk = temp/alltime;//執行率
//                    String af = nf.format(lk);
//                    complete.setText(af);
////                    complete.setTypeface(getTypeface());
//                    String c = String.format("%d", count);
//                    ontime.setText(c);// 以前應執行的次數
//                }
//                else{
//                    complete.setText("No data");
////                    complete.setTypeface(getTypeface());
//                    ontime.setText("0");
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });


//        lateRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                if(snapshot.hasChild(userId)){
//                    int c = 0;
//                    for(DataSnapshot child : snapshot.child(userId).getChildren()){
//                        c++;
//                    }
//                    int i = Integer.valueOf(ontime.getText().toString());
//                    NumberFormat nf = new DecimalFormat("#%");
//                    Double gh = Double.valueOf(i);
//                    Double czx = Double.valueOf(c);
//                    Double mn = (gh - czx)/gh;
//                    String af = nf.format(mn);
//                    ontime.setText(af);
////                    ontime.setTypeface(getTypeface());
//                }
//                else {
//                    ontime.setText("No data");
////                    ontime.setTypeface(getTypeface());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });
//        focusRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                if(snapshot.hasChild(userId)){
//                    int c = 0, i = 0, all = 0;
//                    for(DataSnapshot child : snapshot.child(userId).getChildren()){
//                        if(child.hasChild("focus")){
//                            c++;
//                            i = Integer.valueOf(child.child("focus").getValue().toString());
//                            all += i;
//                        }
//                    }
//                    String s = String.format("%d", all/c);
//                    focus.setText(s + "%");
////                    focus.setTypeface(getTypeface());
//                }
//                else{
//                    focus.setText("No data");
////                    focus.setTypeface(getTypeface());
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });
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



    @OnClick(R.id.textView27)
    public void la(View view) {
        Intent intent = new Intent(MainActivity8.this, MainActivity11.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        bundle.putString("eventName", eventName.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }
    @OnClick(R.id.textView28)
    public void lalala(View view) {
        Intent intent = new Intent(MainActivity8.this, MainActivity18.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    @OnClick(R.id.textView4)
    public void jkll(View view) {
        Intent intent = new Intent(MainActivity8.this, MainActivity27.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    @OnClick(R.id.login2)
    public void asd(View view){
        Intent intent = new Intent(MainActivity8.this, MainActivity5.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
    private Typeface getTypeface() {
        @SuppressLint("RestrictedApi")
        Typeface typeface = TypefaceCompat.createFromResourcesFontFile(MainActivity8.this, getResources(), R.font.ubuntu_regular, "", 0);

        return typeface;
    }
    private Typeface getTypefaceBold() {
        @SuppressLint("RestrictedApi")
        Typeface typeface = TypefaceCompat.createFromResourcesFontFile(MainActivity8.this, getResources(), R.font.ubuntu_bold, "", 0);

        return typeface;
    }

}