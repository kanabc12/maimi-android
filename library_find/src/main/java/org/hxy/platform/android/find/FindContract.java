package org.hxy.platform.android.find;


import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.entity.FindsBean;
import org.hxy.platform.android.common.view.BaseView;

/**
 * @author admin
 */

public interface FindContract {
    interface View extends BaseView {
        void setFindData(FindsBean find);
        void setMoreData(FindsBean find);
    }

    abstract class Presenter extends BasePresenter {
       abstract void getFindData();
       abstract void getMoreFindData();
    }

}