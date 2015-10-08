package com.piyush.tick.viewholders;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.piyush.tick.R;

import com.piyush.tick.activity.UserActivity;
import com.piyush.tick.base.BaseViewholder;
import com.piyush.tick.common.ImageLoader;
import com.piyush.tick.pojo.IconItem;

/**
 * Created by hp on 7/28/2015.
 */

public class IconViewHolder extends BaseViewholder implements View.OnClickListener {
    private ImageView ivIcon;
    private TextView tvTitle;
    private IconItem mData;
    private Activity mActivity;

    public IconViewHolder(Activity activity) {
        super(activity);
        this.mActivity = activity;
    }

    @Override
    public View getConvertView() {
        mView = mLayoutInflater.inflate(R.layout.online_user_list_item, null, false);
//        ivIcon = (ImageView) mView.findViewById(R.id.list_image);
//        tvTitle = (TextView) mView.findViewById(R.id.title);
        mView.setOnClickListener(this);
        return mView;
    }

    @Override
    public void applyData() {

        //DisplayImage function from ImageLoader Class

    }
    public void downloadImage(ImageLoader imageLoader, String mImageData){
        imageLoader.DisplayImage(mImageData, ivIcon);
    }

    @Override
    public void initializeData(Object data) {
        this.mData = (IconItem) data;
    }
    @Override
    public void onClick(View v) {
        Intent mIntentMain = new Intent(mActivity, UserActivity.class);
        mActivity.startActivity(mIntentMain);
        mActivity.finish();

    }
}