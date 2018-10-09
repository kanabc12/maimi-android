package org.hxy.platform.android.cart;

import org.hxy.platform.android.common.base.mvp.BaseFragment;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/9 10:56
 * desc   :
 * version: 1.0
 */
public class ShoppingCartFragment extends BaseFragment {

    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_cart;
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
