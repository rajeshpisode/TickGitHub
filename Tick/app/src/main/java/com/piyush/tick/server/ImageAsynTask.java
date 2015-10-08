package com.piyush.tick.server;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


import org.json.JSONArray;
import org.json.JSONObject;

import com.piyush.tick.common.Common;

/**
 * Created by rajesh on 11/7/15.
 */
public class ImageAsynTask extends AsyncTask<String, Void, String> {


    private ProgressDialog progress;
    private Context mContext;

    public ImageAsynTask(Context context){
        this.mContext = context;
    }
    @Override
    protected void onPreExecute() {
        // TODO Auto-generated method stub
        super.onPreExecute();
        progress = Common.createProgressDialog(mContext);
        progress.show();

    }
    @Override
    protected String doInBackground(String... params) {
        try {
            JSONObject obj = new JSONObject(params[0]);
            JSONArray array = obj.getJSONArray("image");
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj1 = array.getJSONObject(i);
                String name = obj1.getString("image_path").toString();
                // tmp hashmap for single contact

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result) {
        // TODO Auto-generated method stub
        super.onPostExecute(result);
        progress.dismiss();
        try {
//            Intent i = new Intent(mContext, IconActivity.class);
//            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            mContext.startActivity(i);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
