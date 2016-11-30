package com.example.mrwuchao.newone.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.activity.LoginActivity;
import com.example.mrwuchao.newone.activity.SetActivity;
import com.example.mrwuchao.newone.utils.SharedPUtil;
import com.example.mrwuchao.newone.views.MineScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的页面的fragment
 */
public class MineFragment extends Fragment implements ViewTreeObserver.OnGlobalLayoutListener {

    @BindView(R.id.mine_bk)
    ImageView mineBk;
    @BindView(R.id.mine_scroll_view)
    MineScrollView mineScrollView;
    @BindView(R.id.mine_touxiang)
    ImageView touxiang;
    @BindView(R.id.mine_login_btn)
    Button loginBtn;
    @BindView(R.id.mine_name)
    TextView mineName;
    @BindView(R.id.mine_dianpin_text)
    TextView dianpinText;
    @BindView(R.id.mine_daidian_text)
    TextView daidianText;
    @BindView(R.id.mine_jilu_text)
    TextView jiluText;
    @BindView(R.id.mine_xingcheng_text)
    TextView xingchengText;
    @BindView(R.id.mine_shoucang_text)
    TextView shoucangText;
    @BindView(R.id.mine_share_text)
    TextView shareText;
    @BindView(R.id.mine_renzheng_text)
    TextView renzhengText;
    @BindView(R.id.mine_setting_text)
    TextView settingText;

    @Override
    public void onStart() {
        super.onStart();
        SharedPUtil sharedPUtil = new SharedPUtil(getActivity());
        String passWord = sharedPUtil.getPassWord();
        if (!passWord.equals("")) {
            mineName.setText(sharedPUtil.getUserName());
            loginBtn.setVisibility(View.GONE);
            mineName.setVisibility(View.VISIBLE);
        }else{
            mineName.setVisibility(View.GONE);
            loginBtn.setVisibility(View.VISIBLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine_view, null);
        ButterKnife.bind(this, view);
        mineBk.getViewTreeObserver().addOnGlobalLayoutListener(this);
        return view;
    }

    @Override
    public void onGlobalLayout() {
        int height = mineBk.getMeasuredHeight();
        int maxHeight = mineBk.getDrawable().getIntrinsicHeight();
        mineScrollView.setNewHeight(height, maxHeight, mineBk);
        mineBk.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    private void Login() {
        Intent intent  = new Intent(getActivity(),LoginActivity.class);
        getActivity().startActivity(intent);
    }

    private void selectPhone() {

    }

    @OnClick({R.id.mine_touxiang, R.id.mine_login_btn,R.id.mine_dianpin_text, R.id.mine_daidian_text, R.id.mine_jilu_text, R.id.mine_xingcheng_text, R.id.mine_shoucang_text, R.id.mine_share_text, R.id.mine_renzheng_text, R.id.mine_setting_text})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_touxiang:
                selectPhone();
                break;
            case R.id.mine_login_btn:
                Login();
                break;
            case R.id.mine_dianpin_text:
                break;
            case R.id.mine_daidian_text:
                break;
            case R.id.mine_jilu_text:
                break;
            case R.id.mine_xingcheng_text:
                break;
            case R.id.mine_shoucang_text:
                break;
            case R.id.mine_share_text:
                break;
            case R.id.mine_renzheng_text:
                break;
            case R.id.mine_setting_text:
                Intent intent = new Intent(getActivity(),SetActivity.class);
                startActivity(intent);
                break;
        }
    }
}
