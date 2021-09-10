package com.example.name.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.name.R;
import com.example.name.inventer;
import com.example.name.test;
import com.example.name.unlock_ability;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class RecyclerView_config {
    private Context mContext;
    private CompetitionsAdapter mCompetitionsAdapter;
    private String user, name;
    public void setConfig(RecyclerView recyclerView, Context context, List<Competition> competitions, List<String> keys, String user, String name){
        mContext = context;
        mCompetitionsAdapter = new CompetitionsAdapter(competitions, keys, user, name);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mCompetitionsAdapter);

    }

    private Context mContextJoin;
    private JoinsAdapter mJoinsAdapter;

    public void setConfig2(RecyclerView recyclerView, Context context, List<Join> joins, List<String> keys){
        mContextJoin = context;
        mJoinsAdapter = new JoinsAdapter(joins, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mJoinsAdapter);
    }

    class CompetitionitemView extends RecyclerView.ViewHolder{
        //ViewHolder存放view reference的地方

        String temp;
        private ImageView com_pic;
        private String key;

        //private TextView mCid;
        private TextView mTitle;
        private TextView mPrize;
        private TextView mDeadline;
        private TextView mPeople;
        private TextView mWinner;

        public CompetitionitemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.competition_list_item, parent, false));
            //mCid = (TextView) itemView.findViewById(R.id.com_button);
            mTitle = (TextView) itemView.findViewById(R.id.title_txtView);
            mPrize = (TextView) itemView.findViewById(R.id.prize_txtView);
            mDeadline = (TextView) itemView.findViewById(R.id.deadline_txtView);
            mPeople = (TextView) itemView.findViewById(R.id.people_txtView);
            mWinner = (TextView) itemView.findViewById(R.id.winner_txt);
        }

        public void bind(Competition competition, String key) {
//            mCid.setText(competition.getCid());
            mTitle.setText(competition.getTitle());
            mPrize.setText(competition.getPrize());
            mDeadline.setText(competition.getDead_line());
            mPeople.setText(competition.getPeople());
            mWinner.setText(competition.getWinner());

//            cid = competition.getCid();
            //抓取網路照片 用到github bumptech/glide 要記得在manifest加premession Internet
            temp = competition.getPic();
            com_pic = (ImageView) itemView.findViewById(R.id.com_pic);
            Glide.with(mContext).load(temp).into(com_pic);

            this.key = key;
        }

    }

    class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionitemView>{
        private List<Competition> mCompetitionList;
        private List<String> mKeys;
        private Button btnInvent;
        private Button btnJoin;
        private Button btnDiscuess;
        private String user;
        private String name;

        public CompetitionsAdapter(List<Competition> mCompetitionList, List<String> mKeys, String user, String name) {
            this.mCompetitionList = mCompetitionList;
            this.mKeys = mKeys;
            this.user = user;
            this.name = name;
        }

        @NonNull
        @Override
        public CompetitionitemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CompetitionitemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CompetitionitemView holder, @SuppressLint("RecyclerView") int position) {
            holder.bind(mCompetitionList.get(position), mKeys.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG","onClick: clicked on: " + mKeys.get(position));

                    Toast.makeText(mContext, mKeys.get(position), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(mContext, test.class);
                    intent.putExtra("url", mKeys.get(position));
                    mContext.startActivity(intent);
                }
            });

            //user setting
//            String user = "Fp0VJqVhyKZrSo45iYSK1ll7a1s2"; // user id
//            String name = "天竺鼠  "; // user name
            String pic = "https://upload.wikimedia.org/wikipedia/commons/7/70/User_icon_BLACK-01.png"; //user pic

            //查看人選
            btnInvent = holder.itemView.findViewById(R.id.btn_invent);

            btnInvent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"click", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, inventer.class);
                    intent.putExtra("url", mKeys.get(position));
                    mContext.startActivity(intent);
                }
            });

            // 我要比賽
            btnJoin = holder.itemView.findViewById(R.id.btn_competition);

            btnJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("join_competition/"+mKeys.get(position));
                    DatabaseReference join = FirebaseDatabase.getInstance().getReference("competition/"+mKeys.get(position)).child("people");

