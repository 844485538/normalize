package com.normalize.resuful.exception;

/**
 * @Author 李健新
 * @Date 2020/11/4
 * @Description
 *
 *      异常统一处理类
 *
 */
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.normalize.resuful.entity.ResponseTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AllExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(AllExceptionHandler.class);

    /**
     * 自定义异常处理
     *
     * @param e
     * @param resp
     * @return
     * @throws IOException
     */
    @ExceptionHandler()
    @ResponseBody
    public ResponseTemplate notLoginException(AdminNotLoginException e, HttpServletResponse resp) throws IOException {
        return ResponseTemplate.notLogin(e.getMessage());
    }

    /**
     * 系统抛出的异常处理
     *
     */
    @ExceptionHandler()
    @ResponseBody
    public ResponseTemplate customException(RuntimeException e, HttpServletResponse resp) throws IOException {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseTemplate.fail(e.getMessage());
    }

}
