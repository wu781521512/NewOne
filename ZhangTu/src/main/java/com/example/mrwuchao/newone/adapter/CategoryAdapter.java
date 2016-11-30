package com.example.mrwuchao.newone.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.activity.MostActivity;
import com.example.mrwuchao.newone.entity.FindCategoryInfo;

import java.util.List;

/**
 * 话题区域的适配器
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyHolder> {

    Context context;
    List<FindCategoryInfo> categoryList;
    public CategoryAdapter(Context context,List<FindCategoryInfo> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.category_item,null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        FindCategoryInfo categoryInfo = categoryList.get(position);
        holder.textView.setText(categoryInfo.getSub_name());
        if (categoryInfo.getIs_new().equals("1")) {
            holder.newTextView.setText("New");
            holder.newTextView.setVisibility(View.VISIBLE);
            holder.newImageView.setVisibility(View.VISIBLE);
        }
        else{
            holder.newTextView.setVisibility(View.INVISIBLE);
            holder.newImageView.setVisibility(View.INVISIBLE);
        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MostActivity.class);
                context.startActivity(intent);
            }
        });
        Glide.with(context).load(categoryInfo.getSub_img()).placeholder(R.mipmap.icon_loading_end).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView newTextView;
        ImageView newImageView;
        RelativeLayout relativeLayout;
        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.category_image);
            textView = (TextView) itemView.findViewById(R.id.category_text);
            newTextView = (TextView) itemView.findViewById(R.id.category_new_text);
            newImageView = (ImageView) itemView.findViewById(R.id.category_new_image);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.category_relative);
        }
    }
}
