package com.example.huangwenjian.myleanchatdemo1.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huangwenjian.myleanchatdemo1.MessageType;
import com.example.huangwenjian.myleanchatdemo1.R;
import com.example.huangwenjian.myleanchatdemo1.conf.ChatConfig;
import com.example.huangwenjian.myleanchatdemo1.entity.Message;
import com.example.huangwenjian.myleanchatdemo1.utils.DateUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 发出的文字消息的holder
 * <p>
 * 时间: 16/9/21
 */
public class RightTextHolder extends ChatHolder {

    @BindView(R.id.tv_message_time)
    TextView tv_message_time;

    @BindView(R.id.ll_message_time)
    LinearLayout ll_message_time;

    @BindView(R.id.tv_right_text)
    TextView tv_right_text;

    @BindView(R.id.iv_right_avatar)
    SimpleDraweeView iv_right_avatar;

    public RightTextHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(Message msg) {
        String content = "暂不支持此消息类型";
        if (msg.getType() == MessageType.Text.getType()) {
            tv_right_text.setText(msg.getContent());                        //设置消息显示内容
        }
        iv_right_avatar.setImageURI(ChatConfig.self.getAvatar());           //设置自己的头像
        tv_message_time.setText(DateUtils.formatDate(msg.getTimeStamp()));
    }

    @Override
    public void showTime(boolean shouldShowTime) {
        ll_message_time.setVisibility(shouldShowTime ? View.VISIBLE : View.GONE);
    }
}
