package com.sxd.projectstructure.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @Author 李健新
 * @Date 2022/3/31
 * @Description
 *
 *          日期时间工具类
 */
public final class DateTimeUtil {

    private DateTimeUtil() {}

    /**
     * 获取当前时间
     *
     * @return
     */
    public static LocalDateTime now() {
        return LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
    }

}
