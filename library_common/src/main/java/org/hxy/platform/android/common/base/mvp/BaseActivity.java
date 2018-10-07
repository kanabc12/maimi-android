package org.hxy.platform.android.common.base.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

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
 * 作者：senon on 2017/12/27 10:09
 * 邮箱：a1083911695@163.com
 * 父类->基类->动态指定类型->泛型设计（通过泛型指定动态类型->由子类指定，父类只需要规定范围即可）
 */
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends RxAppCompatActivity {

    //引用V层和P层
    private P presenter;
    private V view;

    protected Unbinder unbinder;

    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (view == null) {
            view = createView();
        }
        if (presenter != null && view != null) {
            presenter.attachView(view);
        }
        init();
    }

    //由子类指定具体类型
    public abstract int getLayoutId();

    public abstract P createPresenter();

    public abstract V createView();

    public abstract void init();

    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();

        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
//
//                        .doOnSubscribe(new Consumer<Disposable>() {
//                            @Override
//                            public void accept(Disposable disposable) throws Exception {
//                                Log.d("luoyou", "doonsubscribe" + Thread.currentThread().getName());
//                                // 可添加网络连接判断等
//                                if (!NetworkUtil.isNetworkAvailable(BaseActivity.this)) {
//                                    Toast.makeText(BaseActivity.this, "网络连接异常，请检查网络", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        })
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .compose(lifecycle);
//
//            }
//        };
//    }

}
