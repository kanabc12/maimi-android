package org.hxy.platform.android.index;

import org.hxy.platform.android.common.base.mvp.BaseFragment;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/9 9:43
 * desc   :
 * version: 1.0
 */

public class MainHomeFragment extends BaseFragment {

    public static MainHomeFragment newInstance() {
        return new MainHomeFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
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
