package org.hxy.platform.android.my.login.contract;

import org.hxy.platform.android.common.network.NetWorkManager;
import org.hxy.platform.android.common.network.response.ResponseTransformer;


import io.reactivex.disposables.Disposable;

/**
 * Date：2018/10/7 on 8:31
 * Author: kanabc12@126.com
 * Description:
 */
public class LoginPresenter extends LoginContract.Presenter {

    @Override
    public void login(String mobile, String password) {
        Disposable disposable = NetWorkManager.getRequest().login(mobile, password)
                .compose(ResponseTransformer.handleResult())
                .compose(schedulerProvider.applySchedulers())
                .subscribe(userInfoBean -> {
                    if (getView() != null) {
                        // 处理数据
                        getView().saveLoignInfo(userInfoBean);
                    }
                }, throwable -> {
                    // 处理异常
//                    view.getDataFail();
                });
        addDisposabe(disposable);
    }
}
