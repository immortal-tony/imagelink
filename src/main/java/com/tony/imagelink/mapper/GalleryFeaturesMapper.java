package com.tony.imagelink.mapper;

import com.tony.imagelink.mapper.entity.GalleryFeatures;
import com.tony.imagelink.mapper.entity.ModelCollection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName GalleryFeaturesMapper
 * Description TODO
 * @Author hzf
 * @Date 2020/12/13 17:50
 */
public interface GalleryFeaturesMapper {
    /**
     * 插入模特链接表
     * @param galleryFeatures
     * @return
     */
    int insert(GalleryFeatures galleryFeatures);

    /**
     * 插入模特专辑编号，对应的专辑中文名分类
     * @param collectionId
     * @param featies
     * @return
     */
    int insertCollection(@Param("name") Integer collectionId, List<String> featies);

    /**
     * 查询模特链接表
     * @return
     */
    List<GalleryFeatures> selectQueryGalleryFeatures();

    /**
     * 根据专辑中文名分类，获取模特专辑编号
     * @param feature
     * @return
     */
    List<ModelCollection> selectModelCollectionIds(@Param("feature") String feature);

    List<GalleryFeatures> checkUrl(@Param("url") String url);

    List<ModelCollection> checkModelAndCollection(@Param("collection_id") Integer collection_id,@Param("tag") String tag1);

    int insertHotGalleryFeatures(GalleryFeatures galleryFeatures);

    List<GalleryFeatures> checkHotUrl(String url);

    // 最新的查询特辑链接
    List<GalleryFeatures> selectLastQueryGalleryFeatures(@Param("collection_id") Integer collection_id);

    // 最热的最新的专辑链接
    List<GalleryFeatures> selectHotQueryGalleryFeatures(@Param("collection_id") Integer collection_id);

}
