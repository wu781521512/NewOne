package com.example.mrwuchao.newone.entity;

/**
 * Created by Mr.wuchao on 2016/11/14.
 */
public class FindFengXiang {

    /**
     * scenic_id : 3
     * scenic_img : http://img01.zhangtu.com/pic/201606/29/5772f7d71089b0d36208141_t_9801.jpeg
     * scenic_name : 故宫博物院
     * top_num : 1
     */

    private int scenic_id;
    private String scenic_img;
    private String scenic_name;
    private int top_num;

    public int getScenic_id() {
        return scenic_id;
    }

    public void setScenic_id(int scenic_id) {
        this.scenic_id = scenic_id;
    }

    public String getScenic_img() {
        return scenic_img;
    }

    public void setScenic_img(String scenic_img) {
        this.scenic_img = scenic_img;
    }

    public String getScenic_name() {
        return scenic_name;
    }

    public void setScenic_name(String scenic_name) {
        this.scenic_name = scenic_name;
    }

    public int getTop_num() {
        return top_num;
    }

    public void setTop_num(int top_num) {
        this.top_num = top_num;
    }
}
