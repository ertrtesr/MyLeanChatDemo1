package com.example.huangwenjian.myleanchatdemo1.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 本地图片的信息实体类
 * <p>
 * 时间: 16/9/26
 */

public class ImageInfo implements Parcelable {
    private String path;
    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public ImageInfo() {
    }

    public ImageInfo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.path);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    protected ImageInfo(Parcel in) {
        this.path = in.readString();
        this.isSelected = in.readByte() != 0;
    }

    public static final Parcelable.Creator<ImageInfo> CREATOR = new Parcelable.Creator<ImageInfo>() {
        @Override
        public ImageInfo createFromParcel(Parcel source) {
            return new ImageInfo(source);
        }

        @Override
        public ImageInfo[] newArray(int size) {
            return new ImageInfo[size];
        }
    };
}
