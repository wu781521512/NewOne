package com.example.mrwuchao.newone.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

import com.example.mrwuchao.newone.activity.WebActivity;

/**
 * 查看更多功能的clickspan
 */
public class MoreClickableSpan extends ClickableSpan{
    Context context;
    String path;
    public MoreClickableSpan(Context context,String path) {
        this.context = context;
        this.path = path;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(Color.BLACK);
    }
    //这边的跳转页面还没写
    @Override
    public void onClick(View widget) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("path",path);
        context.startActivity(intent);
    }
}
