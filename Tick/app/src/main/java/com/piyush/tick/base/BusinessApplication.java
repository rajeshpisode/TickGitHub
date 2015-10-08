package com.piyush.tick.base;


import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;

import com.piyush.tick.utils.NetworkUtils;


public class BusinessApplication extends Application {

	private static Context context;
	private int screenWidth, screenHeight, mContainerHeight;
	private int mHeaderHeight = 20;
	private boolean isNetworkAvailable;


	public int getmHeaderHeight() {
		return mHeaderHeight;
	}

	public void setmHeaderHeight(int mHeaderHeight) {
		this.mHeaderHeight = mHeaderHeight;
	}

	public int getmContainerHeight() {
		return mContainerHeight;
	}

	public void setmContainerHeight(int mContainerHeight) {
		this.mContainerHeight = mContainerHeight;
	}

	public boolean isNetworkAvailable() {
		return isNetworkAvailable;
	}

	public void setNetworkAvailable(boolean isNetworkAvailable) {
		this.isNetworkAvailable = isNetworkAvailable;
	}



	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context = getApplicationContext();
		calculateScreenSize();
		intializeStatus();
	}

	private void intializeStatus() {
		// TODO Auto-generated method stub
		isNetworkAvailable = NetworkUtils
				.isNetworkAvailable(getApplicationContext());

	}

	private void calculateScreenSize() {
		// TODO Auto-generated method stub
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		screenWidth = metrics.widthPixels;
		screenHeight = metrics.heightPixels;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}

	public int getAvailableScreenHeight() {
		return screenHeight - mHeaderHeight;
	}

}