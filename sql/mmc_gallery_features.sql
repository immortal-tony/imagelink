CREATE TABLE `mmc_gallery_features`
(
    `id`       int(12) NOT NULL AUTO_INCREMENT COMMENT '模特的图片id',
    `title`    varchar(64)  DEFAULT NULL COMMENT '标题',
    `feature`  varchar(64)  DEFAULT NULL COMMENT '分类标签',
    `pic_num`  varchar(64)  DEFAULT NULL COMMENT '图片数量',
    `view_num` varchar(64)  DEFAULT NULL COMMENT '浏览数量',
    `url`      varchar(200) DEFAULT NULL COMMENT '图片地址',
    `comments` varchar(200) DEFAULT NULL COMMENT '评论描述',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8