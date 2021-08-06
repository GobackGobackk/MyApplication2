package com.example.name.model;

public class GroupOnlineUsers {
    private String userId, displayName;

    public GroupOnlineUsers(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
    }

    public GroupOnlineUsers() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "GroupOnlineUsers{" +
                "userId='" + userId + '\'' +
                ", displayName='" + displayName +
                '}';
    }
}
