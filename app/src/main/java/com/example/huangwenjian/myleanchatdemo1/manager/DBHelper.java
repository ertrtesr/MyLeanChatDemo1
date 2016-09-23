package com.example.huangwenjian.myleanchatdemo1.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.huangwenjian.myleanchatdemo1.conf.ChatConfig;
import com.example.huangwenjian.myleanchatdemo1.utils.UIUtils;

/**
 * 作者: huangwenjian
 * <p/>
 * 描述: 数据库帮助类
 * <p/>
 * 时间: 16/9/21
 */
public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper mInstance;

    private DBHelper() {
        this(UIUtils.getContext(), "chat_" + ChatConfig.self.getClientId() + ".db", null, 1);
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public synchronized static DBHelper getInstance() {
        if (mInstance == null) {
            mInstance = new DBHelper();
        }
        return mInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(clientId TEXT NOT NULL UNIQUE,avatar TEXT)");         //创建用户表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
