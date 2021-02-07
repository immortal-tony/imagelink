package com.tony.imagelink.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName UserController
 * Description TODO
 * @Author hzf
 * @Date 2021/2/1 17:45
 */
@RestController
@RequestMapping(value = "user")
@Slf4j
public class UserController {

    // 用户注册修改什么的东西没有完善，还有模特的地址什么的内容修改
    @PostMapping("/registerUser")
    public Object registerUser(@RequestParam("account") String name, @RequestParam("password") String pwd) {
        log.info("验证用户信息！");
        return null;
    }
}
