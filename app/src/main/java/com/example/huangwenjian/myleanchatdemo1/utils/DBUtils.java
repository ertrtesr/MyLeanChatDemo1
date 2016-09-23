package com.example.huangwenjian.myleanchatdemo1.utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.huangwenjian.myleanchatdemo1.entity.Message;
import com.example.huangwenjian.myleanchatdemo1.manager.DBHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: huangwenjian
 * <p>
 * 描述:
 * <p>
 * 时间: 16/9/23
 */

public class DBUtils {
    public static SQLiteDatabase db;

    /**
     * 插入文字消息到表中
     *
     * @param tableName
     * @param msg       文字消息
     */
    public static void insertMsg(String tableName, Message msg) {
        if (db == null) {
            db = DBHelper.getInstance().getWritableDatabase();
        }
        ContentValues values = new ContentValues();
        values.put("messageId", msg.getMessageId());
        values.put("timeStamp", msg.getTimeStamp());
        values.put("fromWho", msg.getFromWho());
        values.put("type", msg.getType());
        values.put("content", msg.getContent());

        db.insert(tableName, null, values);
    }

    /**
     * 更新消息表中的内容
     *
     * @param tableName 表名(因为每个聊天对象都会生成一张表)
     * @param values
     */
    public static void updateMsg(String tableName, ContentValues values, String whereClause, String[] whereArgs) {
        if (db == null) {
            db = DBHelper.getInstance().getWritableDatabase();
        }

        db.update(tableName, values, whereClause, whereArgs);
    }

    /**
     * 根据条件删除消息表中的条目
     *
     * @param tableName
     * @param whereClause
     * @param whereArgs
     */
    public static void deleteMsg(String tableName, String whereClause, String[] whereArgs) {
        if (db == null) {
            db = DBHelper.getInstance().getWritableDatabase();
        }
        db.delete(tableName, whereClause, whereArgs);
    }

    /**
     * 根据表名查询对应的消息表中的所有数据
     *
     * @param tableName
     * @return
     */
    public static List<Message> queryAllMsgs(String tableName) {
        List<Message> messageList = new ArrayList<>();
        Message msg;
        if (db == null) {
            db = DBHelper.getInstance().getWritableDatabase();
        }
        Cursor cursor = db.rawQuery("select * from " + tableName, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String messageId = cursor.getString(cursor.getColumnIndex("messageId"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                long timeStamp = cursor.getLong(cursor.getColumnIndex("timeStamp"));
                String fromWho = cursor.getString(cursor.getColumnIndex("fromWho"));
                int type = cursor.getInt(cursor.getColumnIndex("type"));
                int isDelete = cursor.getInt(cursor.getColumnIndex("isDelete"));

                msg = new Message(messageId, content, timeStamp, fromWho, type, isDelete);      //创建出消息对象
                messageList.add(msg);           //加入消息列表
            }
            cursor.close();
        }
        return messageList;                     //返回消息列表
    }

    public static void createTable(String tableName) {
        if (db == null) {
            db = DBHelper.getInstance().getWritableDatabase();
        }
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName +
                "(messageId TEXT,content TEXT,timeStamp TEXT,fromWho TEXT,type INTEGER,isDelete INTEGER DEFAULT 0)");
    }
}
