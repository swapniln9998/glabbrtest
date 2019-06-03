package com.swapniln.glabbrtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.swapniln.glabbrtest.ui.groupmsg.GroupMsgFragment

class GroupMsgActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.group_msg_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, GroupMsgFragment.newInstance())
                    .commitNow()
        }
    }
}
