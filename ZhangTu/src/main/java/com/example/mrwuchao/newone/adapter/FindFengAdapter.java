package com.example.mrwuchao.newone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.entity.FindFengXiang;

import java.util.List;

/**
 * 发现界面的风向标的适配器
 */
public class FindFengAdapter extends RecyclerView.Adapter<FindFengAdapter.MyHolder>{
    Context context;
    List<FindFengXiang> fengList;
    int oriMargin;
    public FindFengAdapter(Context context,List<FindFengXiang> fengList) {
        this.context = context;
        this.fengList = fengList;
        this.setHasStableIds(true);
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.feng_recycler_item,null);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Log.i("posi","onbind" + position);
        FindFengXiang fengXiang = fengList.get(position);
        if (fengXiang.getScenic_name().length()>=9){
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2,-2);
            layoutParams.topMargin = 0;
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            holder.textView.setLayoutParams(layoutParams);
        }else{
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2,-2);
            layoutParams.topMargin = oriMargin;
            layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
            holder.textView.setLayoutParams(layoutParams);
        }
        holder.textView.setText(fengXiang.getScenic_name());
        holder.noTextView.setText("NO."+fengXiang.getTop_num());
        Glide.with(context).load(fengXiang.getScenic_img()).placeholder(R.mipmap.icon_loading_end).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return fengList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView noTextView;
        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.feng_image);
            textView = (TextView) itemView.findViewById(R.id.feng_text);
            noTextView = (TextView) itemView.findViewById(R.id.feng_no_text);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) textView.getLayoutParams();
            oriMargin = params.topMargin;
        }
    }

    @Override
    public long getItemId(int position) {
        Log.i("posi",position+"");
        return position;
    }
}
