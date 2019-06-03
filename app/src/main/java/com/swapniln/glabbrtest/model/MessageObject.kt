package com.swapniln.glabbrtest.model

import java.util.ArrayList

data class MessageObject(var messageID: Int, var messageTime: Long  // UNIX epoch time
                    , var messageText: String?, var messageStatus: ArrayList<MessageStatus>? // userIds who read the message or who received the message
                    , var isSent: Boolean // message sent successfully
) {

    fun updateMessageStatus(status: MessageStatus): Boolean {

        for (messageStatusObject in messageStatus!!) {
            if (messageStatusObject.userID == status.userID)
                messageStatusObject.status = status.status
            messageStatusObject.time = status.time
            return true
        }
        return false
    }
}
