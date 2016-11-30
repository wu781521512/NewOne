package com.example.mrwuchao.newone.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mrwuchao.newone.R;
import com.example.mrwuchao.newone.adapter.RealAdapter;
import com.example.mrwuchao.newone.adapter.RecomAdapter;
import com.example.mrwuchao.newone.entity.MostDetailInfo;
import com.example.mrwuchao.newone.entity.MostDetailTags;
import com.example.mrwuchao.newone.entity.Score;
import com.example.mrwuchao.newone.utils.DensityUtil;
import com.example.mrwuchao.newone.utils.HandleUtil;
import com.example.mrwuchao.newone.utils.HandlerBack;
import com.example.mrwuchao.newone.utils.HttpRequest;
import com.example.mrwuchao.newone.utils.HttpUrl;
import com.example.mrwuchao.newone.utils.JsonUtil;
import com.example.mrwuchao.newone.views.MyDecoration;
import com.example.mrwuchao.newone.views.MyGridDecoration;
import com.example.mrwuchao.newone.views.MyGridManager;
import com.example.mrwuchao.newone.views.MyLinearManager;
import com.example.mrwuchao.newone.views.MyLiuViewGroup;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class MostDetailActivity extends NotitleActivity {

    @BindView(R.id.most_detail_scroll_top_image)
    ImageView topImage;
    @BindView(R.id.most_detail_name)
    TextView name;
    @BindView(R.id.most_detail_rank)
    TextView rank;
    @BindView(R.id.most_detail_ratting_bar)
    RatingBar rattingBar;
    @BindView(R.id.most_detail_back)
    ImageView back;
    @BindView(R.id.most_detail_title)
    TextView title;
    @BindView(R.id.most_detail_jiesao)
    TextView jiesao;
    @BindView(R.id.most_detail_jiesao_linear)
    LinearLayout jiesaoLinear;
    @BindView(R.id.most_detail_dizhi)
    TextView dizhi;
    @BindView(R.id.most_detail_dizhi_linear)
    LinearLayout dizhiLinear;
    @BindView(R.id.most_detail_open)
    TextView open;
    @BindView(R.id.most_detail_open_linear)
    LinearLayout openLinear;
    @BindView(R.id.most_detail_price)
    TextView price;
    @BindView(R.id.most_detail_price_linear)
    LinearLayout priceLinear;
    @BindView(R.id.most_detail_jianyi)
    TextView jianyi;
    @BindView(R.id.most_detail_jianyi_linear)
    LinearLayout jianyiLinear;
    @BindView(R.id.most_detail_dianhua)
    TextView dianhua;
    @BindView(R.id.most_detail_dianhua_linear)
    LinearLayout dianhuaLinear;
    @BindView(R.id.most_detail_zuijia)
    TextView zuijia;
    @BindView(R.id.most_detail_zuijia_linear)
    LinearLayout zuijiaLinear;
    @BindView(R.id.most_detail_share_check)
    CheckBox shareCheck;
    @BindView(R.id.most_detail_recommend_recycler)
    RecyclerView recommendRecycler;
    @BindView(R.id.most_detail_recommend_more)
    TextView recommendMore;
    @BindView(R.id.most_detail_recommend_linear)
    LinearLayout recommendLinear;
    @BindView(R.id.most_detail_real_recycler)
    RecyclerView realRecycler;
    @BindView(R.id.most_detail_real_more)
    TextView realMore;
    @BindView(R.id.most_detail_real_linear)
    LinearLayout realLinear;
    @BindView(R.id.most_detail_jiazhi_ratting)
    RatingBar jiazhiRatting;
    @BindView(R.id.most_detail_wanzheng_ratting)
    RatingBar wanzhengRatting;
    @BindView(R.id.most_detail_qibei_ratting)
    RatingBar qibeiRatting;
    @BindView(R.id.most_detail_redu_ratting)
    RatingBar reduRatting;
    @BindView(R.id.most_detail_yuansheng_ratting)
    RatingBar yuanshengRatting;
    @BindView(R.id.most_detail_dianping_linear)
    LinearLayout dianpingLinear;
    @BindView(R.id.most_detail_myliu)
    MyLiuViewGroup mostDetailMyliu;
    @BindView(R.id.most_detail_tie_biaoqian)
    TextView tieBiaoqian;
    @BindView(R.id.most_detail_biaoqian_linear)
    LinearLayout biaoqianLinear;
    private MostDetailInfo detailInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_most_detail);
        ButterKnife.bind(this);
        initData();
    }

    @OnClick({R.id.most_detail_back, R.id.most_detail_share_check, R.id.most_detail_recommend_more, R.id.most_detail_real_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.most_detail_back:
                finish();
                break;
            case R.id.most_detail_share_check:
                showShare();
                break;
            case R.id.most_detail_recommend_more:
                break;
            case R.id.most_detail_real_more:
                break;
            case R.id.most_detail_tie_biaoqian:
                break;
        }
    }

    private void showShare() {

        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(detailInfo.getShare_message());
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        //oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText(detailInfo.getShare_message());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(detailInfo.getScenic_share_url());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        //oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        //oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(detailInfo.getScenic_share_url());

        // 启动分享GUI
        oks.show(this);
    }

    class DetailHandler extends HandleUtil {
        public DetailHandler(Context context) {
            super(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == HandlerBack.MOSTDETAIL) {
                handleResult((JSONObject) msg.obj);
            }
        }
    }

    private void handleResult(JSONObject jsonObject) {
        JsonUtil jsonUtil = new JsonUtil();
        detailInfo = jsonUtil.decodeMostDetail(jsonObject);
        Glide.with(this).load(detailInfo.getScenic_img()).placeholder(R.mipmap.icon_loading_end).into(topImage);
        title.setText(detailInfo.getScenic_name());
        name.setText(detailInfo.getScenic_name());
        rank.setText(detailInfo.getRank());
        float rat = (float) (detailInfo.getScenic_star_dec() * 0.5);
        rattingBar.setRating(rat);
        rank.setText(detailInfo.getRank());
        if (detailInfo.getScenic_desc() == null || detailInfo.getScenic_desc().equals("")) {
            jiesaoLinear.setVisibility(View.GONE);
        } else {
            jiesaoLinear.setVisibility(View.VISIBLE);
            jiesao.setText(detailInfo.getScenic_desc());
        }
        if (detailInfo.getScenic_addr() == null || detailInfo.getScenic_addr().equals("")) {
            dizhiLinear.setVisibility(View.GONE);
        } else {
            dizhiLinear.setVisibility(View.VISIBLE);
            dizhi.setText(detailInfo.getScenic_addr());
        }
        if (detailInfo.getScenic_opentime() == null || detailInfo.getScenic_opentime().equals("")) {
            openLinear.setVisibility(View.GONE);
        } else {
            openLinear.setVisibility(View.VISIBLE);
            open.setText(detailInfo.getScenic_opentime());
        }
        if (detailInfo.getScenic_ticket() == null || detailInfo.getScenic_ticket().equals("")) {
            priceLinear.setVisibility(View.GONE);
        } else {
            priceLinear.setVisibility(View.VISIBLE);
            price.setText(detailInfo.getScenic_ticket());
        }
        if (detailInfo.getScenic_duration() == null || detailInfo.getScenic_duration().equals("")) {
            jianyiLinear.setVisibility(View.GONE);
        } else {
            jianyiLinear.setVisibility(View.VISIBLE);
            int duration = Integer.valueOf(detailInfo.getScenic_duration());
            jianyi.setText(duration / 60 + "小时");
        }
        if (detailInfo.getScenic_tel() == null || detailInfo.getScenic_tel().equals("")) {
            dianhuaLinear.setVisibility(View.GONE);
        } else {
            dianhuaLinear.setVisibility(View.VISIBLE);
            dianhua.setText(detailInfo.getScenic_tel());
        }
        if (detailInfo.getScenic_best_month() == null || detailInfo.getScenic_best_month().equals("")) {
            zuijiaLinear.setVisibility(View.GONE);
        } else {
            zuijiaLinear.setVisibility(View.VISIBLE);
            zuijia.setText(detailInfo.getScenic_best_month());
        }
        if (detailInfo.getTagsList().size() == 0) {
            biaoqianLinear.setVisibility(View.GONE);
        }else{
            biaoqianLinear.setVisibility(View.VISIBLE);
            int size = detailInfo.getTagsList().size();
            List<MostDetailTags> tagList = detailInfo.getTagsList();
            ViewGroup.LayoutParams la = new ViewGroup.LayoutParams(-2,-2);
            for (int i = 0; i < size; i++) {
                TextView textView = new TextView(this);
                textView.setLayoutParams(la);
                textView.setTextSize(DensityUtil.sp2px(this,6));
                textView.setTextColor(getResources().getColor(R.color.find_white));
                textView.setText(tagList.get(i).getTag_name());
                textView.setBackgroundResource(R.drawable.yuanjiao);
                int padding = DensityUtil.dip2px(this,5);
                textView.setPadding(padding,padding,padding,padding);
                mostDetailMyliu.addView(textView);
            }
        }
        if (detailInfo.getRecomList().size() == 0) {
            recommendLinear.setVisibility(View.GONE);
        } else {
            recommendLinear.setVisibility(View.VISIBLE);
            RecomAdapter recomAdapter = new RecomAdapter(this, detailInfo.getRecomList());
            MyLinearManager linearManager = new MyLinearManager(this, LinearLayoutManager.HORIZONTAL, false);
            MyDecoration myDecoration = new MyDecoration(18);
            recommendRecycler.addItemDecoration(myDecoration);
            recommendRecycler.setLayoutManager(linearManager);
            recommendRecycler.setAdapter(recomAdapter);
        }
        if (detailInfo.getRealList().size() == 0) {
            realLinear.setVisibility(View.GONE);
        } else {
            realLinear.setVisibility(View.VISIBLE);
            RealAdapter realAdapter = new RealAdapter(this, detailInfo.getRealList());
            MyGridManager gridManager = new MyGridManager(this, 3);
            MyGridDecoration myGridDecoration = new MyGridDecoration(10);
            realRecycler.setLayoutManager(gridManager);
            realRecycler.addItemDecoration(myGridDecoration);
            realRecycler.setAdapter(realAdapter);
        }
        Score score = detailInfo.getScenic_score();
        jiazhiRatting.setRating((float) (Integer.valueOf(score.getOption1()) * 0.5));
        wanzhengRatting.setRating((float) (Integer.valueOf(score.getOption2()) * 0.5));
        qibeiRatting.setRating((float) (Integer.valueOf(score.getOption3()) * 0.5));
        reduRatting.setRating((float) (Integer.valueOf(score.getOption4()) * 0.5));
        yuanshengRatting.setRating((float) (Integer.valueOf(score.getOption5()) * 0.5));
    }

    private void initData() {
        HttpRequest httpRequest = new HttpRequest(this, HttpUrl.MOSTDETAIL, new DetailHandler(this), HandlerBack.MOSTDETAIL);
        httpRequest.startRequest();
    }
}
