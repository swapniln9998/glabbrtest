package com.swapniln.glabbrtest.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.swapniln.glabbrtest.R;
import com.swapniln.glabbrtest.model.UserObject;

import java.util.ArrayList;

import static com.swapniln.glabbrtest.utils._const.DELIVERED;
import static com.swapniln.glabbrtest.utils._const.READ;

public class readByRvAdapter extends RecyclerView.Adapter<readByRvAdapter.userViewHolder> {

    ArrayList<UserObject> userArrayList = new ArrayList<>();
    Context mContext;
    public readByRvAdapter(Context context, ArrayList<UserObject> userObjectArrayList) {

        mContext = context;
        userArrayList = userObjectArrayList;
    }

    public class userViewHolder extends RecyclerView.ViewHolder{

        TextView tvUserName,tvTime;
        ImageView ivUserImage;
        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserName = itemView.findViewById(R.id.tv_userName);
            tvTime = itemView.findViewById(R.id.tv_time);
            ivUserImage = itemView.findViewById(R.id.iv_userImageView);
        }
    }

    @Override
    public readByRvAdapter.userViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_listitem, viewGroup, false);

        return new readByRvAdapter.userViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int i) {

        if(userArrayList.get(i).getStatus()==READ) {
            holder.tvUserName.setText(userArrayList.get(i).getUserName());
            holder.tvTime.setText(new java.text.SimpleDateFormat("hh:mm a").format(new java.util.Date (userArrayList.get(i).getMessageTime())));
            holder.ivUserImage.setImageResource(R.mipmap.ic_launcher);
        }

    }


    @Override
    public int getItemCount() {
        return userArrayList.size();
    }
}
