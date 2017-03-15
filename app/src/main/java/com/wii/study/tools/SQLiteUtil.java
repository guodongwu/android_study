package com.wii.study.tools;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelper是一个辅助类，用来管理数据库的创建和版本他，它提供两个方面的功能
 * 第一，getReadableDatabase()、getWritableDatabase()可以获得SQLiteDatabase对象，通过该对象可以对数据库进行操作
 * 第二，提供了onCreate()、onUpgrade()两个回调函数，允许我们再创建和升级数据库时，进行自己的操作
 */
public class SQLiteUtil extends SQLiteOpenHelper {
    private final static int VERSION = 1;
    private final static String DB_NAME = "phones.db";
    private final static String TABLE_NAME = "phone";
    private final static String CREATE_TBL = "create table phone(_id integer primary key autoincrement, name text, sex text, number text, desc text)";
    private SQLiteDatabase db;
    //SQLiteOpenHelper子类必须要的一个构造函数
    public SQLiteUtil(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //必须通过super 调用父类的构造函数
        super(context, name, factory, version);
    }

    //数据库的构造函数，传递三个参数的
    public SQLiteUtil(Context context, String name, int version){
        this(context, name, null, version);
    }

    //数据库的构造函数，传递一个参数的， 数据库名字和版本号都写死了
    public SQLiteUtil(Context context){
        this(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        System.out.println("create a database");
        this.db = db;
        System.out.println("Create Database");
        db.execSQL(CREATE_TBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("upgrade a database");
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }
    //插入方法
    public void insert(ContentValues values){
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        //插入数据库中
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    //查询方法
    public Cursor query(){
        SQLiteDatabase db = getReadableDatabase();
        //获取Cursor
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null, null);
        return c;

    }

    //根据唯一标识_id  来删除数据
    public void delete(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(id)});
    }


    //更新数据库的内容
    public void update(ContentValues values, String whereClause, String[]whereArgs){
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    //关闭数据库
    public void close(){
        if(db != null){
            db.close();
        }
    }
}
