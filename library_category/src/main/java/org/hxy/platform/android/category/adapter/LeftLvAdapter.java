package org.hxy.platform.android.category.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import org.hxy.platform.android.category.R;
import org.hxy.platform.android.common.entity.CategoryBean;

import java.util.List;


/**
 * 作者：邱宇
 * 时间：2017-12-12 14:07
 * 类的用途：
 */

public class LeftLvAdapter extends BaseAdapter {
    private Context context;
    private List<CategoryBean> dataList;

    public LeftLvAdapter(Context context, List<CategoryBean> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.class_left_item,null);
            holder.tv_classname = view.findViewById(R.id.tv_classname);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        CategoryBean dataBean = dataList.get(position);
        holder.tv_classname.setText(dataBean.getName());
        //自己加的属性，用于判断是否选中
        if (dataBean.isChecked()) {
            holder.tv_classname.setBackgroundColor(Color.parseColor("#EEEEEE"));
            holder.tv_classname.setTextColor(Color.parseColor("#FF0000"));
        } else {
            holder.tv_classname.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tv_classname.setTextColor(Color.parseColor("#FF262426"));
        }
        return view;
    }

    class ViewHolder{
        TextView tv_classname;
    }

    public void press(int position){
        //先遍历循环list集合
        for (int i = 0; i < dataList.size(); i++) {
            CategoryBean dataBean = dataList.get(i);
            dataBean.setChecked(false);
        }
        CategoryBean dataBean = dataList.get(position);
        dataBean.setChecked(true);
        notifyDataSetChanged();
    }
}
