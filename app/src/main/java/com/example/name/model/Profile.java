package com.example.name.model;

import java.io.Serializable;

public class Profile implements Serializable {
    private String start;
    private String finish;

    public Profile(String start, String finish) {
        this.start = start;
        this.finish = finish;

    }
    public Profile(){}
    public String getStart(){
        return start;
    }
    public void setStart(String start){
        this.start = start;
    }
    public String getFinish(){
        return finish;
    }
    public void setFinish(String finish){
        this.finish = finish;
    }
}
