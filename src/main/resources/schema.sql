CREATE TABLE `players` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` TINYTEXT NULL,
    `uuid` TINYTEXT NULL,
    `kingdom` TINYTEXT NULL,
    `rank` TINYTEXT NULL,
    PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

-

CREATE TABLE `kingdoms` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `name` TINYTEXT NULL,
    `uuid` TINYTEXT NULL,
    `players` TEXT NULL,
    PRIMARY KEY (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
