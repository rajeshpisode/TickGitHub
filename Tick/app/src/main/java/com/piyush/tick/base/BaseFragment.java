package com.piyush.tick.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ListView;

public abstract class BaseFragment extends Fragment {
	public BaseFragment() {
		super();
	}

	protected static final String TAG = BaseFragment.class.getSimpleName();

	protected Activity mActivity;
	protected View mView;
	protected FragmentManager mFragmentManager;
	protected ListView lvRechargePlans;
	protected BusinessApplication mApp;
	private ProgressDialog dialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
		mApp = (BusinessApplication) mActivity.getApplication();
		mFragmentManager = getChildFragmentManager();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		init();
	}

	private void init() {
		initViews();
		initMembers();
		initData();
		initListeners();
		initOthers();
	}


	protected abstract void initListeners() ;

	protected abstract void initOthers();

	protected abstract void initViews();

	protected abstract void initMembers();

	protected abstract void initData();
	
	public void wifi(){}
	
	public void battry(){}

	public void print(){}

	public void thumbRecongnition(){}

	public void showDialog(Context activity, String message, String title) {
		dialog = new ProgressDialog(activity);
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setCancelable(false);
		dialog.setCanceledOnTouchOutside(false);
		dialog.show();
	}

	public void dismissDialog() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	public void setProgressMessage(String text) {
		if (dialog != null && dialog.isShowing()) {
			dialog.setMessage(text);
		}
	}

}
