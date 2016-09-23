package com.example.huangwenjian.myleanchatdemo1.entity;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 自定义的消息类,可以添加扩展属性,查询数据库返回的数据都由这个类来设置内容
 * <p>
 * 时间: 16/9/23
 */

public class Message {
    private String messageId;
    private String content;
    private long timeStamp;
    private String fromWho;
    private int type;
    private int isDelete;

    public Message() {
    }

    public Message(String content) {
        this.content = content;
    }

    public Message(String messageId, String content, long timeStamp, String fromWho, int type, int isDelete) {
        this.messageId = messageId;
        this.content = content;
        this.timeStamp = timeStamp;
        this.fromWho = fromWho;
        this.type = type;
        this.isDelete = isDelete;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFromWho() {
        return fromWho;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
