package com.piyush.tick.base;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

public abstract class BaseViewholder {
	public  Activity mActivity;
	public LayoutInflater mLayoutInflater;
	protected View mView;
	protected BusinessApplication mApp;
	
	public BaseViewholder(Activity activity) {
		this.mActivity = activity;
		this.mLayoutInflater = activity.getLayoutInflater();
		mApp = (BusinessApplication) activity.getApplication();
	}
	
	public abstract View getConvertView();
	public abstract void applyData();
	public abstract void initializeData(Object data);	
}
