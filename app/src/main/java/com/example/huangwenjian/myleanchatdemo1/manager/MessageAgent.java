package com.example.huangwenjian.myleanchatdemo1.manager;

import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.AVIMTypedMessage;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCallback;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.example.huangwenjian.myleanchatdemo1.callbacks.OnSendCallback;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 消息代理类,用于提供发送消息的方法
 * <p>
 * 时间: 16/9/21
 */
public class MessageAgent {
    private static MessageAgent mInstance;
    public static AVIMConversation mConversation;
    private OnSendCallback mCallback;

    private MessageAgent() {

    }

    public synchronized static MessageAgent getInstance() {
        if (mInstance == null) {
            mInstance = new MessageAgent();
        }
        return mInstance;
    }

    public void setOnMessageSendCallback(OnSendCallback callback) {
        mCallback = callback;
    }

    private void sendMessage(final AVIMTypedMessage message, final OnSendCallback callback) {
        if (mConversation != null) {
            mConversation.sendMessage(message, AVIMConversation.RECEIPT_MESSAGE_FLAG,
                    new AVIMConversationCallback() {
                        @Override
                        public void done(AVIMException e) {
                            if (callback != null) {
                                if (e == null) {
                                    callback.onSuccess(message);
                                } else {
                                    callback.onError(e);
                                }
                            }
                        }
                    });
        }
    }

    public void sendText(String text) {
        AVIMTextMessage msg = new AVIMTextMessage();
        msg.setText(text);
        sendMessage(msg, mCallback);
    }
}
