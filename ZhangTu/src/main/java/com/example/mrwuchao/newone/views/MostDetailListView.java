package com.example.mrwuchao.newone.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;


/**
 * most详情页面的景区具体信息的listView
 */
public class MostDetailListView extends ListView{

    public MostDetailListView(Context context) {
        this(context,null);
    }

    public MostDetailListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
