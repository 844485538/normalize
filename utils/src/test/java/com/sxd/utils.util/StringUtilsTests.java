package com.sxd.utils.util;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author 李健新
 * @Date 2022/4/1
 * @Description
 */
@SpringBootTest
public class StringUtilsTests {

    @Test
    public void isEmptyTest(){
        String a = null;
        String b = "";
        String c = "   ";
        assertEquals(true, StringUtils.isEmpty(a));
        assertEquals(true, StringUtils.isEmpty(b));
        assertEquals(false, StringUtils.isEmpty(c));
    }


    @Test
    public void isBlankTest(){
        String a = null;
        String b = "";
        String c = "   ";
        assertEquals(true, StringUtils.isBlank(a));
        assertEquals(true, StringUtils.isBlank(b));
        assertEquals(true, StringUtils.isBlank(c));
    }


}
