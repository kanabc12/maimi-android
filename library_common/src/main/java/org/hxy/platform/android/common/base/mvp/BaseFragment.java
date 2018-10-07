package org.hxy.platform.android.common.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import org.hxy.platform.android.common.util.NetworkUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：senon on 2017/12/27 15:59
 * 邮箱：a1083911695@163.com
 * 父类->基类->动态指定类型->泛型设计（通过泛型指定动态类型->由子类指定，父类只需要规定范围即可）
 */
public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends RxFragment {

    //引用V层和P层
    private P presenter;
    private V view;
    public Context mContext;
    private Unbinder unbinder;

    public P getPresenter() {
        return presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (this.view == null) {
            this.view = createView();
        }
        if (presenter != null && view != null) {
            presenter.attachView(this.view);
        }
        init();
        return view;
    }

    //由子类指定具体类型
    public abstract int getLayoutId();
    public abstract P createPresenter();
    public abstract V createView();
    public abstract void init();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
            presenter.clearDisposable();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
    /**
     * 线程调度
     */
//    protected <T> ObservableTransformer<T, T> compose(final LifecycleTransformer<T> lifecycle) {
//        return new ObservableTransformer<T, T>() {
//            @Override
//            public ObservableSource<T> apply(Observable<T> observable) {
//                return observable
//                        .subscribeOn(Schedulers.io())
//                        .doOnSubscribe(new Consumer<Disposable>() {
//                            @Override
//                            public void accept(Disposable disposable) throws Exception {
//                                // 可添加网络连接判断等
//                                if (!NetworkUtil.isNetworkAvailable(getActivity())) {
//                                    Toast.makeText(getActivity(), "网络连接异常，请检查网络", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        })
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .compose(lifecycle);
//            }
//        };
//    }

}