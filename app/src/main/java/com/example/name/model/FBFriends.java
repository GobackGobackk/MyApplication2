package com.example.name.model;

public class FBFriends {

    String name, FacebookId;

    public FBFriends(String name, String FacebookId) {
        this.name = name;
        this.FacebookId = FacebookId;
    }


    public String getFacebookId() {
        return FacebookId;
    }

    public void setFacebookId(String facebookId) {
        FacebookId = facebookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}