package com.example.mrwuchao.newone.views;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by Mr.wuchao on 2016/11/15.
 */
public class MyDecoration extends RecyclerView.ItemDecoration {
    int mSpace;      //间隔距离

    public MyDecoration(int mSpace) {
        this.mSpace = mSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int count = parent.getAdapter().getItemCount();
        //必须获得adapter的count和position  不能是getChildId
        if (mSpace == 12) {
            outRect.set(0,0,0,mSpace);
            return;
        }
        if (mSpace == 20) {
            outRect.set(0,0,0,mSpace);
        }else if (parent.getChildAdapterPosition(view) != count - 1) {
            outRect.set(0, 0, mSpace, 0);
        }
        else {
            outRect.set(0, 0, 0, 0);
        }
    }
}
