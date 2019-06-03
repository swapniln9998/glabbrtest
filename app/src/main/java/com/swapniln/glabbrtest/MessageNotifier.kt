package com.swapniln.glabbrtest

import com.swapniln.glabbrtest.model.MessageStatus

interface MessageNotifier {

    fun updateMessageStatus(messageId: Int, messageStatus: MessageStatus)
}
