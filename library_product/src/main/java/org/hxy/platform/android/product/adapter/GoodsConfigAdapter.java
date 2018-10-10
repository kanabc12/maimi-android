package org.hxy.platform.android.product.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import org.hxy.platform.android.common.entity.GoodsConfigBean;
import org.hxy.platform.android.product.R;

import java.util.List;

/**
 * 商品详情里的规格参数数据适配器
 */
public class GoodsConfigAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<GoodsConfigBean> data;

    public GoodsConfigAdapter(Context context, List<GoodsConfigBean> data) {
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data = data;
    }

    public void setData(List<GoodsConfigBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_config_listview, null);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(position==0){
            holder.itemKey.setBackgroundResource(R.drawable.format_all_bg);
            holder.itemValue.setBackgroundResource(R.drawable.format_right_top_bottom_bg);
        }else{
            holder.itemKey.setBackgroundResource(R.drawable.format_left_right_bottom_bg);
            holder.itemValue.setBackgroundResource(R.drawable.format_right_bottom_bg);
        }
        GoodsConfigBean config = data.get(position);
        holder.itemKey.setText(config.keyProp);
        holder.itemValue.setText(config.value);
        return convertView;
    }

    static class ViewHolder {

        TextView itemKey;
        TextView itemValue;

        public ViewHolder(View view) {
            itemKey = ((TextView) view.findViewById(R.id.item_key));
            itemValue = ((TextView) view.findViewById(R.id.item_value));
            view.setTag(this);
        }
    }

}
