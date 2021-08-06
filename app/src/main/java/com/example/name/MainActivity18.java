package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.example.name.model.Timeee;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity18 extends AppCompatActivity {
    @BindView(R.id.barChart)
    BarChart chart;
    private FirebaseDatabase database;
    private DatabaseReference myRef, timeRef;
    private String userId;
    private int hour1, hour2, hour3, hour4, playhour1, playhour2, playhour3, playhour4;
    private double week1, week2, week3, week4, playweek1, playweek2, playweek3, playweek4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main18);
        ButterKnife.bind(this);
        database = FirebaseDatabase.getInstance();
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        timeRef = database.getReference("chatRooms/time/" + userId);
        timeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                String now = new SimpleDateFormat("MM").format(new Date());
                week1 = 0;
                week2 = 0;
                week3 = 0;
                week4 = 0;
                playweek1 = 0;
                playweek2 = 0;
                playweek3 = 0;
                playweek4 = 0;
                for(DataSnapshot child : snapshot.getChildren()) {
                    Timeee group = new Timeee();
                    group.setCreatedOn(child.child("createdOn").getValue().toString());
                    group.setLastlong(child.child("lastlong").getValue().toString());
                    if(child.hasChild("category")) {//是玩樂
                        if (now.equals(group.getCreatedOn().split("-")[1])) {//當月
                            int i = Integer.parseInt(group.getCreatedOn().split("-")[2]);//日期
                            if (i > 0 && i < 8) {//1-7
                                if (child.child("lastlong").getValue().toString().length() > 5) {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 3600 + Double.parseDouble(group.getLastlong().split(":")[1]) * 60 + Double.parseDouble(group.getLastlong().split(":")[2])) * 1000;
                                    playweek1 = playweek1 + temp;
                                } else {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 60 + Double.parseDouble(group.getLastlong().split(":")[1])) * 1000;
                                    playweek1 = playweek1 + temp;
                                }
                            } else if (i > 7 && i < 16) {//8-15
                                if (child.child("lastlong").getValue().toString().length() > 5) {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 3600 + Double.parseDouble(group.getLastlong().split(":")[1]) * 60 + Double.parseDouble(group.getLastlong().split(":")[2])) * 1000;
                                    playweek2 = playweek2 + temp;
                                } else {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 60 + Double.parseDouble(group.getLastlong().split(":")[1])) * 1000;
                                    playweek2 = playweek2 + temp;
                                }
                            } else if (i > 15 && i < 24) {//16-23
                                if (child.child("lastlong").getValue().toString().length() > 5) {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 3600 + Double.parseDouble(group.getLastlong().split(":")[1]) * 60 + Double.parseDouble(group.getLastlong().split(":")[2])) * 1000;
                                    playweek3 = playweek3 + temp;
                                } else {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 60 + Double.parseDouble(group.getLastlong().split(":")[1])) * 1000;
                                    playweek3 = playweek3 + temp;
                                }
                            } else {
                                if (child.child("lastlong").getValue().toString().length() > 5) {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 3600 + Double.parseDouble(group.getLastlong().split(":")[1]) * 60 + Double.parseDouble(group.getLastlong().split(":")[2])) * 1000;
                                    playweek4 = playweek4 + temp;
                                } else {
                                    Double temp = (Double.parseDouble(group.getLastlong().split(":")[0]) * 60 + Double.parseDouble(group.getLastlong().split(":")[1])) * 1000;
                                    playweek4 = playweek4 + temp;
                                }
                            }
                        }
                    }else{//做事
                        if (now.equals(group.getCreatedOn().split("-")[1])) {//當月
                            int i = Integer.parseInt(group.getCreatedOn().split("-")[2]);//日期
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
                playhour1 = (int) (((playweek1/1000)/3600) % 24);
                playhour2 = (int) (((playweek2/1000)/3600) % 24);
                playhour3 = (int) (((playweek3/1000)/3600) % 24);
                playhour4 = (int) (((playweek4/1000)/3600) % 24);
                initData2(hour1, hour2, hour3, hour4, playhour1, playhour2, playhour3, playhour4);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
//    private void initData1() {
//
//        chart.getDescription().setEnabled(false);
//        chart.setMaxVisibleValueCount(60);
//        chart.setPinchZoom(false);
//        chart.setDrawBarShadow(false);
//        chart.setDrawGridBackground(false);
//        chart.animateY(1500);
//        chart.getLegend().setEnabled(false);
//        XAxis xAxis = chart.getXAxis();
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setDrawGridLines(false);
//        ArrayList<BarEntry> values = new ArrayList<>();
//
//        //Sum就是代码有几个条形
//        int sum=7;
//        for (int i = 0; i < sum; i++) {
//            float multi = (i + 1);
//            float val = (float) (int)((Math.random() * multi) + multi / 2);
//            values.add(new BarEntry(i, val));
//        }
//        BarDataSet set1;
//        if (chart.getData() != null &&
//                chart.getData().getDataSetCount() > 0) {
//            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
//            set1.setValues(values);
//            chart.getData().notifyDataChanged();
//            chart.notifyDataSetChanged();
//        } else {
//            set1 = new BarDataSet(values, "Data Set");
//            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
//            set1.setDrawValues(false);
//            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
//            dataSets.add(set1);
//
//            BarData data = new BarData(dataSets);
//            chart.setData(data);
//            chart.setFitBars(true);
//            final String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; // Your List / array with String Values For X-axis Labels
//            xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));
//        }
//        //这里的作用是将X轴上的文字旋转80度
//        chart.getXAxis().setLabelRotationAngle(-80);
//        chart.getAxisRight().setEnabled(false);
//        chart.setScaleYEnabled(false);
//        chart.setScaleXEnabled(false);
//        chart.invalidate();
//    }
    private void initData2(int hour1, int hour2, int hour3, int hour4, int playhour1, int playhour2, int playhour3, int playhour4) {

        chart.getDescription().setEnabled(false);
        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);
        chart.getAxisRight().setEnabled(false);
        chart.animateY(1500);
        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(true);
        l.setYOffset(0f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(false);
        xAxis.setDrawGridLines(false);
        final String[] weekdays = {"Week1", "Week2", "Week3", "Week4"}; // Your List / array with String Values For X-axis Labels
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weekdays));
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        float groupSpace = 0.2f;
        float barSpace = 0.04f; // x4 DataSet
        float barWidth = 0.3f; // x4 DataSet
        // (0.2 + 0.03) * 4 + 0.08 = 1.00 -> interval per "group"

        //这里设置X\Y
        int k=10;
        int groupCount = 4;
        int startYear = 0;
        int endYear = startYear + groupCount;

        ArrayList<BarEntry> values1 = new ArrayList<>();
        ArrayList<BarEntry> values2 = new ArrayList<>();
//        ArrayList<BarEntry> values3 = new ArrayList<>();
//        ArrayList<BarEntry> values4 = new ArrayList<>();

        float randomMultiplier = k * 10f;
        Integer[] ints = {hour1, hour2, hour3, hour4};
        Integer[] ints2 = {playhour1, playhour2, playhour3, playhour4};
        //这里控制几种类型，我图上加了四个
        for (int i = startYear; i < endYear; i++) {
            values1.add(new BarEntry(i, ints[i]));
            values2.add(new BarEntry(i, ints2[i]));
//            values3.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
//            values4.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
        }

        BarDataSet set1, set2, set3, set4;

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {

            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set2 = (BarDataSet) chart.getData().getDataSetByIndex(1);
//            set3 = (BarDataSet) chart.getData().getDataSetByIndex(2);
//            set4 = (BarDataSet) chart.getData().getDataSetByIndex(3);
            set1.setValues(values1);
            set2.setValues(values2);
//            set3.setValues(values3);
//            set4.setValues(values4);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();
        } else {
            // create 4 DataSets
            set1 = new BarDataSet(values1, "做事");
            set1.setColor(Color.rgb(185,197,183));
            set2 = new BarDataSet(values2, "玩樂");
            set2.setColor(Color.rgb(137,150,134));
//            set3 = new BarDataSet(values3, "依法生产");
//            set3.setColor(Color.rgb(242, 247, 158));
//            set4 = new BarDataSet(values4, "地质灾害");
//            set4.setColor(Color.rgb(255, 102, 0));

            set1.setDrawValues(false);
            set2.setDrawValues(false);
//            set3.setDrawValues(false);
//            set4.setDrawValues(false);

            BarData data = new BarData(set1, set2);
            data.setValueFormatter(new LargeValueFormatter());

            chart.setData(data);
        }

        chart.getBarData().setBarWidth(barWidth);
        // restrict the x-axis range
//        chart.getXAxis().setAxisMinimum(startYear);
        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
//        chart.getXAxis().setAxisMaximum(startYear + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(startYear, groupSpace, barSpace);


        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        chart.getXAxis().setLabelRotationAngle(-80);
        chart.setScaleYEnabled(false);
        chart.setScaleXEnabled(false);
        chart.invalidate();
    }
}