package com.example.mrwuchao.newone.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * 处理handler的基类
 */
public class HandleUtil extends Handler {
    Context context;
    public HandleUtil(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case HandlerBack.ERRORNET:
                Toast.makeText(context,"网络连接异常",Toast.LENGTH_SHORT).show();
                break;
            case HandlerBack.NONET:
                Toast.makeText(context,"当前无网络连接",Toast.LENGTH_SHORT).show();
        }
    }
}
