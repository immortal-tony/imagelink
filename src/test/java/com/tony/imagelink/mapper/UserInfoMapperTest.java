package com.tony.imagelink.mapper;

import com.tony.imagelink.mapper.entity.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoMapperTest extends GalleryFeaturesMapperTest{
    @Autowired
    private UserInfoMapper userInfoMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    void insert() {
    }

    @Test
    void selectUserInfo() {
        System.out.println(userInfoMapper.selectUserInfo("nihao",null));
    }

    @Test
    void checkNameExists() {
    }

    @Test
    void updateUserCollections() {
    }
}