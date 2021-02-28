# 模特的图集
CREATE TABLE myfirst.mmc_model_collection
(
    id           int(12) primary key auto_increment NOT NULL,
    beatyName    varchar(100)                       NULL,
    CollectionId varchar(100)                       NULL
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_general_ci;