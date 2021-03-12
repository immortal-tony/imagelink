package com.tony.imagelink.controller;

import com.tony.imagelink.mapper.UserInfoMapper;
import com.tony.imagelink.ret.BaseReturn;
import com.tony.imagelink.ret.CommonReturn;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private UserInfoMapper userInfoMapper;

    // 用户注册修改什么的东西没有完善，还有模特的地址什么的内容修改
    @PostMapping("/registerUser")
    public Object registerUser(@RequestParam("account") String name){
        log.info("验证用户信息！");
        if(userInfoMapper.checkNameExists(name,null).size()>0){
            return new CommonReturn(1,"该用户名已存在系统，请修改！");
        }
        return new CommonReturn();
    }

    @PostMapping("/loginUser")
    public Object loginUser(@RequestParam("account") String name, @RequestParam("password") String pwd) {
        log.info("用户登录信息验证！");
        if(userInfoMapper.selectUserInfo(name,null) != null && userInfoMapper.selectUserInfo(name,null).getPassword().equals(pwd)){
            BaseReturn ret = new BaseReturn(0,userInfoMapper.selectUserInfo(name,null));
            ret.setMessage("验证成功");
            return ret;
        }
        BaseReturn ret = new BaseReturn(1,"验证成功");
        return ret;
    }

    @PostMapping("/searchUserInfo")
    public Object searchUserInfo(@RequestParam("account") String name) {
        log.info("用户登录信息验证！");
        return userInfoMapper.selectUserInfo(name,null);
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode("Request url: api.mm131a.com:88/mm131/addArticleVideoBrowsingHistory?duration=0%3A05&coverUrl=%2F8ecf5b645577282cf7b85b72e86685dd.jpg&coverHeight=360&videoUrl=https%3A%2F%2Fshpr.nxjqt.com%2Fback%2F20180202%2F605ec42de6de70cb777e713c9d777dce.mp4&coverWidth=640&title=%E5%A6%B9%E5%AD%90%E7%9A%84%E8%BA%AB%E6%9D%90%E6%9B%B2%E7%BA%BF%E6%97%A0%E6%95%8C%E4%BA%86%EF%BC%8C%E9%85%A5%E8%83%B8%E6%99%83%E5%8A%A8%EF%BC%8C%E7%90%83%E6%8A%80%E4%BA%86%E4%B8%8D%E5%BE%97%E5%95%8A&avid=3085","UTF-8"));
    }
}
