package com.example.huangwenjian.myleanchatdemo1.callbacks;

import com.avos.avoscloud.im.v2.AVIMMessageType;
import com.avos.avoscloud.im.v2.AVIMTypedMessage;
import com.avos.avoscloud.im.v2.messages.AVIMAudioMessage;
import com.avos.avoscloud.im.v2.messages.AVIMImageMessage;
import com.avos.avoscloud.im.v2.messages.AVIMTextMessage;
import com.example.huangwenjian.myleanchatdemo1.conf.ChatConfig;
import com.example.huangwenjian.myleanchatdemo1.entity.Message;
import com.example.huangwenjian.myleanchatdemo1.event.MessageSendEvent;
import com.example.huangwenjian.myleanchatdemo1.utils.DBUtils;
import com.example.huangwenjian.myleanchatdemo1.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 消息发送回调类,处理消息发送后的逻辑
 * <p>
 * 时间: 16/9/21
 */
public class OnMessageSendHandler implements OnSendCallback {

    @Override
    public void onSuccess(AVIMTypedMessage msg) {
        int msgType = msg.getMessageType();             //获取到消息类型

        //先用自定义的Message包装一层
        Message saveMsg = new Message();
        saveMsg.setMessageId(msg.getMessageId());
        saveMsg.setFromWho(msg.getFrom());
        saveMsg.setTimeStamp(msg.getTimestamp());
        saveMsg.setType(msg.getMessageType());

        switch (msgType) {
            case AVIMMessageType.TEXT_MESSAGE_TYPE:
                System.out.println(((AVIMTextMessage) msg).getText());

                //设置消息内容,不同类型的消息的存储内容也不同
                //文字消息就存信息内容,图片消息和音频消息存储文件的path路径
                saveMsg.setContent(((AVIMTextMessage) msg).getText());
                break;
            case AVIMMessageType.IMAGE_MESSAGE_TYPE:
                saveMsg.setContent(((AVIMImageMessage) msg).getLocalFilePath());
                break;
            case AVIMMessageType.AUDIO_MESSAGE_TYPE:
                saveMsg.setContent(((AVIMAudioMessage) msg).getLocalFilePath());
                break;
            default:
                break;
        }
        //存入数据库
        DBUtils.insertMsg(ChatConfig.TABLE_MESSAGE, saveMsg);
        EventBus.getDefault().post(new MessageSendEvent(saveMsg));      //
    }

    @Override
    public void onError(Exception e) {
        LogUtils.i(e.getMessage());
    }
}