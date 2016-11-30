package com.example.mrwuchao.newone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.mrwuchao.newone.R;

import java.util.List;


/**
 * 新鲜页面内嵌的recyclerView的适配器
 */
public class JourneyInnerAdapter extends RecyclerView.Adapter<JourneyInnerAdapter.MyHolder> {
    Context context;
    List<String> imgList;

    public JourneyInnerAdapter(Context context, List<String> imgList) {
        this.context = context;
        this.imgList = imgList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.journey_inner_item_view, parent, false);
        MyHolder myHolder = new MyHolder(view);
//        switch (viewType) {
//            case 0:
//                view = LayoutInflater.from(context).inflate(R.layout.journey_inner_item_view,parent,false);
//                myHolder = new MyHolder(view);
//
//            case 1:
//                view = LayoutInflater.from(context).inflate(R.layout.journey_inner_item_view1,parent,false);
//                myHolder = new MyHolder1(view);
//
//            case 2:
//                view = LayoutInflater.from(context).inflate(R.layout.journey_inner_item_view2,parent,false);
//                myHolder = new MyHolder2(view);
//        }
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
//        switch (getItemViewType(position)) {
//            case 0:
//                MyHolder holder0 = (MyHolder) holder;
//                Log.i("one", "onBindViewHolder: 1张图片调用加载" + " " +imgList.get(position));
//                Glide.with(context).load(imgList.get(position)).placeholder(R.mipmap.icon_loading_end).into(holder0.innerImage);
//                break;
////            case 1:
////                Log.i("one", "onBindViewHolder: 2到4张图片调用加载" + " " +imgList.get(position));
////                MyHolder1 holder1 = (MyHolder1) holder;
////                holder1.innerImage1.setImageResource(R.mipmap.icon_loading_end);
//////                Glide.with(context).load(imgList.get(position)).placeholder(R.mipmap.icon_loading_end).into(holder1.innerImage1);
////                break;
////            case 2:
////                Log.i("one", "onBindViewHolder: 5-9张图片调用加载" + " " +imgList.get(position));
////                MyHolder2 holder2 = (MyHolder2) holder;
////                holder2.innerImage2.setImageResource(R.mipmap.icon_loading_end);
//////                Glide.with(context).load(imgList.get(position)).placeholder(R.mipmap.icon_loading_end).into(holder2.innerImage2);
////                break;
////        }
        int size = imgList.size();
        if (size < 2) {
            holder.innerImageSmall.setVisibility(View.GONE);
            holder.innerImageMiddle.setVisibility(View.GONE);
            holder.innerImageBig.setVisibility(View.VISIBLE);
            Glide.with(context).load(imgList.get(position)).placeholder(R.mipmap.icon_loading_end).into(holder.innerImageBig);
        }
        if (size >= 2 && size < 5) {
            holder.innerImageSmall.setVisibility(View.GONE);
            holder.innerImageMiddle.setVisibility(View.VISIBLE);
            holder.innerImageBig.setVisibility(View.GONE);
            Glide.with(context).load(imgList.get(position)).placeholder(R.mipmap.icon_loading_end).into(holder.innerImageMiddle);
        }
        if (size > 5) {
            holder.innerImageSmall.setVisibility(View.VISIBLE);
            holder.innerImageMiddle.setVisibility(View.GONE);
            holder.innerImageBig.setVisibility(View.GONE);
            Glide.with(context).load(imgList.get(position)).placeholder(R.mipmap.icon_loading_end).into(holder.innerImageSmall);
        }
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView innerImageBig;
        ImageView innerImageMiddle;
        ImageView innerImageSmall;

        public MyHolder(View itemView) {
            super(itemView);
            innerImageBig = (ImageView) itemView.findViewById(R.id.journey_inner_imagebig);
            innerImageMiddle = (ImageView) itemView.findViewById(R.id.journey_inner_imagemiddle);
            innerImageSmall = (ImageView) itemView.findViewById(R.id.journey_inner_imagesmall);
        }
    }
//    class MyHolder1 extends RecyclerView.ViewHolder {
//        ImageView innerImage1;
//        public MyHolder1(View itemView) {
//            super(itemView);
//            innerImage1 = (ImageView) itemView.findViewById(R.id.journey_inner_image1);
//        }
//    }
//    class MyHolder2 extends RecyclerView.ViewHolder {
//        ImageView innerImage2;
//        public MyHolder2(View itemView) {
//            super(itemView);
//            innerImage2 = (ImageView) itemView.findViewById(R.id.journey_inner_image2);
//        }
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        /**一共需要三种视图方式**/
//        if (imgList.size()==1) {
//            //只有一张图
//            return 0;
//        }else if (imgList.size()>1&&imgList.size()<5){
//            //图片大于一张小于5张
//            return 1;
//        }else {
//            return 2;
//        }
//    }
}
