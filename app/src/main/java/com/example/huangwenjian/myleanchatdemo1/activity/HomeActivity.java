package com.example.huangwenjian.myleanchatdemo1.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.huangwenjian.myleanchatdemo1.R;
import com.example.huangwenjian.myleanchatdemo1.base.BaseActivity;
import com.example.huangwenjian.myleanchatdemo1.base.BaseFragment;
import com.example.huangwenjian.myleanchatdemo1.factory.FragmentFactory;
import com.example.huangwenjian.myleanchatdemo1.manager.IMClientManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/18
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.ll_conversation)
    LinearLayout ll_conversation;
    @BindView(R.id.ll_contacts)
    LinearLayout ll_contacts;
    @BindView(R.id.ll_setting)
    LinearLayout ll_setting;

    @BindView(R.id.ib_conversation)
    ImageButton ib_conversation;
    @BindView(R.id.ib_contacts)
    ImageButton ib_contacts;
    @BindView(R.id.ib_setting)
    ImageButton ib_setting;

    @BindView(R.id.tv_conversation)
    TextView tv_conversation;
    @BindView(R.id.tv_contacts)
    TextView tv_contacts;
    @BindView(R.id.tv_setting)
    TextView tv_setting;

    @BindView(R.id.tv_title)
    TextView tv_title;

    private FragmentManager mFragmentManager;
    private BaseFragment mConversationFragment;
    private BaseFragment mContactFragment;
    private BaseFragment mSettingFragment;
    private BaseFragment mCurrentFragment;                          //当前的fragment
    private List<BaseFragment> mFragments = new ArrayList<>();      //fragment集合,用于存储fragment

    @Override
    public void initView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    public void initData() {
        String clientId = IMClientManager.getInstance().getClientId();
        System.out.println(clientId);
        mFragmentManager = getSupportFragmentManager();                      //得到fragment管理器
        //通过fragment工厂创建fragment
        mConversationFragment = FragmentFactory.getFragment(0);
        mContactFragment = FragmentFactory.getFragment(1);
        mSettingFragment = FragmentFactory.getFragment(2);

        addFragment(mConversationFragment);                                 //初始化的时候先添加第一个fragment
        switchTab(0);                                                       //切到第一个标签
    }

    private void addFragment(BaseFragment fragment) {
        if (!fragment.isAdded()) {
            //如果没有添加过fragment,就开启事务add
            mFragmentManager.beginTransaction().add(R.id.fl_content, fragment, fragment.getClass().getSimpleName()).commit();
            mFragments.add(fragment);       //将fragment加入集合
        }
    }

    /**
     * 切换fragment的方法
     *
     * @param to 需要切换的fragment
     */
    public void switchFragment(BaseFragment to) {
        if (mCurrentFragment != to) {
            if (!to.isAdded()) {                        //判断fragment是否有add过
                addFragment(to);                        //没有add过就add一下
            }
            FragmentTransaction transaction = mFragmentManager.beginTransaction();
            for (Fragment fragment : mFragments) {
                transaction = transaction.hide(fragment);           //遍历集合,先隐藏所有的fragment
            }
            transaction.show(to).commit();                          //展示对应的fragment并提交,注意commit后事务就不可以再复用了
            mCurrentFragment = to;                                  //置为当前的fragment
        }
    }

    /**
     * 切换标签页和顶部title
     *
     * @param position 位置
     */
    private void switchTab(int position) {
        resetTabs();                                                            //先重置所有标签页
        switch (position) {
            case 0:
                ib_conversation.setImageResource(R.mipmap.tabbar_chat_active);  //切换标签
                tv_conversation.setTextColor(Color.WHITE);
                tv_title.setText(R.string.conversation);                        //设置标题
                break;
            case 1:
                ib_contacts.setImageResource(R.mipmap.tabbar_contacts_active);
                tv_contacts.setTextColor(Color.WHITE);
                tv_title.setText(R.string.contacts);
                break;
            case 2:
                ib_setting.setImageResource(R.mipmap.tabbar_me_active);
                tv_setting.setTextColor(Color.WHITE);
                tv_title.setText(R.string.setting);
                break;
            default:
                break;
        }
    }

    /**
     * 重置所有底部标签
     */
    private void resetTabs() {
        ib_conversation.setImageResource(R.mipmap.tabbar_chat);
        ib_contacts.setImageResource(R.mipmap.tabbar_contacts);
        ib_setting.setImageResource(R.mipmap.tabbar_me);

        tv_conversation.setTextColor(Color.parseColor("#BBBBBB"));
        tv_contacts.setTextColor(Color.parseColor("#BBBBBB"));
        tv_setting.setTextColor(Color.parseColor("#BBBBBB"));
    }

    @Override
    public void initListener() {
        ll_conversation.setOnClickListener(this);
        ll_contacts.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_conversation:
                switchFragment(mConversationFragment);
                switchTab(0);
                break;
            case R.id.ll_contacts:
                switchFragment(mContactFragment);
                switchTab(1);
                break;
            case R.id.ll_setting:
                switchFragment(mSettingFragment);
                switchTab(2);
                break;
            default:
                break;
        }
    }
}
