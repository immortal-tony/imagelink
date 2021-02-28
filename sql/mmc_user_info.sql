# 用户表,信息表
CREATE TABLE `mmc_user_info`
(
    `id`            int(12) NOT NULL AUTO_INCREMENT COMMENT '模特的图片id',
    `name`      varchar(36)      DEFAULT NULL COMMENT '用户名',
    `password`         varchar(64)  DEFAULT NULL COMMENT '用户密码',
    `create_time`      TIMESTAMP NOT NULL COMMENT '用户密码',
    `favorite_models`         varchar(900)  DEFAULT NULL COMMENT '收藏模特ids',
    `favorite_collections`       varchar(900)  DEFAULT NULL COMMENT '收藏模特专辑ids',
    `favorite_pics`       text  DEFAULT NULL COMMENT '收藏的图片',
    `favorite_vides`      text  DEFAULT NULL COMMENT '收藏视频s',
    `membership`      varchar(12)  DEFAULT NULL COMMENT '会员信息',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;