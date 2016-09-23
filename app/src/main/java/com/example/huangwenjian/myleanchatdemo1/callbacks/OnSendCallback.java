package com.example.huangwenjian.myleanchatdemo1.callbacks;

import com.avos.avoscloud.im.v2.AVIMTypedMessage;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/21
 */
public interface OnSendCallback {
    void onSuccess(AVIMTypedMessage message);

    void onError(Exception e);
}
