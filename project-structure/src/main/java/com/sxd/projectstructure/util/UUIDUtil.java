package com.sxd.projectstructure.util;

import java.util.UUID;

/**
 * @author liJianxin
 *
 * UUID工具类
 */
public final class UUIDUtil {

    private UUIDUtil() {
    }

    /**
     * 生成UUID字符串
     *
     * @return string UUID
     */
    public static String getUuid() {
        String s = UUID.randomUUID().toString();
        //去掉"-"符号
        return (s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24)).toUpperCase();
    }

}
