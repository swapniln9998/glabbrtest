package com.swapniln.glabbrtest.ui.groupmsg

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.swapniln.glabbrtest.MessageNotifier
import com.swapniln.glabbrtest.R
import com.swapniln.glabbrtest.adapter.AdapterDeliveredTo
import com.swapniln.glabbrtest.adapter.AdapterReadBy
import com.swapniln.glabbrtest.model.MessageObject
import com.swapniln.glabbrtest.model.MessageStatus
import com.swapniln.glabbrtest.model.UserObject

import java.util.ArrayList
import java.util.Calendar

import kotlinx.android.synthetic.main.group_msg_fragment.*
import kotlinx.android.synthetic.main.group_msg_fragment.view.*

class GroupMsgFragment : Fragment(), MessageNotifier {

    private lateinit var mViewModel: GroupMsgViewModel
    private var readByList = ArrayList<UserObject>()
    private var deliveredToList = ArrayList<UserObject>()
    private var messageObject: MessageObject? = null
    private var readByUsersAdapter: AdapterReadBy? = null
    private  var deliveredToUsersAdapter: AdapterDeliveredTo? = null
    lateinit var messageNotifier: MessageNotifier
    private var userMap: MutableMap<Int, UserObject>? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.group_msg_fragment, container, false)

        // init message listener
        messageNotifier = this

       view.floatingActionButton.setOnClickListener {
           updateData()
       }

        return view
    }

    // trigger messageNotifier to update data
   private fun updateData()
    {
        messageNotifier.updateMessageStatus(101, MessageStatus(2, 2, Calendar.getInstance().timeInMillis))
        Toast.makeText(activity, "user 2 data gets updated to read status", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mViewModel = ViewModelProviders.of(this).get(GroupMsgViewModel::class.java)



        // live date observers for user list
        mViewModel.readByListLiveData.observe(this, Observer { readUserObjectsList ->
            readByList.clear()
            if (readUserObjectsList != null) {
                readByList.addAll(readUserObjectsList)
            }
            readByUsersAdapter?.notifyDataSetChanged()
        })
        mViewModel.deliverToListLiveData.observe(this, Observer { deliveredUserObjectsList ->
            deliveredToList.clear()
            if (deliveredUserObjectsList != null) {
                deliveredToList.addAll(deliveredUserObjectsList)
            }
            deliveredToUsersAdapter?.notifyDataSetChanged()
        })


        setUpUI()


        // set up dummy data
        userMap = mViewModel.setUp() as MutableMap<Int, UserObject>
        mViewModel.sortUsers(userMap!!)
        notifyUI()
        messageObject = mViewModel.messageObject


    }

    private fun setUpUI() {
        readByUsersAdapter = AdapterReadBy(readByList)
        deliveredToUsersAdapter = AdapterDeliveredTo( deliveredToList)

        rv_deliveredToUsers.layoutManager = LinearLayoutManager(activity)
        rv_deliveredToUsers.setHasFixedSize(false)
        rv_deliveredToUsers.adapter = deliveredToUsersAdapter


        rv_readByUsers.layoutManager = LinearLayoutManager(activity)
        rv_readByUsers.setHasFixedSize(false)
        rv_readByUsers.adapter = readByUsersAdapter
    }


    // notify rv Adapters
    fun notifyUI() {


        readByUsersAdapter?.notifyDataSetChanged()
        deliveredToUsersAdapter?.notifyDataSetChanged()


    }


    override fun updateMessageStatus(messageId: Int, messageStatus: MessageStatus) {
        if (messageObject?.messageID == messageId) {
            if (messageObject!!.updateMessageStatus(messageStatus)) {

                // check id user is present in list or not
                if (userMap!!.containsKey(messageStatus.userID)) {
                    val userObject = userMap!![messageStatus.userID]
                    userObject!!.status = messageStatus.status
                    userObject.messageTime = messageStatus.time

                    // update user list
                    userMap!![messageStatus.userID] = userObject

                    //sort data and update UI
                    mViewModel.sortUsers(userMap!!)

                }
            }

        }

    }

    companion object {


        fun newInstance(): GroupMsgFragment {
            return GroupMsgFragment()
        }
    }
}
