package com.example.name.model;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.name.R;

public class MyMessageViewHolder extends RecyclerView.ViewHolder {
    private TextView messageTextView, timeTextView;
    public MyMessageViewHolder(View itemView) {
        super(itemView);
        messageTextView = itemView.findViewById(R.id.myChat_messageTextView);
        timeTextView = itemView.findViewById(R.id.myChat_timeTextView);
    }
    public TextView getMessageTextView() {
        return messageTextView;
    }

    public void setMessageTextView(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    public TextView getTimeTextView() {
        return timeTextView;
    }

    public void setTimeTextView(TextView timeTextView) {
        this.timeTextView = timeTextView;
    }
}
