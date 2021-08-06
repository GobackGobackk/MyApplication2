package com.example.name.model;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class MyViewHolders extends RecyclerView.ViewHolder {
    public MyViewHolders(@NonNull View itemView) {
        super(itemView);
    }
    /**建立抽象類別，並使onBindViewHolder可分別綁定到介面Ａ與介面Ｂ*/
    public abstract void bindViewHolder();
}
