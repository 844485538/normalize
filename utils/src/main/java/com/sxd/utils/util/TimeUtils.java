package com.sxd.utils.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @Author 李健新
 * @Date 2022/3/31
 * @Description
 *
 *          日期时间工具类
 */
public final class TimeUtils {

    private TimeUtils() {}

    /**
     * 获取当前时间
     *
     * @return
     */
    public static LocalDateTime now() {
        return LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
    }


    /**
     * LocalDateTime 转 String
     *
     * @return
     */
    public static String LocalDateTimeToStr(LocalDateTime time){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.format(time);
    }

    /**
     * String 转 LocalDateTime
     *
     * @return
     */
    public static LocalDateTime StrToLocalDateTime(String time){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(time, dtf);
    }


}
