package com.example.mrwuchao.newone.entity;

import java.util.List;

/**
 * most下方的数据实体类
 */
public class MostDataInfo {
    private String data_name;
    private String data_addr;
    private String data_img;
    private String best_month;
    private String desc;
    private String data_share_url;
    private String share_message;
    private String rank;
    List<String> pics;

    public String getData_name() {
        return data_name;
    }

    public void setData_name(String data_name) {
        this.data_name = data_name;
    }

    public String getData_addr() {
        return data_addr;
    }

    public void setData_addr(String data_addr) {
        this.data_addr = data_addr;
    }

    public String getData_img() {
        return data_img;
    }

    public void setData_img(String data_img) {
        this.data_img = data_img;
    }

    public String getBest_month() {
        return best_month;
    }

    public void setBest_month(String best_month) {
        this.best_month = best_month;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getData_share_url() {
        return data_share_url;
    }

    public void setData_share_url(String data_share_url) {
        this.data_share_url = data_share_url;
    }

    public String getShare_message() {
        return share_message;
    }

    public void setShare_message(String share_message) {
        this.share_message = share_message;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }
}
