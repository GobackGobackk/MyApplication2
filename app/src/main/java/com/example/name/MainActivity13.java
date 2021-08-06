package com.example.name;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;

public class MainActivity13 extends Activity implements View.OnClickListener {
    private TextView mTvTime;
    private Button mBtnStart,mBtnCancel,mBtnRest;
    private ImageView image;
    private CountDownTimer mCounDownTimer;
    private String userId, key;
    private int hour, rest;
    private boolean isPause = false; // 用於判斷是否爲暫停狀態
    private FirebaseDatabase database;
    private DatabaseReference myRef, timeRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        database = FirebaseDatabase.getInstance();
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        hour = bundle.getInt("hour");
        rest = bundle.getInt("rest");
        key = bundle.getString("key");
        initView();
        timeRef = database.getReference("chatRooms/time/" + userId + "/" + key);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            new AlertDialog.Builder(MainActivity13.this)
                    .setTitle("確認視窗")
                    .setMessage("確定要結束此任務嗎? (時間不會保存)")
                    .setIcon(R.drawable.baseline_schedule_24)
                    .setPositiveButton("確定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    mCounDownTimer.cancel();
                                    timeRef.removeValue();
                                    Intent intent = new Intent(MainActivity13.this, MainActivity9.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("UserId", userId);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                }
                            }).show();
        }
        return true;}
    private void initView() {
        mTvTime = (TextView) findViewById(R.id.tv_time);
        image = (ImageView) findViewById(R.id.calendar_imageView6);
        image.setImageResource(R.drawable.fruit_bergamot_yellow);
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener((View.OnClickListener) this);
        mBtnStart.setBackgroundResource(R.drawable.background_light);
        mBtnRest = (Button) findViewById(R.id.btn_rest);
        mBtnRest.setOnClickListener((View.OnClickListener) this);
        mBtnRest.setBackgroundResource(R.drawable.background_light);
        mBtnCancel = (Button) findViewById(R.id.btn_cancel);
        mBtnCancel.setOnClickListener((View.OnClickListener) this);
        mBtnCancel.setBackgroundResource(R.drawable.background_light);
    }
    public void pauseText() {
        if (isPause) {
            rest--;
            if (rest == 0) mBtnRest.setText("休息用光囉!");
            else mBtnRest.setText("還剩" + rest + "次休息機會");
            isPause = false;
        }
    }
    public void timerStart(long timeLengthMilli) {
        mCounDownTimer = new CountDownTimer(timeLengthMilli, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.i("millisUntilFinished", "剩餘時間："+millisUntilFinished);
                int iSecond = (int) ((millisUntilFinished/1000)%60);
                int iMinute = (int) (((millisUntilFinished/1000)/60) % 60);
                int iHour = (int) (((millisUntilFinished/1000)/3600) % 24);
                String sVideoLen = "";
                if(iHour>0)
                    // 顯示 時:分:秒
                    sVideoLen = String.format("%d", iHour) + ":" + String.format("%02d", iMinute) + ":" + String.format("%02d", iSecond);
                else
                if(iMinute>0)
                    // 顯示 分:秒
                    sVideoLen = String.format("%d", iMinute) + ":" + String.format("%02d", iSecond);
                else
                    // 顯示 秒
                    sVideoLen = String.format("%d", iSecond);
                mTvTime.setText(sVideoLen);
            }

            @Override
            public void onFinish() {
                mTvTime.setText("計時結束");
                Intent intent = new Intent(MainActivity13.this, MainActivity9.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId", userId);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        };
        mCounDownTimer.start();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                timerStart(hour*3600000);
                break;
            case R.id.btn_cancel:
                mCounDownTimer.cancel();
                timeRef.removeValue();
                Intent intent = new Intent(MainActivity13.this, MainActivity9.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId", userId);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_rest:
                if(rest != 0){
                    if (!isPause) {//繼續計時
                        mCounDownTimer.cancel();
                        isPause = true;
                        mBtnRest.setText("繼續加油");
                    } else {//暫停計時
                        Long temp;
                        if(mTvTime.getText().toString().length()>5){
                            temp = Long.parseLong(mTvTime.getText()
                                    .toString().split(":")[2]) * 1000 + Long.parseLong(mTvTime.getText()
                                    .toString().split(":")[1]) * 1000 * 60 + Long.parseLong(mTvTime.getText()
                                    .toString().split(":")[0]) * 1000 * 60 * 60;
                        }else{
                            temp = Long.parseLong(mTvTime.getText()
                                    .toString().split(":")[1]) * 1000 + Long.parseLong(mTvTime.getText()
                                    .toString().split(":")[0]) * 1000 * 60;
                        }
                        timerStart(temp);
                        pauseText();
                    }
                }
                break;

            default:
                break;
        }
    }
}