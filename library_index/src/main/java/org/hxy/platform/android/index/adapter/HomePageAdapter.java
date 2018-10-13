package org.hxy.platform.android.index.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import org.hxy.platform.android.index.R;
import org.hxy.platform.android.index.R2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 25505 on 2017/7/30.
 */

public class HomePageAdapter extends PagerAdapter {
    private Context mContext;

    public HomePageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position == 0) {
            View menu1 = LayoutInflater.from(mContext).inflate(R.layout.item_title_menu1, null);
            menu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(mContext, TypeActivity.class);
//                    mContext.startActivity(intent);
                    //todo
                }
            });
            container.addView(menu1);
            return menu1;
        } else {
            View menu2 = LayoutInflater.from(mContext).inflate(R.layout.item_title_menu2, null);
            menu2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"等待开放",Toast.LENGTH_SHORT).show();
                }
            });
            container.addView(menu2);
            return menu2;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    static class ViewHolder {
        @BindView(R2.id.jd_shop)
        TextView mJdShop;
        @BindView(R2.id.global)
        TextView mGlobal;
        @BindView(R2.id.clothing)
        TextView mClothing;
        @BindView(R2.id.seafood)
        TextView mSeafood;
        @BindView(R2.id.jddj)
        TextView mJddj;
        @BindView(R2.id.recharge)
        TextView mRecharge;
        @BindView(R2.id.jingdou)
        TextView mJingdou;
        @BindView(R2.id.zhuanqian)
        TextView mZhuanqian;
        @BindView(R2.id.plus)
        TextView mPlus;
        @BindView(R2.id.ticket)
        TextView mTicket;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
