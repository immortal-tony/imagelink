package com.tony.imagelink.mapper.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @ClassName UserInfo
 * Description TODO
 * @Author hzf
 * @Date 2021/2/4 18:58
 */
@Data
public class UserInfo {
    private int id; // 模特的图片id
    private String name; //用户名
    private String password;// 用户密码
    private Timestamp create_time;// 用户密码
    private String favorite_models; // 收藏模特ids
    private String favorite_collections; // 收藏模特专辑ids
    private String favorite_pics; //收藏的图片
    private String favorite_vides; // 收藏视频s
    private String membership; //会员信息
}
