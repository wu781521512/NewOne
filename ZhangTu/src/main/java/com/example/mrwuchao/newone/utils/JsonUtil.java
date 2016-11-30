package com.example.mrwuchao.newone.utils;

import android.util.Log;

import com.example.mrwuchao.newone.entity.Advertise;
import com.example.mrwuchao.newone.entity.FindCategoryInfo;
import com.example.mrwuchao.newone.entity.FindFengXiang;
import com.example.mrwuchao.newone.entity.FindHotInfo;
import com.example.mrwuchao.newone.entity.FindJustGoInfo;
import com.example.mrwuchao.newone.entity.HotDetailTopInfo;
import com.example.mrwuchao.newone.entity.JourneyItemInfo;
import com.example.mrwuchao.newone.entity.MostDataInfo;
import com.example.mrwuchao.newone.entity.MostDetailInfo;
import com.example.mrwuchao.newone.entity.MostDetailTags;
import com.example.mrwuchao.newone.entity.MostInfo;
import com.example.mrwuchao.newone.entity.RealPics;
import com.example.mrwuchao.newone.entity.Recommend;
import com.example.mrwuchao.newone.entity.Score;
import com.example.mrwuchao.newone.entity.TagInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * json解析的工具类
 */
public class JsonUtil {
    List<Advertise> advertiseList;
    List<FindFengXiang> fengList;
    List<FindHotInfo> hotList;
    List<FindCategoryInfo> categoryList;
    List<FindJustGoInfo> justGoList;
    List<JourneyItemInfo> journeyItemList;
    public List<Advertise> decodeAdvertise(JSONObject jsonObject) {
        advertiseList = new ArrayList<>();
        JSONObject result = null;
        try {
             result = jsonObject.getJSONObject("result");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (result.getInt("result_code") == 1) {
                JSONObject datas = jsonObject.getJSONObject("data");
                JSONArray focus = datas.getJSONArray("focus");
                for (int i = 0; i < focus.length(); i++) {
                    JSONObject item = focus.getJSONObject(i);
                    Advertise advertise = new Advertise();
                    advertise.setFocus_title(item.getString("focus_title"));
                    advertise.setFocus_img1(item.getString("focus_img1"));
                    advertise.setFocus_url(item.getString("focus_url"));
                    advertise.setOpen_type(item.getString("open_type"));
                    advertiseList.add(advertise);
                }
                return advertiseList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FindFengXiang> decodeFeng(JSONObject jsonObject) {
        fengList = new ArrayList<>();
        JSONObject result = null;
        try {
            result = jsonObject.getJSONObject("result");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (result.getInt("result_code") == 1) {
                JSONObject datas = jsonObject.getJSONObject("data");
                JSONArray fengxiang = datas.getJSONArray("fengxiangbiao");
                for (int i = 0; i < fengxiang.length(); i++) {
                    JSONObject item = fengxiang.getJSONObject(i);
                    FindFengXiang findFeng = new FindFengXiang();
                    findFeng.setScenic_id(item.getInt("scenic_id"));
                    findFeng.setScenic_img(item.getString("scenic_img"));
                    findFeng.setScenic_name(item.getString("scenic_name"));
                    findFeng.setTop_num(item.getInt("top_num"));
                    fengList.add(findFeng);
                }
                return fengList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FindHotInfo> decodeHot(JSONObject jsonObject) {
        hotList = new ArrayList<>();
        JSONObject result = null;
        try {
            result = jsonObject.getJSONObject("result");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (result.getInt("result_code") == 1) {
                JSONObject datas = jsonObject.getJSONObject("data");
                JSONArray hot = datas.getJSONArray("huati");
                for (int i = 0; i < hot.length(); i++) {
                    JSONObject item = hot.getJSONObject(i);
                    FindHotInfo hotInfo = new FindHotInfo();
                    hotInfo.setHuati_id(item.getString("huati_id"));
                    hotInfo.setHuati_img(item.getString("huati_img"));
                    hotInfo.setHuati_title(item.getString("huati_title"));
                    hotList.add(hotInfo);
                }
                return hotList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FindCategoryInfo> decodeCategory(JSONObject jsonObject) {
        categoryList = new ArrayList<>();
        JSONObject result = null;
        try {
            result = jsonObject.getJSONObject("result");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (result.getInt("result_code") == 1) {
                JSONObject datas = jsonObject.getJSONObject("data");
                JSONArray category = datas.getJSONArray("tag_scenic");
                for (int i = 0; i < category.length(); i++) {
                    JSONObject item = category.getJSONObject(i);
                    FindCategoryInfo categoryInfo = new FindCategoryInfo();
                    categoryInfo.setIs_new(item.getString("is_new"));
                    categoryInfo.setSub_id(item.getString("sub_id"));
                    categoryInfo.setSub_img(item.getString("sub_img"));
                    categoryInfo.setSub_name(item.getString("sub_name"));
                    categoryList.add(categoryInfo);
                }
                return categoryList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<FindJustGoInfo> decodeJustGo(JSONObject jsonObject){
        justGoList = new ArrayList<>();
        JSONObject result = null;
        try {
            result = jsonObject.getJSONObject("result");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (result.getInt("result_code") == 1) {
                JSONObject datas = jsonObject.getJSONObject("data");
                JSONArray category = datas.getJSONArray("went_scenic");
                for (int i = 0; i < category.length(); i++) {
                    JSONObject item = category.getJSONObject(i);
                    FindJustGoInfo justGoInfo = new FindJustGoInfo();
                    justGoInfo.setScenic_name(item.getString("scenic_name"));
                    justGoInfo.setScenic_img(item.getString("scenic_img"));
                    justGoList.add(justGoInfo);
                }
                return justGoList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<JourneyItemInfo> decodeJourneyItem(JSONObject jsonObject,String arrayName) {
        journeyItemList = new ArrayList<>();
        JSONObject result = null;
        try {
            result = jsonObject.getJSONObject("result");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (result.getInt("result_code") == 1) {
                JSONObject datas = jsonObject.getJSONObject("data");
                JSONArray list = datas.getJSONArray(arrayName);
                for (int i = 0; i < list.length(); i++) {
                    JourneyItemInfo itemInfo = new JourneyItemInfo();
                    JSONObject listItem = list.getJSONObject(i);
                    itemInfo.setUsername(listItem.getString("username"));
                    itemInfo.setGender(listItem.getString("gender"));
                    itemInfo.setContent(listItem.getString("content"));
                    itemInfo.setTime(listItem.getString("time"));
                    itemInfo.setPic_cnt(listItem.getString("pic_cnt"));
                    itemInfo.setLike_cnt(listItem.getString("like_cnt"));
                    itemInfo.setAddress(listItem.getString("address"));
                    itemInfo.setAvatar(listItem.getString("avatar"));
                    List<String> imgList = new ArrayList<>();
                    JSONArray pic = listItem.getJSONArray("pics");
                    for (int i1 = 0; i1 < pic.length(); i1++) {
                        JSONObject picItem = pic.getJSONObject(i1);
                        imgList.add(picItem.getString("pic_url"));
                    }
                    itemInfo.setPictureList(imgList);
                    List<TagInfo> tagList = new ArrayList<>();
                    JSONArray tag = listItem.getJSONArray("tag");
                    for (int i1 = 0; i1 < tag.length(); i1++) {
                        JSONObject tagItem = tag.getJSONObject(i1);
                        TagInfo tagInfo = new TagInfo();
                        tagInfo.setTag_name(tagItem.getString("tag_name"));
                        tagList.add(tagInfo);
                    }
                    itemInfo.setTagList(tagList);
                    itemInfo.setShare_url(listItem.getString("share_url"));
                    if (!listItem.isNull("trip")) {
                         JSONObject trip = listItem.getJSONObject("trip");
                         itemInfo.setTrip_name(trip.getString("trip_name"));
                    }

                     journeyItemList.add(itemInfo);
                }
                return journeyItemList;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HotDetailTopInfo decodeHotTop(JSONObject jsonObject){
        JSONObject result = null;
        try {
            result = jsonObject.getJSONObject("result");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (result.getInt("result_code") == 1) {
                JSONObject data = jsonObject.getJSONObject("data");
                JSONObject info = data.getJSONObject("info");
                HotDetailTopInfo hotTopInfo = new HotDetailTopInfo();
                hotTopInfo.setHuati_title(info.getString("huati_title"));
                hotTopInfo.setHuati_summary(info.getString("huati_summary"));
                hotTopInfo.setHuati_summary_html(info.getString("huati_summary_html"));
                hotTopInfo.setHuati_img(info.getString("huati_img"));
                hotTopInfo.setRecord_cnt(info.getString("record_cnt"));
                hotTopInfo.setView_cnt(info.getString("view_cnt"));
                hotTopInfo.setUsername(info.getString("username"));
                hotTopInfo.setShare_url(info.getString("share_url"));
                hotTopInfo.setShare_message(info.getString("share_message"));
                return hotTopInfo;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MostInfo decodeMost(JSONObject jsonObject) {
        Log.i("kong",jsonObject.toString());
        JSONObject result = null;
        try {
            result = jsonObject.getJSONObject("result");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            if (result.getInt("result_code") == 1) {

                JSONObject data = jsonObject.getJSONObject("data");
                MostInfo mostInfo = new MostInfo();

                mostInfo.setSub_name(data.getString("sub_name"));
                mostInfo.setSub_img(data.getString("sub_img"));
                mostInfo.setSub_new_img(data.getString("sub_new_img"));
                mostInfo.setSub_desc(data.getString("sub_desc"));
                mostInfo.setSub_new_desc(data.getString("sub_new_desc"));
                mostInfo.setSub_share_url(data.getString("sub_share_url"));
                mostInfo.setShare_message(data.getString("share_message"));

                JSONArray data_list = data.getJSONArray("data_list");
                List<MostDataInfo> dataInfos = new ArrayList<>();
                int dataLength = data_list.length();
                for (int i = 0; i < dataLength; i++) {
                    JSONObject innerData = data_list.getJSONObject(i);
                    MostDataInfo mostDataInfo = new MostDataInfo();
                    mostDataInfo.setData_name(innerData.getString("data_name"));
                    mostDataInfo.setData_addr(innerData.getString("data_addr"));
                    mostDataInfo.setData_img(innerData.getString("data_img"));
                    mostDataInfo.setBest_month(innerData.getString("best_month"));
                    mostDataInfo.setDesc(innerData.getString("desc"));
                    mostDataInfo.setData_share_url(innerData.getString("data_share_url"));
                    mostDataInfo.setShare_message(innerData.getString("share_message"));
                    mostDataInfo.setRank(innerData.getString("rank"));
                    List<String> imgList = new ArrayList<>();
                    JSONArray pics = innerData.getJSONArray("pics");
                    int picLength = pics.length();
                    for (int i1 = 0; i1 < picLength; i1++) {
                        imgList.add(pics.getJSONObject(i1).getString("pic_url"));
                    }
                    mostDataInfo.setPics(imgList);
                    dataInfos.add(mostDataInfo);
                }
                mostInfo.setMostDataInfoList(dataInfos);
                return mostInfo;

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public MostDetailInfo decodeMostDetail(JSONObject jsonObject){
        JSONObject result = null;
        try {
            result = jsonObject.getJSONObject("result");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            if (result.getInt("result_code") == 1) {
                JSONObject data = jsonObject.getJSONObject("data");
                JSONObject info = data.getJSONObject("scenic_info");
                MostDetailInfo detailInfo = new MostDetailInfo();
                detailInfo.setScenic_name(info.getString("scenic_name"));
                detailInfo.setScenic_addr(info.getString("scenic_addr"));
                detailInfo.setScenic_region(info.getString("scenic_region"));
                detailInfo.setScenic_tel(info.getString("scenic_tel"));
                detailInfo.setScenic_ticket(info.getString("scenic_ticket"));
                detailInfo.setScenic_opentime(info.getString("scenic_opentime"));
                detailInfo.setScenic_duration(info.getString("scenic_duration"));
                detailInfo.setScenic_desc(info.getString("scenic_desc"));
                detailInfo.setScenic_img(info.getString("scenic_img"));
                detailInfo.setScenic_star_dec(info.getInt("scenic_star_dec"));
                detailInfo.setScenic_best_month(info.getString("scenic_best_month"));
                detailInfo.setScenic_share_url(info.getString("scenic_share_url"));
                detailInfo.setShare_message(info.getString("share_message"));
                List<MostDetailTags> tagsList = new ArrayList<>();
                JSONArray tags = info.getJSONArray("tags");
                int tagCount = tags.length();
                for (int i = 0; i < tagCount; i++) {
                    JSONObject tag = tags.getJSONObject(i);
                    MostDetailTags detailTag = new MostDetailTags();
                    detailTag.setTag_name(tag.getString("tag_name"));
                    tagsList.add(detailTag);
                }
                detailInfo.setTagsList(tagsList);
                Score scenic_score = new Score();
                JSONObject sceScore = info.getJSONObject("scenic_score");
                scenic_score.setOption1(sceScore.getString("option1"));
                scenic_score.setOption2(sceScore.getString("option2"));
                scenic_score.setOption3(sceScore.getString("option3"));
                scenic_score.setOption4(sceScore.getString("option4"));
                scenic_score.setOption5(sceScore.getString("option5"));
                scenic_score.setTotal(sceScore.getString("total"));
                detailInfo.setScenic_score(scenic_score);
                detailInfo.setRank(info.getString("rank"));
                List<RealPics> realList = new ArrayList<>();
                JSONArray real = data.getJSONArray("pics");
                int realCount = real.length();
                for (int i = 0; i < realCount; i++) {
                    RealPics realPics = new RealPics();
                    JSONObject realOb = real.getJSONObject(i);
                    realPics.setPic_url(realOb.getString("pic_url"));
                    realPics.setAvatar(realOb.getString("avatar"));
                    realPics.setGender(realOb.getString("gender"));
                    realPics.setProvider(realOb.getString("provider"));
                    realList.add(realPics);
                }
                detailInfo.setRealList(realList);
                List<Recommend> recomList = new ArrayList<>();
                JSONArray poiRecom = data.getJSONArray("poi_recomm");
                int poiCount = poiRecom.length();
                for (int i = 0; i < poiCount; i++) {
                    JSONObject recomOb = poiRecom.getJSONObject(i);
                    Recommend recommend = new Recommend();
                    recommend.setPoi_name(recomOb.getString("poi_name"));
                    recommend.setPoi_img(recomOb.getString("poi_img"));
                    recommend.setPoi_addr(recomOb.getString("poi_addr"));
                    recomList.add(recommend);
                }
                detailInfo.setRecomList(recomList);
                return detailInfo;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}


