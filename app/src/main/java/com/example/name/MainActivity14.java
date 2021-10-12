package com.example.name;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity14 extends Activity implements View.OnClickListener {
    private TextView mTvTime;
    private Button mBtnStart;
    private ImageView image;
    private CountDownTimer mCounDownTimer;
    private String userId, eventName, key;
    private int hour, rest;
    private boolean is = false; // 用於判斷是否爲暫停狀態


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        eventName = bundle.getString("eventName");
        hour = bundle.getInt("hour");
        rest = bundle.getInt("rest");
        key = bundle.getString("key");
        initView();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            new AlertDialog.Builder(MainActivity14.this)
                    .setTitle("確認視窗")
                    .setMessage("確定要結束休息嗎?")
                    .setIcon(R.drawable.baseline_schedule_24)
                    .setPositiveButton("確定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    mCounDownTimer.cancel();
                                    Intent intent = new Intent(MainActivity14.this, MainActivity15.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("UserId", userId);
                                    bundle.putString("eventName", eventName);
                                    bundle.putInt("hour", hour);
                                    bundle.putInt("rest", rest);
                                    bundle.putString("key", key);
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
        image = (ImageView) findViewById(R.id.calendar_imageView8);
        image.setImageResource(R.drawable.fruit_bergamot_yellow);
        mBtnStart = (Button) findViewById(R.id.btn_start);
        mBtnStart.setOnClickListener((View.OnClickListener) this);
        mBtnStart.setBackgroundResource(R.drawable.background_light);
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
                Intent intent = new Intent(MainActivity14.this, MainActivity15.class);
                Bundle bundle = new Bundle();
                bundle.putString("UserId", userId);
                bundle.putString("eventName", eventName);
                bundle.putInt("hour", hour);
                bundle.putInt("rest", rest);
                bundle.putString("key", key);
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
                if(is == false) {
                    timerStart(rest * 60000);
                    mBtnStart.setVisibility(View.INVISIBLE);
                    is = true;
                }
                break;
            default:
                break;
        }
    }

}