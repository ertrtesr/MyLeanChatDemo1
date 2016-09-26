package com.example.huangwenjian.myleanchatdemo1.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/26
 */

public class ImageUtils {
    /**
     * @param url 本地图片路径
     * @return
     */
    public static Bitmap getLocalBitmap(String url) {
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(url));
            return BitmapFactory.decodeStream(bis);  ///把流转化为Bitmap图片
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
