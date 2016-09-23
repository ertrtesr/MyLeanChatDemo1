package com.example.huangwenjian.myleanchatdemo1.utils;

import java.text.SimpleDateFormat;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/22
 */

public class DateUtils {
    public static String formatDate(long time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = dateFormat.format(time);
        return format;
    }
}
