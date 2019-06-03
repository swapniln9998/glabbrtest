package com.swapniln.glabbrtest.adapter


import android.view.LayoutInflater

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.swapniln.glabbrtest.R
import com.swapniln.glabbrtest.model.UserObject

    class AdapterReadBy(private val userList :ArrayList<UserObject> ) :RecyclerView.Adapter<UserViewHolder>()
{
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user : UserObject = userList.get(position);
        holder.bind(user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return UserViewHolder(inflater,parent)
    }

    override fun getItemCount(): Int {
        return userList.size
    }


}



class UserViewHolder(inflater: LayoutInflater,parent: ViewGroup):RecyclerView.ViewHolder(inflater.inflate(R.layout.user_listitem,parent,false))
{
    var tvUserName:TextView?= null
    var tvTime:TextView? = null

    var ivUserImage:ImageView? = null
    init {
        tvUserName = itemView.findViewById(R.id.tv_userName)
        tvTime = itemView.findViewById(R.id.tv_time)
        ivUserImage = itemView.findViewById(R.id.iv_userImageView)
    }

    fun bind(userObject: UserObject) {

        tvUserName?.text = userObject.userName;
        tvTime?.text = (java.text.SimpleDateFormat("hh:mm a").format(java.util.Date(userObject.messageTime)))
        ivUserImage?.setImageResource(R.mipmap.ic_launcher)
    }


}