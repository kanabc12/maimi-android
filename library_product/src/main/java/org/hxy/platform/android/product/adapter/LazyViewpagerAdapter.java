package org.hxy.platform.android.product.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import org.hxy.platform.android.product.fragment.BaseLazyFragment;

import java.util.List;

/**
 * explain:
 * Created by: Zhao
 * Created time: 2016/10/9 15:48
 */

public class LazyViewpagerAdapter extends FragmentPagerAdapter {
    private List<BaseLazyFragment> fragments;
    private String[] titles;

    public LazyViewpagerAdapter(FragmentManager fm, List<BaseLazyFragment> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    @Override
    public BaseLazyFragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments==null?0:fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
