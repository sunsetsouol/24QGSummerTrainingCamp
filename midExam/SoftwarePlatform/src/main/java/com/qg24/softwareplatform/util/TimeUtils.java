package com.qg24.softwareplatform.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    //解析时间格式
    public static LocalDateTime parseTime(String time){
        //时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //解析时间格式
        LocalDateTime parse = LocalDateTime.parse(time, formatter);
        //返回
        return parse;
    }
}
