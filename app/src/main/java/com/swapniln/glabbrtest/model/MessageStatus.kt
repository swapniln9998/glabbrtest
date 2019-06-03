package com.swapniln.glabbrtest.model

 data class MessageStatus(var userID: Int // userId mapping status for which user
                    , var status: Int  // 1 message delivered // 2 message read
                    , var time: Long // time when message was read or delivered
)
