package com.example.huangwenjian.myleanchatdemo1.conf;

import com.example.huangwenjian.myleanchatdemo1.utils.LogUtils;

/**
 * @author Administrator
 * @version $Rev: 46 $
 * @time 2015-7-15 上午11:05:12
 * @des TODO
 * @updateAuthor $Author: admin $
 * @updateDate $Date: 2015-07-20 09:16:55 +0800 (星期一, 20 七月 2015) $
 * @updateDes TODO
 */
public class Constants {

    public static final int DEBUGLEVEL = LogUtils.LEVEL_ALL;    // LEVEL_ALL,显示所有的日子,OFF:关闭日志的显示
    public static final int PAGESIZE = 20;
    public static final int PROTOCOLTIMEOUT = 5 * 60 * 1000;        // 5分钟

    public static final String USERNAME_REGEX = "^[a-zA-z][a-zA-Z0-9_]{2,9}$";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    //聊天对象
    public static final String OHTER_USER = "other_user";
    public static final String OTHER_USERNAME = "";

    //图片选择请求
    public static final int REQUEST_CODE_PICTURE_SELECT = 0;

    public static final int IMAGE_SCAN_FINISHED = 1;            //图片扫描完成
}
