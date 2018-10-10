package org.hxy.platform.android.product.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.hxy.platform.android.common.entity.RecommendGoodsBean;
import org.hxy.platform.android.product.R;

import java.util.List;

/**
 * Created by Administrator on 2016/4/6.
 * 地址管理适配器
 */
public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    private List<RecommendGoodsBean> datas;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;
    private Context context;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<RecommendGoodsBean> getDatas() {
        return datas;
    }

    public RecommendAdapter(List<RecommendGoodsBean> list, Context context) {
        this.datas = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public RecommendAdapter(List<RecommendGoodsBean> list, Context context, OnItemClickListener onItemClickListener) {
        this.datas = list;
        this.inflater = LayoutInflater.from(context);
        this.onItemClickListener = onItemClickListener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_recommend, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final RecommendGoodsBean dataBean = datas.get(position);
        //设置数据
        if (dataBean == null) {
            return;
        }
        holder.name.setText(dataBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addAll(List<RecommendGoodsBean> dd) {
        datas.addAll(dd);
        notifyDataSetChanged();
    }

    /**
     * 清楚
     */
    public void clear() {
        datas.clear();
        notifyDataSetChanged();
    }

    /**
     * 删除某条item
     *
     * @param position
     */
    public void removeItem(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            name = ((TextView) itemView.findViewById(R.id.titleId));

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getLayoutPosition(), v);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(int position, View view);
    }

}
