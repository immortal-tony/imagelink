package com.tony.imagelink.mapper;

import com.tony.imagelink.mapper.entity.Model;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

public interface ModelMapper{
    /**
     * 插入模特信息
     * @param model
     * @return
     */
    int insert(Model model);

    /**
     * 选取模特的图集id和图集的模特特征描述，比如：feature=南初妹妹,秀人网,可爱,运动,
     * @return
     */
    List<Model> selectQueryModels(@Param("modelId") int modelId,@Param("collectionId") String collectionId);


    /**
     *     推荐模特专辑展示表
     */
    List<Model> slelctHotQueryModels(@Param("modelId") int modelId,@Param("collectionId") String collectionId);

    List<Integer> checkModel(@Param("modelId") int modelId,@Param("collectionIds") String collectionIds);
}