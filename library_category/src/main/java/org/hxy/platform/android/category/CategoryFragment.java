package org.hxy.platform.android.category;

import android.widget.ListView;

import org.hxy.platform.android.category.contract.CategoryContact;
import org.hxy.platform.android.category.contract.CategroyPresenter;
import org.hxy.platform.android.common.base.mvp.BaseFragment;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.common.entity.CategoryBean;
import org.hxy.platform.android.common.entity.ProductCatagoryBean;

import java.util.List;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/9 10:08
 * desc   :
 * version: 1.0
 */
public class CategoryFragment extends BaseFragment<CategoryContact.View,CategoryContact.Presenter>  implements CategoryContact.View {
    private ListView mLv;

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }
    @Override
    public int getLayoutId() {
        return R.layout.catecary_fragment;
    }

    @Override
    public CategoryContact.Presenter createPresenter() {
        return new CategroyPresenter()  ;
    }

    @Override
    public CategoryContact.View createView() {
        return this;
    }


    @Override
    public void init() {

    }

    @Override
    public void showData(List<CategoryBean.DataBean> list) {

    }

    @Override
    public void showElvData(List<ProductCatagoryBean.DataBean> groupList, List<List<ProductCatagoryBean.DataBean.ListBean>> childList) {

    }

}
