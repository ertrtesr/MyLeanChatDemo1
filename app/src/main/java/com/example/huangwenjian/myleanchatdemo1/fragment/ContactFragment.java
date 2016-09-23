package com.example.huangwenjian.myleanchatdemo1.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huangwenjian.myleanchatdemo1.R;
import com.example.huangwenjian.myleanchatdemo1.activity.SingleChatActivity;
import com.example.huangwenjian.myleanchatdemo1.adapter.ContactAdapter;
import com.example.huangwenjian.myleanchatdemo1.base.BaseFragment;
import com.example.huangwenjian.myleanchatdemo1.base.listener.OnRecyclerViewTouchListener;
import com.example.huangwenjian.myleanchatdemo1.conf.Constants;
import com.example.huangwenjian.myleanchatdemo1.manager.UserProvider;
import com.example.huangwenjian.myleanchatdemo1.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 联系人界面
 * <p>
 * 时间: 16/9/18
 */
public class ContactFragment extends BaseFragment {
    @BindView(R.id.rv_contacts)
    RecyclerView rv_contacts;
    private ContactAdapter mContactAdapter;

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initAfterActivityCreated() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UIUtils.getContext());
        rv_contacts.setLayoutManager(linearLayoutManager);
        mContactAdapter = new ContactAdapter(UserProvider.getInstance().getAllUsers());
        rv_contacts.setAdapter(mContactAdapter);

        rv_contacts.addOnItemTouchListener(new OnRecyclerViewTouchListener(rv_contacts,
                new OnRecyclerViewTouchListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        System.out.println(position);

                        Intent intent = new Intent(getActivity(), SingleChatActivity.class);
                        intent.putExtra(Constants.OHTER_USER, mContactAdapter.getUser(position));
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        System.out.println("long---" + position);
                    }
                }));
    }
}
