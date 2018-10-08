package org.hxy.platform.android.my.coupon;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.hxy.platform.android.common.base.mvp.BaseActivity;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.my.R;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/9/28 15:20
 * desc   :
 * version: 1.0
 */
@Route(path = "/my/coupon")
public class CouponListActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_coupon;
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
