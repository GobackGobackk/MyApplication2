package com.example.name.model;

import java.util.HashMap;

public class GroupChatRoom {
    private String groupId;
    private String groupName;
    private HashMap<String,GroupOnlineUsers> members = new HashMap<>();
    private String createdOn;
    private String pic;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public HashMap<String, GroupOnlineUsers> getMembers() {
        return members;
    }

    public void setMembers(HashMap<String, GroupOnlineUsers> membersListWithOnlineStatus) {
        this.members = membersListWithOnlineStatus;
    }


    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getPic() { return pic;}

    public void setPic(String pic) {this.pic = pic;}

    public String toString() {
        return "Group{" +
                "id='" + groupId + '\'' +
                ", name='" + groupName + '\'' +
                ", time='" + createdOn + '\'' +
                '}';
    }
    public String yaa() {
        return groupName + "      " + groupId;
    }
}

