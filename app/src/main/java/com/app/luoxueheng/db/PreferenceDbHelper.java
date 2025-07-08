package com.app.luoxueheng.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PreferenceDbHelper extends SQLiteOpenHelper {
    private static  PreferenceDbHelper sHelper;
    private static final String DB_NAME = "preference.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public  PreferenceDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static PreferenceDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new  PreferenceDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建collection_table表
        db.execSQL("create table preference_table(preference_id integer primary key autoincrement, " +
                "preferencetype text" +     //新闻ID

                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    /**
     * 添加历史记录
     */
    public int addPreference(String preferencetype) {
        //判断是否浏览过历史记录
        if(!isPreference(preferencetype)){
            //获取SQLiteDatabase实例
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            //填充占位符
            values.put("preferencetype", preferencetype);
//            values.put("username", username);
//            values.put("new_json", new_json);
            String nullColumnHack = "values(null,?)";
            //执行
            int insert = (int) db.insert("preference_table", nullColumnHack, values);
            //db.close();
            return insert;
        }
        return 0;
    }
    /**
     * 根据用户 唯一_id删除用户
     */
    public int deletepreference(String preferencetype) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 执行SQL
        int delete = db.delete("preference_table", " preferencetype=?", new String[]{preferencetype});
        // 关闭数据库连接
        //db.close();
        return delete;
    }
    /**
     * 判断是否浏览过历史记录
     */
    @SuppressLint("Range")
    public boolean isPreference(String preferencetype) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select preferencetype  from preference_table where preferencetype=?";
        String[] selectionArgs = {preferencetype};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        //cursor.close();
        //db.close();
        return cursor.moveToNext();
    }




}
