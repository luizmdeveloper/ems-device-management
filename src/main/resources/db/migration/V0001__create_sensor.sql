CREATE TABLE IF NOT EXISTS `sensors` (

    `id` BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(60),
    `protocol` varchar(50),
    `model` varchar(50),
    `location` varchar(100),
    `ip` varchar(20),
    `enabled` SMALLINT
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;