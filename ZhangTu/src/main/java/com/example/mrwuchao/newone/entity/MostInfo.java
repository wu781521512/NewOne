package com.example.mrwuchao.newone.entity;

import java.util.List;

/**
 * 发现页面最XXX的详情实体类
 */
public class MostInfo {
    private String sub_id;
    private String sub_name;
    private String sub_img;
    private String sub_new_img;
    private String sub_desc;
    private String sub_new_desc;
    private String sub_share_url;
    private String share_message;

    public List<MostDataInfo> getMostDataInfoList() {
        return mostDataInfoList;
    }

    public void setMostDataInfoList(List<MostDataInfo> mostDataInfoList) {
        this.mostDataInfoList = mostDataInfoList;
    }

    public String getShare_message() {
        return share_message;
    }

    public void setShare_message(String share_message) {
        this.share_message = share_message;
    }

    public String getSub_share_url() {
        return sub_share_url;
    }

    public void setSub_share_url(String sub_share_url) {
        this.sub_share_url = sub_share_url;
    }

    public String getSub_new_desc() {
        return sub_new_desc;
    }

    public void setSub_new_desc(String sub_new_desc) {
        this.sub_new_desc = sub_new_desc;
    }

    public String getSub_desc() {
        return sub_desc;
    }

    public void setSub_desc(String sub_desc) {
        this.sub_desc = sub_desc;
    }

    public String getSub_new_img() {
        return sub_new_img;
    }

    public void setSub_new_img(String sub_new_img) {
        this.sub_new_img = sub_new_img;
    }

    public String getSub_img() {
        return sub_img;
    }

    public void setSub_img(String sub_img) {
        this.sub_img = sub_img;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    List<MostDataInfo> mostDataInfoList;
}
