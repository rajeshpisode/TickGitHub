package com.piyush.tick.syncservices;

import android.content.Context;


/**
 * 
 * This class contains all the calling to offline data and retrieving from
 * server. this is a singleton class so that same data can be access from
 * everywhere.
 * 
 * @author Ashish.Ba.Sharma
 * 
 */
public class OfflineService implements TicTackToeServices {
	private Context context;

	private static OfflineService singleInstance = null;

	public static OfflineService getInstance(Context context) {
		if (singleInstance == null)
			singleInstance = new OfflineService(context);
		return singleInstance;
	}

	private OfflineService(Context context) {
		this.context = context;
	}

	private OfflineService() {
	}



}
