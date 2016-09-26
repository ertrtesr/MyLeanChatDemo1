package com.example.huangwenjian.myleanchatdemo1.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.huangwenjian.myleanchatdemo1.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/26
 */

public class PictureHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.sdv_picture)
    public SimpleDraweeView sdv_picture;

    @BindView(R.id.iv_selected)
    public ImageView iv_selected;

    public PictureHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
