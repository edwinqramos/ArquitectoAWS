CREATE DATABASE IF NOT EXISTS encuestabd CHARACTER SET latin1 COLLATE latin1_swedish_ci;

USE encuestabd;

SET foreign_key_checks = 0;

DROP TABLE IF EXISTS tbl_encuesta;

SET foreign_key_checks = 1;

CREATE TABLE tbl_encuesta(
   	id INT(3) NOT NULL AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
	lastname VARCHAR(50) NOT NULL,
    age INT(3) NOT NULL,
    language VARCHAR(20) NOT NULL,
    create_at DATETIME NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=INNODB;
