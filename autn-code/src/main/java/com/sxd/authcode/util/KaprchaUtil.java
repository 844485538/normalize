package com.sxd.authcode.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.Properties;

/**
 * @Author 李健新
 * @Date 2020/11/5
 * @Description
 */
public class KaprchaUtil {

    /**
     * session 中验证码的属性 key
     */
    private static final String SESSION_AUTH_CODE = "login_validate_code";

    /**
     * 验证码配置对象
     */
    private static final DefaultKaptcha DEFAULT_KAPTCHA = new DefaultKaptcha();

    static {
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border", "no");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "black");
        //边框厚度
        properties.setProperty("kaptcha.border.thickness", "1");
        // 图片宽
        properties.setProperty("kaptcha.image.width", "200");
        // 图片高
        properties.setProperty("kaptcha.image.height", "50");
        //图片实现类
        properties.setProperty("kaptcha.producer.impl", "com.google.code.kaptcha.impl.DefaultKaptcha");
        //文本实现类
        properties.setProperty("kaptcha.textproducer.impl", "com.google.code.kaptcha.text.impl.DefaultTextCreator");
        //文本集合，验证码值从此集合中获取
        properties.setProperty("kaptcha.textproducer.char.string", "01234567890");
        //验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体");
        //字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        //文字间隔
        properties.setProperty("kaptcha.textproducer.char.space", "5");
        //干扰实现类
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");
        //干扰颜色
        properties.setProperty("kaptcha.noise.color", "blue");
        //干扰图片样式
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");
        //背景实现类
        properties.setProperty("kaptcha.background.impl", "com.google.code.kaptcha.impl.DefaultBackground");
        //背景颜色渐变，结束颜色
        properties.setProperty("kaptcha.background.clear.to", "white");
        //文字渲染器
        properties.setProperty("kaptcha.word.impl", "com.google.code.kaptcha.text.impl.DefaultWordRenderer");
        DEFAULT_KAPTCHA.setConfig(new Config(properties));

    }

    /**
     * 生成验证码
     *
     * @param request
     * @param response
     * @throws Exception
     */
    public static void initAuthCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // Set to expire far in the past.
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");

        // return a jpeg
        response.setContentType("image/jpeg");

        // create the text for the image
        String capText = DEFAULT_KAPTCHA.createText();

        // store the text in the session
        request.getSession().setAttribute(SESSION_AUTH_CODE, capText);

        // create the image with the text
        BufferedImage bi = DEFAULT_KAPTCHA.createImage(capText);

        try(ServletOutputStream out = response.getOutputStream()) {
            // write the data out
            ImageIO.write(bi, "jpg", out);
            out.flush();
        }
    }

    /**
     * 验证验证码
     *
     * @param request
     * @param authCode
     * @return
     */
    public static boolean checkAuthCode(HttpServletRequest request, String authCode){
        return authCode.equals(request.getSession().getAttribute(SESSION_AUTH_CODE));
    }

    /**
     * 清空验证码
     *
     * @param request
     */
    public static void removeCode(HttpServletRequest request) {
        request.getSession().setAttribute(SESSION_AUTH_CODE, null);
    }

}
