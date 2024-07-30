CREATE DATABASE IF NOT EXISTS `book_tracker`;
USE `book_tracker`;

-- Table structure for table `book`
DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `number_of_pages` int DEFAULT NULL,
  `date_of_release` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;