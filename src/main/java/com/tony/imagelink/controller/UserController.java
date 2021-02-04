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

    @PostMapping("/registerUser")
    public Object registerUser(@RequestParam("account") String name, @RequestParam("password") String pwd) {
return null;
    }
}
