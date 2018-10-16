package org.hxy.platform.android.product.contact;

import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.common.bean.CommentInfoBean;
import org.hxy.platform.android.common.bean.ProductDesInfoBean;
import org.hxy.platform.android.common.bean.ProductInfoBean;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/15 15:44
 * desc   :
 * version: 1.0
 */
public interface ProductContact {
    interface View extends BaseView {
        void setProductInfo(ProductInfoBean productInfo);
        void setCommentInfo(CommentInfoBean commentInfoBean);
        void setProductDesInfo(ProductDesInfoBean productDesInfo);
    }
    abstract class Presenter extends BasePresenter<View>{
        public abstract  void getProductInfo(int mId);
    }
}
