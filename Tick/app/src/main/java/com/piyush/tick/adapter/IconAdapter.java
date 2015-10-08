package com.piyush.tick.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.piyush.tick.base.BaseListAdapter;
import com.piyush.tick.common.ImageLoader;
import com.piyush.tick.pojo.IconItem;
import com.piyush.tick.viewholders.IconViewHolder;

/**
 * Created by hp on 7/28/2015.
 */
public class IconAdapter extends BaseListAdapter {

    private List<IconItem> list;
    private IconViewHolder mHolder;
    public ImageLoader imageLoader;

    public IconAdapter(Activity context, List<IconItem> list) {
        this.mActivity = context;
        this.list = list;
        imageLoader = new ImageLoader(context.getApplicationContext());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setItemsList(List<IconItem> locations) {
        this.list = locations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mHolder = new IconViewHolder(mActivity);
            convertView = mHolder.getConvertView();
            convertView.setTag(mHolder);
        } else
            mHolder = (IconViewHolder) convertView.getTag();

        mHolder.initializeData(list.get(position));
        mHolder.applyData();
        mHolder.downloadImage(imageLoader, list.get(position).getImgdata());
        return convertView;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setList(List<?> data) {
        this.list = (List<IconItem>) data;
    }


}
