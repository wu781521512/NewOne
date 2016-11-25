package com.example.mrwuchao.newone.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.mrwuchao.newone.inter.HuatiWithScroll;

/**
 * most页面的scrollView
 */
public class MostScrollView extends ScrollView {
    RelativeLayout relativeLayout;
    HuatiWithScroll huatiWithScroll;
    int height;  //下半部顶所处高度
    int postion = 0;   //记录之前所处位置 0上半部  1下半部
    int currentPosition = 0;
    public MostScrollView(Context context) {
        this(context,null);
    }

    public MostScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setHuatiWithScroll(HuatiWithScroll huatiWithScroll){
        this.huatiWithScroll = huatiWithScroll;
    }

    public void setLayout(RelativeLayout relativeLayout){
        this.relativeLayout = relativeLayout;
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(getScrollY() >= relativeLayout.getMeasuredHeight()) {
            currentPosition = 1;
            if (currentPosition != postion) {
                huatiWithScroll.moveToBottom(1);
            }
            postion = 1;
        }else{
            currentPosition = 0;
            if (currentPosition != postion) {
                huatiWithScroll.moveToBottom(0);
            }
            postion = 0;
        }
    }
}
