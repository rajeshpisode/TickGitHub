package com.piyush.tick.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.piyush.tick.R;
import com.piyush.tick.base.BaseActivity;

/**
 * Created by hp on 7/30/2015.
 */
public class UserActivity extends BaseActivity {
    private ListView mListViewUser;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.user_layout);
        super.onCreate(arg0);
    }

    @Override
    protected void initOthers() {

    }

    @Override
    protected void initViews() {
        mListViewUser = (ListView)findViewById(R.id.list_user);
    }

    @Override
    protected void initMembers() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListeners() {

    }
}
