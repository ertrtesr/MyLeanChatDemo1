package com.example.huangwenjian.myleanchatdemo1.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.huangwenjian.myleanchatdemo1.R;
import com.example.huangwenjian.myleanchatdemo1.adapter.PictureAdapter;
import com.example.huangwenjian.myleanchatdemo1.base.BaseActivity;
import com.example.huangwenjian.myleanchatdemo1.conf.Constants;
import com.example.huangwenjian.myleanchatdemo1.entity.ImageInfo;
import com.example.huangwenjian.myleanchatdemo1.manager.ThreadManager;
import com.example.huangwenjian.myleanchatdemo1.utils.LoadingDialogUtils;
import com.example.huangwenjian.myleanchatdemo1.utils.LogUtils;
import com.example.huangwenjian.myleanchatdemo1.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/26
 */

public class PictureSelectActivity extends BaseActivity {

    @BindView(R.id.btn_picture_preview)
    Button btn_picture_preview;

    @BindView(R.id.btn_picture_send)
    Button btn_picture_send;

    @BindView(R.id.tv_singlechat_name)
    TextView tv_title;

    @BindView(R.id.rl_back)
    RelativeLayout rl_back;

    @BindView(R.id.rv_picture_select)
    RecyclerView rv_picture_select;

    private ContentResolver mContentResolver;
    private List<ImageInfo> mImages = new ArrayList<>();
    private GridLayoutManager mGridLayoutManager;
    private PictureAdapter mPictureAdapter;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_picture_select);
//        LoadingDialogUtils.show(this);                              //显示进度条
    }

    @Override
    public void initData() {
        tv_title.setText("图片");            //设置标题
        initRecyclerView();
        scanLocalImages();                  //扫描本地图片
    }

    /**
     * 初始化图片选择的recyclerview
     */
    private void initRecyclerView() {
        mGridLayoutManager = new GridLayoutManager(this, 3);        //初始化布局管理器
        mPictureAdapter = new PictureAdapter(mImages);
        rv_picture_select.setLayoutManager(mGridLayoutManager);
        rv_picture_select.setAdapter(mPictureAdapter);
    }

    /**
     * 扫描本地图片
     */
    private void scanLocalImages() {
        //利用ContentProvider扫描本地图片
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            ToastUtils.showToast("当前存储卡不可用");
            return;
        } else {
            ThreadManager.getSinglePool().execute(new ScannImageTask());        //开启线程池扫描
        }
    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.rl_back)
    void back() {
        finish();
    }

    @Override
    public void processMsg(Message msg) {
        super.processMsg(msg);

        switch (msg.what) {
            case Constants.IMAGE_SCAN_FINISHED:
                LogUtils.i("扫描完成");
                refreshUI(mImages);
                LoadingDialogUtils.hide();          //隐藏进度条
                break;
            default:
                break;
        }
    }

    private void refreshUI(List<ImageInfo> images) {
        mPictureAdapter.setImages(images);
        mPictureAdapter.notifyDataSetChanged();
    }

    /**
     * 图片扫描任务
     */
    class ScannImageTask implements Runnable {

        @Override
        public void run() {
            Uri imgUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            mContentResolver = getContentResolver();
            Cursor cursor = mContentResolver.query(imgUri, null, MediaStore.Images.Media.MIME_TYPE + "=? or " + MediaStore.Images.Media.MIME_TYPE + "=?",
                    new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);

            while (cursor.moveToNext()) {
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));        //图片路径
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setPath(path);
                mImages.add(imageInfo);
            }
            cursor.close();

            mAHandler.sendEmptyMessage(Constants.IMAGE_SCAN_FINISHED);
        }
    }
}
