package com.example.mrwuchao.newone.views;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.activity.WebActivity;
import com.example.mrwuchao.newone.entity.Advertise;

import java.util.ArrayList;
import java.util.List;

/**
 * 广告轮播的组合实现类
 */
public class AdvertiseView implements ViewPager.OnPageChangeListener {

    //图片轮播的组合模板类，创建对象传入参数，调用getView就可以使用，返回viewpager视图
    private List<Advertise> list;
    private RelativeLayout mRelative;
    private Context context;
    private ViewPager viewPager;
    List<ImageView> dotList;
    List<View> viewList;
    public AutoThread autoThread;
    private int currentNum;
    public boolean isAlive = true;
    private boolean auto = true;
    private int currentDot;
    private LinearLayout dotLinear;
    //处理自动轮播的handler
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            if (msg.what == 0x1){
                viewPager.setCurrentItem(currentNum);
                changeDot(currentNum % viewList.size());
            }
            return true;
        }
    });

    public AdvertiseView(List<Advertise> list, Context context) {
        this.list = list;
        this.context = context;
        viewList = new ArrayList<>();
        dotList = new ArrayList<>();
    }
//    private Handler handler1 = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//            if (msg.what == 0x2) {
//                changeDot(msg.arg1);
//            }
//            return true;
//        }
//    });

    private void changeDot(int i) {
        dotList.get(currentDot).setImageResource(R.mipmap.tabmain_dot_icon_unselect);
        dotList.get(i).setImageResource(R.mipmap.tabmain_dot_icon);
        currentDot = i;
    }



    //启动一条线程实现自动轮播
    public void aotuPlay() {
        auto = true;
    }

    public void stopPlay() {
        auto = false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentNum = position;
//        Message me = Message.obtain();
//        me.what = 0x1;
//        me.arg1 = position % viewList.size();
        //为避免自动时多次发送相同信息，判断是否处于自动
        if (!auto)
        handler.sendEmptyMessage(0x1);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:   //拖拽说明认为动作  取消自动
                auto = false;
                break;
            case ViewPager.SCROLL_STATE_IDLE:        //空闲说明可以自动
                auto = true;
                break;
        }
    }


    class AutoThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (isAlive) {
                if (auto) {
                    try {
                        Thread.sleep(3500);
                        currentNum++;
                        handler.sendEmptyMessage(0x1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    //提供方法让fragment获得广告的视图
    public View getView() {
        if (mRelative == null) {
            initView();
        }
        return mRelative;
    }

    //初始化视图，给出viewpager的容器
    private void initView() {
        mRelative = new RelativeLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        mRelative.setLayoutParams(layoutParams);
        initViewPager();
    }

    //初始化ViewPager，获得Viewpager的数据，设置适配器
    private void initViewPager() {
        viewPager = new ViewPager(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        viewPager.setLayoutParams(layoutParams);
        mRelative.addView(viewPager);
        initViewPagerList();
        viewPager.setAdapter(new AdvertiseAdapter());
        viewPager.setOnPageChangeListener(this);
        //创建图片下方的小圆点
        initDot();
        autoThread = new AutoThread();
        autoThread.start();
    }
    //初始化小圆点的布局
    private void initDot() {
        dotLinear = new LinearLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        layoutParams.bottomMargin = 10;
        dotLinear.setOrientation(LinearLayout.HORIZONTAL);
        dotLinear.setLayoutParams(layoutParams);
        createDot();
        mRelative.addView(dotLinear);

    }
    //创建小圆点
    private void createDot() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = 10;
        layoutParams.rightMargin = 10;
        layoutParams.gravity = Gravity.CENTER_VERTICAL;
        for (int i = 0; i < viewList.size(); i++) {
            ImageView imageView = new ImageView(context);
            if (i == 0)
                imageView.setImageResource(R.mipmap.tabmain_dot_icon);
            else
                imageView.setImageResource(R.mipmap.tabmain_dot_icon_unselect);
            imageView.setLayoutParams(layoutParams);
            dotLinear.addView(imageView);
            dotList.add(imageView);
        }
    }

    //获取Viewpager的数据
    private void initViewPagerList() {

        for ( int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(context).load(list.get(i).getFocus_img1()).into(imageView);
            viewList.add(imageView);
        }
        for (int i = 0; i < viewList.size(); i++) {
            ImageView imageView = (ImageView) viewList.get(i);
            imageView.setId(i);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView imageView1 = (ImageView) v;
                    Intent intent = new Intent(context, WebActivity.class);
                    intent.putExtra("path",list.get(imageView1.getId()).getFocus_url());
                    context.startActivity(intent);
                }
            });
        }

    }

    //建立适配器，重写方法，利用模运算，形成循环滑动
    class AdvertiseAdapter extends PagerAdapter {
        int max = Integer.MAX_VALUE;

        @Override
        public int getCount() {
            return max;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View v = viewList.get(position % viewList.size());
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position % viewList.size()));
        }
    }


}
