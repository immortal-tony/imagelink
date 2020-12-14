package com.tony.imagelink.mapper.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName Description TODO
 * @Author hzf
 * @Date 2020/12/13 12:39
 */

@Data
public class GalleryFeatures {
    private String title;
    private String feature; //园园呀 秀人网 内衣 黑丝
    private String picNum;
    private String viewNum;
    private String url; // 图片地址
    private int collectionId;
    private String comments;
}
