package com.example.name;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.name.config.config;
import com.example.name.config.config2;
import com.example.name.model.Competition;
import com.example.name.model.Spinnerrr;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity29 extends AppCompatActivity {
    @BindView(R.id.textView80)
    TextView text;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String grpId, groupName, userId, name, ev123, ssdd;
    private FirebaseDatabase database;
    private DatabaseReference myRef, myRef2;
    ArrayList<String> ar = new ArrayList<String>();
    ArrayList<Competition> ar2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main29);
        ButterKnife.bind(this);
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        userId = bundle.getString("UserId");
        database = FirebaseDatabase.getInstance();
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ar2 );
        myRef = database.getReference("dicuess/");
        myRef2 = database.getReference("competition/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                ar.clear();
                for(DataSnapshot child : snapshot.getChildren()){
                    for(DataSnapshot child2 : child.getChildren()){
                        if(child2.hasChild("user")){
                            if(userId.equals(child2.child("user").getValue().toString())){
                                ar.add(child.getKey());//49794 51054 ...
                            }
                        }
                    }
                }
                myRef2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        ar2.clear();
                        List<String> keys = new ArrayList<>();
                        for(DataSnapshot child : snapshot.getChildren()){
                            if(ar.contains(child.child("cid").getValue().toString())){
//                                ar2.add(child.child("title").getValue().toString());//2021金屬創新應用競賽 2021ICARE身心障礙...
                                Competition competition = child.getValue(Competition.class);
                                ar2.add(competition);
                                keys.add(child.getKey());
                                new config2().setConfig(recyclerView, MainActivity29.this, ar2, keys, userId);
                            }
                        }
//                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

//        list.setAdapter(adapter);
//        list.setOnItemClickListener(new ListView.OnItemClickListener(){
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                String sel = parent.getItemAtPosition(position).toString();
//                Intent intent = new Intent(MainActivity29.this,MainActivity4.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("UserId", userId);
//                bundle.putString("GroupId", sel);//群組id
//                intent.putExtras(bundle);
//                startActivity(intent);
//                finish();
//            }
//        });
    }
    @OnClick(R.id.login3)
    public void asd(View view){
        Intent intent = new Intent(MainActivity29.this, MainActivity24.class);
        Bundle bundle = new Bundle();
        bundle.putString("UserId", userId);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}