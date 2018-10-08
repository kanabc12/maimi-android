package org.hxy.platform.android.my.collection;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import org.hxy.platform.android.common.base.mvp.BaseActivity;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.common.view.RecyclerLoadMoreView;
import org.hxy.platform.android.common.view.RecyclerRefreshLayout;
import org.hxy.platform.android.my.R;
import org.hxy.platform.android.my.R2;
import org.hxy.platform.android.my.collection.adapter.CollectionAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
 *  创建者:   tiao
 *  创建时间:  2017/8/3 0003 18:57
 *  描述：    TODO
 */
@Route(path = "/my/collection")
public class CollectionActivity extends BaseActivity {
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.tv_title)
    TextView mTvTitle;
    @BindView(R2.id.rl_container)
    RecyclerLoadMoreView mRlContainer;
    @BindView(R2.id.rl_refresh)
    RecyclerRefreshLayout mRlRefresh;
    private CollectionAdapter mCollectionAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.GRAY);
        }
//        initData();
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        mRlContainer.setLayoutManager(manager);
        mCollectionAdapter = new CollectionAdapter(this);

        mRlContainer.setAdapter(mCollectionAdapter);
//        mCollectionAdapter.setOnRefreshListener(new RecyclerLoadMoreView.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                initData();
//            }
//        });
    }

    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {

    }

    private void initData() {
//        final Observable<NewsProductInfoBean> newsObservable = RetrofitFactory.getInstance().listCollectProduct(PreferenceUtils.getUserId(this), 1, 10);
//        newsObservable.compose(compose(this.<NewsProductInfoBean>bindToLifecycle())).subscribe(new BaseObserver<NewsProductInfoBean>(this) {
//            @Override
//            protected void onHandleSuccess(NewsProductInfoBean newsProductInfoBean) {
//                if (newsProductInfoBean.getProductList() != null) {
//                    mCollectionAdapter.setNewsProductInfoBeen(newsProductInfoBean.getProductList());
//                    mRlContainer.onLoadSuccess();
//
//                }
//            }
//
//            @Override
//            protected void onHandleError(String msg) {
//                super.onHandleError(msg);
//                mRlContainer.onLoadFailure();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                mRlContainer.onLoadFailure();
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
        mRlRefresh.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void OnRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(2000);
                        mRlRefresh.post(new Runnable() {
                            @Override
                            public void run() {
                                mRlRefresh.closeRefresh();
                            }
                        });
                    }
                }).start();
            }
        });
    }

    @OnClick(R2.id.iv_back)
    public void onClick() {
        finish();
    }
}
