package org.hxy.platform.android.common.bean;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/9/29 10:56
 * desc   :
 * version: 1.0
 */
public class UserInfoBean {
    /**
     * userid : 153224
     */

    private String userid;

    private String token;

    private int expire;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "userid='" + userid + '\'' +
                '}';
    }
}
