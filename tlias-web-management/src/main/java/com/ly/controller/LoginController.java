package com.ly.controller;

import com.ly.pojo.Emp;
import com.ly.pojo.LoginInfo;
import com.ly.pojo.Result;
import com.ly.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("登录",emp);
        LoginInfo info=empService.login(emp);
        if (info!=null){
            return Result.success(info);
        }
        return Result.error("用户名或密码错误");
    }
}
