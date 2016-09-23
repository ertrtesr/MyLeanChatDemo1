package com.example.huangwenjian.myleanchatdemo1.activity;

import android.content.Intent;

import com.example.huangwenjian.myleanchatdemo1.R;
import com.example.huangwenjian.myleanchatdemo1.base.BaseActivity;
import com.example.huangwenjian.myleanchatdemo1.utils.UIUtils;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/18
 */
public class SplashActivity extends BaseActivity {

    @Override
    public void initView() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void initData() {
        UIUtils.postTaskDelay(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    public void initListener() {

    }
}
