package org.hxy.platform.android.common.event;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/16 15:00
 * desc   :
 * version: 1.0
 */
public class EventBusBean {
    private String message;
    public EventBusBean(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
