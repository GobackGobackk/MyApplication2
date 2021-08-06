package com.example.name.model;

import java.io.Serializable;

public class Timeee implements Serializable {
    private String lastlong;
    private String eventName;
    private String createdOn;
    private String category;
    private String playFun;
    private String onTime;//結束時間
    private String startOn;//開始時間
    public Timeee(String start, String eventName, String createdOn, String category, String playFun) {
        this.lastlong = start;
        this.eventName = eventName;
        this.createdOn = createdOn;
        this.category = category;
        this.playFun = playFun;
    }
    public Timeee(){}
    public String getLastlong(){
        return lastlong;
    }
    public void setLastlong(String start){
        this.lastlong = start;
    }
    public String getEventName() {return eventName;}
    public void setEventName(String eventName) {this.eventName = eventName;}
    public String getCreatedOn() {return createdOn;}
    public void setCreatedOn(String createdOn) {this.createdOn = createdOn;}
    public String getCategory() {return category;}
    public void setCategory(String categoty) {this.category = categoty;}
    public String getPlayFun() {return playFun;}
    public void setPlayFun(String playFun) {this.playFun = playFun;}
    public String getOnTime() {return onTime;}
    public void setOnTime(String onTime) {this.onTime = onTime;}
    public String getStartOn() {return startOn;}
    public void setStartOn(String startOn) {this.startOn = startOn;}
}

