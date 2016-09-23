package com.example.huangwenjian.myleanchatdemo1.event;

import com.example.huangwenjian.myleanchatdemo1.entity.Message;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 消息的发送事件
 * <p>
 * 时间: 16/9/21
 */
public class MessageSendEvent {
    private Message msg;

    public MessageSendEvent() {
    }

    public MessageSendEvent(Message msg) {
        this.msg = msg;
    }

    public Message getMsg() {
        return msg;
    }

    public void setMsg(Message msg) {
        this.msg = msg;
    }
}
