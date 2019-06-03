package com.swapniln.glabbrtest.ui.groupmsg

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.swapniln.glabbrtest.model.MessageObject
import com.swapniln.glabbrtest.model.MessageStatus
import com.swapniln.glabbrtest.model.UserObject
import com.swapniln.glabbrtest.utils._const.DELIVERED

import java.util.ArrayList
import java.util.Calendar
import java.util.HashMap

import com.swapniln.glabbrtest.utils._const.READ

class GroupMsgViewModel : ViewModel() {

    lateinit var messageStatusArrayList: ArrayList<MessageStatus>
    lateinit var userObjectArrayList: ArrayList<UserObject>
    lateinit var readByList: ArrayList<UserObject>
    lateinit var deliveredToList: ArrayList<UserObject>
    lateinit var userMap: MutableMap<Int, UserObject>
    lateinit var messageObject: MessageObject
        internal set


    var readByListLiveData = MutableLiveData<ArrayList<UserObject>>()
        internal set
    var deliverToListLiveData = MutableLiveData<ArrayList<UserObject>>()
        internal set

    fun setUp(): Map<Int, UserObject> {

        // create dummy message status data

        messageStatusArrayList = ArrayList()
        messageStatusArrayList.add(MessageStatus(1, 1, Calendar.getInstance().timeInMillis))
        messageStatusArrayList.add(MessageStatus(2, 1, Calendar.getInstance().timeInMillis))
        messageStatusArrayList.add(MessageStatus(3, 1, Calendar.getInstance().timeInMillis))
        messageStatusArrayList.add(MessageStatus(4, 1, Calendar.getInstance().timeInMillis))
        messageStatusArrayList.add(MessageStatus(5, 1, Calendar.getInstance().timeInMillis))
        messageStatusArrayList.add(MessageStatus(6, 1, Calendar.getInstance().timeInMillis))
        messageStatusArrayList.add(MessageStatus(7, 1, Calendar.getInstance().timeInMillis))
        messageStatusArrayList.add(MessageStatus(8, 1, Calendar.getInstance().timeInMillis))
        messageStatusArrayList.add(MessageStatus(9, 1, Calendar.getInstance().timeInMillis))
        messageStatusArrayList.add(MessageStatus(10, 1, Calendar.getInstance().timeInMillis))

        // create dummy message object
        messageObject = MessageObject(101, Calendar.getInstance().timeInMillis, "Happy Birthday !!!", messageStatusArrayList, true)

        readByList = ArrayList()
        deliveredToList = ArrayList()

        // store dummy user data
        userObjectArrayList = ArrayList()
        userObjectArrayList.add(UserObject(1, "user1", 0, 1, ""))
        userObjectArrayList.add(UserObject(2, "user2", 0, 1, ""))
        userObjectArrayList.add(UserObject(3, "user3", 0, 1, ""))
        userObjectArrayList.add(UserObject(4, "user4", 0, 1, ""))
        userObjectArrayList.add(UserObject(5, "user5", 0, 2, ""))
        userObjectArrayList.add(UserObject(6, "user6", 0, 2, ""))
        userObjectArrayList.add(UserObject(7, "user7", 0, 2, ""))
        userObjectArrayList.add(UserObject(8, "user8", 0, 2, ""))
        userObjectArrayList.add(UserObject(9, "user9", 0, 2, ""))
        userObjectArrayList.add(UserObject(10, "user10", 0, 2, ""))


        return setUserMap(userObjectArrayList)

    }

    // load user map
    private fun setUserMap(userObjectArrayList: ArrayList<UserObject>): Map<Int, UserObject> {
        userMap = HashMap()
        for (user in userObjectArrayList) {
            userMap[user.userID] = user
        }
        return userMap
    }

    fun sortUsers(userObjectMap: Map<Int, UserObject>) {
        readByList.clear()
        deliveredToList.clear()

        for (user in userObjectMap.values) {
            if (user.status == READ) {
                // if user status is READ add to read list
                readByList.add(user)
            } else if(user.status == DELIVERED){
                //if user status is DELIVERED add to delivered list
                deliveredToList.add(user)
            }

        }

        // update live  data value
        readByListLiveData.value = readByList
        deliverToListLiveData.value = deliveredToList
    }
}
