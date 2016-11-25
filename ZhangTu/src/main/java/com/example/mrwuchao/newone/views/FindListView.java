package com.example.mrwuchao.newone.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Mr.wuchao on 2016/11/14.
 */
public class FindListView extends ListView{
    public FindListView(Context context) {
        this(context,null);
    }

    public FindListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int newHeight = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        setMeasuredDimension(widthMeasureSpec,newHeight);
    }
}
