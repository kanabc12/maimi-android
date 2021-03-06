package org.hxy.platform.android.my;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;

import org.hxy.platform.android.common.base.mvp.BaseFragment;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.common.util.OrderUtil;
import org.hxy.platform.android.common.view.MoreImageView;

import butterknife.BindView;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/8 11:17
 * desc   :
 * version: 1.0
 */
public class PersonFragment extends BaseFragment implements View.OnClickListener {

    private static final String TAG = "PersonFragment";

    private Context mContext;

    @BindView(R2.id.person_order_all_container)
    View allOrderLayout;
    @BindView(R2.id.personal_order_waitpay_layout)
    View waitPayLayout;            //待支付
    @BindView(R2.id.personal_order_waitreceive_layout)
    View waitReceiveLayout;        //待收货
    @BindView(R2.id.personal_order_waitcomment_layout)
    View waitCommentLayout;        //待评价
    @BindView(R2.id.personal_order_returned)
    View waitReturnLayout;        //退换货
    @BindView(R2.id.person_collect_aview)
    View collectLayout;            //收藏
    @BindView(R2.id.person_integrate_rlayout)
    View integrateView;            //积分,余额
    @BindView(R2.id.person_receive_address_aview)
    View receiveAddressView;    //收货地址
    @BindView(R2.id.person_coupon_aview)
    View couponView;            //优惠券
    //View settingsView;			//设置

    @BindView(R2.id.person_balance_txtv)
    TextView balanceTxtv;            //余额
    @BindView(R2.id.person_point_txtv)
    TextView pointTxtv;                //积分
    @BindView(R2.id.person_coupon_txtv)
    TextView couponCountTxtv;        //优惠券数量
    //SPGuessYouLikeView  recommendProductView;

    @BindView(R2.id.nickname_txtv)
    TextView nicknameTxtv;            //昵称

    @BindView(R2.id.header_relayout)
    RelativeLayout header_relayout;
    @BindView(R2.id.head_mimgv)
    MoreImageView nickImage;

    //setting_btn
    @BindView(R2.id.setting_btn)
    Button settingBtn;

    //account_rlayout
    @BindView(R2.id.account_rlayout)
    View accountView;

    //level_img
    @BindView(R2.id.level_img)
    ImageView levelImgv;

    //level_name_txtv
    @BindView(R2.id.level_name_txtv)
    TextView levelName;
    @BindView(R2.id.product_gdv)
    GridView mGridView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        initEvent();
        return view;
    }


    public void initEvent() {

        //addressView.setOnClickListener(this);

        allOrderLayout.setOnClickListener(this);
        waitPayLayout.setOnClickListener(this);
        waitReceiveLayout.setOnClickListener(this);
        waitCommentLayout.setOnClickListener(this);
        waitReturnLayout.setOnClickListener(this);
        collectLayout.setOnClickListener(this);

        integrateView.setOnClickListener(this);
        receiveAddressView.setOnClickListener(this);
        couponView.setOnClickListener(this);
        //settingsView.setOnClickListener(this);

        header_relayout.setOnClickListener(this);
        nickImage.setOnClickListener(this);
        nicknameTxtv.setOnClickListener(this);

        settingBtn.setOnClickListener(this);
        accountView.setOnClickListener(this);

//        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Log.i(TAG, "onItemClick...");
//
//                if (mProducts != null && position >= 0 && position < mProducts.size()) {
//                    SPProduct product = mProducts.get(position);
//                    startupActivity(product.getGoodsID());
//                    Log.i(TAG, "onItemClick product.goodsName :"+product.getGoodsName() );
//                }
//            }
//        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.person_fragment;
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

    @Override
    public void onClick(View view) {
        int result = view.getId();
        ActivityOptionsCompat compat = ActivityOptionsCompat.
                makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        if(result == R.id.person_order_all_container){
            //所有订单
            startupOrderList(OrderUtil.OrderStatus.all.value(),"/my/orderList",compat);
        }else if(result == R.id.personal_order_waitpay_layout){
            //待支付订单
            startupOrderList(OrderUtil.OrderStatus.waitPay.value(),"/my/orderList",compat);
        }else if(result == R.id.personal_order_waitreceive_layout){
            //待收货订单
            startupOrderList(OrderUtil.OrderStatus.waitReceive.value(),"/my/orderList",compat);
        }else if(result == R.id.personal_order_waitcomment_layout){
            //待评论订单
            startupOrderList(OrderUtil.OrderStatus.waitComment.value(),"/my/orderList",compat);
        }else if(result == R.id.personal_order_returned){
            //退款订单
            startupOrderList(OrderUtil.OrderStatus.returned.value(),"/my/orderList",compat);
        }else if(result==R.id.person_collect_aview){
            //我的收藏
            navigate(compat,"/my/collection");
        }else if(result == R.id.person_integrate_rlayout){
            //积分,余额
            navigate(compat,"/my/wallet");
        }else if(result== R.id.person_receive_address_aview){
            //收货地址
            navigate(compat,"/my/address");
        }else if(result == R.id.setting_btn){
            //设置
            navigate(compat,"/my/account");
        }else if(result == R.id.person_coupon_aview){
            //优惠券
            navigate(compat,"/my/coupon");
        }else if(result==R.id.nickname_txtv){
            //登录页
            navigate(compat,"/my/login");
        }
    }

    public void startupOrderList(int orderStatus,String  url,ActivityOptionsCompat compat){

//        if (!SPMobileApplication.getInstance().isLogined){
//            showToastUnLogin();
//            toLoginPage();
//            return;
//        }
        ARouter.getInstance().build(url)
                .withOptionsCompat(compat)
                .withInt("orderStatus",orderStatus)
                .navigation();
    }
    private void navigate(ActivityOptionsCompat compat,String url){
        ARouter.getInstance().build(url)
                .withOptionsCompat(compat)
                .navigation();
    }
    public static PersonFragment newInstance() {
        return new PersonFragment();
    }

}
