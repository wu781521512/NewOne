package com.example.mrwuchao.newone.utils;

/**
 * 网络访问的url路径
 */
public class HttpUrl {

    /**
     * 基础访问路径
     **/
    public static final String BASE = "http://192.168.1.168:8080/wuchao/";
    /**
     * 发现页面的访问路径
     **/
    public static final String SEARCH = BASE + "search";
    /**
     * 新鲜页面最新的路径
     **/
    public static final String JOURNEYNEW = BASE + "journey/new";
    /**
     * 新鲜页面热榜的路径
     **/
    public static final String JOURNEYHOT = BASE + "journey/hot";
    /**
     * 新鲜页面关注的路径
     **/
    public static final String JOURNEYFOCUS = BASE + "journey/focus";
    /**热门话题最新数据路径**/
    public static final String HUATINEW = SEARCH + "/huati/new";
    /**热门话题最热数据路径**/
    public static final String HUATIHOT = SEARCH + "/huati/hot";
    /**发现页面分类页面**/
    public static final String MOST = SEARCH + "/most";
    /**发现页面分类页面详情**/
    public static final String MOSTDETAIL = SEARCH + "/most/detail";
}
