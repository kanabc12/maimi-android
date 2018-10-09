package org.hxy.platform.android.common.config;

/**
 * Created by yc on 2018/7/23.
 */

public class UrlConfig {
    public final static String BASE_HOST = "http://localhost:8888/";
    public final static String BASE_URL = BASE_HOST+"api/";
    public static final String FLEXIBLE_THUMBNAIL = BASE_HOST + "/index.php?m=Api&c=Goods&a=goodsThumImages&width=%d&height=%d&goods_id=%s";

}
