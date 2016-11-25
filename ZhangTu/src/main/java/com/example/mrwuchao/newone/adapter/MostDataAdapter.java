package com.example.mrwuchao.newone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mrwuchao.newone.R;

import java.util.List;

/**
 * 发现页面分类界面的内嵌recyclerView的适配器
 */
public class MostDataAdapter extends RecyclerView.Adapter<MostDataAdapter.MyHolder>{
    List<String> picList;
    Context context;
    public MostDataAdapter(List<String> pics, Context context) {
        picList = pics;
        this.context =context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.most_data_inner_recycler,null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Glide.with(context).load(picList.get(position)).placeholder(R.mipmap.icon_loading_end).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return picList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.most_data_inner_image);
        }
    }
}
