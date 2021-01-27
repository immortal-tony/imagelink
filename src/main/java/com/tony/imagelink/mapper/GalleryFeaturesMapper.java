package com.tony.imagelink.mapper;

import com.tony.imagelink.mapper.entity.GalleryFeatures;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

/**
 * @ClassName GalleryFeaturesMapper
 * Description TODO
 * @Author hzf
 * @Date 2020/12/13 17:50
 */
public interface GalleryFeaturesMapper {

    int insert(GalleryFeatures galleryFeatures);

}
