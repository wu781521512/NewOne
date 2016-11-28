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
    private int marLeft;
    private int marTop;
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
        widthList.clear();
        int childCount = getChildCount();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            int left = (int) (Math.random()* (getResources().getDisplayMetrics().widthPixels*2));
            int top = (int) (Math.random()*(getHeight()-100));
            widthList.add(left);
            widthList.add(top);
            Log.i("view","view的宽度"+view.getMeasuredWidth());
            int right = left+view.getMeasuredWidth();
            int bottom = top+view.getMeasuredHeight();
            newMaxWidth = Math.max(currentWidth,right);
            currentWidth = right;
        }
        setMeasuredDimension(newMaxWidth+20,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int right = widthList.get(i*2)+view.getMeasuredWidth();
            int bottom = widthList.get(i*2+1)+view.getMeasuredHeight();
            view.layout(widthList.get(i*2),widthList.get(i*2+1),right,bottom);
        }
    }
}
