package com.sxd.utils.util;

import java.util.*;
import java.util.stream.Collectors;

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
     * <br>
     *  <br>StringUtils.isEmpty(null) = true
     *  <br>StringUtils.isEmpty("") = true
     *  <br>StringUtils.isEmpty(" ") = false
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
     *<br>
     *  <br>StringUtils.isEmpty(null) = false
     *  <br>StringUtils.isEmpty("") = false
     *  <br>StringUtils.isEmpty(" ") = true
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
     *<br>
     *  <br>StringUtils.isEmpty(null) = true
     *  <br>StringUtils.isEmpty("") = true
     *  <br>StringUtils.isEmpty(" ") = true
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
     *<br>
     *  <br>StringUtils.isEmpty(null) = false
     *  <br>StringUtils.isEmpty("") = false
     *  <br>StringUtils.isEmpty(" ") = false
     *
     * @param cs 字符串
     * @return
     *
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }


    /**
     * 根据指定分割福分割字符串
     *
     * string  -> List<String>
     * @param str           字符串
     * @param separator     分割福
     * @return
     */
    public static List<String> string2Set(String str, String separator) {
        if (StringUtils.isEmpty(str)){
            return null;
        }
        return Arrays.stream(str.split(separator)).collect(Collectors.toList());
    }

}
