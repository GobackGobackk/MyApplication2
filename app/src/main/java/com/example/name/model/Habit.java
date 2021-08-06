package com.example.name.model;

import java.io.Serializable;

public class Habit implements Serializable {
    private String habitName;
    private String method;
    private String execute;
    private String rest;
    public Habit(String habitName, String dayOfWeek, String start, String finish) {
        this.habitName = habitName;
        this.method = dayOfWeek;
        this.execute = start;
        this.rest = finish;
    }
    public Habit(){}
    public String getHabitName() { return habitName;}
    public void setHabitName(String habitName) {this.habitName = habitName;}

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getExecute(){
        return execute;
    }
    public void setExecute(String execute){
        this.execute = execute;
    }
    public String getRest(){
        return rest;
    }
    public void setRest(String rest){
        this.rest = rest;
    }
    public String yaa() { return habitName + ": 每個 " + method + " 的 " + execute + " 到 " + rest;}

}
