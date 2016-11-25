package com.example.mrwuchao.newone.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.fragment.FindFragment;
import com.example.mrwuchao.newone.fragment.JourneyFragment;
import com.example.mrwuchao.newone.fragment.MineFragment;
import com.example.mrwuchao.newone.fragment.NearByFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mr.wuchao on 2016/11/15.
 */
public class MainActivity extends NotitleActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.find_radio_button)
    RadioButton findButton;
    @BindView(R.id.nearby_radio_button)
    RadioButton nearbyButton;
    @BindView(R.id.journey_radio_button)
    RadioButton journeyButton;
    @BindView(R.id.mine_radio_button)
    RadioButton mineButton;
    @BindView(R.id.main_radiogroup)
    RadioGroup maingroup;
    @BindView(R.id.main_frame)
    FrameLayout mainFrame;
    private int current = -1;      //标记当前选中的界面

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment fragment = new FindFragment();
        transaction.add(R.id.main_frame, fragment, String.valueOf(R.id.find_radio_button));
        transaction.commit();
        current = R.id.find_radio_button;
        findButton.setChecked(true);
        maingroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId != current) {
            change(checkedId);
        }
    }

    private void change(int id) {
        //点击的不是同一个才切换
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        String tag = String.valueOf(id);
        Fragment fragment = fm.findFragmentByTag(tag);
        switch (id) {
            case R.id.find_radio_button:
                if (fragment == null) {
                    fragment = new FindFragment();
                    transaction.add(R.id.main_frame, fragment, tag);
                }
                findButton.setTextColor(getResources().getColor(R.color.main_color));
                break;
            case R.id.nearby_radio_button:
                if (fragment == null) {
                    fragment = new NearByFragment();
                    transaction.add(R.id.main_frame, fragment, tag);
                }
                nearbyButton.setTextColor(getResources().getColor(R.color.main_color));
                break;
            case R.id.journey_radio_button:
                if (fragment == null) {
                    fragment = new JourneyFragment();
                    transaction.add(R.id.main_frame, fragment, tag);
                }
                journeyButton.setTextColor(getResources().getColor(R.color.main_color));
                break;
            case R.id.mine_radio_button:
                if (fragment == null) {
                    fragment = new MineFragment();
                    transaction.add(R.id.main_frame, fragment, tag);
                }
                mineButton.setTextColor(getResources().getColor(R.color.main_color));
                break;
        }

        switch (current) {
            case R.id.find_radio_button:
                findButton.setTextColor(getResources().getColor(R.color.qianhui));
                break;
            case R.id.nearby_radio_button:
                nearbyButton.setTextColor(getResources().getColor(R.color.qianhui));
                break;
            case R.id.journey_radio_button:
                journeyButton.setTextColor(getResources().getColor(R.color.qianhui));
                break;
            case R.id.mine_radio_button:
                mineButton.setTextColor(getResources().getColor(R.color.qianhui));
                break;
        }

        transaction.hide(fm.findFragmentByTag(String.valueOf(current)));
        transaction.show(fragment);
        transaction.commit();
        current = id;
    }
}
