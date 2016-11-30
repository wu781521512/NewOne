package com.example.mrwuchao.newone.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.views.FindContentView;
import com.example.mrwuchao.newone.views.MyScollView;

/**
 * 发现页面的fragment
 */
public class FindFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find,null);
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.find_refresh);
        MyScollView scollView = (MyScollView) view.findViewById(R.id.find_scroll_view);
        final FindContentView contentView = new FindContentView(getActivity());
        contentView.setSwipe(swipeRefreshLayout);
        swipeRefreshLayout.setNestedScrollingEnabled(true);
        swipeRefreshLayout.setColorSchemeColors(Color.GREEN,Color.CYAN,Color.BLUE);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                contentView.refreshNow();
            }
        });
        scollView.addView(contentView.getView());
        return view;
    }
}
