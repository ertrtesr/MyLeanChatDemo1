package com.example.huangwenjian.myleanchatdemo1.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.avos.avoscloud.AVOSCloud;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述:
 * <p/>
 * 时间: 16/9/18
 */
public class BaseApplication extends Application {
    private static Context mContext;
    private static Thread mMainThread;
    private static long mMainTreadId;
    private static Looper mMainLooper;
    private static Handler mHandler;

    public static Handler getHandler() {
        return mHandler;
    }

    public static Context getContext() {
        return mContext;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static long getMainTreadId() {
        return mMainTreadId;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    @Override
    public void onCreate() {// 程序的入口
        // 初始化化一些.常用属性.然后放到盒子里面来
        mContext = getApplicationContext();             //上下文
        mMainThread = Thread.currentThread();           //主线程
        mMainTreadId = android.os.Process.myTid();      //主线程Id
        mMainLooper = getMainLooper();                  //主线程Looper对象
        mHandler = new Handler();                       //定义一个handler
        initLeanCloud();                                //初始化LeanCloud
        initFresco();                                   //初始化Fresco
        super.onCreate();
    }

    private void initFresco() {
        Fresco.initialize(this);
    }

    private void initLeanCloud() {
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this, "XbpBxb2BebPt3vuDXpBMdxot-gzGzoHsz", "bbbtkdPCq4Qe27FoVQBcOe3P");
    }
}
