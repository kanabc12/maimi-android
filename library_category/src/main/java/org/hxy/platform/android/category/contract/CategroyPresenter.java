package org.hxy.platform.android.category.contract;


import org.hxy.platform.android.common.entity.ProductCatagoryBean;
import org.hxy.platform.android.common.network.NetWorkManager;
import org.hxy.platform.android.common.network.response.ResponseTransformer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 作者：邱宇
 * 时间：2017-12-13 09:18
 * 类的用途：
 */

public class CategroyPresenter extends CategoryContact.Presenter {

    public void getCatagroy() {
        Disposable disposable = NetWorkManager.getRequest().getCatagroy()
                .compose(ResponseTransformer.handleResult())
                .compose(schedulerProvider.applySchedulers())
                .subscribe(categoryBeans -> {
                    getView().showData(categoryBeans);
                    //拿到右侧第一条数据
                    int cid = categoryBeans.get(0).getCid();
                    getProductCatagory(cid + "");
                }, throwable -> {
                    //
                });
        addDisposabe(disposable);
    }

    public void getProductCatagory(String cid) {
        Disposable disposable = NetWorkManager.getRequest().getProductCatagory(cid)
                .compose(ResponseTransformer.handleResult())
                .compose(schedulerProvider.applySchedulers())
                .subscribe(productCatagoryBeans -> {
                    List<List<ProductCatagoryBean.ListBean>> childList = new ArrayList<>();
                    //遍历添加数据
                    for (int i = 0; i < productCatagoryBeans.size(); i++) {
                        List<ProductCatagoryBean.ListBean> list = productCatagoryBeans.get(i).getList();
                        childList.add(list);
                    }
                    //给二级列表设置数据
                    getView().showElvData(productCatagoryBeans, childList);
                }, throwable -> {

                });
        addDisposabe(disposable);
    }
}
