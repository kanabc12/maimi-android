package org.hxy.platform.android.category;

import org.hxy.platform.android.common.base.mvp.BaseFragment;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/9 10:08
 * desc   :
 * version: 1.0
 */
public class CategoryFragment extends BaseFragment {
    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }
    @Override
    public int getLayoutId() {
        return R.layout.catecary_fragment;
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