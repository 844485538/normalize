package com.normalize.resuful.exception;

/**
 * @Author 李健新
 * @Date 2020/11/6
 * @Description
 */
public class AdminNotLoginException extends RuntimeException {

    public AdminNotLoginException(){
        super("未登陆，无权访问");
    }

}
