package org.hxy.platform.android.product.contact;


import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;

import java.util.List;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/12 15:12
 * desc   :
 * version: 1.0
 */
public interface ProductSearchContact {
    interface View extends BaseView {
        void setHotSearch(List<String> hotSearchList);
        void setHistorySearch(List<String> historySearch);
    }
    abstract class Presenter extends BasePresenter<View>{
        public abstract void getHotSearchList();
        public abstract void getHistorySearchList();
        public abstract void saveSearchHistory(Object object);
    }
}
