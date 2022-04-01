package com.sxd.authcode.controller;

import com.sxd.authcode.util.KaprchaUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 李健新
 * @Date 2022/4/1
 * @Description
 */
@RequestMapping("/auth/code")
@RestController
public class AuthCodeController {

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/init")
    public void initAuthCode(HttpServletRequest request, HttpServletResponse response) throws Exception{
        KaprchaUtil.initAuthCode(request, response);
    }

    /**
     * 验证验证码
     *
     * @param request
     * @param authCode
     */
    @GetMapping("/check")
    public boolean checkAuthCode(HttpServletRequest request, @RequestParam String authCode) {
        return KaprchaUtil.checkAuthCode(request, authCode);
    }


}
