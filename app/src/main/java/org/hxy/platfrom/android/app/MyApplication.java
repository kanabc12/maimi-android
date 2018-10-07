package org.hxy.platfrom.android.app;

import android.app.Application;

import org.hxy.platform.android.common.network.NetWorkManager;


/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/9/29 10:33
 * desc   :
 * version: 1.0
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkManager.getInstance().init();
    }
}
