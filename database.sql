CREATE DATABASE `shop`  DEFAULT CHARACTER SET utf8 ;
use shop;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` TEXT NOT NULL,
  `price` double NOT NULL,
  `count` int(11) NOT NULL,
  `img` varchar(200) NOT NULL,
  `createTime` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

INSERT INTO `user` (`username`, `password`, `phone`, `address`, `type`) VALUES ( 'admin', '123456', '888888', '花果山', '1');

