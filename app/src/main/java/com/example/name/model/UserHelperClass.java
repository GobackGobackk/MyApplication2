package com.example.name.model;

public class UserHelperClass {

    String id, name, email, gender, fbId;

    public UserHelperClass(String id, String name, String email, String gender, String fbId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.fbId = fbId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }
}
