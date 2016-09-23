package com.example.huangwenjian.myleanchatdemo1.holder;

import android.view.View;
import android.widget.TextView;

import com.example.huangwenjian.myleanchatdemo1.R;
import com.example.huangwenjian.myleanchatdemo1.entity.Message;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/21
 */
public class LeftTextHolder extends ChatHolder {

    @BindView(R.id.tv_left_text)
    public TextView tv_left_text;

    @BindView(R.id.iv_left_avatar)
    public SimpleDraweeView iv_left_avatar;

    public LeftTextHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(Message msg) {

    }

    @Override
    public void showTime(boolean shouldShowTime) {

    }
}
