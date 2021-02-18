package com.tony.imagelink.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode("http://api.mm131a.com:88/mm131/\n" +
                "addArticleVideoBrowsingHistory?\n" +
                "duration=0%3A15\n" +
                "&coverUrl=%2Fa84b66ff580beec3a554fa543b2aac95.jpg\n" +
                "&coverHeight=360\n" +
                "&videoUrl=https%3A%2F%2Fshpr.nxjqt.com%2Fback%2F20190624%2F58da8da8431565df1ee98674b1c5b261.mp4\n" +
                "&coverWidth=640\n" +
                "&title=%E9%99%86%E6%A2%93%E7%90%AA%E4%BC%9A%E5%91%8A%E8%AF%89%E4%BD%A0%E4%BB%80%E4%B9%88%E6%89%8D%E6%98%AF%E9%87%8E%E6%80%A7%E7%9A%84%E8%AF%B1%E6%83%91%EF%BC%8C%E4%B8%B0%E8%85%B4%E5%B0%8F%E7%BF%98%E8%87%80%EF%BC%8C%E5%AE%8C%E7%BE%8E%E6%97%A0%E7%91%95\n" +
                "&avid=7902","UTF-8"));
    }
}
