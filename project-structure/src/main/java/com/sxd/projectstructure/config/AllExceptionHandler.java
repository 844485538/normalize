package com.sxd.projectstructure.config;

import com.sxd.projectstructure.entity.VO.ResponseTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;

/**
 * @Author 李健新
 * @Date 2020/11/4
 * @Description
 *
 *      统一异常处理类
 */
@ControllerAdvice
public class AllExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(AllExceptionHandler.class);

    /**
     * 系统抛出的异常处理
     *
     */
    @ExceptionHandler()
    @ResponseBody
    public ResponseTemplate customException(RuntimeException e) throws IOException {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseTemplate.fail(e.getMessage());
    }

}
