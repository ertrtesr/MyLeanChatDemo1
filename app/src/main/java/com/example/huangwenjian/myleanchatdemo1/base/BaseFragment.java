package com.example.huangwenjian.myleanchatdemo1.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huangwenjian.myleanchatdemo1.handler.UIHandler;
import com.example.huangwenjian.myleanchatdemo1.handler.interfaces.IHandler;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/2
 */
public abstract class BaseFragment extends Fragment {

    protected UIHandler mFHandler = new UIHandler(BaseApplication.getMainThreadLooper());       //初始化handler对象

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView(inflater, container);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAfterActivityCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mFHandler != null) {
            mFHandler.removeCallbacksAndMessages(null);
            mFHandler = null;
        }
    }

    protected void init() {
        mFHandler.setHandler(new IHandler() {
            @Override
            public void handleMessage(Message message) {
                processMsg(message);
            }
        });
    }

    protected void processMsg(Message message) {

    }

    protected abstract View initView(LayoutInflater inflater, @Nullable ViewGroup container);

    protected abstract void initAfterActivityCreated();
}
