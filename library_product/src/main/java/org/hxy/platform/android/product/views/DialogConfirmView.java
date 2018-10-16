package org.hxy.platform.android.product.views;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;


import org.hxy.platform.android.common.bean.ProductInfoBean;
import org.hxy.platform.android.common.config.UrlConfig;
import org.hxy.platform.android.common.entity.GoodsOrderInfoBean;
import org.hxy.platform.android.product.R;
import org.hxy.platform.android.product.R2;
import org.hxy.platform.android.common.dao.CartDao;
import org.hxy.platform.android.product.ui.ProductDetailActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author BraveheartLinxiNa on 2017/7/31.
 */

public class DialogConfirmView extends Dialog {

    @BindView(R2.id.order_iv_icon)
    ImageView mOrderIvIcon;
    @BindView(R2.id.order_goods_price)
    TextView mOrderGoodsPrice;
    @BindView(R2.id.order_dialog_exit)
    ImageView mOrderDialogExit;
    @BindView(R2.id.order_goods_minus)
    Button mOrderGoodsMinus;
    @BindView(R2.id.order_goods_count)
    EditText mOrderGoodsCount;
    @BindView(R2.id.order_goods_add)
    Button mOrderGoodsAdd;
    @BindView(R2.id.order_goods_confirm)
    Button mOrderGoodsConfirm;
    @BindView(R2.id.limit)
    TextView mLimit;

    @BindView(R2.id.goods_icon_container)
    ImageView mGoodsIconContainer;
    @BindView(R2.id.goods_dimensions_container)
    RadioGroup mGoodsDimensionsContainer;
    @BindView(R2.id.goods_color_container)
    RadioGroup mGoodsColorContainer;

    private String mGoodsCount;
    private int mCount;
    private ProductInfoBean mProductInfoBean;
    private Activity mDetailActivity;
    private String mColor;
    private String mDimen;
    private int mColorId;
    private int mDimenId = -1;
    private boolean isChecked = true;
    private RadioGroup.LayoutParams mRadioParams;
    private int mRadiotextPxSize;
    private GoodsOrderInfoBean mGoodsOrderInfoBean;

    public DialogConfirmView(ProductDetailActivity activity) {
        this(activity, null);
    }

