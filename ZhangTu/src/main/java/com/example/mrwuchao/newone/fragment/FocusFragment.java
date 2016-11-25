package com.example.mrwuchao.newone.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.adapter.JourneyAdapter;
import com.example.mrwuchao.newone.entity.JourneyItemInfo;
import com.example.mrwuchao.newone.utils.HandleUtil;
import com.example.mrwuchao.newone.utils.HandlerBack;
import com.example.mrwuchao.newone.utils.HttpRequest;
import com.example.mrwuchao.newone.utils.HttpUrl;
import com.example.mrwuchao.newone.utils.JsonUtil;
import com.example.mrwuchao.newone.views.MyDecoration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * journey界面的最新界面的fragment
 */
public class FocusFragment extends Fragment {
    List<JourneyItemInfo> itemList ;
    RecyclerView recycler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new,null);
        recycler = (RecyclerView) view.findViewById(R.id.journey_out_recycler);
        initData();
        return view;
    }

    private void initData() {
        HttpRequest httpRequest = new HttpRequest(getContext(), HttpUrl.JOURNEYFOCUS,new FocusHandler(getContext()), HandlerBack.JOURNEYFOCUS);
        httpRequest.startRequest();
    }

    class FocusHandler extends HandleUtil {
        public FocusHandler(Context context) {
            super(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == HandlerBack.JOURNEYFOCUS) {
                handleResult((JSONObject) msg.obj);
            }
        }
    }

    private void handleResult(JSONObject jsonObject) {
        itemList = new ArrayList<>();
        JsonUtil jsonUtil = new JsonUtil();
        itemList.clear();
        itemList.addAll(jsonUtil.decodeJourneyItem(jsonObject,"list"));
        LinearLayoutManager linearManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recycler.setLayoutManager(linearManager);
        recycler.addItemDecoration(new MyDecoration(20));
        JourneyAdapter adapter = new JourneyAdapter(getContext(),itemList);
        recycler.setAdapter(adapter);
    }
}
