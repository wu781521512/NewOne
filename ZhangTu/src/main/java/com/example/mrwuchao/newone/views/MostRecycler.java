package com.example.mrwuchao.newone.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 *发现页面的分类页面的recyclerView
 */
public class MostRecycler extends RecyclerView {
    boolean isSetted = false;
    public MostRecycler(Context context) {
        this(context,null);
    }

    public MostRecycler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean getSetted() {
        return isSetted;
    }

    public void setSetted(){
        isSetted = true;
    }
}
