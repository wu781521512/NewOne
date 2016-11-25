package com.example.mrwuchao.newone.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.LruCache;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;
import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.activity.WebActivity;
import com.example.mrwuchao.newone.entity.JourneyItemInfo;
import com.example.mrwuchao.newone.utils.GlideCircleTranform;
import com.example.mrwuchao.newone.utils.MoreClickableSpan;
import com.example.mrwuchao.newone.utils.MyClickableSpan;
import com.example.mrwuchao.newone.utils.TimeUtil;
import com.example.mrwuchao.newone.views.CircleView;
import com.example.mrwuchao.newone.views.MyGridDecoration;
import com.example.mrwuchao.newone.views.MyGridManager;
import com.example.mrwuchao.newone.views.MyRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 新鲜页面的适配器
 */
public class JourneyAdapter extends RecyclerView.Adapter<JourneyAdapter.MyHolder>{
    Context context;
    List<JourneyItemInfo> itemList;
    final int MAXTEXT = 110;   //最大文字显示数量
    private View view;
//    List<Bitmap> bitmapList = new ArrayList<>();
//    LruCache<String,Bitmap> lru = new LruCache<String,Bitmap>((int) (Runtime.getRuntime().maxMemory()/8)) {
//        @Override
//        protected int sizeOf(String key, Bitmap value) {
//            return value.getByteCount();
//        }
//    };

    public JourneyAdapter(Context context,List<JourneyItemInfo> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = View.inflate(context, R.layout.journey_item_view,null);
        view = LayoutInflater.from(context).inflate(R.layout.journey_item_view,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        final JourneyItemInfo itemInfo = itemList.get(position);
//        Glide.with(context).load(itemInfo.getAvatar()).asBitmap().into(new SimpleTarget<Bitmap>(){
//            @Override
//            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
//                lru.put(String.valueOf(position),bitmap);
//                holder.touxiangImg.setNetBitmap(bitmap);
//            }
//
//            @Override
//            public void onDestroy() {
//                super.onDestroy();
//            }
//        });
        Glide.with(context).load(itemInfo.getAvatar()).asBitmap().into(new ViewTarget<CircleView,Bitmap>(holder.touxiangImg) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                this.view.setNetBitmap(resource);
            }
        });
        //为头像图片设置点击 跳转到用户的详情
        holder.touxiangImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (itemInfo.getGender().equals("0")) {
            holder.sexImg.setVisibility(View.INVISIBLE);
        }else if (itemInfo.getGender().equals("1")) {
            holder.sexImg.setImageResource(R.mipmap.icon_man);
        }else {
            holder.sexImg.setImageResource(R.mipmap.icon_female);
        }
        MyGridDecoration gridDecoration = null;
        MyGridManager gridManager = null;
        int picCount = Integer.parseInt(itemInfo.getPic_cnt());
        if (picCount == 1) {
            Log.i("diaoyong","1张图片加载");
            gridManager = new MyGridManager(context,1);
        }
        if (picCount > 1 && picCount <5){
            Log.i("diaoyong","1到5张图片加载");
            gridManager = new MyGridManager(context,2);
        }
        if (picCount >= 5) {
            gridManager = new MyGridManager(context,3);
        }
        //只有当目标的recyclerView没有设置过分隔线才创建
        if (picCount != 1 && !holder.recyclerView.getSetted()) {
            gridDecoration = new MyGridDecoration(5);
            holder.recyclerView.addItemDecoration(gridDecoration);
            //将分隔线设置状态设为true
            holder.recyclerView.setSetted();
        }

