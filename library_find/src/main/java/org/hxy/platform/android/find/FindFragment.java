package org.hxy.platform.android.find;

import org.hxy.platform.android.common.base.mvp.BaseFragment;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/9 9:40
 * desc   :
 * version: 1.0
 */
public class FindFragment extends BaseFragment {
    public static FindFragment newInstance() {
        return new FindFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_find;
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
