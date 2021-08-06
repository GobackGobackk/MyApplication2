package com.example.name;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity20 extends AppCompatActivity {
    @BindView(R.id.editTextTextPersonName4)
    EditText text;

    private FirebaseDatabase database;
    private DatabaseReference myRef, dateRef, detailRef;
    private String grpId, groupName, userId, calId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main20);
        ButterKnife.bind(this);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chatRooms/userProfiles/" + userId);

    }
    @OnClick(R.id.ok)
    public void zxc (View view){
//            int checkedRadioButtonId = rg0.getCheckedRadioButtonId();
//            RadioButton radioButton = findViewById(checkedRadioButtonId);
//            myRef.child("goal").setValue(radioButton.getText().toString());
            Intent intent = new Intent(MainActivity20.this, MainActivity22.class);
            Bundle bundle = new Bundle();
            bundle.putString("UserId", userId);
//            bundle.putString("goal", radioButton.getText().toString());
            bundle.putString("groupName", text.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
    }
}