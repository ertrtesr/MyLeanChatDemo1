package com.example.huangwenjian.myleanchatdemo1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.avos.avoscloud.im.v2.AVIMMessageType;
import com.example.huangwenjian.myleanchatdemo1.R;
import com.example.huangwenjian.myleanchatdemo1.entity.Message;
import com.example.huangwenjian.myleanchatdemo1.holder.ChatHolder;
import com.example.huangwenjian.myleanchatdemo1.holder.LeftTextHolder;
import com.example.huangwenjian.myleanchatdemo1.holder.RightTextHolder;
import com.example.huangwenjian.myleanchatdemo1.manager.IMClientManager;
import com.example.huangwenjian.myleanchatdemo1.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/21
 */
public class MessageAdapter extends RecyclerView.Adapter<ChatHolder> {

    private final int ITEM_LEFT_TEXT = 0;
    private final int ITEM_RIGHT_TEXT = 1;

    private final int ITEM_LEFT_IMAGE = 2;
    private final int ITEM_RIGHT_IMAGE = 3;

    private List<Message> messageList = new ArrayList<>();

    //时间间隔最小为2分钟
    private final long TIME_INTERVAL = 2 * 60 * 1000;

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        //根据viewType确定返回的holder类型
        switch (viewType) {
            case ITEM_RIGHT_TEXT:
                itemView = UIUtils.inflate(R.layout.item_right_text);
                return new RightTextHolder(itemView);
            case ITEM_RIGHT_IMAGE:
                itemView = UIUtils.inflate(R.layout.item_right_image);
            case ITEM_LEFT_TEXT:
                itemView = UIUtils.inflate(R.layout.item_left_text);
                return new LeftTextHolder(itemView);
            case ITEM_LEFT_IMAGE:

            default:
                break;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        holder.bindData(messageList.get(position));     //给holder绑定数据
        holder.showTime(shouldShowTime(position));      //由条目自己来确定是否要显示时间
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;
        Message msg = messageList.get(position);

        //如果是自己发出的消息
        if (msg.getFromWho().equals(IMClientManager.getInstance().getClientId())) {
            switch (msg.getType()) {
                case AVIMMessageType.TEXT_MESSAGE_TYPE:
                    viewType = ITEM_RIGHT_TEXT;
                    break;
                case AVIMMessageType.IMAGE_MESSAGE_TYPE:
                    viewType = ITEM_RIGHT_IMAGE;
                    break;
                default:
                    break;
            }
        } else {    //别人发的消息
            switch (msg.getType()) {
                case AVIMMessageType.TEXT_MESSAGE_TYPE:
                    viewType = ITEM_LEFT_TEXT;
                    break;
                case AVIMMessageType.IMAGE_MESSAGE_TYPE:
                    viewType = ITEM_LEFT_IMAGE;
                    break;
                default:
                    break;
            }
        }
        return viewType;
    }

    /**
     * 位置为position的条目是否应该显示时间
     *
     * @param position
     * @return
     */
    public boolean shouldShowTime(int position) {
        if (position == 0) { //如果是第0条条目,就展示时间
            return true;
        }
        long lastTime = messageList.get(position - 1).getTimeStamp();       //上一条消息的时间
        long currentTime = messageList.get(position).getTimeStamp();        //本条消息的时间
        return currentTime - lastTime > TIME_INTERVAL;
    }

    /**
     * 添加单条聊天信息
     *
     * @param msg
     */
    public void addMessage(Message msg) {
        if (msg != null) {
            messageList.add(msg);
        }
    }

    /**
     * 添加聊天信息集合
     *
     * @param msgs
     */
    public void addMessages(List<Message> msgs) {
        if (messageList != null && messageList.size() > 0) {
            messageList.clear();
        }
        if (msgs != null && msgs.size() > 0) {
            messageList.addAll(msgs);
        }
    }
}
