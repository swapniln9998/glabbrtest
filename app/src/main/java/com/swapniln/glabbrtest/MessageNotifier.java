package com.swapniln.glabbrtest;

import com.swapniln.glabbrtest.model.MessageStatus;

public interface MessageNotifier {

    void updateMessageStatus (int messageId, MessageStatus messageStatus);
}
