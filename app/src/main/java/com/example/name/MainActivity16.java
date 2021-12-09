package com.example.name;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.name.Notifications.FcmNotificaionSender;
import com.example.name.model.Calendarrr;
import com.example.name.model.Habit;
import com.example.name.model.Msg;
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
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity16 extends Activity implements View.OnClickListener {
    @BindView(R.id.textView34)
    TextView text;
    @BindView(R.id.textView50)
    TextView text2;
    @BindView(R.id.textView51)
    TextView lateBad;
    @BindView(R.id.textView53)
    TextView text3;
    @BindView(R.id.start3)
    Button sub;
    @BindView(R.id.seekBar2)
    SeekBar seek;
    @BindView(R.id.textView54)
    TextView text4;
    @BindView(R.id.textView55)
    TextView text5;
    @BindView(R.id.textView56)
    TextView text6;//傳資料用
    @BindView(R.id.textView3)
    TextView text7;//傳資料用
    @BindView(R.id.textView75)
    TextView text8;//傳資料用
    @BindView(R.id.textView78)
    TextView text9;//傳資料用
    @BindView(R.id.textView92)
    TextView text10;//傳資料用
    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef, detailRef, lateRef, lateRef2, uRef, mRef, abilityRef, timeRef, gRef;
    ArrayList<String> ar = new ArrayList<String>();
    private String userId, sel, ssss;
    private User user;
    private HashMap<String, Integer> hashMap = new HashMap<>();

    private ImageView image;
    private TextView timer;
    private Button start;
    private Button pause;
    private Button reset;
    private Button stop;
    private Chronometer chronometer;
    private boolean oneTime = false;
    private boolean isPause = false; // 用於判斷是否爲暫停狀態
    //new for 11/7 notifications
    String userToken;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        sel = bundle.getString("sel");
        text.setText(sel);


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chatRooms/time/"+userId);
        timeRef = database.getReference("chatRooms/time/");
        detailRef = database.getReference("chatRooms/detail/"+userId);
        userRef = database.getReference("chatRooms/calendar/"+userId);
        lateRef = database.getReference("chatRooms/late/"+userId);
        lateRef2 = database.getReference("chatRooms/late/");
        uRef = database.getReference("chatRooms/userProfiles/"+userId);
        abilityRef = database.getReference("chatRooms/userProfiles/"+userId+"/ability/");

        uRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String p = String.valueOf(progress);
                text4.setText(p);//專心度數值
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        userRef.addValueEventListener(new ValueEventListener() { //行事曆
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    if(child.hasChild("dayofweek")) {
                        if (child.child("eventName").getValue().toString().equals(sel)) {
                            Calendarrr group = new Calendarrr();
                            group.setDayofweek(child.child("dayofweek").getValue().toString());
                            group.setEventStartTime(child.child("eventStartTime").getValue().toString());
                            group.setEventFinishTime(child.child("eventFinishTime").getValue().toString());
                            text2.setText(group.getDayofweek()+" "+group.getEventStartTime()+" 到 "+group.getEventFinishTime());

                            Double temp = ( Double.parseDouble(child.child("eventStartTime").getValue().toString().split(":")[0]) * 60 + Double.parseDouble(child.child("eventStartTime").getValue().toString().split(":")[1]));
                            Double temp2 = ( Double.parseDouble(child.child("eventFinishTime").getValue().toString().split(":")[0]) * 60 + Double.parseDouble(child.child("eventFinishTime").getValue().toString().split(":")[1]));
                            Double temp3 = temp2 - temp;//一周應執行分鐘
                            String ss = Double.toString(temp3);
                            text7.setText(ss);//暫存
//                            int ii = Integer.valueOf(temp3.intValue());

                            if(!child.child("notification").equals("")){
                                ssss = child.child("notification").getValue().toString();//群組id
                                mRef = database.getReference("chatRooms/ring/"+ssss);
                                gRef = database.getReference("chatRooms/group/"+ssss);
//                                gRef = database.getReference("chatRooms/group/"+ssss+"/members/");
                                gRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                                        text10.setText(snapshot.child("groupName").getValue().toString());
                                        for(DataSnapshot child : snapshot.child("members").getChildren()){
                                            mRef.child("members").child(child.getKey()).child("userId").setValue(child.getKey());
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                                    }
                                });
                            }
                        }
                    }
                    //每個活動執行的分鐘 之後算執行率
                    Double temp = ( Double.parseDouble(child.child("eventStartTime").getValue().toString().split(":")[0]) * 60 + Double.parseDouble(child.child("eventStartTime").getValue().toString().split(":")[1]));
                    Double temp2 = ( Double.parseDouble(child.child("eventFinishTime").getValue().toString().split(":")[0]) * 60 + Double.parseDouble(child.child("eventFinishTime").getValue().toString().split(":")[1]));
                    Double temp3 = temp2 - temp;//一周應執行分鐘
                    int ii = Integer.valueOf(temp3.intValue());// 60
                    hashMap.put(child.child("eventName").getValue().toString(), ii);// 背英文單字 60
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        image = (ImageView) findViewById(R.id.calendar_imageView9);
        image.setImageResource(R.drawable.calendar);
        timer = (TextView) findViewById(R.id.textView30);
        pause = (Button) findViewById(R.id.pause);
        pause.setBackgroundResource(R.drawable.background_light);
        start = (Button) findViewById(R.id.start);
        start.setBackgroundResource(R.drawable.background_light);
        reset = (Button) findViewById(R.id.reset);
        reset.setBackgroundResource(R.drawable.background_light);
        stop = (Button) findViewById(R.id.stop);
        stop.setBackgroundResource(R.drawable.background_light);
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        // 註冊監聽器
        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        reset.setOnClickListener(this);
        stop.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                // 保證什麼時候開始，計時的時間都是從0開始計時
                chronometer.start();
                chronometer.setBase(SystemClock.elapsedRealtime());
                detailRef.addValueEventListener(new ValueEventListener() {//今天之前 該做的所有次數
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        Date now = new Date();//取得目前即時的時間
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = sdf.format(now);
                        int count = 0, alltime = 0;
                        for(DataSnapshot val : snapshot.getChildren()){
//                            if(val.child("eventName").getValue().toString().equals(sel)) {
                                SimpleDateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    Date noww = new Date();//取得目前即時的時間
                                    Date f = df3.parse(val.child("datee").getValue().toString());
                                    if(noww.getTime() > f.getTime()){//以前的目標數量
                                        if(hashMap.containsKey(val.child("eventName").getValue().toString())){
                                            text8.setText(hashMap.get(val.child("eventName").getValue().toString())+"/");
                                            int bb = Integer.valueOf(text8.getText().toString().replace("/", ""));//取出之前存在hashMap裡的分鐘數
                                            alltime += bb; }//把分鐘數加在一起    應達成的時間
                                        count++;
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                if (val.child("datee").getValue().toString().equals(dateString)) {//當天
                                    Double temp = (Double.parseDouble(val.child("timee").getValue().toString().split(":")[0]) * 60 + Double.parseDouble(val.child("timee").getValue().toString().split(":")[1]));
                                    String nowww = new SimpleDateFormat("HH:mm").format(new Date());
                                    Double noww = (Double.parseDouble(nowww.split(":")[0]) * 60 + Double.parseDouble(nowww.split(":")[1]));
                                    if (temp < noww) { //就當天 該活動而言 執行時間比計畫時間晚
                                        lateBad.setText("遲到 BAD!");
                                        if (oneTime == false) {
                                            String wer = dateString+sel;
                                            lateRef.child(wer).child("late").setValue(sel);
                                            oneTime = true;
                                        }
                                    }
                                }
//                            }
                        }
                        text6.setText(String.format("%d", count));
                        text9.setText(String.format("%d", alltime));
                    }
                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });

                break;
            case R.id.pause:
                // 實現了計時的暫停功能
                // 如果不是處於暫停狀態，則停止計時
                // 如果處於暫停狀態，則通過計算，從暫停的那個時間段開始計時
                if (!isPause) {
                    chronometer.stop();
                    isPause = true;
                    pause.setText("繼續計時");
                } else {
                    Double temp = ( Double.parseDouble(chronometer.getText()
                            .toString().split(":")[0]) * 60 + Double.parseDouble(chronometer.getText()
                            .toString().split(":")[1]) ) * 1000;
                    chronometer
                            .setBase((long) (SystemClock.elapsedRealtime() - temp));
                    chronometer.start();
                    pauseText();
                }
                break;
            case R.id.reset:
                // chronometer.start();這句是爲了防止點擊暫停鍵後，chronometer處於stop（）狀態，不能計時
                chronometer.start();
                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseText();
                break;
            case R.id.stop:
                // 停止後，使時間歸零
                chronometer.stop();
                timer.setText("獲得的時間爲：" + chronometer.getText().toString());
                Double ddd = Double.valueOf(text7.getText().toString());//行事曆活動時間 單位分鐘
                ddd = ddd * 60;
                int iii = Integer.valueOf(ddd.intValue());
                int ii;//實際執行時間
                if(chronometer.getText().toString().length()>5) {
                    Double temp = ( Double.parseDouble(chronometer.getText().toString().split(":")[0]) * 3600 + Double.parseDouble(chronometer.getText().toString().split(":")[1]) * 60 + Double.parseDouble(chronometer.getText().toString().split(":")[2]));
                    ii = Integer.valueOf(temp.intValue());
                }else{
                    Double temp = ( Double.parseDouble(chronometer.getText().toString().split(":")[0]) * 60 + Double.parseDouble(chronometer.getText().toString().split(":")[1]));
                    ii = Integer.valueOf(temp.intValue());
                }
                if(iii<ii){//只要執行的比預設的時間久 就會自動傳訊息到聊天室
                    String messageId = myRef.push().getKey();
                    String createdOn = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
                    String content = "已完成" + sel;
                    Msg msg = new Msg(messageId, content, user.getId(),
                            user.getName(), createdOn ,Msg.TYPE_SENT);
                    mRef.child(messageId).setValue(msg);
                    mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                    //send notification
//                    String receiver_name = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                    String receiver_name = user.getName();

                    String myText = "你的"+ text10.getText().toString() +"群組中有人"+ content;
                    //sendNotfification(userId, receiver_name, myText);

                    //send Notification to topic(groupId)
                    FcmNotificaionSender notificaionSender = new FcmNotificaionSender("/topics/"+ssss, "You got a message.",
                            myText, getApplicationContext(), MainActivity16.this);
                    notificaionSender.SendNotifications();

                }

                //計算準時率
                lateRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if(snapshot.hasChild(userId)){
                            int c = 0;
                            for(DataSnapshot child : snapshot.child(userId).getChildren()){
                                c++;
                            }
                            int i = Integer.valueOf(text6.getText().toString());
                            NumberFormat nf = new DecimalFormat("#%");
                            Double gh = Double.valueOf(i);//共有幾次
                            Double czx = Double.valueOf(c);//遲到幾次
                            Double mn = (gh - czx)/gh;
                            String af = nf.format(mn);
                            abilityRef.child("ontime").setValue(af);
                        }
                        else {
                            abilityRef.child("ontime").setValue("100%");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });




