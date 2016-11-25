package com.example.mrwuchao.newone.utils;

import android.text.style.ClickableSpan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 计算发布时间的工具类
 */
public class TimeUtil {
    public static String decodeTime(String time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("y/MM/dd HH:mm 发布", Locale.getDefault());
        long nowTime = System.currentTimeMillis();
        long releaseTime = Long.parseLong(time);
        long chaTime = (nowTime - releaseTime)/1000/60/60;
        if (chaTime >= 24) {
            return simpleDateFormat.format(new Date(releaseTime));
        }else{
            if (chaTime < 1) {
                return (nowTime - releaseTime)/1000/60+"前 发布";
            }else {
                return chaTime+"前 发布";
            }
        }
    }
}
