package com.example.mrwuchao.newone.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.renderscript.Sampler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ScrollView;

/**
 * 我的页面的scrollView
 */
public class MineScrollView extends ScrollView {
    int oriHeight;
    ImageView imageView;
    int maxHeight;
    private float newHeight;
    private boolean mode;

    public MineScrollView(Context context) {
        super(context);
    }

    public MineScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (deltaY < 0 && isTouchEvent && mode){
            updateHeight(newHeight - deltaY);
            Log.i("huadong",deltaY+"");
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    private void updateHeight(float delteY) {
        newHeight = delteY;
        if (newHeight <= oriHeight*2) {
            imageView.getLayoutParams().height = (int) newHeight;
            imageView.requestLayout();
        }

    }

    public void setNewHeight(int height, int maxHeight,ImageView imageView) {
        oriHeight = height;
        newHeight = oriHeight;
        this.imageView = imageView;
        this.maxHeight = maxHeight;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (getScrollY() <= 0) {
            mode = true;
        }else{
            mode = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(newHeight,oriHeight);
            valueAnimator.setDuration(300);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    updateHeight((Float) animation.getAnimatedValue());
                }
            });
            valueAnimator.start();
            return true;
        }
        return super.onTouchEvent(ev);
    }
}
