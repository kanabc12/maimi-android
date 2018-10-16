package org.hxy.platform.android.cart.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import org.hxy.platform.android.cart.R;
import org.hxy.platform.android.cart.R2;
import org.hxy.platform.android.common.bean.CartInfoBean;
import org.hxy.platform.android.common.config.UrlConfig;
import org.hxy.platform.android.common.dao.CartDao;
import org.hxy.platform.android.common.entity.GoodsOrderInfoBean;
import org.hxy.platform.android.common.event.EventBusBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/*
 *  创建者:   liu89
 *  创建时间:  2017/7/31 16:35
 *  描述：    TODO
 */
public class GoodsShowAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private CartInfoBean mList;
    private CartInfoBean.CartBean.ProductBean mProduct;
    private int mNumber;
    private boolean mIsCheckedAll;
    private CartInfoBean mPayList = mList;

    public GoodsShowAdapter(Context context) {
        mContext = context;
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.goods_show_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setData(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.getCart().size();
        }
        return 0;
    }

    public void setData(CartInfoBean list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void setAllselect(boolean isCheckedAll) {
        mIsCheckedAll = isCheckedAll;

    }


    public int getTotalMoney() {
        int money = 0;
        if (mList!=null){
            if (mList.getCart()!=null) {
                for (int i = 0; i < mList.getCart().size(); i++) {
                    int prodNum = mList.getCart().get(i).getProdNum();
                    int price = mList.getCart().get(i).getProduct().getPrice();
                    money += prodNum * price;

                }
            }
        }
        //mList.setTotalPrice(money);
        return money;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.goods_checkbox)
        CheckBox mGoodsCheckbox;
        @BindView(R2.id.goods_icon)
        ImageView mGoodsIcon;
        @BindView(R2.id.goods_desc)
        TextView mGoodsDesc;
        @BindView(R2.id.goods_color)
        TextView mGoodsColor;
        @BindView(R2.id.goods_price)
        TextView mGoodsPrice;
        @BindView(R2.id.delete_unclick)
        ImageButton mDeleteUnclick;
        @BindView(R2.id.delete_click)
        ImageButton mDeleteClick;
        @BindView(R2.id.goods_num)
        TextView mGoodsNum;
        @BindView(R2.id.goods_add)
        ImageButton mGoodsAdd;
        private View mView;
        private int mTotalCount;
        private int mPosition;



        @OnClick({R2.id.goods_checkbox, R2.id.delete_click, R2.id.goods_add})
        public void onClick(View view) {
            int result = view.getId();
            mNumber = mList.getCart().get(mPosition).getProdNum();
            if(result == R.id.goods_checkbox){

            }else if(result == R.id.delete_click){
                if (!mGoodsCheckbox.isChecked()){
                    mGoodsCheckbox.setChecked(true);
                }
                mNumber--;
                if (mNumber == 1) {
                    mDeleteClick.setVisibility(View.GONE);
                    mDeleteUnclick.setVisibility(View.VISIBLE);
                }

                mList.getCart().get(getAdapterPosition()).setProdNum(mNumber);
                mGoodsNum.setText(mNumber + "");
                GoodsOrderInfoBean goodsOrderInfoBeanMinus = CartDao.queryGood(mProduct.getId());
                goodsOrderInfoBeanMinus.setCount(mNumber);
                CartDao.updateCart(goodsOrderInfoBeanMinus);
                //((MainActivity)mContext).updataCount();
                EventBus.getDefault().post(new EventBusBean("updateCount"));
                int minusPrice = mList.getCart().get(getAdapterPosition()).getProduct().getPrice();
//                ((ShopCartFragment)((MainActivity)mContext).mFragments.get(3)).setminPrice(minusPrice);
            }else if(result == R.id.goods_add){
                if (!mGoodsCheckbox.isChecked()){
                    mGoodsCheckbox.setChecked(true);
                    int firstPrice = (mNumber+1)*mList.getCart().get(getAdapterPosition()).getProduct().getPrice();
//                    ((ShopCartFragment)((MainActivity)mContext).mFragments.get(3)).setFirstPrice(firstPrice);
                }else{
                    int addPrice = mList.getCart().get(getAdapterPosition()).getProduct().getPrice();
//                    ((ShopCartFragment)((MainActivity)mContext).mFragments.get(3)).setPrice(addPrice);
                }
                if (mNumber > 0) {
                    mDeleteClick.setVisibility(View.VISIBLE);
                    mDeleteUnclick.setVisibility(View.GONE);
                }
                mNumber++;
                mList.getCart().get(getAdapterPosition()).setProdNum(mNumber);
                GoodsOrderInfoBean goodsOrderInfoBeanAdd = CartDao.queryGood(mProduct.getId());
                goodsOrderInfoBeanAdd.setCount(mNumber);
                CartDao.updateCart(goodsOrderInfoBeanAdd);
//                ((MainActivity)mContext).updataCount();
                EventBus.getDefault().post(new EventBusBean("updateCount"));
            }
            mGoodsNum.setText(mNumber + "");
        }

        ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);
        }

        public void setData( int position) {
            mPosition = position;

            if (mIsCheckedAll == true) {
                mGoodsCheckbox.setChecked(true);

            } else {
                mGoodsCheckbox.setChecked(false);
            }
            mGoodsCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    int currentCount = Integer.parseInt(mGoodsNum.getText().toString().trim());
                    if (isChecked){
                        int firstPrice = currentCount*mList.getCart().get(getAdapterPosition()).getProduct().getPrice();
                        Log.d("luoyou", "onchecked");
//                        ((ShopCartFragment)((MainActivity)mContext).mFragments.get(3)).setFirstPrice(firstPrice);
                    }else{
                        Log.d("luoyou", "nochecked");
                        int firstPrice = currentCount*mList.getCart().get(getAdapterPosition()).getProduct().getPrice();
//                        ((ShopCartFragment)((MainActivity)mContext).mFragments.get(3)).setFirstPrice(-firstPrice);
                    }
//                    ((ShopCartFragment)((MainActivity)mContext).mFragments.get(3)).isAllselect(getTotalMoney());
                }
            });

            int prodNum = mList.getCart().get(mPosition).getProdNum();
            mGoodsNum.setText(prodNum + "");
            mProduct = mList.getCart().get(mPosition).getProduct();
            mGoodsPrice.setText(mProduct.getPrice() + "");
            mGoodsDesc.setText(mProduct.getName());
            mGoodsColor.setText(mProduct.getProductProperty().get(0).getV()+"/"+mProduct.getProductProperty().get(1).getV());
            Glide.with(mContext.getApplicationContext()).load(UrlConfig.BASE_URL + mProduct.getPic()).into(mGoodsIcon);

            mView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                            .setTitle("是否确定删除")
                            .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    CartInfoBean.CartBean cartBean = mList.getCart().get(mPosition);
                                    CartDao.deleteCart(cartBean.getProduct().getId());
                                    mList.getCart().remove(mPosition);

//                                    ((MainActivity)mContext).updataCount();
                                    EventBus.getDefault().post(new EventBusBean("updateCount"));
                                    notifyDataSetChanged();
//                                    ((ShopCartFragment)((MainActivity)mContext).mFragments.get(2)).mShoppingCarFragmentAdapter.notifyDataSetChanged();

                                }
                            })
                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    builder.show();

                    return false;
                }
            });
            mTotalCount += Integer.parseInt(mGoodsNum.getText().toString().trim());


        }

    }
}
