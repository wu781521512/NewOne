package com.example.mrwuchao.newone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.entity.FindCategoryInfo;
import com.example.mrwuchao.newone.entity.FindJustGoInfo;

import java.util.List;

/**
 * Created by Mr.wuchao on 2016/11/15.
 */
public class JustGoAdapter extends RecyclerView.Adapter<JustGoAdapter.MyHolder> {

    Context context;
    List<FindJustGoInfo> justGoList;
    public JustGoAdapter(Context context,List<FindJustGoInfo> justGoList) {
        this.context = context;
        this.justGoList = justGoList;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.just_go_item,null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        FindJustGoInfo justGoInfo = justGoList.get(position);
        holder.textView.setText(justGoInfo.getScenic_name());
        Glide.with(context).load(justGoInfo.getScenic_img()).placeholder(R.mipmap.icon_loading_end).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return justGoList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.just_go_image);
            textView = (TextView) itemView.findViewById(R.id.just_go_text);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
