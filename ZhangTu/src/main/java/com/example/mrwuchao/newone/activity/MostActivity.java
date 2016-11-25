package com.example.mrwuchao.newone.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.adapter.MostAdapter;
import com.example.mrwuchao.newone.entity.MostDataInfo;
import com.example.mrwuchao.newone.entity.MostInfo;
import com.example.mrwuchao.newone.inter.HuatiWithScroll;
import com.example.mrwuchao.newone.utils.HandleUtil;
import com.example.mrwuchao.newone.utils.HandlerBack;
import com.example.mrwuchao.newone.utils.HttpRequest;
import com.example.mrwuchao.newone.utils.HttpUrl;
import com.example.mrwuchao.newone.utils.JsonUtil;
import com.example.mrwuchao.newone.views.MostRecycler;
import com.example.mrwuchao.newone.views.MostScrollView;
import com.example.mrwuchao.newone.views.MyDecoration;
import com.example.mrwuchao.newone.views.MyLinearManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MostActivity extends NotitleActivity {

    @BindView(R.id.most_back)
    ImageButton mostBack;
    @BindView(R.id.most_share)
    ImageView mostShare;
    @BindView(R.id.most_map)
    ImageView mostMap;
    @BindView(R.id.most_top_image)
    ImageView topImage;
    @BindView(R.id.most_top)
    RelativeLayout mostTop;
    @BindView(R.id.most_middle_select_pin_text)
    TextView middleSelectPinText;
    @BindView(R.id.most_middle_select_top_pin_view)
    View middleSelectTopPinView;
    @BindView(R.id.most_middle_select_pin)
    RelativeLayout middleSelectPin;
    @BindView(R.id.most_middle_select_juli_text)
    TextView middleSelectJuliText;
    @BindView(R.id.most_middle_select_juli_view)
    View middleSelectJuliView;
    @BindView(R.id.most_middle_select_juli)
    RelativeLayout middleSelectJuli;
    @BindView(R.id.most_middle_linear)
    LinearLayout middleLinear;
    @BindView(R.id.most_recycler)
    MostRecycler mostRecycler;
    @BindView(R.id.most_scroll)
    MostScrollView mostScroll;
    @BindView(R.id.most_top_select_pin_text)
    TextView topSelectPinText;
    @BindView(R.id.most_top_select_top_pin_view)
    View topSelectTopPinView;
    @BindView(R.id.most_top_select_pin)
    RelativeLayout topSelectPin;
    @BindView(R.id.most_top_select_juli_text)
    TextView topSelectJuliText;
    @BindView(R.id.most_top_select_juli_view)
    View topSelectJuliView;
    @BindView(R.id.most_top_select_juli)
    RelativeLayout topSelectJuli;
    @BindView(R.id.most_top_linear)
    LinearLayout topLinear;
    @BindView(R.id.most_top_tip_image)
    ImageView topTipImage;
    @BindView(R.id.most_title)
    RelativeLayout mostTitle;
    @BindView(R.id.most_swipe)
    SwipeRefreshLayout mostSwipe;
    @BindView(R.id.most_all_linear)
    LinearLayout mostAllLinear;
    @BindView(R.id.most_big_name)
    TextView bigName;
    private MostInfo mostInfo;
    List<MostDataInfo> disList = new ArrayList<>();
    private MostAdapter mostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most);
        ButterKnife.bind(this);
        mostSwipe.setColorSchemeColors(Color.CYAN,Color.GREEN,Color.BLACK);
        mostSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        mostAllLinear.setVisibility(View.INVISIBLE);
        mostScroll.setHuatiWithScroll(new HuatiWithScroll() {
            @Override
            public void moveToBottom(int flag) {
                if (flag == 1) {
                    topLinear.setVisibility(View.VISIBLE);
                }
                if (flag == 0) {
                    topLinear.setVisibility(View.GONE);
                }
            }
        });
        mostScroll.setLayout(mostTop);
        initData();
    }

    @OnClick({R.id.most_back, R.id.most_share, R.id.most_map, R.id.most_top_tip_image, R.id.most_middle_select_pin, R.id.most_middle_select_juli, R.id.most_top_select_pin, R.id.most_top_select_juli})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.most_back:
                finish();
                break;
            case R.id.most_share:
                break;
            case R.id.most_map:
                break;
            case R.id.most_middle_select_pin:
            case R.id.most_top_select_pin:
                Log.i("dianji","点评被点击");
                toPin();
                break;
            case R.id.most_middle_select_juli:
            case R.id.most_top_select_juli:
                Log.i("dianji","距离被点击");
                toDistance();
                break;
            case R.id.most_top_tip_image:
                shareTip();
                break;
        }
    }

    public void toPin(){
        middleSelectPinText.setTextColor(getResources().getColor(R.color.journey_lv));
        middleSelectTopPinView.setVisibility(View.VISIBLE);
        topSelectPinText.setTextColor(getResources().getColor(R.color.journey_lv));
        topSelectTopPinView.setVisibility(View.VISIBLE);
        middleSelectJuliText.setTextColor(getResources().getColor(R.color.find_black));
        middleSelectJuliView.setVisibility(View.GONE);
        topSelectJuliText.setTextColor(getResources().getColor(R.color.find_black));
        topSelectJuliView.setVisibility(View.GONE);

    }

    public void toDistance(){
        middleSelectJuliText.setTextColor(getResources().getColor(R.color.journey_lv));
        middleSelectJuliView.setVisibility(View.VISIBLE);
        topSelectJuliText.setTextColor(getResources().getColor(R.color.journey_lv));
        topSelectJuliView.setVisibility(View.VISIBLE);
        middleSelectPinText.setTextColor(getResources().getColor(R.color.find_black));
        middleSelectTopPinView.setVisibility(View.GONE);
        topSelectPinText.setTextColor(getResources().getColor(R.color.find_black));
        topSelectTopPinView.setVisibility(View.GONE);
    }

    private void shareTip() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        View view = View.inflate(this, R.layout.most_dialog, null);
        TextView discuss = (TextView) view.findViewById(R.id.most_dialog_discuss);
        discuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        TextView content = (TextView) view.findViewById(R.id.most_dialog_text);
        content.setText(mostInfo.getSub_desc());
        content.setMovementMethod(ScrollingMovementMethod.getInstance());
        alertDialog.setView(view);
        alertDialog.show();
    }


    class MostHandler extends HandleUtil {
        public MostHandler(Context context) {
            super(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == HandlerBack.MOST) {
                handleMost((JSONObject) msg.obj);
                mostSwipe.setRefreshing(false);
            }
        }
    }

    private void handleMost(JSONObject jsonObject) {
        JsonUtil jsonUtil = new JsonUtil();
        mostInfo = jsonUtil.decodeMost(jsonObject);
        Log.i("kong", mostInfo + "");
        if (mostInfo != null)
        {
            mostAllLinear.setVisibility(View.VISIBLE);
            bigName.setText(mostInfo.getSub_name());
            Glide.with(this).load(mostInfo.getSub_img()).placeholder(R.mipmap.icon_loading_end).into(topImage);
            MyLinearManager myLinearManager = new MyLinearManager(this, LinearLayoutManager.VERTICAL, false);
            mostRecycler.setLayoutManager(myLinearManager);
            mostRecycler.addItemDecoration(new MyDecoration(12));
            mostAdapter = new MostAdapter(mostInfo, this);
            mostRecycler.setAdapter(mostAdapter);
        }

    }

    private void initData() {
        HttpRequest httpRequest = new HttpRequest(this, HttpUrl.MOST, new MostHandler(this), HandlerBack.MOST);
        httpRequest.startRequest();
    }
}
