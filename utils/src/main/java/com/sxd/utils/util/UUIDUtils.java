package com.sxd.utils.util;

import java.util.UUID;

/**
 * @author liJianxin
 *
 *      UUID工具类
 */
public final class UUIDUtils {

    private UUIDUtils() {
    }

    /**
     * 生成32位UUID字符串，由数字和大写字母组成
     *
     * @return string UUID
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

}
