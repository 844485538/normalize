package com.sxd.projectstructure.config;

import com.sxd.projectstructure.entity.VO.ResponseTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author 李健新
 * @Date 2020/11/4
 * @Description
 *
 *      统一异常处理类
 */
@RestControllerAdvice
public class AllExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(AllExceptionHandler.class);

    /**
     * 处理 JSR - 303 参数异常
     */
    @ExceptionHandler(value= {MethodArgumentNotValidException.class, BindException.class})
    public ResponseTemplate checkParamException(Exception e) {
        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException)e).getBindingResult();
        } else if (e instanceof BindException) {
            bindingResult = ((BindException)e).getBindingResult();
        }
        return ResponseTemplate.fail(bindingResult.getFieldErrors().get(0).getDefaultMessage());
    }

    /**
     * 系统抛出的异常处理
     *
     */
    @ExceptionHandler()
    public ResponseTemplate osRuntimeException(RuntimeException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseTemplate.fail(e.getMessage());
    }

}
