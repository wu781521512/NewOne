package com.example.mrwuchao.newone.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.activity.HotDetailActivity;
import com.example.mrwuchao.newone.entity.FindHotInfo;

import java.util.List;

/**
 * 发现界面话题部分的适配器
 */
public class HotAdapter extends RecyclerView.Adapter<HotAdapter.MyHolder> {

    Context context;
    List<FindHotInfo> hotList;
    public HotAdapter(Context context,List<FindHotInfo> fengList) {
        this.context = context;
        this.hotList = fengList;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context,R.layout.hot_item,null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        FindHotInfo hotInfo = hotList.get(position);
        Log.i("message",hotInfo.getHuati_img());
        holder.textView.setText(hotInfo.getHuati_title());
        Glide.with(context).load(hotInfo.getHuati_img()).placeholder(R.mipmap.icon_loading_end).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,HotDetailActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hotList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.hot_image);
            textView = (TextView) itemView.findViewById(R.id.hot_text);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
