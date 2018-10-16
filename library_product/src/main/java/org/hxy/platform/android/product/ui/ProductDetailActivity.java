package org.hxy.platform.android.product.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.gxz.PagerSlidingTabStrip;

import org.hxy.platform.android.common.base.mvp.BaseActivity;
import org.hxy.platform.android.common.bean.CommentInfoBean;
import org.hxy.platform.android.common.bean.ProductDesInfoBean;
import org.hxy.platform.android.common.bean.ProductInfoBean;
import org.hxy.platform.android.common.entity.GoodsOrderInfoBean;
import org.hxy.platform.android.product.R;
import org.hxy.platform.android.product.R2;
import org.hxy.platform.android.product.adapter.ItemTitlePagerAdapter;
import org.hxy.platform.android.product.contact.ProductContact;
import org.hxy.platform.android.common.dao.CartDao;
import org.hxy.platform.android.product.fragment.GoodsCommentFragment;
import org.hxy.platform.android.product.fragment.GoodsDetailFragment;
import org.hxy.platform.android.product.fragment.GoodsInfoFragment;
import org.hxy.platform.android.product.views.DialogConfirmView;
import org.hxy.platform.android.product.views.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@Route(path = "/product/detail")
public class ProductDetailActivity extends BaseActivity<ProductContact.View, ProductContact.Presenter> implements ProductContact.View {
    @BindView(R2.id.iv_back)
    ImageView mIvBack;
    @BindView(R2.id.ll_back)
    LinearLayout mLlBack;
    @BindView(R2.id.psts_tabs)
    public PagerSlidingTabStrip mPstsTabs;
    @BindView(R2.id.tv_title)
    public TextView mTvTitle;
    @BindView(R2.id.ll_title_root)
    LinearLayout mLlTitleRoot;
    @BindView(R2.id.vp_content)
    public NoScrollViewPager mVpContent;
    @BindView(R2.id.ll_buy_des)
    LinearLayout mLlBuyDes;
    @BindView(R2.id.tv_contact)
    TextView mTvContact;
    @BindView(R2.id.tv_shop)
    TextView mTvShop;
    @BindView(R2.id.tv_concern)
    TextView mTvConcern;
    @BindView(R2.id.iv_concern)
    ImageView mIvConcern;
    @BindView(R2.id.ll_concern)
    LinearLayout mLlConcern;
    @BindView(R2.id.tv_cart)
    TextView mTvCart;
    @BindView(R2.id.tvCount)
    TextView mTvCount;
    @BindView(R2.id.tv_buy)
    TextView mTvBuy;

    private List<Fragment> fragmentList = new ArrayList<>();
    public GoodsInfoFragment goodsInfoFragment;
    public GoodsDetailFragment goodsDetailFragment;
    public GoodsCommentFragment goodsCommentFragment;
    @Autowired
    int mId = 0;
    public ProductInfoBean mProductInfoBean;
    public CommentInfoBean mCommentInfoBean;
    public ProductDesInfoBean mDesInfoBean;
    private ItemTitlePagerAdapter mPagerAdapter;
    private boolean isConcern = true;
    public DialogConfirmView mDialogView;
    private GoodsOrderInfoBean mGoodsInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentList.add(goodsInfoFragment = new GoodsInfoFragment());
        fragmentList.add(goodsDetailFragment = new GoodsDetailFragment());
        fragmentList.add(goodsCommentFragment = new GoodsCommentFragment());
        mPagerAdapter = new ItemTitlePagerAdapter(getSupportFragmentManager(),
                fragmentList, new String[]{"商品", "详情", "评价"});
        mVpContent.setAdapter(mPagerAdapter);
        mVpContent.setOffscreenPageLimit(3);
        mPstsTabs.setViewPager(mVpContent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_product;
    }

    @Override
    public ProductContact.Presenter createPresenter() {
        return null;
    }

    @Override
    public ProductContact.View createView() {
        return this;
    }


    @Override
    public void init() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    //请求网络数据
    private void initData() {
        //请求商品详情
        requestProductDetail();
    }

    private void requestProductDetail() {
//        Observable<ProductInfoBean> productObservable = RetrofitFactory.getInstance().listProductInfo(mId);
//        productObservable.compose(compose(this.<ProductInfoBean>bindToLifecycle())).subscribe(new BaseObserver<ProductInfoBean>(this) {
//            @Override
//            protected void onHandleSuccess(ProductInfoBean productInfoBean) {
//                mProductInfoBean = productInfoBean;
//                //请求商品描述
//                requestProductDesc();
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        int mCount = 0;
        List<GoodsOrderInfoBean> goodsOrderInfoBeen = CartDao.queryAll();
        if (goodsOrderInfoBeen.size() > 0) {
            for (GoodsOrderInfoBean goodsOrderInfoBean : goodsOrderInfoBeen) {
                mCount += goodsOrderInfoBean.getCount();
            }
            mTvCount.setVisibility(View.VISIBLE);
            mTvCount.setText(mCount + "");
        } else {
            mTvCount.setVisibility(View.INVISIBLE);
        }
        Log.d("luoyou", "OnResume");
        //回显选中的属性
        if (mGoodsInfo != null) {
            Log.d("luoyou", "mgoodsinfo" + mGoodsInfo.toString());
            String[] split = mGoodsInfo.getProperty().split(",");
            if (split.length > 1) {
                String colorName = "";
                switch (Integer.parseInt(split[0])) {
                    case 1:
                        colorName = "红色";
                        break;
                    case 2:
                        colorName = "绿色";
                        break;
                }
                String diment = "";
                switch (Integer.parseInt(split[1])) {
                    case 3:
                        diment = "M";
                        break;
                    case 4:
                        diment = "XXL";
                        break;
                    case 5:
                        diment = "XXXL";
                        break;
                    default:
                        diment = "M";
                        break;
                }
                Log.d("luoyou", "已选" + colorName + "," + diment);
                goodsInfoFragment.setSelectedGood(colorName + "/" + diment);
            }
        }

    }

    @Override
    public void setProductInfo(ProductInfoBean productInfo) {
        mProductInfoBean = productInfo;
    }

    @Override
    public void setCommentInfo(CommentInfoBean commentInfoBean) {
        mCommentInfoBean = commentInfoBean;
    }

    @Override
    public void setProductDesInfo(ProductDesInfoBean productDesInfo) {
        mDesInfoBean = productDesInfo;
    }
}
