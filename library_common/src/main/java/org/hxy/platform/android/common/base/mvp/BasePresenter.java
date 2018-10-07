package org.hxy.platform.android.common.base.mvp;

import org.hxy.platform.android.common.network.schedulers.BaseSchedulerProvider;
import org.hxy.platform.android.common.network.schedulers.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：senon on 2017/12/27 10:10
 * 邮箱：a1083911695@163.com
 */

public abstract class BasePresenter<V extends BaseView>{

    protected CompositeDisposable disposables;
    private V mView;
    protected BaseSchedulerProvider schedulerProvider;

    public BasePresenter() {
        this.schedulerProvider = SchedulerProvider.getInstance();
        disposables = new CompositeDisposable();

    }

    public void addDisposabe(Disposable disposable){
        if(disposables == null){
            disposables = new CompositeDisposable();
        }
        disposables.add(disposable);

    }

    /**
     * 注销观察者，防止泄露
     */
    public void clearDisposable(){
        if(disposables != null){
            disposables.clear();
            disposables = null;
        }
    }


    public V getView(){
        return mView;
    }

    public void attachView(V v){
        mView = v;
    }

    public void detachView(){
        mView = null;
    }


}
