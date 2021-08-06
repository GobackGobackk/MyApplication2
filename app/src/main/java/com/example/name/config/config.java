package com.example.name.config;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.name.MainActivity4;
import com.example.name.R;
import com.example.name.model.GroupChatRoom;

import java.util.List;

public class config {
    private Context mContext;
    private CompetitionsAdapter mCompetitionsAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<GroupChatRoom> competitions, List<String> keys, String userId) {
        mContext = context;
        mCompetitionsAdapter = new CompetitionsAdapter(competitions, keys, userId);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mCompetitionsAdapter);
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
        private Button button;


        public CompetitionitemView(ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false));
            //mCid = (TextView) itemView.findViewById(R.id.com_button);
            mTitle = (TextView) itemView.findViewById(R.id.tvName);
            mPrize = (TextView) itemView.findViewById(R.id.tvDescription);
//            mDeadline = (TextView) itemView.findViewById(R.id.deadline_txtView);
//            mPeople = (TextView) itemView.findViewById(R.id.people_txtView);
        }

        public void bind(GroupChatRoom competition, String key){
            //mCid.setText(competition.getCid());
            mTitle.setText(competition.getGroupName());
            mPrize.setText(competition.getGroupId());
//            mDeadline.setText(competition.getDead_line());
//            mPeople.setText(competition.getPeople());
//
//            //抓取網路照片 用到github bumptech/glide 要記得在manifest加premession Internet
//            temp = competition.getPic();
            com_pic = (ImageView) itemView.findViewById(R.id.ivUser);
            if(competition.getPic().equals("Male")) com_pic.setImageResource(R.drawable.study_school_jugyou_man);
            else com_pic.setImageResource(R.drawable.study_school_jugyou_woman);
//            Glide.with(mContext).load(temp).into(com_pic);

            this.key = key;
        }

    }
    class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionitemView>{
        private List<GroupChatRoom> mCompetitionList;
        private List<String> mKeys;
        private String userId;

        public CompetitionsAdapter(List<GroupChatRoom> mCompetitionList, List<String> mKeys, String userId) {
            this.mCompetitionList = mCompetitionList;
            this.mKeys = mKeys;
            this.userId = userId;
        }

        @NonNull
        @Override
        public CompetitionitemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CompetitionitemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CompetitionitemView holder, int position) {
            holder.bind(mCompetitionList.get(position), mKeys.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG","onClick: clicked on: " + mKeys.get(position));

//                    Toast.makeText(mContext, mKeys.get(position), Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(mContext, MainActivity4.class);
                    Bundle bundle4 = new Bundle();
                    bundle4.putString("GroupId", mKeys.get(position));
                    bundle4.putString("UserId", userId);
                    intent.putExtras(bundle4);
                    mContext.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mCompetitionList.size();
        }
    }
}
