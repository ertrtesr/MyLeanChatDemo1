package com.example.huangwenjian.myleanchatdemo1.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SignUpCallback;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.huangwenjian.myleanchatdemo1.R;
import com.example.huangwenjian.myleanchatdemo1.base.BaseActivity;
import com.example.huangwenjian.myleanchatdemo1.conf.ChatConfig;
import com.example.huangwenjian.myleanchatdemo1.conf.Constants;
import com.example.huangwenjian.myleanchatdemo1.entity.User;
import com.example.huangwenjian.myleanchatdemo1.manager.IMClientManager;
import com.example.huangwenjian.myleanchatdemo1.utils.SPUtils;
import com.example.huangwenjian.myleanchatdemo1.utils.ToastUtils;
import com.rengwuxian.materialedittext.MaterialEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述:
 * <p/>
 * 时间: 16/9/18
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.met_username)
    MaterialEditText met_username;

    @BindView(R.id.met_password)
    MaterialEditText met_password;

    @BindView(R.id.btn_signup)
    Button btn_signup;

    @BindView(R.id.btn_login)
    Button btn_login;

    private String mUsername;
    private String mPassword;
    private AVUser mUser;

    @Override
    public void initView() {
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.btn_signup)
    void signUp() {
        //判断用户名和密码是否符合要求
        //得到用户名
        mUsername = met_username.getText().toString().trim();
        //得到密码
        mPassword = met_password.getText().toString().trim();
        if (checkLoginInfo(mUsername, mPassword)) {
            saveLoginInfo(mUsername, mPassword);
            signUp(mUsername, mPassword);
        }
    }

    /**
     * 点击登录按钮后的登录操作
     */
    @OnClick(R.id.btn_login)
    void login() {
        //判断用户名和密码是否符合要求
        //得到用户名
        mUsername = met_username.getText().toString().trim();
        //得到密码
        mPassword = met_password.getText().toString().trim();
        login(mUsername, mPassword);
    }

    /**
     * 存储用户名和密码
     *
     * @param username
     * @param password
     */
    private void saveLoginInfo(String username, String password) {
        SPUtils.putString(Constants.USERNAME, username);
        SPUtils.putString(Constants.PASSWORD, password);
    }

    @Override
    public void initData() {
        //从sp中获取用户名和密码
        mUsername = SPUtils.getString(Constants.USERNAME);
        mPassword = SPUtils.getString(Constants.PASSWORD);

        //如果sp中的用户名和密码不为空,就展示到输入框上
        if (!TextUtils.isEmpty(mUsername)) {
            met_username.setText(mUsername);
            met_username.setSelection(mUsername.length());
        }
        if (!TextUtils.isEmpty(mPassword)) {
            met_password.setText(mPassword);
        }
    }

    @Override
    public void initListener() {

    }

    /**
     * 校验用户名和密码
     *
     * @param username
     * @param password
     * @return
     */
    public boolean checkLoginInfo(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showToast("用户名不能为空");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast("密码不能为空");
            return false;
        }
        if (!username.matches(Constants.USERNAME_REGEX)) {
            ToastUtils.showToast("用户名输入不符合要求,请重新入数");
            return false;
        }
        return true;
    }

    public void signUp(String username, String password) {
        mUser = new AVUser();
        mUser.setUsername(mUsername);
        mUser.setPassword(mPassword);
        mUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                System.out.println(Thread.currentThread().getName());
                if (filterException(e)) {
                    ToastUtils.showToast("注册成功");
                }
            }
        });
    }

    private void login(String username, String password) {
//        AVUser.logInInBackground(username, password, new LogInCallback<AVUser>() {
//            @Override
//            public void done(AVUser avUser, AVException e) {
//                if (filterException(e)) {
//                    goToHomeActivity();                 //进入主界面
//                }
//            }
//        });

        //open方法执行以后会返回client,即登录的用户本身
        IMClientManager.getInstance().open(mUsername,
                new AVIMClientCallback() {
                    @Override
                    public void done(AVIMClient client, AVIMException e) {
                        if (filterException(e)) {
                            saveLoginInfo(mUsername, mPassword);        //保存信息到sp
                            ChatConfig.self = new User(mUsername, "http://pic.yupoo.com/ertrtesr/FRY76pbH/small.jpg");      //用静态变量记录登录用户
                            goToHomeActivity();                         //进入主界面
                        }
                    }
                });
    }

    /**
     * 进入主界面
     */
    private void goToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(Constants.USERNAME, mUsername);         //将用户名传递到HomeActivity
        startActivity(intent);
        finish();
    }
}
