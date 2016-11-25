package com.example.mrwuchao.newone.views;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * recyclerView的GridLayout的间隔
 */
public class MyGridDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;

    public MyGridDecoration(int mSpace) {
        this.mSpace = mSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //发现页面的分类的recyclerView专用  因为margin  不再设置下方的分隔

        if (mSpace == 6) {
            if ((parent.getChildAdapterPosition(view)+1)%3 != 0) {
                 outRect.set(0, 0, mSpace, 0);
            }
        }

        int count = parent.getAdapter().getItemCount();   //获得总数量
        int position = parent.getChildAdapterPosition(view);
        if (count > 1 && count < 5) {
            if ((position + 1) % 2 == 0) {
                outRect.set(0, 0, 0, mSpace);
            } else {
                outRect.set(0, 0, mSpace, mSpace);
            }
        }else{
            if ((position + 1)%3 == 0) {
                outRect.set(0,0,0,mSpace);
            }else{
                outRect.set(0,0,mSpace,mSpace);
            }
        }
//        //横向跨度3个的分隔
//        if (mSpace == 5) {
//            int count = parent.getAdapter().getItemCount();   //获得总数量
//            int position = parent.getChildAdapterPosition(view);
//            if ((position+1)%3 == 0) {
//                //处于边缘
//                if (count <= 6) {
//                    //说明最多就2行 而且处于边缘
//                    if (position <= 2) {
//                        //说明处于第一行  设置下不设置右边
//                        outRect.set(0,0,0,mSpace);
//                    }else{
//                        //处于第二行 右边和下都不设置
//                        outRect.set(0,0,0,0);
//                    }
//                }else{
//                    //元素个数大于6个  说明有3行
//                    if (position < 6) {
//                        //说明处于第一行或第二行 设置下面
//                        outRect.set(0,0,0,mSpace);
//                    }else{
//                        //处于第三行  都不设置
//                        outRect.set(0,0,0,0);
//                    }
//                }
//            }else {
//                //不处于边缘
//                if (count <= 6) {
//                    //说明最多就2行 不处于边缘
//                    if (position <= 2) {
//                        //说明处于第一行  都设置
//                        outRect.set(0,0,mSpace,mSpace);
//                    }else{
//                        //处于第二行 设置右边
//                        outRect.set(0,0,mSpace,0);
//                    }
//                }else{
//                    //元素个数大于6个  说明有3行
//                    if (position < 6) {
//                        //说明处于第一行或第二行 设置下面
//                        outRect.set(0,0,mSpace,mSpace);
//                    }else{
//                        //处于第三行  都不设置
//                        outRect.set(0,0,mSpace,0);
//                    }
//                }
//            }
//
//        }
//        //横向跨度2个的分隔
//        if (mSpace == 7) {
//            int count = parent.getAdapter().getItemCount();
//            int position = parent.getChildAdapterPosition(view);
//            if ((position + 1) % 2 == 0) {
//                //处于边缘
//                if (count < 3) {
//                    //说明只占一行 而且在边缘 都不设置
//                    outRect.set(0, 0, 0, 0);
//                } else {
//                    //2行 不在边缘  设置右边
//                    if (position < 2) {
//                        //第一行的设置  下 右
//                        outRect.set(0,0,0,mSpace);
//                    }else{
//                        //第二行的设置  都不设置
//                        outRect.set(0, 0, 0, 0);
//                    }
//                }
//            }else{
//                //不在边缘  说明右边都需要设置
//                if (count < 3) {
//                    //说明只占一行 而且不在边缘 设置右边
//                    outRect.set(0, 0, mSpace, 0);
//                } else {
//                    if (position < 2) {
//                        //第一行的设置  右下
//                        outRect.set(0,0,mSpace,mSpace);
//                    }
//                    else{
//                        //第二行的设置  右边
//                        outRect.set(0,0,mSpace,0);
//                    }
//                }
//            }
//        }
    }
}
