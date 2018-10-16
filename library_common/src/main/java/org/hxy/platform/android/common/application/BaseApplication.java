package org.hxy.platform.android.common.application;


import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import org.hxy.platform.android.common.greendao.DaoMaster;
import org.hxy.platform.android.common.greendao.DaoSession;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/16 9:34
 * desc   :
 * version: 1.0
 */
public class BaseApplication extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
    }

    private void setupDatabase() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
}
