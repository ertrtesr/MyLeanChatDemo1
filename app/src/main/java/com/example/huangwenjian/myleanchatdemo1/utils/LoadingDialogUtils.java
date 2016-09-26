package com.example.huangwenjian.myleanchatdemo1.utils;

import android.app.Dialog;
import android.content.Context;

import com.example.huangwenjian.myleanchatdemo1.dialog.LoadingDialog;

/**
 * Created by huangwenjian on 2016/8/17.
 */
public class LoadingDialogUtils {

    public static Dialog mDialog;

    /**
     * 展示loading对话框
     *
     * @param context
     */
    public static void show(Context context) {
        mDialog = new LoadingDialog(context);
        mDialog.show();
    }

    public static void hide() {
        if (mDialog != null) {
            mDialog.dismiss();
            mDialog = null;
        }
    }
}
