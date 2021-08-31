package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.name.model.Msg;
import com.example.name.model.MsgAdapter;
import com.example.name.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity4 extends AppCompatActivity {
        private List<Msg> msgList = new ArrayList<>();
        private EditText editText;
        private ImageButton sendButton;
        private RecyclerView recyclerView;
        private MsgAdapter msgAdapter;
        private DatabaseReference myRef, userRef;
        private FirebaseDatabase firebaseDatabase;
        private User user;
        private String GroupId, userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        GroupId = bundle.getString("GroupId");
        userId = bundle.getString("UserId");
//        Toast.makeText(MainActivity4.this, bundle.getString("GroupId")+bundle.getString("UserId"), Toast.LENGTH_LONG).show();

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
                        msgList.clear();
                        for (DataSnapshot val : dataSnapshot.getChildren()) {
                           Msg msg = val.getValue(Msg.class);
                           if(msg.getCreatedBy().equals(user.getId())){
                               msg.setMessageType(1);//自己
                           }else msg.setMessageType(0);//別人
                           msgList.add(msg);
                        }
                        msgAdapter.notifyItemInserted(msgList.size()-1);
                        //要求recyclerView布局将消息刷新
                        recyclerView.scrollToPosition(msgList.size()-1);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
//        initMsgs();
        editText = (EditText)findViewById(R.id.enter);
        sendButton = (ImageButton)findViewById(R.id.send);
        recyclerView =(RecyclerView)findViewById(R.id.chatroomRecyclerView);
        //布局排列方式
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        msgAdapter = new MsgAdapter(msgList);
        recyclerView.setAdapter(msgAdapter);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框中的内容
                String content = editText.getText().toString();
                //判断内容不是空的
                if(!"".equals(content)){
                    String messageId = myRef.push().getKey();
                    String createdOn = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
                    Msg msg = new Msg(messageId, content, user.getId(),
                            user.getName(), createdOn ,Msg.TYPE_SENT);
                    myRef.child(messageId).setValue(msg);
                    //将内容添加到单例中
//                    msgList.add(msg);
//                    //要求适配器重新刷新
//                    msgAdapter.notifyItemInserted(msgList.size()-1);
//                    //要求recyclerView布局将消息刷新
//                    recyclerView.scrollToPosition(msgList.size()-1);
                    editText.setText("");
                }
            }
        });
    }
//    public void  initMsgs(){
//        Msg msg1 = new Msg("你好！",Msg.TYPE_RECEIVED);
//        msgList.add(msg1);
//        Msg msg2 = new Msg("谢谢！你好。",Msg.TYPE_SENT);
//        msgList.add(msg2);
//        Msg msg3 = new Msg("加班么？",Msg.TYPE_RECEIVED);
//        msgList.add(msg3);
//    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_member) {
            Intent intent = new Intent(MainActivity4.this, MainActivity28.class);
            Bundle bundle = new Bundle();
            bundle.putString("GroupId", GroupId);
            bundle.putString("UserId", userId);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.third_menu, menu);
        return true;
    }
}