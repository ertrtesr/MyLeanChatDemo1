package com.example.huangwenjian.myleanchatdemo1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.huangwenjian.myleanchatdemo1.R;
import com.example.huangwenjian.myleanchatdemo1.entity.ImageInfo;
import com.example.huangwenjian.myleanchatdemo1.holder.PictureHolder;
import com.example.huangwenjian.myleanchatdemo1.utils.UIUtils;

import java.util.List;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/26
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureHolder> {

    private List<ImageInfo> mImages;

    public PictureAdapter() {

    }

    public PictureAdapter(List<ImageInfo> images) {
        mImages = images;
    }

    @Override
    public PictureHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtils.inflate(R.layout.item_picture_select);
        return new PictureHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PictureHolder holder, int position) {
        holder.sdv_picture.setImageURI("file://" + mImages.get(position).getPath());
//        holder.sdv_picture.setImageBitmap(localBitmap);
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void setImages(List<ImageInfo> images) {
        this.mImages = images;
    }
}
