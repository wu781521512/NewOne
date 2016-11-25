package com.example.mrwuchao.newone.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 判断当前网络状况的工具类
 */
public class NetWorkUtil {
    public static boolean  forNetWork(Context context){
        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = conn.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifi != null) {
            if(wifi.getState().equals(NetworkInfo.State.CONNECTED)) {
                return true;
            }
        }
        NetworkInfo gprs = conn.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (gprs != null) {
            if (gprs.getState().equals(NetworkInfo.State.CONNECTED)) {
                return true;
            }
        }
        return false;
    }
}
