package com.swapniln.glabbrtest.ui.groupmsg;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.swapniln.glabbrtest.model.MessageObject;
import com.swapniln.glabbrtest.model.MessageStatus;
import com.swapniln.glabbrtest.model.UserObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.swapniln.glabbrtest.utils._const.READ;

public class GroupMsgViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    ArrayList<MessageStatus> messageStatusArrayList;
    ArrayList<UserObject> userObjectArrayList;
    ArrayList<UserObject> readByList;
    ArrayList<UserObject> deliveredToList;
    Map<Integer, UserObject> userMap;
    MessageObject messageObject;


    MutableLiveData<ArrayList<UserObject>> readByListLiveData = new MutableLiveData<>();
    MutableLiveData<ArrayList<UserObject>> deliverToListLiveData = new MutableLiveData<>();

    public Map<Integer, UserObject> setUp() {

        // create dummy message status data

        messageStatusArrayList = new ArrayList<>();
        messageStatusArrayList.add(new MessageStatus(1, 1, Calendar.getInstance().getTimeInMillis()));
        messageStatusArrayList.add(new MessageStatus(2, 1, Calendar.getInstance().getTimeInMillis()));
        messageStatusArrayList.add(new MessageStatus(3, 1, Calendar.getInstance().getTimeInMillis()));
        messageStatusArrayList.add(new MessageStatus(4, 1, Calendar.getInstance().getTimeInMillis()));
        messageStatusArrayList.add(new MessageStatus(5, 1, Calendar.getInstance().getTimeInMillis()));
        messageStatusArrayList.add(new MessageStatus(6, 1, Calendar.getInstance().getTimeInMillis()));
        messageStatusArrayList.add(new MessageStatus(7, 1, Calendar.getInstance().getTimeInMillis()));
        messageStatusArrayList.add(new MessageStatus(8, 1, Calendar.getInstance().getTimeInMillis()));
        messageStatusArrayList.add(new MessageStatus(9, 1, Calendar.getInstance().getTimeInMillis()));
        messageStatusArrayList.add(new MessageStatus(10, 1, Calendar.getInstance().getTimeInMillis()));

        // create dummy message object
        messageObject = new MessageObject(101, Calendar.getInstance().getTimeInMillis(), "Happy Birthday !!!", messageStatusArrayList, true);

        readByList = new ArrayList<>();
        deliveredToList = new ArrayList<>();

        // store dummy user data
        userObjectArrayList = new ArrayList<>();
        userObjectArrayList.add(new UserObject(1, "user1", 0, 1, ""));
        userObjectArrayList.add(new UserObject(2, "user2", 0, 1, ""));
        userObjectArrayList.add(new UserObject(3, "user3", 0, 1, ""));
        userObjectArrayList.add(new UserObject(4, "user4", 0, 1, ""));
        userObjectArrayList.add(new UserObject(5, "user5", 0, 2, ""));
        userObjectArrayList.add(new UserObject(6, "user6", 0, 2, ""));
        userObjectArrayList.add(new UserObject(7, "user7", 0, 2, ""));
        userObjectArrayList.add(new UserObject(8, "user8", 0, 2, ""));
        userObjectArrayList.add(new UserObject(9, "user9", 0, 2, ""));
        userObjectArrayList.add(new UserObject(10, "user10", 0, 2, ""));


        return setUserMap(userObjectArrayList);

    }

    // load user map
    private Map<Integer, UserObject> setUserMap(ArrayList<UserObject> userObjectArrayList) {
        userMap = new HashMap<>();
        for (UserObject user : userObjectArrayList) {
            userMap.put(user.getUserID(), user);
        }
        return userMap;
    }

    public MessageObject getMessageObject() {
        return messageObject;
    }

    public void sortUsers(Map<Integer, UserObject> userObjectMap) {
        readByList.clear();
        deliveredToList.clear();

        for (UserObject user : userObjectMap.values()) {
            if (user.getStatus() == READ) {
                // if user status is READ add to read list
                readByList.add(user);
            } else {
                //if user status is DELIVERED add to delivered list
                deliveredToList.add(user);
            }

        }
        readByListLiveData.setValue(readByList);
        deliverToListLiveData.setValue(deliveredToList);
    }

    public MutableLiveData<ArrayList<UserObject>> getReadByListLiveData() {
        return readByListLiveData;
    }

    public MutableLiveData<ArrayList<UserObject>> getDeliverToListLiveData() {
        return deliverToListLiveData;
    }
}
