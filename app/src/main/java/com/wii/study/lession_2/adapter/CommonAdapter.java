package com.wii.study.lession_2.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by wu on 2017/3/6.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 数据源
     */
    protected List<T> listDatas;
    /**
     * Item布局ID
     */
    protected int layoutId;

    public CommonAdapter(Context context, List<T> listDatas, int layoutId) {
        this.mContext = context;
        this.listDatas = listDatas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return listDatas == null ? 0 : listDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return listDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void onDataChange(List<T> data) {
        this.listDatas = data;
        this.notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.getViewHolder(mContext, convertView,
                parent, layoutId);
        fillData(holder, listDatas.get(position));
        return holder.getMConvertView();
    }
    /**
     *
     * 抽象方法，用于子类实现，填充数据
     * @param holder
     * @param t
     */
    protected abstract void fillData(ViewHolder holder, T t);
}
