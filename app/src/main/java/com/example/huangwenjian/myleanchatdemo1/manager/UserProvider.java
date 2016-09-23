package com.example.huangwenjian.myleanchatdemo1.manager;

import com.example.huangwenjian.myleanchatdemo1.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述:
 * <p/>
 * 时间: 16/9/20
 */
public class UserProvider {
    private static UserProvider mUserProvider;
    private static List<User> mUsers = new ArrayList<>();

    private UserProvider() {
    }

    public synchronized static UserProvider getInstance() {
        if (mUserProvider == null) {
            mUserProvider = new UserProvider();
        }
        return mUserProvider;
    }

    /**
     * 初始化一些用户
     */
    static {
        mUsers.add(new User("Tom", "http://www.avatarsdb.com/avatars/tom_and_jerry2.jpg"));
        mUsers.add(new User("Jerry", "http://www.avatarsdb.com/avatars/jerry.jpg"));
        mUsers.add(new User("Harry", "http://www.avatarsdb.com/avatars/young_harry.jpg"));
        mUsers.add(new User("William", "http://www.avatarsdb.com/avatars/william_shakespeare.jpg"));
        mUsers.add(new User("Bob", "http://www.avatarsdb.com/avatars/bath_bob.jpg"));
    }

    /**
     * 返回所有的用户
     *
     * @return
     */
    public List<User> getAllUsers() {
        return mUsers;
    }
}
