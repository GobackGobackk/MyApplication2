package com.example.name.model;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.name.R;

public class TheirMessageViewHolder extends RecyclerView.ViewHolder {
    private TextView senderNameTextView, messageTextView, timeTextView;
    public TheirMessageViewHolder(View itemView) {
        super(itemView);
        senderNameTextView = itemView.findViewById(R.id.theirChat_senderNameTextView);
        messageTextView = itemView.findViewById(R.id.theirChat_messageTextView);
        timeTextView = itemView.findViewById(R.id.theirChat_timeTextView);
    }

    public TextView getSenderNameTextView() {
        return senderNameTextView;
    }

    public void setSenderNameTextView(TextView senderNameTextView) {
        this.senderNameTextView = senderNameTextView;
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
