package org.hxy.platform.android.index;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;

import org.hxy.platform.android.common.base.mvp.BaseFragment;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.common.entity.NewsProductInfoBean;
import org.hxy.platform.android.common.view.RecyclerLoadMoreView;
import org.hxy.platform.android.common.view.RecyclerRefreshLayout;
import org.hxy.platform.android.index.adapter.HomeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import zxing.activity.CaptureActivity;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/9 9:43
 * desc   :
 * version: 1.0
 */

public class MainHomeFragment extends BaseFragment {

    private static final String TAG = "MainHomeFragment";

    @BindView(R2.id.ib_sweep)
    TextView mIbSweep;
    @BindView(R2.id.et_search)
    EditText mEtSearch;
    @BindView(R2.id.tv_news)
    TextView mTvMsg;
    @BindView(R2.id.top_container)
    LinearLayout mTopContainer;
    @BindView(R2.id.rv_home)
    RecyclerLoadMoreView mRvHome;
    @BindView(R2.id.refresh)
    RecyclerRefreshLayout mRefresh;

    private View mHomeView;
    private HomeAdapter mHomeRvAdapter;
    //上拉透明效果变量
    private int mSumY = 0;
    private float mDistance = 200;  //控制透明度在距离150px的变化
    private int startValue = 0x50436EEE;
    private int endValue = 0xff436EEE;
    private int bgColor;
    private ArgbEvaluator mEvaluator;
    private HomeAdapter mHomeAdapter;
    private List<NewsProductInfoBean.ProductListBean> mProductList;

    public static MainHomeFragment newInstance() {
        return new MainHomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  super.onCreateView(inflater, container, savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(0xAA000000);
        }
        statusBarAplaChange();
        initListener();
        return view;
    }

    private void initListener() {
        mRefresh.setOnRefreshListener(new RecyclerRefreshLayout.OnRefreshListener() {
            @Override
            public void OnRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        mRefresh.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mRefresh.closeRefresh();
                            }
                        },2000);
                    }
                }).start();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
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
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position > 1 ? 1 : 2;
            }
        });
        mRvHome.setLayoutManager(manager);

        mHomeRvAdapter = new HomeAdapter(getActivity());
//        mHomeRvAdapter = new HomeRvAdapter(getActivity());
        mRvHome.setAdapter(mHomeRvAdapter);
        mEvaluator = new ArgbEvaluator();



        mTvMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate(v,"/app/main");
            }
        });

        mEtSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigate(v,"/product/search");
            }
        });
    }

    //设置标题栏渐变沉浸效果
    private void statusBarAplaChange() {
        //监听recyclerview移动距离来设置透明度
        mRvHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mSumY += dy;
                if (mSumY <= 0) {
                    bgColor = startValue;
                } else if (mSumY >= mDistance) {
                    bgColor = endValue;
                } else {
                    bgColor = (int) mEvaluator.evaluate(mSumY / mDistance, startValue, endValue);
                }
                mTopContainer.setBackgroundColor(bgColor);
            }
        });
    }
    private void navigate(View view,String url){
        ActivityOptionsCompat compat = ActivityOptionsCompat.
                makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        ARouter.getInstance().build(url)
                .withOptionsCompat(compat)
                .navigation();
    }

    @OnClick(R2.id.ib_sweep)
    public void onViewClicked() {
        startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            String goodsId = data.getStringExtra("GoodsId");
            Log.d(TAG, "onActivityResult: " + requestCode + "|" + resultCode + "|" + goodsId);
            if (resultCode == -1) {
                ARouter.getInstance().build("/product/detail")
                        .withInt("id",Integer.valueOf(goodsId))
                        .getDestination();
            }
        }
    }
}
