package com.example.mrwuchao.newone.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.example.mrwuchao.newone.R;

/**
 * 各个浏览滑动的界面的用户圆形头像
 */
public class CircleView extends View {
    private Paint paint;
    private Paint mPaint;
    private Bitmap netBitmap;

    public CircleView(Context context) {
        this(context,null);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2);
        paint =  new Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(getCircle(),0,0,null);
    }

    public Bitmap getCircle(){
        Bitmap newBitmap = null;
        int width = getWidth();
        int height = getHeight();
        float radis2 = (float) (width * 0.5);
        Bitmap firstBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_4444);
        Canvas mCanvas = new Canvas(firstBitmap);
        mCanvas.drawCircle(radis2,radis2,radis2,mPaint);
        Matrix matrix = new Matrix();
        float rateX = (float) 0.0;
        float rateY = (float) 0.0;
        if (netBitmap == null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            options.inJustDecodeBounds =false;
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            Bitmap oriBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.icon_personal_login_default,options);
            rateX = (float) (width*1.0/oriBitmap.getWidth());
            rateY = (float) (height*1.0/oriBitmap.getHeight());
            matrix.postScale(rateX,rateY);
            newBitmap = Bitmap.createBitmap(oriBitmap,0,0,oriBitmap.getWidth(),oriBitmap.getHeight(),matrix,true);
        }else{
            rateX = (float) (width*1.0/netBitmap.getWidth());
            rateY = (float) (height*1.0/netBitmap.getHeight());
            matrix.postScale(rateX,rateY);
            newBitmap = Bitmap.createBitmap(netBitmap,0,0,netBitmap.getWidth(),netBitmap.getHeight(),matrix,true);
        }
        mCanvas.drawBitmap(newBitmap,0,0,paint);
        return firstBitmap;
    }
    public void setNetBitmap(Bitmap bitmap){
        netBitmap = bitmap;
        invalidate();
    }
}
