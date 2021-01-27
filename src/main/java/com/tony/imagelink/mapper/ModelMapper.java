package com.tony.imagelink.mapper;

import com.tony.imagelink.mapper.entity.Model;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

public interface ModelMapper{
    int insert(Model model);
}