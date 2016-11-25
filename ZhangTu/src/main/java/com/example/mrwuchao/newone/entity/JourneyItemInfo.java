package com.example.mrwuchao.newone.entity;

import java.util.List;

/**
 * 新鲜页面的数据信息类
 */
public class JourneyItemInfo {
    String username;
    String gender;
    String content;
    String time;
    String pic_cnt;
    String like_cnt;
    String address;
    String avatar;   //头像地址
    List<String> pictureList;
    List<TagInfo> tagList;
    String share_url;
    String trip_name; //来自什么  可能为空  其他没这个
    Boolean isLike = false;

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }

    public String getTrip_name() {
        return trip_name;
    }

    public void setTrip_name(String trip_name) {
        this.trip_name = trip_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPic_cnt() {
        return pic_cnt;
    }

    public void setPic_cnt(String pic_cnt) {
        this.pic_cnt = pic_cnt;
    }

    public String getLike_cnt() {
        return like_cnt;
    }

    public void setLike_cnt(String like_cnt) {
        this.like_cnt = like_cnt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public List<TagInfo> getTagList() {
        return tagList;
    }

    public void setTagList(List<TagInfo> tagList) {
        this.tagList = tagList;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }
}
