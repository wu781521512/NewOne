package com.example.mrwuchao.newone.entity;

import java.util.List;

/**
 * 更多风景详情页面数据实体类
 */
public class MostDetailInfo {
    private String scenic_name;
    private String scenic_addr;
    private String scenic_region;
    private String scenic_tel;
    private String scenic_ticket;
    private String scenic_opentime;
    private String scenic_duration;
    private String scenic_desc;
    private String scenic_img;
    private int scenic_star_dec;
    private String scenic_best_month;
    private String scenic_share_url;
    private String share_message;
    private List<MostDetailTags> tagsList;
    private Score scenic_score;
    private String rank;
    private List<RealPics> realList;
    private List<Recommend> recomList;

    public String getScenic_name() {
        return scenic_name;
    }

    public void setScenic_name(String scenic_name) {
        this.scenic_name = scenic_name;
    }

    public String getScenic_addr() {
        return scenic_addr;
    }

    public void setScenic_addr(String scenic_addr) {
        this.scenic_addr = scenic_addr;
    }

    public String getScenic_region() {
        return scenic_region;
    }

    public void setScenic_region(String scenic_region) {
        this.scenic_region = scenic_region;
    }

    public String getScenic_tel() {
        return scenic_tel;
    }

    public void setScenic_tel(String scenic_tel) {
        this.scenic_tel = scenic_tel;
    }

    public String getScenic_ticket() {
        return scenic_ticket;
    }

    public void setScenic_ticket(String scenic_ticket) {
        this.scenic_ticket = scenic_ticket;
    }

    public String getScenic_opentime() {
        return scenic_opentime;
    }

    public void setScenic_opentime(String scenic_opentime) {
        this.scenic_opentime = scenic_opentime;
    }

    public String getScenic_duration() {
        return scenic_duration;
    }

    public void setScenic_duration(String scenic_duration) {
        this.scenic_duration = scenic_duration;
    }

    public String getScenic_desc() {
        return scenic_desc;
    }

    public void setScenic_desc(String scenic_desc) {
        this.scenic_desc = scenic_desc;
    }

    public String getScenic_img() {
        return scenic_img;
    }

    public void setScenic_img(String scenic_img) {
        this.scenic_img = scenic_img;
    }

    public int getScenic_star_dec() {
        return scenic_star_dec;
    }

    public void setScenic_star_dec(int scenic_star_dec) {
        this.scenic_star_dec = scenic_star_dec;
    }

    public String getScenic_best_month() {
        return scenic_best_month;
    }

    public void setScenic_best_month(String scenic_best_month) {
        this.scenic_best_month = scenic_best_month;
    }

    public String getScenic_share_url() {
        return scenic_share_url;
    }

    public void setScenic_share_url(String scenic_share_url) {
        this.scenic_share_url = scenic_share_url;
    }

    public String getShare_message() {
        return share_message;
    }

    public void setShare_message(String share_message) {
        this.share_message = share_message;
    }

    public List<MostDetailTags> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<MostDetailTags> tagsList) {
        this.tagsList = tagsList;
    }

    public Score getScenic_score() {
        return scenic_score;
    }

    public void setScenic_score(Score scenic_score) {
        this.scenic_score = scenic_score;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public List<RealPics> getRealList() {
        return realList;
    }

    public void setRealList(List<RealPics> realList) {
        this.realList = realList;
    }

    public List<Recommend> getRecomList() {
        return recomList;
    }

    public void setRecomList(List<Recommend> recomList) {
        this.recomList = recomList;
    }
}
