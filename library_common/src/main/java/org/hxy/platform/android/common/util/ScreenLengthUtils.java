package org.hxy.platform.android.common.util;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

/**
 * explain:
 * Created by: Zhao
 * Created time: 2016/11/23 9:03
 */

public class ScreenLengthUtils {
    //首页
    public static int getBottomHomeWidth(Context context){
        return (int)(28/375f * (getScreenWidth(context)));
    }
    public static int getBottomHomeHeight(Context context){
        return (int)(28/375f * (getScreenWidth(context)));
    }
    public static int getCustomViewTextSize(Context context, int percentSize){
        return (int)(percentSize/375f * (getScreenWidth(context)));
    }

    /**
     * banner图 品牌图的尺寸 750*340
     * @param context
     * @return
     */
    public static int getBannerHeight(Context context){
        return (int)(340/750f * (getScreenWidth(context)));
    }

    /**
     * 获取店铺信息 店招图片的高度
     * @param context
     * @return
     */
    public static int getShopBackImgHeight(Context context){
        return (int)(300/750f * (getScreenWidth(context)));
    }

    //获取屏幕的宽度
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getWidth();
    }

    //获取屏幕的高度
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        return display.getHeight();
    }

    //我的收藏中删除的宽度
    public static int deleteBtnWidth(Context context){
        return (int)(67/375f * (getScreenWidth(context)));
    }

    public static int getPublishCommentImg(Context context){
        return (int)(84/375f * (getScreenWidth(context)));
    }

}
