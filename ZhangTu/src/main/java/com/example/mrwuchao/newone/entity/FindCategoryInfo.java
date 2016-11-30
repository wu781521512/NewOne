package com.example.mrwuchao.newone.entity;

/**
 * 发现页面的分类部分的数据实体
 */
public class FindCategoryInfo {

    /**
     * sub_id : 17
     * sub_name : 西藏最美观景拍摄点
     * is_new : 1
     * sub_img : http://img01.zhangtu.com/pic/201608/30/57c54b3831b6a3g71293608.jpeg
     */

    private String sub_id;
    private String sub_name;
    private String is_new;
    private String sub_img;

    public String getSub_id() {
        return sub_id;
    }

    public void setSub_id(String sub_id) {
        this.sub_id = sub_id;
    }

    public String getSub_name() {
        return sub_name;
    }

    public void setSub_name(String sub_name) {
        this.sub_name = sub_name;
    }

    public String getIs_new() {
        return is_new;
    }

    public void setIs_new(String is_new) {
        this.is_new = is_new;
    }

    public String getSub_img() {
        return sub_img;
    }

    public void setSub_img(String sub_img) {
        this.sub_img = sub_img;
    }
}
