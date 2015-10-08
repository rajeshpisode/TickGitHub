package com.piyush.tick.syncservices;

import android.content.Context;

import com.piyush.tick.utils.HttpUtils;


/**
 * 
 * This class contains all the calling to server and retrieving from server.
 * this is a singleton class so that same data can be access from everywhere.
 * 
 * @author Ashish.Ba.Sharma
 * 
 */
public class SyncService implements TicTackToeServices{
	private static SyncService singleInstance = null;
	private Context mContext;
	private HttpUtils mHttpUtil;
	
	/**
	 * public static SyncService getInstance() { return getInstance(null); }
	 */

	public static SyncService getInstance(Context context) {
		if (singleInstance == null)
			singleInstance = new SyncService(context);
		return singleInstance;
	}

	private SyncService() {
	}

	private SyncService(Context context) {
		mContext = context;
		mHttpUtil = new HttpUtils(context);
	}


}
