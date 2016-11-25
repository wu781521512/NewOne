package com.example.mrwuchao.newone.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 *
 */
public class MyRecyclerView extends RecyclerView {
    boolean isSetted = false;
    public MyRecyclerView(Context context) {
        this(context,null);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        return false;
    }
    public boolean getSetted() {
        return isSetted;
    }

    public void setSetted(){
        isSetted = true;
    }
}
