package com.tony.imagelink.mapper.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Model
 * Description TODO
 * @Author hzf
 * @Date 2020/12/13 12:28
 */
@Data
public class Model {
    private int id;
    private int modelId;
    private String collectionId; // 图集id号
    private String name;
    private String age;
    private String birth;
    private String weight; //体重
    private String constellation; // 星座
    private int stature;  // 身高
    private String birtAddress;  // 出生地
    private String url; //封面图片地址
    private String beatyTag;  // 美女分类
    private String comment; //评论
}
