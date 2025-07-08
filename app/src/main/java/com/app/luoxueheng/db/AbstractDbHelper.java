package com.app.luoxueheng.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.app.luoxueheng.entity.AbstractInfo;

import java.util.ArrayList;
import java.util.List;

public class AbstractDbHelper extends SQLiteOpenHelper {
    private static  AbstractDbHelper sHelper;
    private static final String DB_NAME = "abstract.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public  AbstractDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static AbstractDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new  AbstractDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建collection_table表
        db.execSQL("create table abstract_table(abstract_id integer primary key autoincrement, " +
                "newsID text," +       //新闻ID//用户名
                "Abstract text" +      //密码

                ")");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    /**
     * 添加历史记录
     */
    public int addAbstract(String newsID, String Abstract) {
        //判断是否浏览过历史记录
        if(!isAbstract(newsID)){
            //获取SQLiteDatabase实例
            SQLiteDatabase db = getWritableDatabase();
            ContentValues values = new ContentValues();
            //填充占位符
            values.put("newsID", newsID);
            values.put("Abstract", Abstract);
            String nullColumnHack = "values(null,?,?)";
            //执行
            int insert = (int) db.insert("abstract_table", nullColumnHack, values);
            //db.close();
            return insert;
        }
        return 0;
    }
    /**
     * 判断是否生成过摘要
     */
    @SuppressLint("Range")
    public boolean isAbstract(String newsID) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select Abstract_id,newsID,Abstract  from abstract_table where newsID=?";
        String[] selectionArgs = {newsID};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        //cursor.close();
        //db.close();
        return cursor.moveToNext();
    }

    /**
     * 查询摘要记录
     */
    @SuppressLint("Range")
    public List<AbstractInfo> queryAbstractListData(String newsID) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        List<AbstractInfo > list = new ArrayList<>();
        String sql;
        Cursor cursor = null;
        sql="select Abstract_id,Abstract from abstract_table where newsID=?";
        while (cursor.moveToNext()) {
            int Abstract_id = cursor.getInt(cursor.getColumnIndex("Abstract_id"));
            String Abstract = cursor.getString(cursor.getColumnIndex("Abstract"));
            list.add(new AbstractInfo(Abstract_id, newsID,Abstract));
        }
        cursor.close();
        //db.close();
        return list;
    }
    @SuppressLint("Range")
    public String getAbstractByNewsID(String newsID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT Abstract FROM abstract_table WHERE newsID = ?";

        String[] args = {newsID};
        Cursor cursor = db.rawQuery(query, args);

        String abstractText = null;
        if (cursor.moveToFirst()) {
            abstractText = cursor.getString(cursor.getColumnIndex("Abstract"));
        }
        cursor.close();
        db.close();

        return abstractText;
    }




}
