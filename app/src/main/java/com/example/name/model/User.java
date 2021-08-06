package com.example.name.model;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private String password;
    //    private String userProfileImageUrl;
//    private String firstName;
//    private String lastName;
//    private String emailId;
    private String city;
    private String gender;
//    private boolean online;

    //    public User(String firstName, String lastName, String emailId, String city, String gender, boolean online) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.emailId = emailId;
//        this.city = city;
//        this.gender = gender;
//        this.online = online;
//    }
    public User(String name, String email, String gender){
        this.name = name;
        this.email = email;
        this.gender = gender;
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
//    public String getUserProfileImageUrl() {
//        return userProfileImageUrl;
//    }

    //    public void setUserProfileImageUrl(String userProfileImageUrl) {
//        this.userProfileImageUrl = userProfileImageUrl;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getEmailId() {
//        return emailId;
//    }
//
//    public void setEmailId(String emailId) {
//        this.emailId = emailId;
//    }
//
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
//
//    public boolean isOnline() {
//        return online;
//    }
//
//    public void setOnline(boolean online) {
//        this.online = online;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
//                ", emailId='" + emailId + '\'' +
                ", city='" + city + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
