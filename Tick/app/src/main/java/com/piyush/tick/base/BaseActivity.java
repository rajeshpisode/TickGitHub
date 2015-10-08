package com.piyush.tick.base;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {

	private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		init();
	}

	private void init() {
		initViews();
		initMembers();
		initData();
		initListeners();
		initOthers();
	}

	public void hideProgress() {

	}

	public void showProgress(String header, String message) {

	}

	protected abstract void initOthers();

	protected abstract void initViews();

	protected abstract void initMembers();

	protected abstract void initData();

	protected abstract void initListeners();

	public void addFragment(BaseFragment fragment, boolean isAddToBackstack) {
	}

	public void replaceFragment(BaseFragment fragment, boolean isAddToBackstack) {
	}

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
