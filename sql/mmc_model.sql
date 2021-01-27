CREATE TABLE `mmc_model`
(
    `id`            int(12) NOT NULL AUTO_INCREMENT COMMENT '模特的id编号',
    `name`          varchar(12)  DEFAULT NULL COMMENT '模特姓名',
    `age`           varchar(12)  DEFAULT NULL COMMENT '模特年龄',
    `birth`         varchar(36)  DEFAULT NULL COMMENT '模特生日',
    `constellation` varchar(12)  DEFAULT NULL COMMENT '模特星座',
    `stature`       int(4)       DEFAULT NULL COMMENT '模特身高',
    `birtAddress`   varchar(36)  DEFAULT NULL COMMENT '模特出生地',
    `url`           varchar(200) DEFAULT NULL COMMENT '模特封面图片地址',
    `beatyTag`      varchar(12)  DEFAULT NULL COMMENT '美女分类',
    `collectionId`  varchar(12)  DEFAULT NULL COMMENT '模特图集id号',
    `comment`       varchar(200) DEFAULT NULL COMMENT '模特评论',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8