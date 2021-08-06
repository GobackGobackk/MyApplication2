package com.example.name.model;

public class Calendarrr {
    private String calendarId;
    private String eventName;
    private String eventStartDay;
    private String eventFinishDay;
    private String eventStartTime;
    private String eventFinishTime;
    private String notification;
    private String dayofweek;
    private String allFinishDay;

    public Calendarrr(String eventName, String eventStartDay, String eventFinishDay, String eventStartTime, String eventFinishTime, String notification) {
        this.eventName = eventName;
        this.eventStartDay = eventStartDay;
        this.eventFinishDay = eventFinishDay;
        this.eventStartTime = eventStartTime;
        this.eventFinishTime = eventFinishTime;
        this.notification = notification;
    }

    public Calendarrr() {

    }
    public String getCalendarId(){
        return  calendarId;
    }
    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }
    public String getEventName(){
        return eventName;
    }
    public void setEventName(String eventName){
        this.eventName = eventName;
    }
    public String getEventStartDay(){
        return eventStartDay;
    }
    public void setEventStartDay(String eventStartDay){
        this.eventStartDay = eventStartDay;
    }
    public String getEventFinishDay(){
        return eventFinishDay;
    }
    public void setEventFinishDay(String eventFinishDay){
        this.eventFinishDay = eventFinishDay;
    }
    public String getEventStartTime(){
        return eventStartTime;
    }
    public void setEventStartTime(String eventStartTime){
        this.eventStartTime = eventStartTime;
    }
    public String getEventFinishTime(){
        return eventFinishTime;
    }
    public void setEventFinishTime(String eventFinishTime){ this.eventFinishTime = eventFinishTime; }
    public String getNotification(){
        return notification;
    }
    public void setNotification(String notification){
        this.notification = notification;
    }
    public String getDayofweek() {return dayofweek;}
    public void setDayofweek(String dayofweek) {this.dayofweek = dayofweek;}
    public String getAllFinishDay() {return allFinishDay;}
    public void setAllFinishDay(String allFinishDay) {this.allFinishDay = allFinishDay;}
    public String abc() {return dayofweek + eventStartTime + eventFinishTime;}
}
