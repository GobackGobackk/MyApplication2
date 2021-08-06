package com.example.name.model;

/**
 * Created by lenovo on 2018/5/4.
 */
/*
 我们需要单例保存的数据为：
 1.消息的内容；
 2.消息的类型：发送还是接收；
 3.消息创建时间
 */
public class Msg {
    public final static int TYPE_RECEIVED=0;
    public final static int TYPE_SENT=1;
    private String messageId;
    private String message;
    private String createdBy;
    private String createdByName;
    private String createdOn;
    private int messageType;

    public Msg(String messageId, String message, String createdBy, String createdByName, String createdOn, int messageType) {
        this.messageId = messageId;
        this.message = message;
        this.createdBy = createdBy;
        this.createdByName = createdByName;
        this.createdOn = createdOn;
        this.messageType = messageType;
    }

    public Msg() {

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

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
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

    public String yaa() {
        return message;
    }

}
