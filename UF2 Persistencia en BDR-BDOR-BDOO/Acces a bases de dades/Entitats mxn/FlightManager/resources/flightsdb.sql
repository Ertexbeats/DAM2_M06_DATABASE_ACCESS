CREATE USER 'flightssusr'@'localhost' IDENTIFIED BY 'flightspwd';
CREATE DATABASE flightsdb
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
GRANT SELECT, INSERT, UPDATE, DELETE ON flightsdb.* TO 'flightssusr'@'localhost';
USE flightsdb;
CREATE TABLE `flights` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`code` VARCHAR(10) NOT NULL UNIQUE,
`capacity` INT(2) DEFAULT 0,
`date` DATE NOT NULL,
`time` TIME NOT NULL,
PRIMARY KEY (`id`)
);
CREATE TABLE `passengers` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`phone` VARCHAR(15) NOT NULL UNIQUE,
`minor` BOOLEAN DEFAULT true,
PRIMARY KEY (`id`)
);
CREATE TABLE `flightspassengers` (
`flight_id` INT(4) NOT NULL,
`passenger_id` INT(4) NOT NULL,
PRIMARY KEY (`flight_id`, `passenger_id`)
);
ALTER TABLE flightspassengers ADD FOREIGN KEY fk_flight (flight_id) REFERENCES flights(id);
ALTER TABLE flightspassengers ADD FOREIGN KEY fk_passenger (passenger_id) REFERENCES passengers(id);