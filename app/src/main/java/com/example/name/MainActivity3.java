package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.name.model.Message;
import com.example.name.model.MessageAdapter;
import com.example.name.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity3 extends AppCompatActivity {
    @BindView(R.id.send)
    ImageButton send;
    @BindView(R.id.list_of_messages)
    RecyclerView list_of_messages;
    @BindView(R.id.input)
    EditText input;

    private DatabaseReference myRef, userRef;
    private FirebaseDatabase firebaseDatabase;
    private User user;
    ArrayList<Message> messagesArrayList = new ArrayList<>();
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Toast.makeText(MainActivity3.this, bundle.getString("GroupId")+bundle.getString("UserId"), Toast.LENGTH_LONG).show();

        firebaseDatabase = FirebaseDatabase.getInstance();
        userRef = firebaseDatabase.getReference("chatRooms/userProfiles/"+ bundle.getString("UserId"));
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        myRef = firebaseDatabase.getReference("chatRooms/messages/" + bundle.getString("GroupId"));
        myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        messagesArrayList.clear();

                        for (DataSnapshot val : dataSnapshot.getChildren()) {

                            Message messages = val.getValue(Message.class);
                            messagesArrayList.add(messages);
                            messageAdapter = new MessageAdapter(user, bundle.getString("GroupId"), messagesArrayList);

                            list_of_messages.setAdapter(messageAdapter);
                            messageAdapter.notifyDataSetChanged();
                            list_of_messages.smoothScrollToPosition(messagesArrayList.size()-1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
    @OnClick(R.id.send)
    public void onClick(View view) {
        if(!input.getText().toString().isEmpty()) {
            String messageId = myRef.push().getKey();
            String createdOn = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
            Message messages = new Message(messageId, input.getText().toString(), user.getId(),
                    user.getName(), createdOn, "testMessage");
            myRef.child(messageId).setValue(messages);
            input.setText("");
            hideSoftKeyboard(this);
        }
    }
    public void hideSoftKeyboard(Activity activity) { InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE); inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0); }

}