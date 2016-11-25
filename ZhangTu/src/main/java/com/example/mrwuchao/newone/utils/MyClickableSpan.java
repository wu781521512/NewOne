package com.example.mrwuchao.newone.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;

import com.example.mrwuchao.newone.activity.WebActivity;

/**
 * 浏览页面最美。。点击响应
 */
public class MyClickableSpan extends ClickableSpan {
    Context context;
    public MyClickableSpan(Context context) {
        this.context = context;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(Color.parseColor("#0FBBA1"));
    }
    //这边的跳转页面还没写
    @Override
    public void onClick(View widget) {
        Intent intent = new Intent(context, WebActivity.class);
        context.startActivity(intent);
    }
}
