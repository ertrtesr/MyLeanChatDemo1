package com.example.huangwenjian.myleanchatdemo1.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

import com.example.huangwenjian.myleanchatdemo1.handler.UIHandler;
import com.example.huangwenjian.myleanchatdemo1.handler.interfaces.IHandler;
import com.example.huangwenjian.myleanchatdemo1.utils.ToastUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/9 0009.
 */
public abstract class BaseActivity extends FragmentActivity {
    protected UIHandler mAHandler = new UIHandler(BaseApplication.getMainThreadLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView();
        initData();
        initListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAHandler != null) {
            mAHandler.removeCallbacksAndMessages(null);
            mAHandler = null;
        }
    }

    public void initHandler() {
        mAHandler.setHandler(new IHandler() {
            @Override
            public void handleMessage(Message msg) {   //只要收到UIHandler发送的消息,这个方法里的代码就会执行
                processMsg(msg);
            }
        });
    }

    public void init() {
        initSystemBar(this);
        initHandler();                              //设置handler
    }

    public void initSystemBar(Activity activity) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);  // 使用颜色资源
        tintManager.setStatusBarTintColor(Color.TRANSPARENT);

//        //透明状态栏
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            // Translucent status bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
    }

    /**
     * 过滤错误信息
     *
     * @param e
     * @return
     */
    protected boolean filterException(Exception e) {
        if (e != null) {
            e.printStackTrace();
            ToastUtils.showToast(e.getMessage());
            return false;
        } else {
            return true;
        }
    }

    @TargetApi(19)
    private void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void setContentView() {
        initView();
        ButterKnife.bind(this);
    }

    protected abstract void initView();

    public abstract void initData();

    public abstract void initListener();

    /**
     * 处理消息的方法,让子类实现
     *
     * @param msg
     */
    public void processMsg(Message msg) {
        
    }
}
