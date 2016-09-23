package com.example.huangwenjian.myleanchatdemo1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.huangwenjian.myleanchatdemo1.R;
import com.example.huangwenjian.myleanchatdemo1.entity.User;
import com.example.huangwenjian.myleanchatdemo1.holder.ContactHolder;
import com.example.huangwenjian.myleanchatdemo1.utils.UIUtils;

import java.util.List;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述: 联系人适配器
 * <p/>
 * 时间: 16/9/19
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {
    private List<User> mUsers;

    public ContactAdapter() {
    }

    public ContactAdapter(List<User> users) {
        mUsers = users;
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = UIUtils.inflate(R.layout.item_contact, parent);
        ContactHolder contactHolder = new ContactHolder(itemView);
        return contactHolder;
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        holder.iv_contact_avatar.setImageURI(mUsers.get(position).getAvatar());
        holder.tv_contact_name.setText(mUsers.get(position).getClientId());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    /**
     * 设置数据
     *
     * @param users
     */
    public void setUser(List<User> users) {
        mUsers = users;
    }

    public User getUser(int position) {
        if (mUsers != null && mUsers.size() > 0) {
            return mUsers.get(position);
        }
        return null;
    }
}
