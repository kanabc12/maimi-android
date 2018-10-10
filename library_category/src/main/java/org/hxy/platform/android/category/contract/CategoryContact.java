package org.hxy.platform.android.category.contract;

import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.common.entity.CategoryBean;
import org.hxy.platform.android.common.entity.ProductCatagoryBean;


import java.util.List;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/10 11:16
 * desc   :
 * version: 1.0
 */
public interface CategoryContact {
    interface View  extends BaseView {
        void showData(List<CategoryBean> list);
        void showElvData(List<ProductCatagoryBean> groupList, List<List<ProductCatagoryBean.ListBean>> childList);
    }
    abstract class Presenter extends BasePresenter<View> {
        abstract void getCatagroy();
        abstract void getProductCatagory(String cid);
    }
}
