package com.example.huangwenjian.myleanchatdemo1.dialog;

import android.app.Dialog;
import android.content.Context;

import com.example.huangwenjian.myleanchatdemo1.R;

/**
 * Created by Administrator on 2016/8/17 0017.
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context, R.style.CustomDialogStyle);
        setContentView(R.layout.dialog_loading);
    }
}