//                chronometer.setBase(SystemClock.elapsedRealtime());
                pauseText();
                sub.setVisibility(View.VISIBLE);
                seek.setVisibility(View.VISIBLE);
                text3.setVisibility(View.VISIBLE);
                text4.setVisibility(View.VISIBLE);
                text5.setVisibility(View.VISIBLE);
                break;

            default:
                break;
        }
    }

    // 設置處於暫停狀態時，pause按鈕的文字顯示
    public void pauseText() {
        if (isPause) {
            pause.setText("暫停計時");
            isPause = false;
        }
    }
    @OnClick(R.id.start3)
    public void jdfks(View view){
        String jkl = myRef.push().getKey(); //myRef 是 time
        String createdOn = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String timeOn = new SimpleDateFormat("HH:mm").format(new Date());
        //儲存執行時間 活動名稱 開始時間 準時
        Timeee profile = new Timeee();
        profile.setLastlong(chronometer.getText().toString());
        profile.setEventName(sel);
        profile.setCreatedOn(createdOn);
        profile.setOnTime(timeOn);
        myRef.child(jkl).setValue(profile);
//        myRef = database.getReference("chatRooms/time/"+userId);
        myRef.child(jkl).child("focus").setValue(text4.getText().toString());

        //計算專注率 和 執行率的實際執行時間部分
        timeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.hasChild(userId)){
                    int c = 0, i = 0, all = 0;
                    Double day = 0.0;
                    for(DataSnapshot child : snapshot.child(userId).getChildren()){
                        if(child.hasChild("focus")) {
                            Double temp;
                            if (child.child("lastlong").getValue().toString().length() > 5) {
                                temp = (Double.parseDouble(child.child("lastlong").getValue().toString().split(":")[0]) * 3600 + Double.parseDouble(child.child("lastlong").getValue().toString().split(":")[1]) * 60 + Double.parseDouble(child.child("lastlong").getValue().toString().split(":")[2]));
                            } else if (child.child("lastlong").getValue().toString().split(":")[0].equals("00")) {
                                temp = Double.parseDouble(child.child("lastlong").getValue().toString().split(":")[1]);
                            } else {
                                temp = (Double.parseDouble(child.child("lastlong").getValue().toString().split(":")[0]) * 60 + Double.parseDouble(child.child("lastlong").getValue().toString().split(":")[1]));
                            }
                            day += temp; //秒為單位

                            c++;
                            i = Integer.valueOf(child.child("focus").getValue().toString());
                            all += i;
                        }
                    }

                    Double ddd = Double.valueOf(text9.getText().toString());//預設 分鐘為單位
                    if(ddd == 0.0){
                        abilityRef.child("complete").setValue("100%");
                    }else{
                        String x = String.format("%.0f", day/(ddd/60.0*100.0));
                        abilityRef.child("complete").setValue(x+"%");
                    }
                    String s = String.format("%d", all/c);
                    abilityRef.child("focus").setValue(s+"%");
                }
                else{
                    abilityRef.child("complete").setValue("0%");
                    abilityRef.child("focus").setValue("0%");
                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        Intent intent = new Intent(MainActivity16.this, MainActivity6.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("UserId", userId);
//        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    //new for 10/22
    private void sendNotfification(String receiver_id, String receiver_name, String message){

        FirebaseDatabase.getInstance().getReference("Tokens").child(receiver_id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NotNull DataSnapshot snapshot) {
                        userToken = snapshot.getValue().toString();
                        Log.d("demo", userToken);

                    }

                    @Override
                    public void onCancelled(@NotNull DatabaseError error) {

                    }
                });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FcmNotificaionSender notificaionSender = new FcmNotificaionSender(userToken, "You've got a message", receiver_name+ " : " +message,
                        getApplicationContext(), MainActivity16.this);
                notificaionSender.SendNotifications();
            }
        }, 3000);
    }
}