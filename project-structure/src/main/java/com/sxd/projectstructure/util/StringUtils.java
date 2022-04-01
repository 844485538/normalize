package com.sxd.projectstructure.util;

/**
 * @Author 李健新
 * @Date 2022/4/1
 * @Description
 *
 *      String 工具类
 */
public class StringUtils {

    /**
     * 判定字符串是否为空
     *
     *  StringUtils.isEmpty(null) = true
     *  StringUtils.isEmpty("") = true
     *  StringUtils.isEmpty(" ") = false
     *
     * @param cs 字符串
     * @return
     *
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 判定字符串是否非空
     *
     *  StringUtils.isEmpty(null) = false
     *  StringUtils.isEmpty("") = false
     *  StringUtils.isEmpty(" ") = true
     *
     * @param cs 字符串
     * @return
     *
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 判定字符串是否为空白（多个空格也算空白）
     *
     *  StringUtils.isEmpty(null) = true
     *  StringUtils.isEmpty("") = true
     *  StringUtils.isEmpty(" ") = true
     *
     * @param cs 字符串
     * @return
     *
     */
    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判定字符串是否非空白（多个空格也算空白）
     *
     *  StringUtils.isEmpty(null) = false
     *  StringUtils.isEmpty("") = false
     *  StringUtils.isEmpty(" ") = false
     *
     * @param cs 字符串
     * @return
     *
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

}
