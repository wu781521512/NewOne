package com.example.mrwuchao.newone.views;

import android.content.Context;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.adapter.CategoryAdapter;
import com.example.mrwuchao.newone.adapter.FindFengAdapter;
import com.example.mrwuchao.newone.adapter.HotAdapter;
import com.example.mrwuchao.newone.adapter.JustGoAdapter;
import com.example.mrwuchao.newone.entity.Advertise;
import com.example.mrwuchao.newone.entity.FindCategoryInfo;
import com.example.mrwuchao.newone.entity.FindFengXiang;
import com.example.mrwuchao.newone.entity.FindHotInfo;
import com.example.mrwuchao.newone.entity.FindJustGoInfo;
import com.example.mrwuchao.newone.utils.HandleUtil;
import com.example.mrwuchao.newone.utils.HandlerBack;
import com.example.mrwuchao.newone.utils.HttpRequest;
import com.example.mrwuchao.newone.utils.HttpUrl;
import com.example.mrwuchao.newone.utils.JsonUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现页面的scrollView中的内容视图组合实现类
 */
public class FindContentView {
    Context context;
    View mView;
    List<Advertise> adverList = new ArrayList<>();
    List<FindFengXiang> fengList = new ArrayList<>();
    List<FindHotInfo> hotList = new ArrayList<>();
    List<FindCategoryInfo> categoryList = new ArrayList<>();
    List<FindJustGoInfo> justGoList = new ArrayList<>();
    private LinearLayout layout;
    private RecyclerView feng;
    private RecyclerView hot;
    private RecyclerView category;
    private RecyclerView justGo;
    private JsonUtil jsonUtil;
    private LinearLayout bottomLinear;
    private SwipeRefreshLayout swipeRefreshLayout;
    public FindContentView(Context context){
        this.context = context;
        jsonUtil = new JsonUtil();
    }

    public View getView(){
        if (mView == null) {
            initView();
        }
        return mView;
    }

    private void initView() {
        MyDecoration myDecoration = new MyDecoration(5);
        MyGridDecoration gridDecoration = new MyGridDecoration(7);
        mView = View.inflate(context, R.layout.find_content,null);
        feng = (RecyclerView) mView.findViewById(R.id.find_fengxiang);
        feng.addItemDecoration(myDecoration);
        hot = (RecyclerView) mView.findViewById(R.id.find_hot);
        hot.addItemDecoration(gridDecoration);
        category = (RecyclerView) mView.findViewById(R.id.find_category);
        MyGridDecoration gridDecoration1 = new MyGridDecoration(6);
        category.addItemDecoration(gridDecoration1);
        justGo = (RecyclerView) mView.findViewById(R.id.find_just_go);
        justGo.addItemDecoration(myDecoration);
        layout = (LinearLayout) mView.findViewById(R.id.find_advertise_linear);
        bottomLinear = (LinearLayout) mView.findViewById(R.id.find_bottom_linear);
        bottomLinear.setVisibility(View.INVISIBLE);
        initData();
    }
    public void refreshNow(){
        initData();
    }

    public void setSwipe(SwipeRefreshLayout refreshLayout){
        swipeRefreshLayout = refreshLayout;
    }
    class FindHandler extends HandleUtil{

        public FindHandler(Context context) {
            super(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == HandlerBack.SEARCHBACK) {
                swipeRefreshLayout.setRefreshing(false);
                initContent((JSONObject) msg.obj);
            }
        }
    }
    //从网络中申请数据
    private void initData() {
        HttpRequest httpRequest = new HttpRequest(context, HttpUrl.SEARCH,new FindHandler(context),HandlerBack.SEARCHBACK);
        httpRequest.startRequest();
    }
    //网络请求获得数据，开始渲染各个控件的数据
    private void initContent(JSONObject jsonObject) {
        initAdvertise(jsonObject);
        initFeng(jsonObject);
        initHot(jsonObject);
        initCategory(jsonObject);
        initJustGo(jsonObject);
    }

    private void initJustGo(JSONObject jsonObject) {
        justGoList.clear();
        justGoList.addAll(jsonUtil.decodeJustGo(jsonObject));
        MyLinearManager linearManager = new MyLinearManager(context,LinearLayoutManager.HORIZONTAL,false);
        justGo.setLayoutManager(linearManager);
        JustGoAdapter justGoAdapter = new JustGoAdapter(context,justGoList);
        justGo.setAdapter(justGoAdapter);
        bottomLinear.setVisibility(View.VISIBLE);
    }

    private void initCategory(JSONObject jsonObject) {
        categoryList.clear();
        categoryList.addAll(jsonUtil.decodeCategory(jsonObject));
        MyGridManager gridManager = new MyGridManager(context,3);
        category.setLayoutManager(gridManager);
        CategoryAdapter categoryAdapter = new CategoryAdapter(context,categoryList);
        category.setAdapter(categoryAdapter);
    }

    private void initHot(JSONObject jsonObject) {
        hotList.clear();
        hotList.addAll(jsonUtil.decodeHot(jsonObject));
        MyGridManager gridManager = new MyGridManager(context,2);
        hot.setLayoutManager(gridManager);
        HotAdapter hotAdapter = new HotAdapter(context,hotList);
        hot.setAdapter(hotAdapter);
    }

    private void initFeng(JSONObject jsonObject) {
        fengList.clear();
        fengList.addAll(jsonUtil.decodeFeng(jsonObject));
        MyLinearManager layoutManager = new MyLinearManager(context,LinearLayoutManager.HORIZONTAL,false);
        feng.setLayoutManager(layoutManager);
        FindFengAdapter fengAdapter = new FindFengAdapter(context,fengList);
        feng.setAdapter(fengAdapter);
    }

    private void initAdvertise(JSONObject jsonObject) {

        //先解析广告轮播的数据
        adverList.clear();
        Log.i("srco",adverList + "   "+ jsonUtil + "   " + jsonObject);
        adverList.addAll(jsonUtil.decodeAdvertise(jsonObject));
        AdvertiseView advertiseView = new AdvertiseView(adverList,context);
        layout.addView(advertiseView.getView());
    }
}
