package com.example.huangwenjian.myleanchatdemo1.manager;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMConversationQuery;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationQueryCallback;
import com.example.huangwenjian.myleanchatdemo1.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/21
 */
public class ChatManager {
    private static ChatManager mInstance;
    private AVIMClient mClient;

    private ChatManager() {
        mClient = IMClientManager.getInstance().getClient();
    }

    public synchronized static ChatManager getInstance() {
        if (mInstance == null) {
            mInstance = new ChatManager();
        }
        return mInstance;
    }

    public void obtainConversation(final User self, final User other) {
        final List<String> members = new ArrayList<>();
        members.add(other.getClientId());                       //添加会话成员
        AVIMConversationQuery query = mClient.getQuery();
        query.withMembers(members, true);                       //true代表包含自己
        query.findInBackground(new AVIMConversationQueryCallback() {
            @Override
            public void done(List<AVIMConversation> conversations, AVIMException e) {
                if (e == null) {
                    if (conversations != null && conversations.size() > 0) {  // 已经有一个和 XXX 的对话存在，继续在这一对话中聊天
                        MessageAgent.mConversation = conversations.get(0);                    //用一个静态变量记录下会话
                    } else {    // 不曾和 XXX 聊过，新建一个对话。！！**这里是重点**！！
                        Map<String, Object> attr = new HashMap<>();             //自定义attribute

                        attr.put("clientId", self.getClientId());               //将自己的clientId和avatar放入,接收者可以从attr中获取
                        attr.put("avatar", self.getAvatar());

                        mClient.createConversation(members, null, attr, false, true,
                                new AVIMConversationCreatedCallback() {
                                    @Override
                                    public void done(AVIMConversation avimConversation, AVIMException e) {
                                        MessageAgent.mConversation = avimConversation;                //用一个静态变量记录下会话
                                    }
                                });
                    }
                }
            }
        });
    }
}
