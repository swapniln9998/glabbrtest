package com.swapniln.glabbrtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.swapniln.glabbrtest.ui.groupmsg.GroupMsgFragment;

public class groupMsgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_msg_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, GroupMsgFragment.newInstance())
                    .commitNow();
        }
    }
}
