package com.example.name.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.name.R;

import org.jetbrains.annotations.NotNull;
import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int MY_TEXT_MESSAGE = 0, THEIR_TEXT_MESSAGE = 1;
    private ArrayList<Message> messagesArrayList;
    private User user;
    private String groupId;
    private PrettyTime pt = new PrettyTime();
    //傳入外部 list
    public MessageAdapter(User user, String groupId, ArrayList<Message> messagesArrayList) {
        this.messagesArrayList = messagesArrayList;
        this.groupId = groupId;
        this.user = user;
    }
    @Override
    public int getItemViewType(int position) {
        Message message = messagesArrayList.get(position);
        if (message.getCreatedBy().equals(user.getId())) {
           return MY_TEXT_MESSAGE;
        }
        else return THEIR_TEXT_MESSAGE;
    }
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == MY_TEXT_MESSAGE) {
            View v1 = inflater.inflate(R.layout.fragment_message_items_my_text_message, parent, false);
            viewHolder = new MyMessageViewHolder(v1);
        }
        else {
            View v1 = inflater.inflate(R.layout.fragment_message_items_their_text_message, parent, false);
            viewHolder = new TheirMessageViewHolder(v1);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
        try {
            if (holder.getItemViewType() == MY_TEXT_MESSAGE) {
                MyMessageViewHolder vh1 = (MyMessageViewHolder) holder;
                configureMyTextMessageViewHolder(vh1, position);
            } else if (holder.getItemViewType() == THEIR_TEXT_MESSAGE) {
                TheirMessageViewHolder vh1 = (TheirMessageViewHolder) holder;
                configureTheirTextMessageViewHolder(vh1, position);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
    private void configureTheirTextMessageViewHolder(TheirMessageViewHolder viewHolder, int position) throws ParseException {
        Message messages = messagesArrayList.get(position);
        viewHolder.getSenderNameTextView().setText(messages.getCreatedByName());
        viewHolder.getMessageTextView().setText(messages.getMessage());

        Date date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(messages.getCreatedOn());
        viewHolder.getTimeTextView().setText(pt.format(date));
    }
    private void configureMyTextMessageViewHolder(MyMessageViewHolder viewHolder, int position) throws ParseException {
        Message messages = messagesArrayList.get(position);
        viewHolder.getMessageTextView().setText(messages.getMessage());
        Date date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(messages.getCreatedOn());
        viewHolder.getTimeTextView().setText(pt.format(date));
    }
    @Override
    public int getItemCount () {
        return messagesArrayList.size();
    }
}
