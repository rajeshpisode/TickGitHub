package com.piyush.tick.server;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;



import org.json.JSONException;
import org.json.JSONObject;

import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;
import com.piyush.tick.activity.IconActivity;

import com.piyush.tick.socketInterface.iSocketUrlInterface;
import com.piyush.tick.utils.TicTackToeConstants;

/**
 * Created by rajesh on 10/7/15.
 */
public class SocketConnection implements iSocketUrlInterface
{

    public Context mContext;
    public SocketConnection(Context context){
        this.mContext = context;
    }
    public SocketIO getConnection() {
        SocketIO  mSocketIO = null;
            try {

                  mSocketIO = new SocketIO("http://"+SERVER_IP+":"+SERVER_PORT+"");
                mSocketIO.connect(new IOCallback() {

                    @Override
                    public void onMessage(JSONObject json, IOAcknowledge ack) {
                        try {
                            System.out.println("Server said:" + json.toString(2));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onMessage(String data, IOAcknowledge ack) {
                        System.out.println("Server said: " + data);
                    }

                    @Override
                    public void onError(SocketIOException socketIOException) {
                        System.out.println("an Error occured");
                        socketIOException.printStackTrace();
                    }

                    @Override
                    public void onDisconnect() {
                        System.out.println("Connection terminated.");
                    }

                    @Override
                    public void onConnect() {
                        System.out.println("Connection established");

                    }

                    @Override
                    public void on(String event, IOAcknowledge ack, Object... args) {

                        if (TicTackToeConstants.FUNCTION_IMAGE.equals(event) && args.length > 0) {
                            Log.d("Socket", "" + args[0]);
                            Bundle bundle = new Bundle();
                            bundle.putString("image", "" + args[0]);
                            Message mMsg = new Message();
                            mMsg.setData(bundle);
                            mHandler.sendMessage(mMsg);
                        }
                        System.out.println("Server triggered event2 '" + event + "'");


                    }

                });

            } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return mSocketIO;
    }
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Intent i = new Intent(mContext, IconActivity.class);
            i.putExtra("image","" + msg.getData().getString("image"));
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            mContext.startActivity(i);
        }
    };
}
