package com.swapniln.glabbrtest.ui.groupmsg;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.card.MaterialCardView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.swapniln.glabbrtest.MessageNotifier;
import com.swapniln.glabbrtest.R;
import com.swapniln.glabbrtest.adapter.deliveredToRvAdapter;
import com.swapniln.glabbrtest.adapter.readByRvAdapter;
import com.swapniln.glabbrtest.model.MessageObject;
import com.swapniln.glabbrtest.model.MessageStatus;
import com.swapniln.glabbrtest.model.UserObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GroupMsgFragment extends Fragment implements MessageNotifier {

    @BindView(R.id.tvMessageTxt)
    TextView tvMessageTxt;
    @BindView(R.id.tvMessageTime)
    TextView tvMessageTime;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.lay_messageInfo)
    ConstraintLayout layMessageInfo;
    @BindView(R.id.tv_readBy)
    TextView tvReadBy;
    @BindView(R.id.view1)
    View view1;

    @BindView(R.id.iv_messageReadStatus)
    ImageView ivMessageReadStatus;
    @BindView(R.id.cv_readByLayout)
    MaterialCardView cvReadByLayout;
    @BindView(R.id.tv_deliveredTo)
    TextView tvDeliveredTo;
    @BindView(R.id.view2)
    View view2;

    // @BindView(R.id.rv_deliveredToUsers)
    RecyclerView rvDeliveredToUsers;
    // @BindView(R.id.rv_readByUsers)
    RecyclerView rvReadByUsers;

    @BindView(R.id.iv_messageDeliveredToStatus)
    ImageView ivMessageDeliveredToStatus;
    @BindView(R.id.cv_deliveredToLayout)
    MaterialCardView cvDeliveredToLayout;

    Unbinder unbinder;
    @BindView(R.id.floatingActionButton)
    FloatingActionButton fab;
    private GroupMsgViewModel mViewModel;

    ArrayList<MessageStatus> messageStatusArrayList;
    ArrayList<UserObject> userObjectArrayList;
    ArrayList<UserObject> readByList = new ArrayList<>();
    ArrayList<UserObject> deliveredToList = new ArrayList<>();
    MessageObject messageObject;


    readByRvAdapter readByUsersAdapter;
    deliveredToRvAdapter deliveredToUsersAdapter;
    MessageNotifier messageNotifier;
    Map<Integer, UserObject> userMap;



    public static GroupMsgFragment newInstance() {
        return new GroupMsgFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.group_msg_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        messageNotifier = this;




        rvReadByUsers = view.findViewById(R.id.rv_readByUsers);
        rvDeliveredToUsers = view.findViewById(R.id.rv_deliveredToUsers);
        fab = view.findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageNotifier.updateMessageStatus(101, new MessageStatus(2, 2, Calendar.getInstance().getTimeInMillis()));
                Toast.makeText(getActivity(), "user 2 data gets updated to read status", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(GroupMsgViewModel.class);


        mViewModel.getReadByListLiveData().observe(this, new Observer<ArrayList<UserObject>>() {
            @Override
            public void onChanged(@Nullable ArrayList<UserObject> readUserObjectsList) {
                readByList.clear();
                if (readUserObjectsList != null) {
                    readByList.addAll(readUserObjectsList);
                }
                readByUsersAdapter.notifyDataSetChanged();
            }
        });
        mViewModel.getDeliverToListLiveData().observe(this, new Observer<ArrayList<UserObject>>() {
            @Override
            public void onChanged(@Nullable ArrayList<UserObject> deliveredUserObjectsList) {
               deliveredToList.clear();
                if (deliveredUserObjectsList != null) {
                    deliveredToList.addAll(deliveredUserObjectsList);
                }
                deliveredToUsersAdapter.notifyDataSetChanged();
            }
        });


        setUpUI();


        // set up dummy data
        userMap = mViewModel.setUp();
        mViewModel.sortUsers(userMap);
        notifyUI();
        messageObject = mViewModel.messageObject;






    }

    private void setUpUI() {
        readByUsersAdapter = new readByRvAdapter(getActivity(), readByList);
        deliveredToUsersAdapter = new deliveredToRvAdapter(getActivity(), deliveredToList);

        rvDeliveredToUsers.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDeliveredToUsers.setHasFixedSize(false);
        rvDeliveredToUsers.setAdapter(deliveredToUsersAdapter);


        rvReadByUsers.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvReadByUsers.setHasFixedSize(false);
        rvReadByUsers.setAdapter(readByUsersAdapter);
    }



    // notify rv Adapters
    void notifyUI() {


        readByUsersAdapter.notifyDataSetChanged();
        deliveredToUsersAdapter.notifyDataSetChanged();


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }




    @Override
    public void updateMessageStatus(int messageId, MessageStatus messageStatus) {
        if (messageObject.getMessageID() == messageId) {
            if (messageObject.updateMessageStatus(messageStatus)) {

                // check id user is present in list or not
                if(userMap.containsKey(messageStatus.getUserID()))
                {
                    UserObject userObject = userMap.get(messageStatus.getUserID());
                    userObject.setStatus(messageStatus.getStatus());
                    userObject.setMessageTime(messageStatus.getTime());

                    // update user list
                    userMap.put(messageStatus.getUserID(),userObject);

                    //sort data and update UI
                    mViewModel.sortUsers(userMap);

                }
            }

        }

    }
}
