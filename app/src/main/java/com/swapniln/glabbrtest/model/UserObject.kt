package com.swapniln.glabbrtest.model

data class UserObject(var userID: Int // userId mapping status for which user
                 , var userName: String // user name
                 , var messageTime: Long // time when message was read or delivered
                 , var status: Int  // 1 message delivered // 2 message read
                 , var userImage: String // user display Image
)
