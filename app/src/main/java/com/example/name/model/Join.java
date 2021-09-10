package com.example.name.model;

public class Join {

    private String user;
    private String name;
    private String pic; //照片

    public Join() {
    }

    public Join(String user, String name, String pic) {
        this.user = user;
        this.name = name;
        this.pic = pic;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
