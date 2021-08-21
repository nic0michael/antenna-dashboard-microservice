-- Use the antenna database

USE antenna;			

DROP TABLE IF EXISTS employee;

CREATE TABLE `employee` (
  `employee_id` bigint(20) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `cellphone` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `id_number` varchar(128) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `user_id` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  UNIQUE KEY `UK_6tpj1vwpvfk1jljoylys4ixyp` (`id_number`) USING HASH,
  UNIQUE KEY `UK_mpps3d3r9pdvyjx3iqixi96fi` (`user_id`) USING HASH
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `employee`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1; 