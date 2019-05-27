package com.swapniln.glabbrtest.model;

public class UserObject {

    int userID; // userId mapping status for which user
    String userName ; // user name
    long messageTime; // time when message was read or delivered
    int status;  // 1 message delivered // 2 message read
    String userImage; // user display Image

    public UserObject(int userID, String userName, long messageTime, int status, String userImage) {
        this.userID = userID;
        this.userName = userName;
        this.messageTime = messageTime;
        this.status = status;
        this.userImage = userImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
