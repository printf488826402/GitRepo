package com.ly.exception;

import com.ly.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result handleException(Exception e){
        log.error("程序出错了",e);
        return Result.error("程序出错，请联系管理员");
    }
    @ExceptionHandler
    //用户信息重复时抛出异常
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错了",e);
        String message = e.getMessage();
        int i =message.indexOf("Duplicate entry");
        String errMsy=message.substring(i);
        String[] arr = errMsy.split("");
        return Result.error(arr[2]+"已存在");
    }
}
