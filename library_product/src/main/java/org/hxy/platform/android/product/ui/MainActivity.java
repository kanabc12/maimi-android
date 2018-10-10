package org.hxy.platform.android.product.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


import org.hxy.platform.android.product.R;
import org.hxy.platform.android.product.adapter.LazyViewpagerAdapter;
import org.hxy.platform.android.product.fragment.BaseLazyFragment;
import org.hxy.platform.android.product.fragment.DetailFragment;
import org.hxy.platform.android.product.fragment.RecommendFragment;
import org.hxy.platform.android.product.views.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private NoScrollViewPager viewPager;
    private String[] titles= new String[]{"详情","评价"};
    private TextView title;
    private LazyViewpagerAdapter viewpagerAdapter;
    public int selectPosition;

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        viewPager.setCurrentItem(selectPosition);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onResume() {
        viewPager.setCurrentItem(selectPosition);
        super.onResume();
    }

    private void initView() {
        title = ((TextView) findViewById(R.id.title));
        tabLayout = ((TabLayout) findViewById(R.id.tabLayout));
        viewPager = ((NoScrollViewPager) findViewById(R.id.viewPager));
        List<BaseLazyFragment> fragmentList = new ArrayList<>();
        fragmentList.add(DetailFragment.newInstance());
        fragmentList.add(RecommendFragment.newInstance());
        viewpagerAdapter = new LazyViewpagerAdapter(getSupportFragmentManager(),fragmentList,titles);
        viewPager.setAdapter(viewpagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void operaTitleBar(boolean scroAble,boolean titleVisiable,boolean tanVisiable){
        viewPager.setNoScroll(scroAble);
        title.setVisibility(titleVisiable? View.VISIBLE:View.GONE);
        tabLayout.setVisibility(tanVisiable? View.VISIBLE:View.GONE);
    }
}
