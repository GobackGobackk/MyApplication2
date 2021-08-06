package com.example.name.model;

public class Message {
    private String messageId;
    private String message;
    private String createdBy;
    private String createdByName;
    private String createdOn;
    private String messageType;

    public Message(String messageId, String message, String createdBy, String createdByName, String createdOn, String messageType) {
        this.messageId = messageId;
        this.message = message;
        this.createdBy = createdBy;
        this.createdByName = createdByName;
        this.createdOn = createdOn;
        this.messageType = messageType;

    }

    public Message() {

    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", message='" + message + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdByName='" + createdByName + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", messageType='" + messageType + '\'' +
                '}';
    }
}