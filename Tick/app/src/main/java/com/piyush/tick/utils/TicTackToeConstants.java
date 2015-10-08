package com.piyush.tick.utils;

import android.graphics.drawable.Drawable;

import io.socket.SocketIO;

/**
 * Created by hp on 7/20/2015.
 */
public class TicTackToeConstants {

    public static final String ERROR_CODE_TIMEOUT = "999";
    public static final String ERROR_CODE_UNKNOWN = "998";
    public static final String ERROR_MSG_TIMEOUT = "Unable to reach server, try again latter.";
    public static final String ERROR_MSG_UNKNOWN = "Something went wrong, try again latter.";
    public static final String PREF_KEY_SSOTOKEN = "ssotoken";
    private static final String BASE_URL = "";
    public static final String URL_AGENT_LOGIN = BASE_URL + "";
    public static final String URL_CUSTOMER_SEARCH = BASE_URL + "";
    public static String Environment ="E2E";
    public static final String CONNECTION_URL = "http://52.25.77.161:9999/";

    public static final String SERVER_IP = "";
    public static final String SERVER_PORT = "";
    public static Drawable USER_IMAGE = null;
    public static String FUNCTION_LOGIN = "login";
    public static String FUNCTION_IMAGE = "getImage";
    public static SocketIO mConstSocketIO = null;
    public static Integer PLAYER1 =1;
    public static Integer PLAYER2 =2;

}
