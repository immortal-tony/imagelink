package com.tony.imagelink.mapper;

import com.tony.imagelink.ImagelinkApplication;
import com.tony.imagelink.mapper.entity.GalleryFeatures;
import com.tony.imagelink.mapper.entity.Model;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ImagelinkApplication.class, DataSourceAutoConfiguration.class})
@MapperScan({"com.tony.imagelink.mapper"})
class GalleryFeaturesMapperTest {

    @Autowired
    GalleryFeaturesMapper a;

    @Autowired
    ModelMapper b;

    @BeforeEach
    void setUp() {
        System.out.println("开始测试");
    }

    @AfterEach
    void tearDown() {
        System.out.println("测试结束！");
    }

    @Test
    void insert() {
        GalleryFeatures galleryFeatures = new GalleryFeatures();
        galleryFeatures.setTitle("[XiuRen] 2020.09.27 No.2605 允薾");

        galleryFeatures.setFeature("允薾,秀人网,女仆,黑丝");
        galleryFeatures.setPicNum("42张照片");
        galleryFeatures.setViewNum("该图集包含，在 2020/11/24 创建，被浏览了 74037 次");
        galleryFeatures.setUrl("https://img.onvshen.com:85/gallery/28081/34353/s/0.jpg");
        galleryFeatures.setCollectionId(34353);
        galleryFeatures.setComments("新人@允薾 首套发布，真实F杯，身材前凸后翘，肌肤雪白如雪，本次吃蛋糕主题，深V实力在线，小哥哥们尽管来撩。");
        int ret = a.insert(galleryFeatures);
        System.out.println("程序结果为: " + ret);
        Model model = new Model();
        model.setId(0);
        model.setName("舌尖上的菊花");
        model.setAge("25 (属猪)");
        model.setBirth("1995-03-15");
        model.setStature(165);
        model.setBeatyTag("B90 W59 H90");
        model.setUrl("https://img.onvshen.com:85/girl/28090/28090.jpg");
        model.setBirtAddress("中国 广东");
        model.setCollectionId("34473");
        model.setComment("舌尖上的菊花，内地90后平面模特，来自广东，40w粉丝网红模特。");
        model.setConstellation("处女座");
        int ret1 = b.insert(model);
        System.out.println("模特存入即结果为: " + ret1);

    }
}