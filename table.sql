CREATE TABLE `blog` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `author` (
  `author_id` int(16) NOT NULL AUTO_INCREMENT,
  `author_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8;

CREATE TABLE `comment` (
  `comment_id` int(16) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `bid` int(16) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

INSERT INTO `blog` (`bid`, `name`, `author_id`) VALUES (1, 'RabbitMQ延时消息', 1001);
INSERT INTO `blog` (`bid`, `name`, `author_id`) VALUES (2, 'MyBatis源码分析', 1008);
INSERT INTO `author` (`author_id`, `author_name`) VALUES (1001, '青山');
INSERT INTO `comment` (`comment_id`, `content`, `bid`) VALUES (1, '写得真好，学习了', 1);
INSERT INTO `comment` (`comment_id`, `content`, `bid`) VALUES (2, '刚好碰到这个问题，谢谢', 1);


