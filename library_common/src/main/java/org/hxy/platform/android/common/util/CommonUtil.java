package org.hxy.platform.android.common.util;

import android.content.Context;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Date：2018/9/15 on 21:02
 * Author: kanabc12@126.com
 * Description:
 */
public class CommonUtil {
    public CommonUtil() {
    }
    public static String getThumbnail(String url, String goodsId) {
        return getThumbnail(url, 320, 320, goodsId);
    }

    public static List<Integer> listStringToInt(List<String> spceids) {
        List<Integer> ids = new ArrayList();
        Iterator var3 = spceids.iterator();

        while(var3.hasNext()) {
            String id = (String)var3.next();
            ids.add(Integer.valueOf(id));
        }

        return ids;
    }

    public static boolean verifyArray(List list) {
        return list != null && list.size() >= 1;
    }

    public static String getThumbnail(String url, int width, int height, String goodsId) {
        return String.format(url, width, height, goodsId);
    }

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    public static String getDateShortTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date(time));
    }

    public static String getDateFullTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date(time));
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String signParameter(Map<String, String> params, String time, String signKey, String url) {
        String signStr = null;
        Set<String> keySet = params.keySet();
        List<String> keyList = new ArrayList();
        Iterator var8 = keySet.iterator();

        while(var8.hasNext()) {
            String key = (String)var8.next();
            keyList.add(key);
        }

        Collections.sort(keyList, new Comparator<String>() {
            public int compare(String lhs, String rhs) {
                return lhs.compareTo(rhs);
            }
        });
        List<String> valueList = new ArrayList();
        Iterator var9 = keyList.iterator();

        String firstPart;
        while(var9.hasNext()) {
            firstPart = (String)var9.next();
            String value = ((String)params.get(firstPart)).toString();
            valueList.add(value);
        }

        firstPart = listToString(valueList, "");
        String signBefore = firstPart + time + signKey;

        try {
            signStr = EncryptUtil.md5Digest(signBefore);
        } catch (Exception var11) {
            var11.printStackTrace();
            signStr = "";
        }

        return signStr;
    }

    public static String listToString(List list, String separator) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < list.size(); ++i) {
            sb.append(list.get(i)).append(separator);
        }

        return separator.equals("") ? sb.toString() : sb.toString().substring(0, sb.toString().length() - 1);
    }

}
