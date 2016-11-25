package com.example.mrwuchao.newone.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.adapter.MyPagerAdapter;
import com.example.mrwuchao.newone.utils.DensityUtil;
import com.example.mrwuchao.newone.utils.HandleUtil;
import com.example.mrwuchao.newone.utils.HandlerBack;
import com.example.mrwuchao.newone.utils.HttpRequest;
import com.example.mrwuchao.newone.utils.HttpUrl;
import com.example.mrwuchao.newone.utils.JsonUtil;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.TriangularPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新鲜页面的fragment
 */
public class JourneyFragment extends Fragment {
    private View view;
    private ImageView camera;
    private MagicIndicator magic;
    private ViewPager viewPager;
    List<Fragment> fragmentList;
    private NewFragment newFragment;
    private HotFragment hotFragment;
    private FocusFragment focusFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_journey, null);
        newFragment = new NewFragment();
        hotFragment = new HotFragment();
        focusFragment = new FocusFragment();
        initView();
        initMagic();
        initViewPager();
        return view;
    }

    private void initMagic() {
        final List<String> textList = new ArrayList<>();
        textList.add("最新");
        textList.add("热榜");
        textList.add("关注");
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setEnablePivotScroll(true);
        commonNavigator.setScrollPivotX(0.6f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return textList == null?0:textList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView titleView = new SimplePagerTitleView(getActivity());
                titleView.setText(textList.get(index));
                titleView.setTextSize(DensityUtil.sp2px(getActivity(),10));
//                titleView.setTextColor(Color.YELLOW);
                titleView.setSelectedColor(Color.parseColor("#0CB69C"));
                titleView.setNormalColor(Color.parseColor("#FFFFFF"));
                titleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return titleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                WrapPagerIndicator indicator = new WrapPagerIndicator(getActivity());
                indicator.setFillColor(Color.parseColor("#FFFFFF"));
                indicator.setRoundRadius(20);
                return indicator;
            }
        });
        magic.setNavigator(commonNavigator);
    }

    private void initView() {
        camera = (ImageView) view.findViewById(R.id.journey_camera);
        magic = (MagicIndicator) view.findViewById(R.id.journey_magic);
        viewPager = (ViewPager) view.findViewById(R.id.journey_view_pager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                magic.onPageScrolled(position,positionOffset,positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                magic.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                magic.onPageScrollStateChanged(state);
            }
        });
    }

    private void initViewPager() {
        fragmentList = new ArrayList<>();
        fragmentList.add(newFragment);
        fragmentList.add(hotFragment);
        fragmentList.add(focusFragment);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(fm,fragmentList);
        viewPager.setAdapter(pagerAdapter);
    }
}
