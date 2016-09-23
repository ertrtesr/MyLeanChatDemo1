package com.example.huangwenjian.myleanchatdemo1.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述: 聊天对象实体类
 * <p/>
 * 时间: 16/9/19
 */
public class User implements Parcelable {
    private String clientId;            //clientId即用户名,唯一标示
    private String avatar;              //头像

    public User(String clientId, String avatar) {
        this.clientId = clientId;
        this.avatar = avatar;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.clientId);
        dest.writeString(this.avatar);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.clientId = in.readString();
        this.avatar = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
