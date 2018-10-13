package org.hxy.platform.android.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import org.hxy.platform.android.category.adapter.LeftLvAdapter;
import org.hxy.platform.android.category.adapter.RightClassAdapter;
import org.hxy.platform.android.category.contract.CategoryContact;
import org.hxy.platform.android.category.contract.CategroyPresenter;
import org.hxy.platform.android.common.base.mvp.BaseFragment;
import org.hxy.platform.android.common.base.mvp.BasePresenter;
import org.hxy.platform.android.common.base.mvp.BaseView;
import org.hxy.platform.android.common.entity.CategoryBean;
import org.hxy.platform.android.common.entity.ProductCatagoryBean;
import org.hxy.platform.android.common.util.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author : 胡幸园
 * e-mail : kanabc12@163.com
 * date   : 2018/10/9 10:08
 * desc   :
 * version: 1.0
 */
public class CategoryFragment extends BaseFragment<CategoryContact.View, CategoryContact.Presenter> implements CategoryContact.View {
    @BindView(R2.id.lv)
    ListView mLv;
    LeftLvAdapter lvAdapter;
    @BindView(R2.id.right_banner)
    Banner mBanner;
    @BindView(R2.id.elv)
    ExpandableListView mElv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        getPresenter().getCatagroy();
        //给左边的条目设置点击事件
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lvAdapter.press(position);
                //加载对应的右侧数据
                //先获取cid
                CategoryBean dataBean = (CategoryBean) parent.getItemAtPosition(position);
                int cid = dataBean.getCid();
                getPresenter().getProductCatagory(cid + "");
            }
        });
        return view;
    }

    public static CategoryFragment newInstance() {
        return new CategoryFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.catecary_fragment;
    }

    @Override
    public CategoryContact.Presenter createPresenter() {
        return new CategroyPresenter();
    }

    @Override
    public CategoryContact.View createView() {
        return this;
    }


    @Override
    public void init() {
        /**
         * 添加Banner轮播
         */
        List<String> images = new ArrayList<>();
        images.add("http://img1.imgtn.bdimg.com/it/u=594559231,2167829292&fm=27&gp=0.jpg");
        images.add("http://pic4.nipic.com/20091217/3885730_124701000519_2.jpg");
        images.add("http://pic.58pic.com/58pic/13/74/51/99d58PIC6vm_1024.jpg");
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)//设置banner样式
                .setImageLoader(new GlideImageLoader())//设置图片加载器
                .setImages(images)//设置图片集合
                .setBannerAnimation(Transformer.DepthPage)//设置banner动画效果
                .isAutoPlay(true)//设置自动轮播，默认为true
                .setDelayTime(1500)//设置轮播时间
                .setIndicatorGravity(BannerConfig.CENTER)//设置指示器位置（当banner模式中有指示器时）
                .start();//banner设置方法全部调用完毕时最后调用
    }

    /**
     * 展示左边的分类
     *
     * @param list
     */
    @Override
    public void showData(List<CategoryBean> list) {
        lvAdapter = new LeftLvAdapter(getActivity(), list);
        mLv.setAdapter(lvAdapter);
    }

    @Override
    public void showElvData(List<ProductCatagoryBean> groupList, List<List<ProductCatagoryBean.ListBean>> childList) {
        RightClassAdapter classAdapter = new RightClassAdapter(getActivity(), groupList, childList);
        mElv.setAdapter(classAdapter);
        //设置默认全部展开
        for (int i = 0; i < childList.size(); i++) {
            mElv.expandGroup(i);
        }
    }
}
