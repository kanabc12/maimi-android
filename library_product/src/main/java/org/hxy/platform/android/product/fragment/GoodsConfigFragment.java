package org.hxy.platform.android.product.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.hxy.platform.android.common.entity.GoodsConfigBean;
import org.hxy.platform.android.product.R;
import org.hxy.platform.android.product.adapter.GoodsConfigAdapter;
import org.hxy.platform.android.product.views.ItemListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文详情里的规格参数的Fragment
 */
public class GoodsConfigFragment extends Fragment {

    ItemListView listView;

    public Activity activity=null;

    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.activity = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_config, null);
        listView = ((ItemListView) view.findViewById(R.id.listView));
        init();
        return view;
    }

    private void init() {
        listView.setFocusable(false);
        List<GoodsConfigBean> data = new ArrayList<>();
        data.add(new GoodsConfigBean("商品名称", "华为荣耀V10"));
        data.add(new GoodsConfigBean("系统", "安卓"));
        data.add(new GoodsConfigBean("商品产地", "中国大陆"));
        data.add(new GoodsConfigBean("运行内存", "6GB"));
        data.add(new GoodsConfigBean("前置摄像头像素", "800万-1599万"));
        data.add(new GoodsConfigBean("热点", "快速充电，Type-C，后置双摄像头"));
        data.add(new GoodsConfigBean("电池容量", "3000mAh-3999mAh"));
        data.add(new GoodsConfigBean("机身内存", "机身内存"));
        data.add(new GoodsConfigBean("颜色", "蓝色"));
        listView.setAdapter(new GoodsConfigAdapter(activity, data));
    }
}
