create USER 'usrsubscribers'@'localhost' IDENTIFIED BY 'pswsubscribers';
create DATABASE dbsubscribers
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;
GRANT select, insert, update, delete ON dbsubscribers.* TO 'usrsubscribers'@'localhost';
use dbsubscribers;
create TABLE `subscribers` (
`id` INT(4) NOT NULL AUTO_INCREMENT,
`name` VARCHAR(40) NOT NULL,
`address` VARCHAR(40) DEFAULT NULL,
`phone` VARCHAR(40) UNIQUE DEFAULT NULL,
`age` INT DEFAULT 0,
PRIMARY KEY (`id`)
) ENGINE=InnoDB;
insert into `subscribers` (`id`, `name`, `address`, `phone`, `age`)
values
(1, 'name01', 'add01', 'phone01', 11),
(2, 'name02', 'add02', 'phone02', 12),
(3, 'name03', 'add03', 'phone03', 13),
(4, 'name04', 'add04', 'phone04', 14),
(5, 'name05', 'add05', 'phone05', 15),
(6, 'name06', 'add06', 'phone06', 16),
(7, 'name07', 'add07', 'phone07', 17),
(8, 'name08', 'add08', 'phone08', 18);