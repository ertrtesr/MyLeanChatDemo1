package com.example.huangwenjian.myleanchatdemo1.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huangwenjian.myleanchatdemo1.MessageType;
import com.example.huangwenjian.myleanchatdemo1.R;
import com.example.huangwenjian.myleanchatdemo1.adapter.MessageAdapter;
import com.example.huangwenjian.myleanchatdemo1.base.BaseActivity;
import com.example.huangwenjian.myleanchatdemo1.callbacks.OnMessageSendHandler;
import com.example.huangwenjian.myleanchatdemo1.conf.ChatConfig;
import com.example.huangwenjian.myleanchatdemo1.conf.Constants;
import com.example.huangwenjian.myleanchatdemo1.entity.Message;
import com.example.huangwenjian.myleanchatdemo1.entity.User;
import com.example.huangwenjian.myleanchatdemo1.event.MessageSendEvent;
import com.example.huangwenjian.myleanchatdemo1.manager.ChatManager;
import com.example.huangwenjian.myleanchatdemo1.manager.MessageAgent;
import com.example.huangwenjian.myleanchatdemo1.utils.AndroidBug5497Workaround;
import com.example.huangwenjian.myleanchatdemo1.utils.DBUtils;
import com.example.huangwenjian.myleanchatdemo1.utils.KeyboardUtils;
import com.example.huangwenjian.myleanchatdemo1.utils.TimeUtils;
import com.example.huangwenjian.myleanchatdemo1.utils.ToastUtils;
import com.example.huangwenjian.myleanchatdemo1.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * 作者: huangwenjian
 * <p>
 * 描述: 单聊页面
 * <p>
 * 时间: 16/9/19
 */
public class SingleChatActivity extends BaseActivity {

    @BindView(R.id.sl_singlechat)
    SwipeRefreshLayout sl_singlechat;

    @BindView(R.id.rv_singlechat)
    RecyclerView rv_singlechat;

    @BindView(R.id.rl_back)
    RelativeLayout rl_back;

    @BindView(R.id.tv_singlechat_name)
    TextView tv_singlechat_name;

    @BindView(R.id.et_send)
    EditText et_send;                   //消息输入框

    @BindView(R.id.btn_send)
    Button btn_send;                    //发送按钮

    @BindView(R.id.iv_more)
    ImageView iv_more;                  //加号按钮

    @BindView(R.id.ll_more)
    LinearLayout ll_more;               //更多布局

    @BindView(R.id.tv_image)
    TextView tv_image;                  //选取本地图片

    @BindView(R.id.tv_camera)
    TextView tv_camera;                 //拍照

    private User mSelf;                 //自己本人
    private User mOhter;                //目标聊天对象
    private String mInputText;
    private MessageAdapter mMessageAdapter;

    private OnMessageSendHandler mSendHandler;          //消息发送的回调类
    private LinearLayoutManager mLayoutManager;         //线性布局管理器

    private boolean isOpen;             //设置底部布局是否处于打开状态,默认为false

