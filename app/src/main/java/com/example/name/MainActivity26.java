package com.example.name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity26 extends AppCompatActivity {
    @BindView(R.id.checkBox)
    CheckBox c1;
    @BindView(R.id.checkBox2)
    CheckBox c2;
    @BindView(R.id.checkBox3)
    CheckBox c3;
    @BindView(R.id.checkBox4)
    CheckBox c4;
    @BindView(R.id.checkBox5)
    CheckBox c5;
    @BindView(R.id.checkBox6)
    CheckBox c6;
    @BindView(R.id.checkBox7)
    CheckBox c7;
    @BindView(R.id.checkBox8)
    CheckBox c8;
    @BindView(R.id.checkBox9)
    CheckBox c9;
    @BindView(R.id.checkBox10)
    CheckBox c10;
    @BindView(R.id.checkBox11)
    CheckBox c11;
    @BindView(R.id.checkBox12)
    CheckBox c12;
    @BindView(R.id.checkBox13)
    CheckBox c13;
    @BindView(R.id.checkBox14)
    CheckBox c14;
    @BindView(R.id.checkBox15)
    CheckBox c15;
    @BindView(R.id.checkBox16)
    CheckBox c16;
    @BindView(R.id.checkBox17)
    CheckBox c17;
    @BindView(R.id.checkBox18)
    CheckBox c18;
    @BindView(R.id.checkBox19)
    CheckBox c19;
    @BindView(R.id.checkBox20)
    CheckBox c20;
    @BindView(R.id.checkBox21)
    CheckBox c21;
    @BindView(R.id.checkBox22)
    CheckBox c22;
    @BindView(R.id.checkBox23)
    CheckBox c23;
    @BindView(R.id.checkBox24)
    CheckBox c24;
    @BindView(R.id.checkBox25)
    CheckBox c25;
    @BindView(R.id.checkBox26)
    CheckBox c26;
    @BindView(R.id.checkBox27)
    CheckBox c27;
    @BindView(R.id.checkBox28)
    CheckBox c28;
    @BindView(R.id.checkBox29)
    CheckBox c29;
    @BindView(R.id.checkBox30)
    CheckBox c30;
    @BindView(R.id.checkBox31)
    CheckBox c31;
    @BindView(R.id.checkBox32)
    CheckBox c32;
    @BindView(R.id.checkBox33)
    CheckBox c33;
    @BindView(R.id.checkBox34)
    CheckBox c34;
    @BindView(R.id.checkBox35)
    CheckBox c35;
    @BindView(R.id.checkBox36)
    CheckBox c36;
    @BindView(R.id.checkBox37)
    CheckBox c37;
    @BindView(R.id.checkBox38)
    CheckBox c38;
    @BindView(R.id.checkBox39)
    CheckBox c39;
    @BindView(R.id.checkBox40)
    CheckBox c40;
    @BindView(R.id.checkBox41)
    CheckBox c41;
    @BindView(R.id.checkBox42)
    CheckBox c42;
    @BindView(R.id.checkBox43)
    CheckBox c43;
    @BindView(R.id.checkBox44)
    CheckBox c44;
    private FirebaseDatabase database;
    private DatabaseReference myRef, userRef, eventRef;
    private String grpId, groupName, userId;
    private List<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main26);
        ButterKnife.bind(this);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("chatRooms/userProfiles/"+ userId+"/goal");
        list.clear();
    }
    @OnClick(R.id.RegisterButton)
    public void jkl(View view) {
        if(c1.isChecked()) list.add(c1.getText().toString());
        if(c2.isChecked()) list.add(c2.getText().toString());
        if(c3.isChecked()) list.add(c3.getText().toString());
        if(c4.isChecked()) list.add(c4.getText().toString());
        if(c5.isChecked()) list.add(c5.getText().toString());
        if(c6.isChecked()) list.add(c6.getText().toString());
        if(c7.isChecked()) list.add(c7.getText().toString());
        if(c8.isChecked()) list.add(c8.getText().toString());
        if(c9.isChecked()) list.add(c9.getText().toString());
        if(c10.isChecked()) list.add(c10.getText().toString());
        if(c11.isChecked()) list.add(c11.getText().toString());
        if(c12.isChecked()) list.add(c12.getText().toString());
        if(c13.isChecked()) list.add(c13.getText().toString());
        if(c14.isChecked()) list.add(c14.getText().toString());
        if(c15.isChecked()) list.add(c15.getText().toString());
        if(c16.isChecked()) list.add(c16.getText().toString());
        if(c17.isChecked()) list.add(c17.getText().toString());
        if(c18.isChecked()) list.add(c18.getText().toString());
        if(c19.isChecked()) list.add(c19.getText().toString());
        if(c20.isChecked()) list.add(c20.getText().toString());
        if(c21.isChecked()) list.add(c21.getText().toString());
        if(c22.isChecked()) list.add(c22.getText().toString());
        if(c23.isChecked()) list.add(c23.getText().toString());
        if(c24.isChecked()) list.add(c24.getText().toString());
        if(c25.isChecked()) list.add(c25.getText().toString());
        if(c26.isChecked()) list.add(c26.getText().toString());
        if(c27.isChecked()) list.add(c27.getText().toString());
        if(c28.isChecked()) list.add(c28.getText().toString());
        if(c29.isChecked()) list.add(c29.getText().toString());
        if(c30.isChecked()) list.add(c30.getText().toString());
        if(c31.isChecked()) list.add(c31.getText().toString());
        if(c32.isChecked()) list.add(c32.getText().toString());
        if(c33.isChecked()) list.add(c33.getText().toString());
        if(c34.isChecked()) list.add(c34.getText().toString());
        if(c35.isChecked()) list.add(c35.getText().toString());
        if(c36.isChecked()) list.add(c36.getText().toString());
        if(c37.isChecked()) list.add(c37.getText().toString());
        if(c38.isChecked()) list.add(c38.getText().toString());
        if(c39.isChecked()) list.add(c39.getText().toString());
        if(c40.isChecked()) list.add(c40.getText().toString());
        if(c41.isChecked()) list.add(c41.getText().toString());
        if(c42.isChecked()) list.add(c42.getText().toString());
        if(c43.isChecked()) list.add(c43.getText().toString());
        if(c44.isChecked()) list.add(c44.getText().toString());
        for(String i : list){
            String key = userRef.push().getKey();
            userRef.child(key).child("goal").setValue(i);
        }
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("UserId", userId);
//        editor.apply();
        Intent intent = new Intent(MainActivity26.this, MainActivity21.class);
        Bundle bundle4 = new Bundle();
        bundle4.putString("UserId", userId);
        intent.putExtras(bundle4);
        startActivity(intent);
        finish();
    }
}