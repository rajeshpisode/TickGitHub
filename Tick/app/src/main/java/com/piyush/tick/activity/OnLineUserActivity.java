package com.piyush.tick.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.piyush.tick.R;
import com.piyush.tick.adapter.ImageAdapter;
import com.piyush.tick.adapter.UserAdapter;
import com.piyush.tick.base.BaseActivity;

/**
 * Created by hp on 10/6/2015.
 */
public class OnLineUserActivity extends BaseActivity {

    private ListView mListViewUser;
    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.online_user);
        super.onCreate(arg0);
    }

    @Override
    protected void initOthers() {

    }

    @Override
    protected void initViews() {
        mListViewUser = (ListView)findViewById(R.id.lv_online_user);
    }

    @Override
    protected void initMembers() {

    }

    @Override
    protected void initData() {
        mListViewUser.setAdapter(new UserAdapter(this));
    }

    @Override
    protected void initListeners() {

    }
}
