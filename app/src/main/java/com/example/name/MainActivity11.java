package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.name.model.GroupChatRoom;
import com.example.name.model.Habit;
import com.example.name.model.Timeee;
import com.example.name.model.User;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity11 extends AppCompatActivity {
    @BindView(R.id.barChart)
    BarChart barChart;
    private FirebaseDatabase database;
    private DatabaseReference myRef, timeRef;
    private String userId, eventName;
    private int hour1, hour2, hour3, hour4;
    private double week1, week2, week3, week4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        eventName = bundle.getString("eventName");
        timeRef = database.getReference("chatRooms/time/" + userId);
        timeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String now = new SimpleDateFormat("MM").format(new Date());
                week1 = 0;
                week2 = 0;
                week3 = 0;
                week4 = 0;
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    Timeee group = new Timeee();
                    group.setCreatedOn(child.child("createdOn").getValue().toString());
                    group.setLastlong(child.child("lastlong").getValue().toString());
                    if(child.child("eventName").getValue().toString().equals(eventName)) {
                        if (now.equals(group.getCreatedOn().split("-")[1])) {//??????
                            int i = Integer.parseInt(group.getCreatedOn().split("-")[2]);//??????
                            if (i > 0 && i < 8) {//1-7
                                if (child.child("lastlong").getValue().toString().length() > 5) {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 3600 + Double.parseDouble(group.getLastlong().split(":")[1]) * 60 + Double.parseDouble(group.getLastlong().split(":")[2])) * 1000;
                                    week1 = week1 + temp;
                                } else {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 60 + Double.parseDouble(group.getLastlong().split(":")[1])) * 1000;
                                    week1 = week1 + temp;
                                }
                            } else if (i > 7 && i < 16) {//8-15
                                if (child.child("lastlong").getValue().toString().length() > 5) {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 3600 + Double.parseDouble(group.getLastlong().split(":")[1]) * 60 + Double.parseDouble(group.getLastlong().split(":")[2])) * 1000;
                                    week2 = week2 + temp;
                                } else {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 60 + Double.parseDouble(group.getLastlong().split(":")[1])) * 1000;
                                    week2 = week2 + temp;
                                }
                            } else if (i > 15 && i < 24) {//16-23
                                if (child.child("lastlong").getValue().toString().length() > 5) {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 3600 + Double.parseDouble(group.getLastlong().split(":")[1]) * 60 + Double.parseDouble(group.getLastlong().split(":")[2])) * 1000;
                                    week3 = week3 + temp;
                                } else {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 60 + Double.parseDouble(group.getLastlong().split(":")[1])) * 1000;
                                    week3 = week3 + temp;
                                }
                            } else {
                                if (child.child("lastlong").getValue().toString().length() > 5) {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 3600 + Double.parseDouble(group.getLastlong().split(":")[1]) * 60 + Double.parseDouble(group.getLastlong().split(":")[2])) * 1000;
                                    week4 = week4 + temp;
                                } else {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 60 + Double.parseDouble(group.getLastlong().split(":")[1])) * 1000;
                                    week4 = week4 + temp;
                                }
                            }
                        }
                    }
                }
                hour1 = (int) (((week1/1000)/3600) % 24);
                hour2 = (int) (((week2/1000)/3600) % 24);
                hour3 = (int) (((week3/1000)/3600) % 24);
                hour4 = (int) (((week4/1000)/3600) % 24);
                //??????x????????????
                XAxis xAxis = barChart.getXAxis();
                //????????????X?????????(???X???????????????),?????????true
                xAxis.setDrawGridLines(false);
                //??????XAxis??????????????????????????? XAxisPosition{ TOP, BOTTOM, BOTH_SIDED, TOP_INSIDE, BOTTOM_INSIDE}
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                //??????X??????????????????,???????????????????????????
                xAxis.setGranularity(1f);
                //??????X??????????????????
                xAxis.setLabelCount(4);
                final String[] weekdays = {"Sun", "Week1", "Week2", "Week3", "Week4"}; // Your List / array with String Values For X-axis Labels
                xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));

                //Step1: ?????? List
                //??????BarEntry
                List<BarEntry> barEntries = new ArrayList<>();
                Integer[] ints = {hour1, hour2, hour3, hour4};
                for (int i = 0; i < ints.length; i++) {    //0-4
                    BarEntry barEntry = new BarEntry(i+1,ints[i]);         //x,y
                    barEntries.add(barEntry);
                }
                //Step2: ??? List ??????????????? BarDataSet ???, ???????????????????????????????????????

                //BarDataSet
                BarDataSet set1 = new BarDataSet(barEntries, "the hours");
                //??????????????????????????????,?????????????????????setColors???setColor
                set1.setColor(Color.rgb(137,150,134));
                //Step3: ??? BarDataSet ??????????????? List ???

                //IBarDataSet
                ArrayList<IBarDataSet> barDataSets = new ArrayList<>();
                barDataSets.add(set1);

                //BarData
                //Step4: List ??????????????? BarData ???
                BarData barData = new BarData(barDataSets);
                //????????????????????????
                barData.setBarWidth(0.5f);
                //?????????????????????????????????
                barData.setValueTextSize(10);

                //Step5: ??? BarData ??????????????? BarChart ???
                barChart.setData(barData);
                barChart.animateY(1500);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}