//                    String userId = reference.push().getKey();
//                    reference.child(userId).setValue("user0124");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        public void onDataChange(DataSnapshot dataSnapshot) {


                            if(!dataSnapshot.exists()){

                                reference.child(user).child("user").setValue(user);
                                reference.child(user).child("name").setValue(name);
                                reference.child(user).child("pic").setValue(pic);

                                Toast.makeText(v.getContext(),"成功加入比賽", Toast.LENGTH_SHORT).show();

                                join.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String people = snapshot.getValue().toString();
                                        int p = Integer.valueOf(people);
                                        p = p+1;
                                        String people_new = Integer.toString(p);
                                        join.setValue(people_new);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }else{
                                boolean is_in_join = false;
                                for (DataSnapshot key : dataSnapshot.getChildren()) {

                                    if(key.child("user").getValue().toString().equals(user)){
                                        is_in_join = true;
                                        Toast.makeText(v.getContext(), "您已加入此比賽，盡快為您配對", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                }

                                if( is_in_join == false){
//                                    String userId = reference.push().getKey();
                                    reference.child(user).child("user").setValue(user);
                                    reference.child(user).child("name").setValue(name);
                                    reference.child(user).child("pic").setValue(pic);

                                    Toast.makeText(v.getContext(),"成功加入比賽", Toast.LENGTH_SHORT).show();

                                    join.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String people = snapshot.getValue().toString();
                                            int p = Integer.valueOf(people);
                                            p = p+1;
                                            String people_new = Integer.toString(p);
                                            join.setValue(people_new);
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });

                                }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.w("TAG", "Failed to read value.", error.toException());
                        }
                    });

                }
            });

            // 我要交流
            btnDiscuess = holder.itemView.findViewById(R.id.btn_discuss);

            btnDiscuess.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatabaseReference discuess = FirebaseDatabase.getInstance().getReference("dicuess/"+mKeys.get(position));

                    discuess.addListenerForSingleValueEvent(new ValueEventListener() {
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if(!dataSnapshot.exists()){
                                discuess.push().child("user").setValue(user);
                                Toast.makeText(v.getContext(),"成功加入此比賽交流圈", Toast.LENGTH_SHORT).show();
                            }else{
                                boolean is_in_discuess = false;
                                for (DataSnapshot key : dataSnapshot.getChildren()) {

                                    if(key.child("user").getValue().toString().equals(user)){
                                        is_in_discuess = true;
                                        Toast.makeText(v.getContext(), "您已在交流圈裡，盡快為您配對", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                }

                                if( is_in_discuess == false){
                                    String userId = discuess.push().getKey();
                                    discuess.child(userId).child("user").setValue(user);
                                    Toast.makeText(v.getContext(),"成功加入此比賽交流圈", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.w("TAG", "Failed to read value.", error.toException());
                        }
                    });

                }
            });



        }

        @Override
        public int getItemCount() {
            return mCompetitionList.size();
        }
    }

    class JoinitemView extends RecyclerView.ViewHolder{

        String temp2;
        private String key;
        private TextView mName;
        private ImageView user_pic;

        public JoinitemView(ViewGroup parent){
            super(LayoutInflater.from(mContextJoin).inflate(R.layout.inventer, parent, false));
            mName = (TextView) itemView.findViewById(R.id.txt_join);
        }

        public void bind(Join join, String key) {
            mName.setText(join.getName());
            temp2 = join.getPic();
            user_pic = (ImageView) itemView.findViewById(R.id.imageView);
            Glide.with(mContextJoin).load(temp2).into(user_pic);
            this.key = key;
        }

    }

    class JoinsAdapter extends RecyclerView.Adapter<JoinitemView>{
        private List<Join> mJoinList;
        private List<String> mKeys;
        private Button btnLock;

        public JoinsAdapter(List<Join> mJoinList, List<String> mKeys) {
            this.mJoinList = mJoinList;
            this.mKeys = mKeys;
        }

        @NonNull
        @Override
        public JoinitemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new JoinitemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull JoinitemView holder, @SuppressLint("RecyclerView") int position) {
            holder.bind(mJoinList.get(position), mKeys.get(position));

            //查看解鎖
            btnLock = holder.itemView.findViewById(R.id.btn_lock);

            btnLock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(v.getContext(),"click"+position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContextJoin, unlock_ability.class);
                    intent.putExtra("user_position", position);
                    intent.putExtra("user", mKeys.get(position));
                    mContextJoin.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mJoinList.size();
        }
    }
}