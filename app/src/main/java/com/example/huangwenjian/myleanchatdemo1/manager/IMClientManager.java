package com.example.huangwenjian.myleanchatdemo1.manager;

import android.text.TextUtils;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述:
 * <p/>
 * 时间: 16/9/19
 */
public class IMClientManager {
    private static IMClientManager mIMClientManager;
    private String mClientId;
    private AVIMClient mClient;

    private IMClientManager() {

    }

    public synchronized static IMClientManager getInstance() {
        if (mIMClientManager == null) {
            mIMClientManager = new IMClientManager();
        }
        return mIMClientManager;
    }

    public void open(String clientId, AVIMClientCallback callback) {
        mClientId = clientId;                               //可以是自己的username
        mClient = AVIMClient.getInstance(clientId);
        if (mClient != null)
            mClient.open(callback);
    }

    public AVIMClient getClient() {
        return mClient;
    }

    public String getClientId() {
        if (TextUtils.isEmpty(mClientId)) {
            throw new IllegalStateException("Please call AVImClientManager.open first");
        }
        return mClientId;
    }
}
