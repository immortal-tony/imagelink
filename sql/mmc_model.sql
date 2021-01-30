# 升级MySQL，然后将对应的数据类型改为utf8mb4类型（mysql 版本 >= 5.5）
CREATE TABLE `mmc_model`
(
    `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '模特的id编号',
    `modelId` int(12) DEFAULT NULL COMMENT '模特Id',
    `collectionId` varchar(200) DEFAULT NULL COMMENT '模特图集id号集',
    `name` varchar(256) DEFAULT NULL COMMENT '模特姓名',
    `age` varchar(12) DEFAULT NULL COMMENT '模特年龄',
    `birth` varchar(36) DEFAULT NULL COMMENT '模特生日',
    `weight` varchar(36) DEFAULT NULL COMMENT '模特体重',
    `constellation` varchar(256) DEFAULT NULL COMMENT '模特星座',
    `stature` int(4) DEFAULT NULL COMMENT '模特身高',
    `birtAddress` varchar(36) DEFAULT NULL COMMENT '模特出生地',
    `url` varchar(200) DEFAULT NULL COMMENT '模特封面图片地址',
    `beatyTag` varchar(256) DEFAULT NULL COMMENT '美女分类',
    `comment` text DEFAULT NULL COMMENT '模特评论',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;
ALTER TABLE mmc_model MODIFY COLUMN comment text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;