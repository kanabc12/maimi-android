package org.hxy.platform.android.product.event;

/**
 * Created by EtherealPatrick on 2016/10/8.
 */

public class SearchEvent {
    private String mMsg;
    public SearchEvent(String msg) {

        mMsg = msg;
    }
    public String getMsg(){
        return mMsg;
    }
}
