package org.hxy.platform.android.product.fragment;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.hxy.platform.android.common.entity.RecommendGoodsBean;
import org.hxy.platform.android.product.R;
import org.hxy.platform.android.product.adapter.RecommendAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Time：2017-12-15 13:18.
 * @Author：zhaoXH.
 * @Description:
 */

public class RecommendFragment extends BaseLazyFragment {
    private RecyclerView recyclerview;
    private RecommendAdapter adapter;

    public static RecommendFragment newInstance(){
        RecommendFragment fragment = new RecommendFragment();
        return fragment;
    }
    @Override
    protected void getBundles() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void initData() {
        List<RecommendGoodsBean> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            RecommendGoodsBean bean = new RecommendGoodsBean();
            bean.setTitle("这是第"+(i+1)+"个人的评论");
            list.add(bean);
        }
        if(adapter!=null){
           adapter.clear();
            adapter.addAll(list);
        }
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_recomment,container,false);
        initRecyclerview(view);
        return view;
    }

    private void initRecyclerview(View view) {
        recyclerview = ((RecyclerView) view.findViewById(R.id.recyclerview));
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setHasFixedSize(true);
        adapter = new RecommendAdapter(new ArrayList<RecommendGoodsBean>(), getContext(), new RecommendAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {

            }
        });
        recyclerview.setAdapter(adapter);
    }
}
