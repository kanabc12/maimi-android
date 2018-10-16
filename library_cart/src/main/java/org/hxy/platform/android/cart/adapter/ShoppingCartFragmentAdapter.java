package org.hxy.platform.android.cart.adapter;

import android.content.Context;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;

import org.hxy.platform.android.cart.R;
import org.hxy.platform.android.cart.R2;
import org.hxy.platform.android.common.bean.CartInfoBean;
import org.hxy.platform.android.common.bean.RecommendInfoBean;
import org.hxy.platform.android.common.config.UrlConfig;
import org.hxy.platform.android.common.util.PreferenceUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *  创建者:   liu89
 *  创建时间:  2017/7/30 16:20
 *  描述：    TODO
 */
public class ShoppingCartFragmentAdapter extends RecyclerView.Adapter {

    private static final int TYPE_TITLE = 0;
    private static final int TYPE_GLIDE = 1;


    private Context mContext;
    private List<RecommendInfoBean.ProductListBean> mData;
    public GoodsShowAdapter mGoodsShowAdapter;


    public ShoppingCartFragmentAdapter(Context context) {
        mContext = context;

        notifyDataSetChanged();
    }

    public CartInfoBean mList;

    public void setList(CartInfoBean list) {
        mList = list;

        notifyDataSetChanged();
    }

    public void setData(List<RecommendInfoBean.ProductListBean> data) {
        mData = data;
        Log.d("data", "mdata==============" + mData.toString());

        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        Log.d("shopcar", "得到数目");
        if (mData != null) {
            Log.d("data", "mData.getListCount()===============" + mData.size());
            return mData.size();
        }
        Log.d("data", "得到一个0");
        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        Log.d("shopcar", "调用getitemviewtype");
        if (position == 0) {
            return TYPE_TITLE;
        } else {
            return TYPE_GLIDE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("shopcar", "创建viewholder");
        switch (viewType) {
            case TYPE_TITLE:
                View titleView = View.inflate(mContext, R.layout.shop_car_title, null);
                ViewHolder viewHolder = new ViewHolder(titleView);
                Log.d("shopcar", "创建头布局");
                return viewHolder;
            case TYPE_GLIDE:
                View grideView = View.inflate(mContext, R.layout.shop_car_item, null);

                ItemViewHolder itemViewHolder = new ItemViewHolder(grideView);
                Log.d("shopcar", "创建其他布局");
                return itemViewHolder;
            default:
                Log.d("shopcar", "出现了第三种情况");
                return null;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("shopcar", "调用onbindviewholder");
        int viewType = getItemViewType(position);
        switch (viewType) {
            case TYPE_TITLE:
                ViewHolder viewHolder = (ViewHolder) holder;
                viewHolder.setData();
                break;
            case TYPE_GLIDE:
                ItemViewHolder grideViewHolder = (ItemViewHolder) holder;
                grideViewHolder.setData(position);
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.login)
        Button mLogin;
        @BindView(R2.id.sencond_kill)
        Button mSencondKill;
        @BindView(R2.id.check)
        Button mCheck;
        @BindView(R2.id.goods_show)
        RecyclerView mGoodsShow;
        @BindView(R2.id.visible_table)
        LinearLayout mVisibleTable;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            mLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了login");
                    navigate(v, "/my/login");
                }
            });
            mSencondKill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了SencondKill");
                    navigate(v, "/index/secKill");
                }
            });
            mCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了mCheck");
                    if (PreferenceUtil.getUserId(mContext).length() != 0) {
                        navigate(v, "/my/login");
                    }
                    navigate(v, "/my/collection");
                }
            });
        }

        public void showSeckill() {
            mVisibleTable.setVisibility(View.VISIBLE);
        }

        public void hideSeckill() {
            mVisibleTable.setVisibility(View.GONE);
        }

        public void setData() {
            if (mList != null) {
                mGoodsShow.setVisibility(View.VISIBLE);
                mVisibleTable.setVisibility(View.GONE);

            } else {
                mGoodsShow.setVisibility(View.GONE);
                mVisibleTable.setVisibility(View.VISIBLE);
            }
            mGoodsShowAdapter = new GoodsShowAdapter(mContext);
            mGoodsShowAdapter.setData(mList);
            mGoodsShow.setLayoutManager(new LinearLayoutManager(mContext));
            mGoodsShow.setAdapter(mGoodsShowAdapter);
        }
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.goaway)
        ImageView mGoaway;
        @BindView(R2.id.add)
        ImageView mAdd;
        @BindView(R2.id.item_src)
        ImageView mItemSrc;
        @BindView(R2.id.item_desc)
        TextView mItemDesc;
        @BindView(R2.id.item_price)
        TextView mItemPrice;
        @BindView(R2.id.goods_left)
        RelativeLayout mGoodsLeft;

        ItemViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);

            mAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了add");
                }
            });

            mGoaway.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了goaway");
                    mData.remove(getLayoutPosition());
                    notifyDataSetChanged();
                }
            });

            mGoodsLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("shopcar", "点击了进入详情");
                }
            });

        }

        public void setData(int position) {

            RecommendInfoBean.ProductListBean productList = mData.get(position);
            mItemDesc.setText(productList.getName());
            mItemPrice.setText(productList.getPrice() + "");
            Glide.with(mContext.getApplicationContext()).load(UrlConfig.BASE_URL + productList.getPic()).into(mItemSrc);
        }
    }

    private void navigate(View view, String url) {
        ActivityOptionsCompat compat = ActivityOptionsCompat.
                makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0);
        ARouter.getInstance().build(url)
                .withOptionsCompat(compat)
                .navigation();
    }


}

