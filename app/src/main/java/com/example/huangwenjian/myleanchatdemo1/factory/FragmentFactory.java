package com.example.huangwenjian.myleanchatdemo1.factory;

import android.support.v4.util.SparseArrayCompat;

import com.example.huangwenjian.myleanchatdemo1.base.BaseFragment;
import com.example.huangwenjian.myleanchatdemo1.fragment.ContactFragment;
import com.example.huangwenjian.myleanchatdemo1.fragment.ConversationFragment;
import com.example.huangwenjian.myleanchatdemo1.fragment.SettingFragment;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: Fragment工厂类
 * <p>
 * 时间: 16/9/18
 */
public class FragmentFactory {
    static SparseArrayCompat<BaseFragment> cachesFragment = new SparseArrayCompat<>();

    public static BaseFragment getFragment(int position) {

        BaseFragment fragment = null;
        // 如果缓存里面有对应的fragment,就直接取出返回

        BaseFragment tmpFragment = cachesFragment.get(position);
        if (tmpFragment != null) {
            fragment = tmpFragment;
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = new ConversationFragment();
                break;
            case 1:
                fragment = new ContactFragment();
                break;
            case 2:
                fragment = new SettingFragment();
                break;
            default:
                break;
        }
        // 保存对应的fragment
        cachesFragment.put(position, fragment);
        return fragment;
    }
}
