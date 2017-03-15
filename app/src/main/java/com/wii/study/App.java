package com.wii.study;

import android.app.Application;
import android.util.Log;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.x;

/**
 * Created by wu on 2017/3/13.
 * Xutils 程序入口
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);//初始化XUtils
        x.Ext.setDebug(BuildConfig.DEBUG);//是否输出debug日志
    }
    /**
     * 初始化DaoConfig配置
     */
    public static DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
            //设置数据库名，默认xutils.db
            .setDbName("myapp.db")
            //设置数据库路径，默认存储在app的私有目录
            //.setDbDir(new File("/mnt/sdcard/"))
            //设置数据库的版本号
            .setDbVersion(2)
            //设置数据库打开的监听
            .setDbOpenListener(new DbManager.DbOpenListener() {
                @Override
                public void onDbOpened(DbManager db) {
                    //开启数据库支持多线程操作，提升性能，对写入加速提升巨大
                    db.getDatabase().enableWriteAheadLogging();
                }
            })
            //设置数据库更新的监听
            .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                @Override
                public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                }
            })
            //设置表创建的监听
            .setTableCreateListener(new DbManager.TableCreateListener() {
                @Override
                public void onTableCreated(DbManager db, TableEntity<?> table){
                    Log.i("JAVA", "onTableCreated：" + table.getName());
                }
            });
    //设置是否允许事务，默认true
    //.setAllowTransaction(true)
}
