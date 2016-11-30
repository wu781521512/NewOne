package com.example.mrwuchao.newone.utils;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * 将Glide加载的图片转换呈圆形图片
 */
public class GlideCircleTranform extends BitmapTransformation {
    public GlideCircleTranform(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool bitmapPool, Bitmap bitmap, int i, int i1) {
        return bitmap;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }

//    private static Bitmap circleFit(BitmapPool bitmapPool, Bitmap bitmap){
//        if (bitmap == null) {
//            return null;
//        }
//        int size = Math.max(bitmap.getWidth(),bitmap.getHeight());
//        int x = (bitmap.getWidth() - size) / 2;
//        int y = (bitmap.getHeight() - size) / 2;
//
//        // TODO this could be acquired from the pool too
//        Bitmap squared = Bitmap.createBitmap(bitmap, x, y, size, size);
//        Bitmap result = bitmapPool.get(size,size, Bitmap.Config.ARGB_4444);
//        if (result == null) {
//            result = Bitmap.createBitmap(size,size, Bitmap.Config.ARGB_4444);
//        }
//        Canvas canvas = new Canvas(result);
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
//        float last = (float) (size*0.5);
//        canvas.drawCircle(last,last,last,paint);
//        return result;
//    }
}