    public DialogConfirmView(Activity activity, ProductInfoBean productInfoBean) {
        super(activity, R.style.ConfirmOrderDialog);
        mDetailActivity = activity;
        mProductInfoBean = productInfoBean;
        //获取window对象
        Window window = getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0); //消除边距
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.BOTTOM | Gravity.CENTER;
        //初始化radiobutton间距以及文字大小
        int endPxSize = activity.getResources().getDimensionPixelSize(R.dimen.dp_15);
        mRadiotextPxSize = activity.getResources().getDimensionPixelSize(R.dimen.dp_5);
        int startPxSize = activity.getResources().getDimensionPixelSize(R.dimen.dp_5);
        mRadioParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,RadioGroup.LayoutParams.WRAP_CONTENT);
        mRadioParams.setMarginEnd(endPxSize);
        mRadioParams.setMarginStart(startPxSize);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_order_confirm);
        ButterKnife.bind(this, this);
        getCurrentCount();
        setData(mProductInfoBean);
        setListener();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mOrderGoodsCount.setText(mCount+"");
    }

    private void setListener() {
        mGoodsDimensionsContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                mDimenId = group.getCheckedRadioButtonId()%5;
                if (group.getCheckedRadioButtonId()%5==0){
                    mDimenId = 5;
                }

                for(int i=0;i<group.getChildCount();i++){
                    View childAt = group.getChildAt(i);
                    if (childAt instanceof RadioButton&&((RadioButton) childAt).isChecked()){
                        childAt.setBackgroundResource(R.drawable.radio_btn_select);
                    }else{
                        childAt.setBackgroundResource(R.drawable.radio_btn_normal);
                    }
                }
            }
        });
        mGoodsColorContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mColorId = group.getCheckedRadioButtonId()%5;
                for(int i=0;i<group.getChildCount();i++){
                    View childAt = group.getChildAt(i);
                    if (childAt instanceof RadioButton&&((RadioButton) childAt).isChecked()){
                        childAt.setBackgroundResource(R.drawable.radio_btn_select);
                    }else{
                        childAt.setBackgroundResource(R.drawable.radio_btn_normal);
                    }
                }

            }
        });
    }

    private void getCurrentCount() {
        //获取当前的数量
        mGoodsCount = mOrderGoodsCount.getText().toString().trim();
        //转换成int数
        mCount = Integer.parseInt(mGoodsCount);
    }

    @OnClick({R2.id.order_goods_confirm, R2.id.order_dialog_exit, R2.id.order_goods_minus, R2.id.order_goods_add})
    public void onViewClicked(View view) {
        int result = view.getId();
        if(result == R.id.order_dialog_exit){
            dismiss();
        }else if(result == R.id.order_goods_minus){
            getCurrentCount();
            if (mCount <= 1) {
                mOrderGoodsMinus.setEnabled(false);
                mOrderGoodsMinus.setFocusable(false);
                return;
            }
            mCount--;
            mOrderGoodsCount.setText(String.valueOf(mCount));
        }else if(result == R.id.order_goods_add){
            mOrderGoodsCount.requestFocus();  //让光标置最后
            getCurrentCount();
            if (mCount >= mProductInfoBean.getProduct().getBuyLimit()) {
                Toast.makeText(getContext(), "亲!限购10件哦!", Toast.LENGTH_SHORT).show();
                return;
            }
            mCount++;
            if (mCount>0){
                mOrderGoodsMinus.setEnabled(true);
                mOrderGoodsMinus.setFocusable(true);
            }
            mOrderGoodsCount.setText(String.valueOf(mCount));
        }else if(result ==R.id.order_goods_confirm){
            //获取用户选择的对象
            getCurrentCount();
            //int count = mCount;
            int goodsId = mProductInfoBean.getProduct().getId();

            if(mDimenId < 1) {
                Toast.makeText(mDetailActivity, "请选择尺码", Toast.LENGTH_SHORT).show();
                return;
            }
            if (mColorId < 1) {
                Toast.makeText(mDetailActivity, "请选择颜色", Toast.LENGTH_SHORT).show();
                return;
            }
            //从数据库获取已有的数据
            GoodsOrderInfoBean goods = CartDao.queryGood(goodsId);
            if (goods !=null){
                mCount += goods.getCount();
            }
            mGoodsOrderInfoBean = new GoodsOrderInfoBean(goodsId,mCount,mColorId+","+mDimenId);
            CartDao.insertCart(mGoodsOrderInfoBean);
            Log.d("luoyou", "dao"+ mGoodsOrderInfoBean.toString());
            dismiss();
        }
    }
    public GoodsOrderInfoBean getGoodsInfo(){
        return mGoodsOrderInfoBean;
    }
    //123456111
    public void setData(ProductInfoBean productInfoBean) {
        ProductInfoBean.ProductBean childBean = productInfoBean.getProduct();
        //图片
        if (childBean.getPics().size()>0){
            Glide.with(getContext()).load(UrlConfig.BASE_URL + childBean.getPics().get(0)).override(Target.SIZE_ORIGINAL,Target.SIZE_ORIGINAL).into(mGoodsIconContainer);
        }
        mOrderGoodsPrice.setText(childBean.getMarketPrice() + "");
        mLimit.setText("(限购 " + childBean.getBuyLimit() + "件)");
        //属性
        List<ProductInfoBean.ProductBean.ProductPropertyBean> property = childBean.getProductProperty();
        for (int i = 0; i < property.size(); i++) {
            //颜色  尺码
            if (i <= 1) {
                RadioButton radioButton = new RadioButton(getContext());
                //RadioBtnView radioButton = new RadioBtnView(getContext());
                radioButton.setPadding(8, 8, 8, 8);
                radioButton.setGravity(Gravity.CENTER);
                radioButton.setText(property.get(i).getV());
                Log.d("luoyou", "V"+property.get(i).getV());
                radioButton.setTextSize(mRadiotextPxSize);
                radioButton.setButtonDrawable(android.R.color.transparent);
                radioButton.setTextColor(getContext().getResources().getColorStateList(R.color.radio_text_selector));
                radioButton.setBackgroundResource(R.drawable.radio_btn_normal);
                mGoodsColorContainer.addView(radioButton,mRadioParams);
            } else {
                RadioButton radioButton = new RadioButton(getContext());
                radioButton.setPadding(8, 8, 8, 8);
                radioButton.setText(property.get(i).getV());
                radioButton.setGravity(Gravity.CENTER);
                radioButton.setTextSize(mRadiotextPxSize);
                radioButton.setButtonDrawable(android.R.color.transparent);
                radioButton.setTextColor(getContext().getResources().getColorStateList(R.color.radio_text_selector));
                radioButton.setBackgroundResource(R.drawable.radio_btn_normal);
                mGoodsDimensionsContainer.addView(radioButton,mRadioParams);
            }
        }
    }

}
