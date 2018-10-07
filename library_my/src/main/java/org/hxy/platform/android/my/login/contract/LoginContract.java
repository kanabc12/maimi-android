package org.hxy.platform.android.my.login.contract;

import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.common.bean.UserInfoBean;

import java.util.HashMap;

/**
 * Date：2018/10/7 on 8:06
 * Author: kanabc12@126.com
 * Description:
 */
public interface LoginContract {
    interface View extends BaseView {
        String getMobile();
        String getPassword();
        void saveLoignInfo(UserInfoBean userInfoBean);
    }
    abstract class Presenter extends BasePresenter<View> {
        public abstract void login(String mobile,String password);
    }
}