        holder.recyclerView.setLayoutManager(gridManager);
        holder.recyclerView.setAdapter(new JourneyInnerAdapter(context,itemInfo.getPictureList()));
        holder.topLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,WebActivity.class);
                intent.putExtra("path",itemInfo.getShare_url());
                context.startActivity(intent);
            }
        });

        holder.nameText.setText(itemInfo.getUsername());
        //时间应该计算  可以写工具类或方法
        holder.timeText.setText(TimeUtil.decodeTime(itemInfo.getTime()));
        //判断定位信息是否存在
        if (itemInfo.getAddress()==null || itemInfo.getAddress().equals("")) {
            holder.locText.setVisibility(View.GONE);
        }else{
            holder.locText.setText(itemInfo.getAddress());
            holder.locText.setVisibility(View.VISIBLE);
        }

        //为定位显示的问题设置点击事件
        holder.locText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.locText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,position+"",Toast.LENGTH_SHORT).show();
            }
        });
        if (itemInfo.getContent() == null || itemInfo.getContent().equals("")) {
            holder.contentText.setVisibility(View.GONE);
        }else{

            //将获得到的文字提出到sb中  供后面的操作统一使用 不然操作不同 效果不统一
            StringBuilder commonSb = new StringBuilder(itemInfo.getContent());
            holder.contentText.setVisibility(View.VISIBLE);
            //避免复用发生文字残留，先清空缓存的字
            holder.contentText.setText("");
            /*先判断字数是否大于最大显示字数*/
            if (itemInfo.getContent().length() >= MAXTEXT) {
                StringBuilder stringBuilder = new StringBuilder(itemInfo.getContent());
                String more = "...[查看全部]";
                SpannableString spanMore = new SpannableString(more);
                //裁剪掉多出的文字
                stringBuilder.delete(MAXTEXT,itemInfo.getContent().length());
                MoreClickableSpan myClickableSpan = new MoreClickableSpan(context,itemInfo.getShare_url());
                spanMore.setSpan(myClickableSpan,0,spanMore.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                //判断有没有剩余的#字符
                if (itemInfo.getContent().contains("#")){
                    //有#  找到字符串  先裁剪后组合成原样
                    int first = stringBuilder.indexOf("#");
                    int last = stringBuilder.lastIndexOf("#");
                    String subString = stringBuilder.substring(first,last+1);
                    SpannableString spanString = new SpannableString(subString);
                    MyClickableSpan myClickableSpan1 = new MyClickableSpan(context);
                    spanString.setSpan(myClickableSpan1, 0, subString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    //找到#标记的字符串后  取出来做特殊处理 从原数据中删除
                    stringBuilder.delete(first,last+1);
                    //判断可点击的字在头出现还是在尾部出现  重新构建位置
                    if (first == 0) {
                        holder.contentText.append(spanString);
                        holder.contentText.append(stringBuilder.toString());
                    }else{
                        holder.contentText.append(stringBuilder.toString());
                        holder.contentText.append(spanString);
                    }
                }else{
                    //不包含#  吧裁剪后的stringBuilder添加
                    holder.contentText.append(stringBuilder.toString());
                }
                holder.contentText.append(spanMore);
                holder.contentText.setMovementMethod(LinkMovementMethod.getInstance());
            }else{
                //字数小于最大限制的  就光考虑是否有#
                if (!itemInfo.getContent().contains("#")) {
                    //没有# 直接放进去
                    holder.contentText.setText(itemInfo.getContent());
                }else{
                    //有#
                    StringBuilder sb = new StringBuilder(itemInfo.getContent());
                    int first = sb.indexOf("#");
                    int last = sb.lastIndexOf("#");
                    String subString = sb.substring(first,last+1);
                    SpannableString spanString = new SpannableString(subString);
                    MyClickableSpan myClickableSpan1 = new MyClickableSpan(context);
                    spanString.setSpan(myClickableSpan1, 0, subString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    sb.delete(first,last+1);

                    //判断可点击的字在头出现还是在尾部出现  重新构建位置
                    if (first == 0) {
                        holder.contentText.append(spanString);
                        holder.contentText.append(sb.toString());
                    }else{
                        holder.contentText.append(sb.toString());
                        holder.contentText.append(spanString);
                    }
                    holder.contentText.setMovementMethod(LinkMovementMethod.getInstance());
                }
            }
        }

        //判断带小飞机的来自...是否存在
        if (itemInfo.getTrip_name() == null || itemInfo.getTrip_name().equals("")) {
            holder.fromLinear.setVisibility(View.GONE);
        }else{
            holder.fromText.setText("来自 ["+itemInfo.getTrip_name()+"] 的行程");
            holder.fromLinear.setVisibility(View.VISIBLE);
        }

        //为来自XXX的行程设置点击
        holder.fromLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (itemInfo.getTagList().size() < 1) {
            holder.tagLinear.setVisibility(View.GONE);
        }else{
            holder.tagLinear.setVisibility(View.VISIBLE);
            holder.tagLinear.removeAllViews();
            int tagSize = itemInfo.getTagList().size();
            if (tagSize!=0) {
                for (int i = 0; i < tagSize; i++) {
                    TextView text = new TextView(context);
                    LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(-2,-2);
                    layout.gravity = Gravity.CENTER_VERTICAL;
                    layout.rightMargin = 20;
                    text.setLayoutParams(layout);
                    text.setText(itemInfo.getTagList().get(i).getTag_name());
                    text.setBackgroundResource(R.color.qinhui);
                    text.setPadding(10,5,10,5);
                    holder.tagLinear.addView(text);
                }
            }
        }

        holder.likeCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int cnt = Integer.parseInt(itemInfo.getLike_cnt());
                if (isChecked) {
                    itemInfo.setLike(true);
                    itemInfo.setLike_cnt(String.valueOf(cnt+1));
                }else{
                    itemInfo.setLike(false);
                    itemInfo.setLike_cnt(String.valueOf(cnt-1));
                }
//                notifyDataSetChanged();
            }
        });
        holder.likeCheck.setText(itemInfo.getLike_cnt());
        holder.likeCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentLike = Integer.parseInt(itemInfo.getLike_cnt());
                if (holder.likeCheck.isChecked()) {
                    //将元数据的赞数减1
                    currentLike--;
                }else{
                    currentLike++;
                }
                itemInfo.setLike_cnt(currentLike+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        CircleView touxiangImg;
        ImageView sexImg;
        TextView nameText,timeText;
        MyRecyclerView recyclerView;
        TextView locText,contentText,fromText;
        CheckBox likeCheck;
        LinearLayout fromLinear,tagLinear,topLinear;

        public MyHolder(View itemView) {
            super(itemView);
            touxiangImg = (CircleView) itemView.findViewById(R.id.journey_touxiang);
            sexImg = (ImageView) itemView.findViewById(R.id.journey_sex);
            nameText = (TextView) itemView.findViewById(R.id.journey_user_name);
            timeText = (TextView) itemView.findViewById(R.id.journey_time);
            recyclerView = (MyRecyclerView) itemView.findViewById(R.id.journey_inner_recycler);
            locText = (TextView) itemView.findViewById(R.id.journey_location);
            contentText = (TextView) itemView.findViewById(R.id.journey_content_text);
            fromText = (TextView) itemView.findViewById(R.id.journey_from_text);
            likeCheck = (CheckBox) itemView.findViewById(R.id.journey_zan_check);
            fromLinear = (LinearLayout) itemView.findViewById(R.id.journey_from_linear);
            tagLinear = (LinearLayout) itemView.findViewById(R.id.journey_tag_linear);
//            recyclerLinear = (LinearLayout) itemView.findViewById(R.id.journey_recycler_linear);
//            pinLinear = (LinearLayout) itemView.findViewById(R.id.journey_pinlun_linear);
            topLinear = (LinearLayout) itemView.findViewById(R.id.journey_top_linear);
        }
    }
}
