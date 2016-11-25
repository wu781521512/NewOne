package com.example.mrwuchao.newone.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.adapter.JourneyAdapter;
import com.example.mrwuchao.newone.entity.HotDetailTopInfo;
import com.example.mrwuchao.newone.entity.JourneyItemInfo;
import com.example.mrwuchao.newone.inter.HuatiWithScroll;
import com.example.mrwuchao.newone.utils.HandleUtil;
import com.example.mrwuchao.newone.utils.HandlerBack;
import com.example.mrwuchao.newone.utils.HttpRequest;
import com.example.mrwuchao.newone.utils.HttpUrl;
import com.example.mrwuchao.newone.utils.JsonUtil;
import com.example.mrwuchao.newone.views.HotDetailScrollView;
import com.example.mrwuchao.newone.views.MyDecoration;
import com.example.mrwuchao.newone.views.MyLinearManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HotDetailActivity extends NotitleActivity implements View.OnClickListener {

    @BindView(R.id.hot_detail_title)
    TextView title;
    @BindView(R.id.hot_detail_canyu)
    TextView hotDetailCc;
    @BindView(R.id.hot_detail_liulan)
    TextView liulan;
    @BindView(R.id.hot_detail_faqi)
    TextView faqi;
    @BindView(R.id.hot_detail_summary)
    TextView summary;
    @BindView(R.id.hot_detail_more)
    CheckBox hotDetailMore;
    @BindView(R.id.hot_detail_back)
    ImageView hotDetailBack;

    @BindView(R.id.hot_detail_top_linear)
    LinearLayout topLinear;
    @BindView(R.id.hot_detail_scroll)
    HotDetailScrollView scroll;

    int position;        //标记当前滑动位置
    @BindView(R.id.hot_bottom_linear)
    LinearLayout bottomLinear;
    @BindView(R.id.hot_bottom_linear2)
    LinearLayout bottomLinear2;
    @BindView(R.id.hot_detail_zuixin_text)
    TextView zuixinText;
    @BindView(R.id.hot_detail_zuixin_view1)
    View zuixinView1;
    @BindView(R.id.hot_detail_zuixin)
    RelativeLayout zuixin;
    @BindView(R.id.hot_detail_zuire_text)
    TextView zuireText;
    @BindView(R.id.hot_detail_zuixin_view2)
    View zuixinView2;
    @BindView(R.id.hot_detail_zuire)
    RelativeLayout zuire;
    @BindView(R.id.hot_detail_zuixin_text2)
    TextView zuixinText2;
    @BindView(R.id.hot_detail_zuixin_view3)
    View zuixinView3;
    @BindView(R.id.hot_detail_zuixin2)
    RelativeLayout zuixin2;
    @BindView(R.id.hot_detail_zuire_text2)
    TextView zuireText2;
    @BindView(R.id.hot_detail_zuixin_view4)
    View zuixinView4;
    @BindView(R.id.hot_detail_zuire2)
    RelativeLayout zuire2;
    @BindView(R.id.hot_detail_top_bg)
    ImageView topBg;


    @BindView(R.id.hot_detail_new_recycler)
    RecyclerView newRecycler;

    @BindView(R.id.hot_detail_hot_recycler)
    RecyclerView hotRecycler;


    int currentTag = R.id.hot_detail_zuixin;
    List<JourneyItemInfo> newList = new ArrayList<>();
    List<JourneyItemInfo> hotList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_detail);
        ButterKnife.bind(this);
        scroll.setLinear(topLinear);
        scroll.setWithScroll(new HuatiWithScroll() {
            @Override
            public void moveToBottom(int flag) {
                //处于上半部  印象顶部的最新最热标签  显示正常的
                if (flag == 0) {
                    bottomLinear2.setVisibility(View.GONE);
                    bottomLinear.setVisibility(View.VISIBLE);
                }
                //处于上半部  隐藏正常的显示顶部的标签
                if (flag == 1) {
                    bottomLinear.setVisibility(View.INVISIBLE);
                    bottomLinear2.setVisibility(View.VISIBLE);
                }
            }
        });
        initClick();
        initData();
    }

    private void initClick() {
        zuixin.setOnClickListener(this);
        zuire.setOnClickListener(this);
        zuixin2.setOnClickListener(this);
        zuire2.setOnClickListener(this);
        hotDetailMore.setOnClickListener(this);
        hotDetailBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hot_detail_back:
                finish();
                break;
            case R.id.hot_detail_more:
                if (hotDetailMore.isChecked()) {
                    summary.setMaxLines(100);
                } else {
                    summary.setMaxLines(2);
                }
                break;
            case R.id.hot_detail_zuixin:
            case R.id.hot_detail_zuixin2:
                if (currentTag != R.id.hot_detail_zuixin && currentTag != R.id.hot_detail_zuixin2) {
                    toXin();
                    currentTag = v.getId();
                }
                break;
            case R.id.hot_detail_zuire:
            case R.id.hot_detail_zuire2:
                if (currentTag != R.id.hot_detail_zuire && currentTag != R.id.hot_detail_zuire2) {
                    toRe();
                    currentTag = v.getId();
                }
                break;
        }
    }

    public void toXin() {
        zuixinText.setTextColor(getResources().getColor(R.color.journey_lv));
        zuixinView1.setVisibility(View.VISIBLE);
        zuixinText2.setTextColor(getResources().getColor(R.color.journey_lv));
        zuixinView3.setVisibility(View.VISIBLE);
        zuireText.setTextColor(getResources().getColor(R.color.find_black));
        zuixinView2.setVisibility(View.GONE);
        zuireText2.setTextColor(getResources().getColor(R.color.find_black));
        zuixinView4.setVisibility(View.GONE);
        newRecycler.setVisibility(View.VISIBLE);
        hotRecycler.setVisibility(View.GONE);
    }

    public void toRe() {
        zuixinText.setTextColor(getResources().getColor(R.color.find_black));
        zuixinView1.setVisibility(View.GONE);
        zuixinText2.setTextColor(getResources().getColor(R.color.find_black));
        zuixinView3.setVisibility(View.GONE);
        zuireText.setTextColor(getResources().getColor(R.color.journey_lv));
        zuixinView2.setVisibility(View.VISIBLE);
        zuireText2.setTextColor(getResources().getColor(R.color.journey_lv));
        zuixinView4.setVisibility(View.VISIBLE);
        newRecycler.setVisibility(View.GONE);
        hotRecycler.setVisibility(View.VISIBLE);
    }

    class HotHandler extends HandleUtil {
        public HotHandler(Context context) {
            super(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HandlerBack.HUATINEW:
                    handleNew((JSONObject) msg.obj);
                    break;
                case HandlerBack.HUATIHOT:
                    handleHot((JSONObject) msg.obj);
                    break;
            }
        }
    }

    private void handleHot(JSONObject jsonObject) {
        Log.i("test","hot"+jsonObject.toString()+"");
        JsonUtil jsonUtil = new JsonUtil();
        hotList.clear();
        hotList.addAll(jsonUtil.decodeJourneyItem(jsonObject,"record_list"));
        MyLinearManager linearManager = new MyLinearManager(this, LinearLayoutManager.VERTICAL,false);
        hotRecycler.setLayoutManager(linearManager);
        hotRecycler.addItemDecoration(new MyDecoration(20));
        JourneyAdapter adapter = new JourneyAdapter(this,hotList);
        hotRecycler.setAdapter(adapter);
    }

    private void handleNew(JSONObject jsonObject) {
        Log.i("test","new"+jsonObject.toString()+"");
        JsonUtil jsonUtil = new JsonUtil();
        HotDetailTopInfo topInfo = jsonUtil.decodeHotTop(jsonObject);
        title.setText("#" + topInfo.getHuati_title() + "#");
        hotDetailCc.setText("参与(" + topInfo.getRecord_cnt() + ")");
        int count = Integer.parseInt(topInfo.getView_cnt());
        float realCount = (float) (count * 1.0 / 1000);
        liulan.setText("浏览(" + realCount + "k)");
        faqi.setText("发起人:" + topInfo.getUsername());
        Glide.with(this).load(topInfo.getHuati_img()).placeholder(R.mipmap.icon_loading_end).into(topBg);
        summary.setText(Html.fromHtml(topInfo.getHuati_summary_html()));
        //现在是清空加入 不能实现加载更多
        newList.clear();
        newList.addAll(jsonUtil.decodeJourneyItem(jsonObject,"record_list"));
        MyLinearManager linearManager = new MyLinearManager(this, LinearLayoutManager.VERTICAL,false);
        newRecycler.setLayoutManager(linearManager);
        newRecycler.addItemDecoration(new MyDecoration(20));
        JourneyAdapter adapter = new JourneyAdapter(this,newList);
        newRecycler.setAdapter(adapter);
    }

    private void initData() {
        HttpRequest httpRequest1 = new HttpRequest(this, HttpUrl.HUATINEW, new HotHandler(this), HandlerBack.HUATINEW);
        httpRequest1.startRequest();
        HttpRequest httpRequest = new HttpRequest(this, HttpUrl.HUATIHOT, new HotHandler(this), HandlerBack.HUATIHOT);
        httpRequest.startRequest();
    }

}
