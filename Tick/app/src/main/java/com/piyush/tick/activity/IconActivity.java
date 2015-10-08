package com.piyush.tick.activity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import com.piyush.tick.R;

import com.piyush.tick.adapter.IconChooserAdapter;
import com.piyush.tick.base.BaseActivity;

/**
 * Created by hp on 7/24/2015.
 */
public class IconActivity extends BaseActivity {
    private GridView mGridViewIcon;
    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.choose_icon);
        super.onCreate(arg0);

    }

    @Override
    protected void initOthers() {

    }

    @Override
    protected void initViews() {
        mGridViewIcon = (GridView)findViewById(R.id.gv_choose_icon);
    }

    @Override
    protected void initMembers() {

    }

    @Override
    protected void initData() {

        mGridViewIcon.setAdapter(new IconChooserAdapter(this));
    }

    @Override
    protected void initListeners() {

    }
}
