package org.hxy.platform.android.cart;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.hxy.platform.android.cart.adapter.GoodsShowAdapter;
import org.hxy.platform.android.cart.adapter.ShoppingCartFragmentAdapter;
import org.hxy.platform.android.cart.contact.ShoppingCartContact;
import org.hxy.platform.android.cart.contact.ShoppingCartPresenter;
import org.hxy.platform.android.common.base.mvp.BaseFragment;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.common.bean.CartInfoBean;
import org.hxy.platform.android.common.dao.CartDao;
import org.hxy.platform.android.common.entity.GoodsOrderInfoBean;

import java.util.List;

import butterknife.BindView;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/9 10:56
 * desc   :
 * version: 1.0
 */
public class ShoppingCartFragment extends BaseFragment<ShoppingCartContact.View,ShoppingCartContact.Presenter> implements ShoppingCartContact.View {

    @BindView(R2.id.rv_shopcar)
    RecyclerView mRvShopcar;
    @BindView(R2.id.goods_bottom_checkbox)
    CheckBox mGoodsBottomCheckbox;
    @BindView(R2.id.pay)
    Button mPay;
    @BindView(R2.id.bottom)
    LinearLayout mBottom;
    @BindView(R2.id.total_money)
    TextView mTotalMoney;
    public ShoppingCartFragmentAdapter mShoppingCarFragmentAdapter;
    private boolean mIsCheckedAll = false;
    private GoodsShowAdapter mGoodsShowAdapter;
    private int mMoney;
    private CartInfoBean mCartInfoBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  view = super.onCreateView(inflater, container, savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getActivity().getWindow().setStatusBarColor(0xAA000000);
        }
        mGoodsShowAdapter = new GoodsShowAdapter(getActivity());
        mShoppingCarFragmentAdapter = new ShoppingCartFragmentAdapter(getActivity());
        initView();
        initData();
        mRvShopcar.setAdapter(mShoppingCarFragmentAdapter);
        initListener();
        return view;
    }



    public static ShoppingCartFragment newInstance() {
        return new ShoppingCartFragment();
    }



    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_cart;
    }

    @Override
    public ShoppingCartContact.Presenter createPresenter() {
        return new ShoppingCartPresenter();
    }

    @Override
    public ShoppingCartContact.View createView() {
        return this;
    }

    @Override
    public void init() {

    }

    private void initView() {

    }
    private void initData() {
        List<GoodsOrderInfoBean> goodsOrderInfoBeen = CartDao.queryAll();
        String sku = "";
        for (GoodsOrderInfoBean goodsOrderInfoBean : goodsOrderInfoBeen) {
            sku += goodsOrderInfoBean.toString()+"|";
        }
    }
    private void initListener() {
        mGoodsBottomCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mGoodsBottomCheckbox.setVisibility(View.VISIBLE);
                }else{
                    mGoodsBottomCheckbox.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        List<GoodsOrderInfoBean> goodsOrderInfoBeen = CartDao.queryAll();
        if (goodsOrderInfoBeen.size()==0){
            mCartInfoBean = null;
            mShoppingCarFragmentAdapter.setList(mCartInfoBean);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
