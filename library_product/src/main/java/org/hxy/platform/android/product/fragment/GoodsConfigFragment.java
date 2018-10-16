package org.hxy.platform.android.product.fragment;



import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import org.hxy.platform.android.common.entity.GoodsConfigBean;
import org.hxy.platform.android.product.R;
import org.hxy.platform.android.product.adapter.GoodsConfigAdapter;
import org.hxy.platform.android.product.ui.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 图文详情里的规格参数的Fragment
 */
public class GoodsConfigFragment extends Fragment {

    public ProductDetailActivity activity;
    public ListView lv_config;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (ProductDetailActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_config, null);
        lv_config = view.findViewById(R.id.lv_config);
        lv_config.setFocusable(false);

        List<GoodsConfigBean> data = new ArrayList<>();
        data.add(new GoodsConfigBean("品牌", "LANG SHA/浪莎"));
        data.add(new GoodsConfigBean("型号", "LETV体感-超级内内王"));
        lv_config.setAdapter(new GoodsConfigAdapter(activity, data));
        return view;
    }
}
