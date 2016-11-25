package com.example.mrwuchao.newone.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.mrwuchao.newone.inter.HuatiWithScroll;

/**
 * 热门话题详情页面的scrollView
 */
public class HotDetailScrollView extends ScrollView {
    LinearLayout linearLayout;
    int height;  //下半部顶所处高度
    int postion = 0;   //记录之前所处位置 0上半部  1下半部
    int currentPosition = 0;
    public HotDetailScrollView(Context context) {
        this(context,null);
    }

    public HotDetailScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    HuatiWithScroll withScroll;
    public void setWithScroll(HuatiWithScroll withScroll){
        this.withScroll = withScroll;
    }
    public void setLinear(LinearLayout linear){
        linearLayout = linear;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        Log.i("test","布局高度"+linearLayout.getMeasuredHeight()+"滚动位置"+getScrollY());
        if(getScrollY() >= linearLayout.getMeasuredHeight()) {
            currentPosition = 1;
            if (currentPosition != postion) {
                withScroll.moveToBottom(1);
            }
            postion = 1;
        }else{
            currentPosition = 0;
            if (currentPosition != postion) {
                withScroll.moveToBottom(0);
            }
            postion = 0;
        }
    }
}
