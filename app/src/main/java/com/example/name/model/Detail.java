package com.example.name.model;

import java.io.Serializable;

public class Detail implements Serializable {
    private String eventName;
    private String datee;
    private String timee;

    public Detail(String habitName, String dayOfWeek, String timee) {
        this.eventName = habitName;
        this.datee = dayOfWeek;
        this.timee = timee;
    }
    public Detail(){}
    public String getEventName() { return eventName;}
    public void setEventName(String habitName) {this.eventName = habitName;}

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }
    public String getTimee() {return  timee;}
    public void setTimee(String timee){this.timee = timee;}
}
