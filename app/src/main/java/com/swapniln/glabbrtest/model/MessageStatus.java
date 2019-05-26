package com.swapniln.glabbrtest.model;

public class MessageStatus {
    private  int userID; // userId mapping status for which user
    private int status;  // 1 message delivered // 2 message read
    private long time; // time when message was read or delivered

    public MessageStatus(int userID, int status, long time) {
        this.userID = userID;
        this.status = status;
        this.time = time;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
