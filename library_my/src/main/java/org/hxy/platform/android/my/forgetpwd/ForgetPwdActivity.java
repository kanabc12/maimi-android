package org.hxy.platform.android.my.forgetpwd;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.hxy.platform.android.common.base.mvp.BaseActivity;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.my.R;

/**
 * Date：2018/9/15 on 16:37
 * Author: kanabc12@126.com
 * Description:
 */
@Route(path = "/my/forgetPwd")
public class ForgetPwdActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {

    }
}
