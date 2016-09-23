package com.example.huangwenjian.myleanchatdemo1.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.huangwenjian.myleanchatdemo1.entity.Message;

import butterknife.ButterKnife;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/22
 */

public abstract class ChatHolder extends RecyclerView.ViewHolder {
    public ChatHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    abstract public void bindData(Message msg);

    abstract public void showTime(boolean shouldShowTime);
}
