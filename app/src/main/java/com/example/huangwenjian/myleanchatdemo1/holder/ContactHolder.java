package com.example.huangwenjian.myleanchatdemo1.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.huangwenjian.myleanchatdemo1.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述:
 * <p/>
 * 时间: 16/9/19
 */
public class ContactHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_contact_avatar)
    public SimpleDraweeView iv_contact_avatar;
    @BindView(R.id.tv_contact_name)
    public TextView tv_contact_name;

    public ContactHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
