package org.hxy.platfrom.android.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.squareup.leakcanary.LeakCanary;

import org.hxy.platform.android.common.config.CommonConfig;
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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        if(CommonConfig.DEBUG){
            LeakCanary.install(this);
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        Fresco.initialize(this);
    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // 主要是添加下面这句代码
        MultiDex.install(this);
    }
}
