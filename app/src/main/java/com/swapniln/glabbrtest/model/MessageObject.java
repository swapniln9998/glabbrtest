package com.swapniln.glabbrtest.model;

import java.util.ArrayList;

public class MessageObject {
    private int messageID;
    private long messageTime;  // UNIX epoch time
    private String MessageText;
    private ArrayList<MessageStatus> messageStatus; // userIds who read the message or who received the message
    private boolean sent; // message sent successfully

    public MessageObject(int messageID, long messageTime, String messageText, ArrayList<MessageStatus> messageStatus, boolean sent) {
        this.messageID = messageID;
        this.messageTime = messageTime;
        MessageText = messageText;
        this.messageStatus = messageStatus;
        this.sent = sent;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageText() {
        return MessageText;
    }

    public void setMessageText(String messageText) {
        MessageText = messageText;
    }

    public ArrayList<MessageStatus> getMessageStatus() {
        return messageStatus;
    }

    public boolean updateMessageStatus(MessageStatus status)
    {

        for (MessageStatus messageStatusObject :messageStatus) {
            if(messageStatusObject.getUserID()==status.getUserID())
                messageStatusObject.setStatus(status.getStatus());
                messageStatusObject.setTime(status.getTime());
            return true;
        }
        return false;
    }

    public void setMessageStatus(ArrayList<MessageStatus> messageStatus) {
        this.messageStatus = messageStatus;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
