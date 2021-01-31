# 普通的表
CREATE TABLE `mmc_gallery_features`
(
    `id`            int(12) NOT NULL AUTO_INCREMENT COMMENT '模特的图片id',
    `model_id`      int(12)      DEFAULT NULL COMMENT '模特id',
    `collection_id` int(12)      DEFAULT NULL COMMENT '模特图集id',
    `title`         varchar(64)  DEFAULT NULL COMMENT '标题',
    `feature`       varchar(64)  DEFAULT NULL COMMENT '分类标签',
    `pic_num`       varchar(64)  DEFAULT NULL COMMENT '图片数量',
    `view_num`      varchar(64)  DEFAULT NULL COMMENT '浏览数量',
    `date`      varchar(64)  DEFAULT NULL COMMENT '图集时间',
    `url`           varchar(200) DEFAULT NULL COMMENT '图片地址',
    `comments`      varchar(200) DEFAULT NULL COMMENT '评论描述',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;

# 热门的表
CREATE TABLE `mmc_hot_gallery_features`
(
    `id`            int(12) NOT NULL AUTO_INCREMENT COMMENT '模特的图片id',
    `model_id`      int(12)      DEFAULT NULL COMMENT '模特id',
    `collection_id` int(12)      DEFAULT NULL COMMENT '模特图集id',
    `title`         varchar(64)  DEFAULT NULL COMMENT '标题',
    `feature`       varchar(64)  DEFAULT NULL COMMENT '分类标签',
    `pic_num`       varchar(64)  DEFAULT NULL COMMENT '图片数量',
    `view_num`      varchar(64)  DEFAULT NULL COMMENT '浏览数量',
    `date`      varchar(64)  DEFAULT NULL COMMENT '图集时间',
    `url`           varchar(200) DEFAULT NULL COMMENT '图片地址',
    `comments`      varchar(200) DEFAULT NULL COMMENT '评论描述',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;
