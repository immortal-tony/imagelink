package com.tony.imagelink.mapper;

import com.tony.imagelink.mapper.entity.Model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

public interface ModelMapper{
    int insert(Model model);

    List<Model> selectQueryModels();
}