package com.example.mrwuchao.newone.entity;

/**
 * 热门话题详情的上半部数据实体
 */
public class HotDetailTopInfo {
    private String huati_title;
    private String huati_summary;
    private String huati_summary_html;
    private String huati_img;
    private String record_cnt;
    private String view_cnt;
    private String username;
    private String share_url;
    private String share_message;

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getShare_message() {
        return share_message;
    }

    public void setShare_message(String share_message) {
        this.share_message = share_message;
    }

    public String getHuati_title() {
        return huati_title;
    }

    public void setHuati_title(String huati_title) {
        this.huati_title = huati_title;
    }

    public String getHuati_summary() {
        return huati_summary;
    }

    public void setHuati_summary(String huati_summary) {
        this.huati_summary = huati_summary;
    }

    public String getHuati_summary_html() {
        return huati_summary_html;
    }

    public void setHuati_summary_html(String huati_summary_html) {
        this.huati_summary_html = huati_summary_html;
    }

    public String getHuati_img() {
        return huati_img;
    }

    public void setHuati_img(String huati_img) {
        this.huati_img = huati_img;
    }

    public String getRecord_cnt() {
        return record_cnt;
    }

    public void setRecord_cnt(String record_cnt) {
        this.record_cnt = record_cnt;
    }

    public String getView_cnt() {
        return view_cnt;
    }

    public void setView_cnt(String view_cnt) {
        this.view_cnt = view_cnt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
