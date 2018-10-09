package org.hxy.platform.android.common.util;

import java.security.MessageDigest;

/**
 * Date：2018/9/15 on 21:04
 * Author: kanabc12@126.com
 * Description:
 */
public class EncryptUtil {
    private static final String UTF8 = "utf-8";

    public EncryptUtil() {
    }

    public static String md5Digest(String src) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(src.getBytes("utf-8"));
        return byte2HexStr(b);
    }

    private static String byte2HexStr(byte[] b) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < b.length; ++i) {
            String s = Integer.toHexString(b[i] & 255);
            if (s.length() == 1) {
                sb.append("0");
            }

            sb.append(s);
        }

        return sb.toString();
    }
}