    @Override
    public void init() {
        super.init();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_singlechat);
        AndroidBug5497Workaround.assistActivity(this);
    }

    @Override
    public void initData() {
        //记录下登录用户自己
        mSelf = ChatConfig.self;
        mOhter = getIntent().getParcelableExtra(Constants.OHTER_USER);
        tv_singlechat_name.setText(mOhter.getClientId());                         //设置会话标题

        mLayoutManager = new LinearLayoutManager(this);
        rv_singlechat.setLayoutManager(mLayoutManager);
        mMessageAdapter = new MessageAdapter();                                   //初始化适配器
        rv_singlechat.setAdapter(mMessageAdapter);                                //给recyclerview设置单聊适配器

        initChat();             //初始化聊天的一些配置
        initOldMessages();      //初始化历史聊天记录
    }

    private void initOldMessages() {
        List<Message> oldMsgs = DBUtils.queryAllMsgs(ChatConfig.TABLE_MESSAGE);
        mMessageAdapter.addMessages(oldMsgs);
        mMessageAdapter.notifyDataSetChanged();
    }

    /**
     * 初始化聊天配置,创建跟对话者的消息表
     */
    private void initChat() {
        //只要进入到这个页面,就新建一个表名,用ChatConfig记录下来,表名的拼接规则为"msg_"+对方的clientId;
        ChatConfig.TABLE_MESSAGE = "msg_" + mOhter.getClientId();
        DBUtils.createTable(ChatConfig.TABLE_MESSAGE);                            // 先创建Message表
        ChatManager.getInstance().obtainConversation(mSelf, mOhter);              // 得到AVIMConversation
        MessageAgent.getInstance().setOnMessageSendCallback(mSendHandler = new OnMessageSendHandler());     // 设置消息回调,由回调类处理
    }

    @Override
    public void initListener() {
        et_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeBottom();                              //关闭更多布局
            }
        });
    }

    /**
     * 当输入框中内容改变时调用
     *
     * @param inputText
     */
    @OnTextChanged(R.id.et_send)
    void onMessgeInputChanged(CharSequence inputText) {
        mInputText = inputText.toString().trim();                   //得到输入的文本内容
        if (!TextUtils.isEmpty(mInputText)) { //如果输入内容不为空,则显示发送按钮,隐藏更多的icon
            btn_send.setVisibility(View.VISIBLE);
            iv_more.setVisibility(View.GONE);
        } else {
            btn_send.setVisibility(View.GONE);
            iv_more.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 点击外部区域隐藏软键盘
     */
    @OnClick({R.id.ll_singlechat, R.id.rv_singlechat})
    void hideSoftInput(View v) {
        switch (v.getId()) {
            case R.id.rv_singlechat:
                KeyboardUtils.hideSoftInput(this);
                break;
            default:
                break;
        }
    }

    /**
     * 导航条处的返回键
     */
    @OnClick(R.id.rl_back)
    void back() {
        finish();
    }

    /**
     * 发送按钮
     */
    @OnClick(R.id.btn_send)
    void sendText() {
        if (TextUtils.isEmpty(mInputText)) {
            ToastUtils.showToast("消息不能为空");
            return;
        }
        sendText(mInputText);
        UIUtils.clearInputText(et_send);                //清空输入框
    }

    /**
     * 加号按钮
     */
    @OnClick(R.id.iv_more)
    void openMore() {
        toggleBottom();                                 //切换底部更多布局
    }

    /**
     * 进入图片选择页面
     */
    @OnClick(R.id.tv_image)
    void selectImage() {
        Intent intent = new Intent(this, PictureSelectActivity.class);
        startActivityForResult(intent, Constants.REQUEST_CODE_PICTURE_SELECT);
    }

    @OnClick(R.id.tv_camera)
    void takePhoto() {

    }

    /**
     * 发送文字消息
     *
     * @param text 文本内容
     */
    private void sendText(String text) {
        Message msg = new Message(text);
        msg.setType(MessageType.Text.getType());            //设置消息类型
        msg.setFromWho(mSelf.getClientId());                //设置消息来自谁
        msg.setTimeStamp(TimeUtils.getCurTimeMills());      //设置时间戳
        refreshMessageAdapter(msg);                         //只要一发消息不管成不成功,都要更新适配器,更新UI
        MessageAgent.getInstance().sendText(text);          //MessageAgent发出消息
    }

    /**
     * 消息发送成功后刷新界面
     */
    @Subscribe
    public void refreshUIAfterMessageSend(MessageSendEvent event) {
        Message msg = event.getMsg();
    }

    private void refreshMessageAdapter(Message msg) {
        if (mMessageAdapter == null)
            mMessageAdapter = new MessageAdapter();
        mMessageAdapter.addMessage(msg);
        mMessageAdapter.notifyDataSetChanged();             //更新适配器
        scrollToBottom();
    }

    private void scrollToBottom() {
        mLayoutManager.scrollToPositionWithOffset(mMessageAdapter.getItemCount() - 1, 0);
    }

    private void toggleBottom() {
        if (isOpen) {   //打开-->关闭
            closeBottom();                          //关闭
        } else {        //关闭-->打开
            openBottom();                           //打开
            KeyboardUtils.hideSoftInput(this);      //关闭软键盘
        }
    }

    /**
     * 打开底部更多布局
     */
    private void openBottom() {
        ll_more.setVisibility(View.VISIBLE);    //打开
        isOpen = true;
    }

    /**
     * 关闭底部更多布局
     */
    private void closeBottom() {
        ll_more.setVisibility(View.GONE);
        isOpen = false;
    }
}
