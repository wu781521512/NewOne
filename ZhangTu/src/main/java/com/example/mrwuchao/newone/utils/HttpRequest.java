package com.example.mrwuchao.newone.utils;

import android.content.Context;
import android.os.Handler;

/**
 * Http请求类
 */
public class HttpRequest {
    Context context;
    String url;
    Handler handler;
    int back;

    public HttpRequest(Context context, String url, Handler handler,int back) {
        this.context = context;
        this.url = url;
        this.handler = handler;
        this.back = back;
    }

    public void startRequest() {
        if (NetWorkUtil.forNetWork(context)) {
            HttpUtil httpUtil = new HttpUtil(context);
            httpUtil.jsonRequest(url,handler,back);
        } else {
            //没有网络
            handler.sendEmptyMessage(HandlerBack.NONET);
        }
    }
}
