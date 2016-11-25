package com.example.mrwuchao.newone.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps2d.model.Text;
import com.bumptech.glide.Glide;
import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.activity.MostDetailActivity;
import com.example.mrwuchao.newone.entity.MostDataInfo;
import com.example.mrwuchao.newone.entity.MostInfo;
import com.example.mrwuchao.newone.views.MostRecycler;
import com.example.mrwuchao.newone.views.MyDecoration;
import com.example.mrwuchao.newone.views.MyLinearManager;

import java.util.List;

/**
 * 发现页面的分类界面的适配器
 */
public class MostAdapter extends RecyclerView.Adapter<MostAdapter.MyHolder> {
    List<MostDataInfo> mostInfoList;
    Context context;
    public MostAdapter(MostInfo mostInfo, Context context){
        mostInfoList = mostInfo.getMostDataInfoList();
        this.context = context;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.most_data_item,null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        MostDataInfo mostDataInfo = mostInfoList.get(position);
        Glide.with(context).load(mostDataInfo.getData_img()).placeholder(R.mipmap.icon_loading_end).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MostDetailActivity.class);
                context.startActivity(intent);
            }
        });
        holder.nameText.setText(mostDataInfo.getData_name());
        holder.addrText.setText(mostDataInfo.getData_addr());
        if (mostDataInfo.getRank() == null || mostDataInfo.getRank().equals("")) {
            holder.seeLinear.setVisibility(View.GONE);
        }else{
            holder.seeLinear.setVisibility(View.VISIBLE);
            holder.timeText.setText(Html.fromHtml(mostDataInfo.getRank()));
        }

        if (mostDataInfo.getBest_month() == null || mostDataInfo.getBest_month().equals("")){
            holder.bestLinear.setVisibility(View.GONE);
        }else{
            holder.bestLinear.setVisibility(View.VISIBLE);
            holder.bestText.setText(Html.fromHtml(mostDataInfo.getBest_month()));
        }
        if (mostDataInfo.getDesc() == null || mostDataInfo.getDesc().equals("")){
            holder.miaosuLinear.setVisibility(View.GONE);
        }else{
            holder.miaosuLinear.setVisibility(View.VISIBLE);
            holder.miaoText.setText(Html.fromHtml(mostDataInfo.getDesc()));
        }
        int size = mostDataInfo.getPics().size();
        if (size != 0) {
            holder.recyclerView.setVisibility(View.VISIBLE);
            MostDataAdapter dataAdapter = new MostDataAdapter(mostDataInfo.getPics(),context);
            MyLinearManager linearManager = new MyLinearManager(context, LinearLayoutManager.HORIZONTAL,false);
            if (!holder.recyclerView.getSetted()) {
                holder.recyclerView.addItemDecoration(new MyDecoration(5));
                holder.recyclerView.setSetted();
            }
            holder.recyclerView.setLayoutManager(linearManager);
            holder.recyclerView.setAdapter(dataAdapter);
            //设置横向滑动
        }else{
            holder.recyclerView.setVisibility(View.GONE);
        }
        holder.shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //添加分享功能
            }
        });
    }

    @Override
    public int getItemCount() {
        return mostInfoList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        MostRecycler recyclerView;
        TextView timeText;
        TextView bestText;
        TextView miaoText;
        TextView nameText;
        TextView addrText;
        ImageView shareImage;
        LinearLayout seeLinear,bestLinear,miaosuLinear;
        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.most_data_image);
            recyclerView = (MostRecycler) itemView.findViewById(R.id.most_data_recycler);
            timeText = (TextView) itemView.findViewById(R.id.most_data_see_time_text);
            seeLinear = (LinearLayout) itemView.findViewById(R.id.most_data_see_linear);
            bestText = (TextView) itemView.findViewById(R.id.most_data_best_time_text);
            bestLinear = (LinearLayout) itemView.findViewById(R.id.most_data_best_linear);
            miaoText = (TextView) itemView.findViewById(R.id.most_data_miaosu_text);
            miaosuLinear = (LinearLayout) itemView.findViewById(R.id.most_data_miaosu_linear);
            nameText = (TextView) itemView.findViewById(R.id.most_data_big_name);
            addrText = (TextView) itemView.findViewById(R.id.most_data_small_addr);
            shareImage = (ImageView) itemView.findViewById(R.id.most_share);
        }
    }
}
