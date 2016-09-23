package com.example.huangwenjian.myleanchatdemo1;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/23
 */

public enum MessageType {
    Unsupport(0),
    Text(-1),
    Image(-2),
    Audio(-3),
    Video(-4),
    Location(-5),
    File(-6);

    int type;

    MessageType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

//    int TEXT_MESSAGE_TYPE = -1;
//    int IMAGE_MESSAGE_TYPE = -2;
//    int AUDIO_MESSAGE_TYPE = -3;
//    int VIDEO_MESSAGE_TYPE = -4;
//    int LOCATION_MESSAGE_TYPE = -5;
//    int FILE_MESSAGE_TYPE = -6;
}
