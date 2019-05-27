package com.swapniln.glabbrtest;

import android.os.Bundle;
import android.support.design.card.MaterialCardView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.swapniln.glabbrtest.adapter.deliveredToRvAdapter;
import com.swapniln.glabbrtest.adapter.readByRvAdapter;
import com.swapniln.glabbrtest.model.MessageObject;
import com.swapniln.glabbrtest.model.MessageStatus;
import com.swapniln.glabbrtest.model.UserObject;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.swapniln.glabbrtest.utils._const.READ;

public class MessageDetailsActivity extends AppCompatActivity implements MessageNotifier {

    ArrayList<MessageStatus> messageStatusArrayList;
    ArrayList<UserObject> userObjectArrayList;
    ArrayList<UserObject> readByList;
    ArrayList<UserObject> deliveredToList;
    MessageObject messageObject;
    @BindView(R.id.tvMessageTxt)
    TextView tvMessageTxt;
    @BindView(R.id.tv_readBy)
    TextView tvReadBy;

    @BindView(R.id.iv_messageReadStatus)
    ImageView ivMessageReadStatus;
    @BindView(R.id.cv_readByLayout)
    MaterialCardView cvReadByLayout;
    @BindView(R.id.tv_deliveredTo)
    TextView tvDeliveredTo;
    @BindView(R.id.iv_messageDeliveredToStatus)
    ImageView ivMessageDeliveredToStatus;
    @BindView(R.id.cv_deliveredToLayout)
    MaterialCardView cvDeliveredToLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;


    readByRvAdapter readByUsersAdapter;
    deliveredToRvAdapter deliveredToUsersAdapter;

    MessageNotifier messageNotifier;
   // @BindView(R.id.rv_readByUsers)
    RecyclerView rvReadByUsers;
    //@BindView(R.id.rv_deliveredToUsers)
    RecyclerView rvDeliveredToUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        messageNotifier = this;
        rvReadByUsers= findViewById(R.id.rv_readByUsers);
        rvDeliveredToUsers = findViewById(R.id.rv_deliveredToUsers);


        setUp();


    }

    private void setUp() {


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

        messageObject = new MessageObject(101, Calendar.getInstance().getTimeInMillis(), "Happy Birthday !!!", messageStatusArrayList, true);

       readByList = new ArrayList<>();
       deliveredToList = new ArrayList<>();

        userObjectArrayList = new ArrayList<>();
        userObjectArrayList.add(new UserObject(1, "user1", "", 1, ""));
        userObjectArrayList.add(new UserObject(2, "user2", "", 1, ""));
        userObjectArrayList.add(new UserObject(3, "user3", "", 1, ""));
        userObjectArrayList.add(new UserObject(4, "user4", "", 1, ""));
        userObjectArrayList.add(new UserObject(5, "user5", "", 2, ""));
        userObjectArrayList.add(new UserObject(6, "user6", "", 2, ""));
        userObjectArrayList.add(new UserObject(7, "user7", "", 2, ""));
        userObjectArrayList.add(new UserObject(8, "user8", "", 2, ""));
        userObjectArrayList.add(new UserObject(9, "user9", "", 2, ""));
        userObjectArrayList.add(new UserObject(10, "user10", "", 2, ""));

        sortUsers();

        readByUsersAdapter = new readByRvAdapter(this, readByList);
        deliveredToUsersAdapter = new deliveredToRvAdapter(this, deliveredToList);

       rvDeliveredToUsers.setLayoutManager(new LinearLayoutManager(this));
        rvDeliveredToUsers.setHasFixedSize(true);
        rvDeliveredToUsers.setAdapter(deliveredToUsersAdapter);


        rvReadByUsers.setLayoutManager(new LinearLayoutManager(this));
        rvReadByUsers.setHasFixedSize(true);
        rvReadByUsers.setAdapter(readByUsersAdapter);


    }

    private void sortUsers() {
        readByList.clear();
        deliveredToList.clear();
        for (UserObject user: userObjectArrayList) {
            if(user.getStatus()==READ)
            {
                // if user status is READ add to read list
                readByList.add(user);
            }else
            {
                //if user ststus is DELIVERED add to delivered list
                deliveredToList.add(user);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void updateMessageStatus(int messageId, MessageStatus messageStatus) {

        if (messageObject.getMessageID() == messageId) {
            if (messageObject.updateMessageStatus(messageStatus)) {

                Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @OnClick(R.id.fab)
    public void onViewClicked(View v) {

        //trigger to Message notifier
        messageNotifier.updateMessageStatus(2,new MessageStatus(2,2,Calendar.getInstance().getTimeInMillis()));
        Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    void notifyUI() {


        readByUsersAdapter.notifyDataSetChanged();
        deliveredToUsersAdapter.notifyDataSetChanged();


    }
}
