package com.swapniln.glabbrtest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.swapniln.glabbrtest.model.UserObject

class AdapterDeliveredTo(var userList:List<UserObject>):RecyclerView.Adapter<UserViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val inflector = LayoutInflater.from(parent.context)


        return UserViewHolder(inflector,parent);
    }

    override fun getItemCount(): Int {
       return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user:UserObject = userList.get(position)
        holder.bind(user)
    }

}


