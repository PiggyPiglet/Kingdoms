CREATE TABLE `players` (
	`id` INT(11) NULL DEFAULT NULL,
	`name` TINYTEXT NULL,
	`uuid` TINYTEXT NULL
)
ENGINE=InnoDB
;

-

CREATE TABLE `kingdoms` (
     `id` INT(11) NULL DEFAULT NULL,
     `name` TINYTEXT NULL,
     `players` TEXT NULL
)ENGINE=InnoDB;