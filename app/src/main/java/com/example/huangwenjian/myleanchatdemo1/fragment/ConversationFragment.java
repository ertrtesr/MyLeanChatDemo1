package com.example.huangwenjian.myleanchatdemo1.fragment;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huangwenjian.myleanchatdemo1.R;
import com.example.huangwenjian.myleanchatdemo1.base.BaseFragment;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 会话界面
 * <p>
 * 时间: 16/9/18
 */
public class ConversationFragment extends BaseFragment {

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_conversation, container, false);
        return view;
    }

    @Override
    protected void initAfterActivityCreated() {

    }
}
