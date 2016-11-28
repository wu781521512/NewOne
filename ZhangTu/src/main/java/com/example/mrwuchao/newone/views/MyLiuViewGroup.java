package com.example.mrwuchao.newone.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 风景详情页面航向滑动scrollView内部自定义杂乱排布布局
 */
public class MyLiuViewGroup extends ViewGroup{
    ArrayList<Integer> widthList;
    int heightMeasure;
    int newMaxWidth;
    int currentWidth = 0;
    int newWidth;
    public MyLiuViewGroup(Context context) {
        super(context);
    }

    public MyLiuViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        widthList = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        heightMeasure = heightMeasureSpec;
//        Log.i("hori","自定义的宽"+getMeasuredWidth());

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int left = (int) (Math.random()* (getResources().getDisplayMetrics().widthPixels+100));
            int top = (int) (Math.random()*(getHeight()-100));
            Log.i("position","左边"+left);
            Log.i("position","上方"+top+"   子View宽高" + view.getMeasuredWidth()+"  "+view.getMeasuredHeight());
            int right = left+view.getMeasuredWidth();
            int bottom = top+view.getMeasuredHeight();
            view.layout(left,top,right,bottom);
            newMaxWidth = Math.max(currentWidth,right);
            currentWidth = right;
        }
        newWidth = newMaxWidth;
        setMeasuredDimension(newWidth,heightMeasure);
    }
}
