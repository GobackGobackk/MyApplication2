package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.name.model.Calendarrr;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity17 extends AppCompatActivity {
    @BindView(R.id.list123)
    ListView list;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef;
    private String userId;
    ArrayList<String> ar = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);
        ButterKnife.bind(this);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chatRooms/calendar");
        userRef = database.getReference("chatRooms/calendar/"+userId);
        ArrayAdapter adapter = new ArrayAdapter<String>(
        this, android.R.layout.simple_list_item_1,ar);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ar.clear();
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    Calendarrr group = new Calendarrr();
                    group.setEventName(child.child("eventName").getValue().toString());
                    group.setAllFinishDay(child.child("allFinishDay").getValue().toString());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date now = new Date();
                        Date dt2 = sdf.parse(group.getAllFinishDay());
                        if (now.getTime() < dt2.getTime()) { //list只顯示還沒過期的
                            String value = group.getEventName();
                            ar.add(value);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        list.setAdapter(adapter);
        list.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String sel = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(MainActivity17.this,MainActivity16.class);
                Bundle bundle = new Bundle();

                bundle.putString("UserId", userId);
                bundle.putString("sel", sel);//活動名稱
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }
}