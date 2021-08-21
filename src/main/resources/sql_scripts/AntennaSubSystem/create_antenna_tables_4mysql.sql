-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 29, 2020 at 10:33 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `antenna`
--

-- --------------------------------------------------------

--
-- Table structure for table `antenna`
--

CREATE TABLE `antenna` (
  `antenna_id` int(11) NOT NULL,
  `antenna_code` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `antenna_type_code` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_created` datetime NOT NULL,
  `description` varchar(2048) COLLATE utf8mb4_unicode_ci NOT NULL,
  `main_antenna_code` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `antenna`
--

INSERT INTO `antenna` (`antenna_id`, `antenna_code`, `antenna_type_code`, `date_created`, `description`, `main_antenna_code`, `name`) VALUES
(1, 'ANT001', 'B', '2020-12-29 23:28:20', 'Base antenna without sub-antennas', 'ANT001', 'ANT001-SA1'),
(2, 'ANT002', 'B', '2020-12-29 23:28:20', 'Base antenna without sub-antennas', 'ANT002', 'ANT002-SA2'),
(3, 'ANT003', 'B', '2020-12-29 23:28:20', 'Base antenna with sub-antennas', 'ANT003', 'ANT001-SA3'),
(4, 'ANT004', 'S', '2020-12-29 23:28:20', 'Sub-antenna of ANT003', 'ANT003', 'ANT001-SA4'),
(5, 'ANT005', 'S', '2020-12-29 23:28:20', 'Sub-antenna of ANT003', 'ANT003', 'ANT001-SA5'),
(6, 'ANT006', 'B', '2020-12-29 23:28:20', 'Base antenna with sub-antennas', 'ANT006', 'ANT001-SA6'),
(7, 'ANT007', 'S', '2020-12-29 23:28:20', 'Sub-antenna of ANT006', 'ANT006', 'ANT001-SA7');

-- --------------------------------------------------------

--
-- Table structure for table `antenna_type`
--

CREATE TABLE `antenna_type` (
  `antenna_type_id` int(11) NOT NULL,
  `antenna_type_code` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_created` datetime NOT NULL,
  `description` varchar(2048) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `antenna_type`
--

INSERT INTO `antenna_type` (`antenna_type_id`, `antenna_type_code`, `date_created`, `description`, `name`) VALUES
(1, 'B', '2020-12-29 23:28:20', 'Types an antenna as a base antenna that may contain no or some sub-antennas.', 'Base'),
(2, 'S', '2020-12-29 23:28:20', 'Types an antenna as a sub-antenna of a base antenna that contains it.', 'Sub-Antenna');

-- --------------------------------------------------------

--
-- Table structure for table `audit`
--

CREATE TABLE `audit` (
  `audit_id` bigint(20) NOT NULL,
  `date_created` datetime DEFAULT NULL,
  `guid` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `operation_type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `request` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `data_id` int(11) NOT NULL,
  `date_created` datetime NOT NULL,
  `determinant` double NOT NULL,
  `value` double NOT NULL,
  `antenna_code` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `measurement_code` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `data`
--

INSERT INTO `data` (`data_id`, `date_created`, `determinant`, `value`, `antenna_code`, `measurement_code`) VALUES
(1, '2020-12-29 23:29:08', 100, -9, 'ANT001', 'GAIN'),
(2, '2020-12-29 23:29:08', 110, -6, 'ANT001', 'GAIN'),
(3, '2020-12-29 23:29:08', 130, 0, 'ANT001', 'GAIN'),
(4, '2020-12-29 23:29:08', 140, -3, 'ANT001', 'GAIN'),
(5, '2020-12-29 23:29:08', 150, -6, 'ANT001', 'GAIN'),
(6, '2020-12-29 23:29:08', 160, -9, 'ANT001', 'GAIN'),
(7, '2020-12-29 23:29:08', 100, -9, 'ANT002', 'HPOLAR'),
(8, '2020-12-29 23:29:08', 110, -6, 'ANT002', 'HPOLAR'),
(9, '2020-12-29 23:29:08', 130, 0, 'ANT002', 'HPOLAR'),
(10, '2020-12-29 23:29:08', 140, -3, 'ANT002', 'HPOLAR'),
(11, '2020-12-29 23:29:08', 150, -6, 'ANT002', 'HPOLAR'),
(12, '2020-12-29 23:29:08', 160, -9, 'ANT002', 'HPOLAR'),
(13, '2020-12-29 23:29:08', 100, -9, 'ANT003', 'VPOLAR'),
(14, '2020-12-29 23:29:08', 110, -6, 'ANT003', 'VPOLAR'),
(15, '2020-12-29 23:29:08', 130, 0, 'ANT003', 'VPOLAR'),
(16, '2020-12-29 23:29:08', 140, -3, 'ANT003', 'VPOLAR'),
(17, '2020-12-29 23:29:08', 150, -6, 'ANT003', 'VPOLAR'),
(18, '2020-12-29 23:29:08', 160, -9, 'ANT003', 'VPOLAR'),
(19, '2020-12-29 23:29:08', 100, -9, 'ANT004', 'HPOLAR'),
(20, '2020-12-29 23:29:08', 110, -6, 'ANT004', 'HPOLAR'),
(21, '2020-12-29 23:29:08', 130, 0, 'ANT004', 'HPOLAR'),
(22, '2020-12-29 23:29:08', 140, -3, 'ANT004', 'HPOLAR'),
(23, '2020-12-29 23:29:08', 150, -6, 'ANT004', 'HPOLAR'),
(24, '2020-12-29 23:29:08', 160, -9, 'ANT004', 'HPOLAR'),
(25, '2020-12-29 23:29:08', 100, -9, 'ANT005', 'VSWR'),
(26, '2020-12-29 23:29:08', 110, -6, 'ANT005', 'VSWR'),
(27, '2020-12-29 23:29:08', 130, 0, 'ANT005', 'VSWR'),
(28, '2020-12-29 23:29:08', 140, -3, 'ANT005', 'VSWR'),
(29, '2020-12-29 23:29:08', 150, -6, 'ANT005', 'VSWR'),
(30, '2020-12-29 23:29:08', 160, -9, 'ANT005', 'VSWR'),
(31, '2020-12-29 23:29:08', 100, -9, 'ANT007', 'VSWR'),
(32, '2020-12-29 23:29:08', 110, -6, 'ANT007', 'VSWR'),
(33, '2020-12-29 23:29:08', 130, 0, 'ANT007', 'VSWR'),
(34, '2020-12-29 23:29:08', 140, -3, 'ANT007', 'VSWR'),
(35, '2020-12-29 23:29:08', 150, -6, 'ANT007', 'VSWR'),
(36, '2020-12-29 23:29:08', 160, -9, 'ANT007', 'VSWR');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employee_id` bigint(20) NOT NULL,
  `authority` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `cellphone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `details` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `fullname` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_number` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `telephone` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `user_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employee_id`, `authority`, `cellphone`, `date_created`, `details`, `email`, `enabled`, `fullname`, `id_number`, `password`, `telephone`, `user_id`) VALUES
(1, NULL, NULL, NULL, NULL, 'warton@gmail.com', 1, 'John Warton', '001', NULL, NULL, 'user1'),
(2, NULL, NULL, NULL, NULL, 'lanister@gmail.com', 1, 'Mike Lanister', '002', NULL, NULL, 'user2'),
(3, NULL, NULL, NULL, NULL, 'Reeves@gmail.com', 1, 'Steve Reeves', '003', NULL, NULL, 'user3'),
(4, NULL, NULL, NULL, NULL, 'connor@gmail.com', 1, 'Ronald Connor', '004', NULL, NULL, 'user4'),
(5, NULL, NULL, NULL, NULL, 'Sal@gmail.com', 1, 'Jim Salvator', '005', NULL, NULL, 'user5'),
(6, NULL, NULL, NULL, NULL, 'henley@gmail.com', 1, 'Peter Henley', '006', NULL, NULL, 'user6'),
(7, NULL, NULL, NULL, NULL, 'carson@gmail.com', 1, 'Richard Carson', '007', NULL, NULL, 'user7'),
(8, 'ROLE_ADMIN', NULL, NULL, NULL, 'miles@gmail.com', 1, 'Honor Miles', '008', '$2a$10$XhADpGqFMvF33YzPmdV7JOTVbvRl9KqN5Tgxx3jHyOcVVxfRqPIwi', NULL, 'admin'),
(9, 'ROLE_USER', NULL, NULL, NULL, 'roggers@gmail.com', 1, 'Tony Roggers', '009', '$2a$10$8D29dyYhGe2Z8VPT3BV84eL6JptlMWgoFAJObP9Gq9IYG6Gc1DXSa', NULL, 'user'),
(10, 'ROLE_ADMIN', NULL, NULL, NULL, 'nmichael@gmail.com', 1, 'Nicholas Michael', '1956', '$2a$10$r4325krPku2wNegHS5zLY.4PWtbc4Xz7Zu4NfS2AWaiNVNONtrt.2', NULL, 'klidi'),
(11, 'ROLE_ADMIN', NULL, NULL, NULL, 'jd@gmail.com', 1, 'John Daratos', '19119911', '$2a$10$r4325krPku2wNegHS5zLY.4PWtbc4Xz7Zu4NfS2AWaiNVNONtrt.2', NULL, 'johnd');

-- --------------------------------------------------------

--
-- Table structure for table `file_image`
--

CREATE TABLE `file_image` (
  `file_id` int(11) NOT NULL,
  `antenna_code` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `antenna_type_code` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date_uploaded` datetime NOT NULL,
  `file_name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL,
  `measurement_code` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `number_of_records` int(11) NOT NULL,
  `status` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `file_image`
--

INSERT INTO `file_image` (`file_id`, `antenna_code`, `antenna_type_code`, `date_uploaded`, `file_name`, `measurement_code`, `number_of_records`, `status`) VALUES
(1, 'ANT001', 'B', '2020-12-29 23:29:26', 'ant001Measurements.csv', 'GAIN', 10, 'UPLOADED'),
(2, 'ANT004', 'S', '2020-12-29 23:29:26', 'ant004Measurements.csv', 'HPOLAR', 9, 'PUBLISHED'),
(3, 'ANT003', 'B', '2020-12-29 23:29:26', 'ant006Measurements.csv', 'VPOLAR', 12, 'REJECTED'),
(4, 'ANT007', 'S', '2020-12-29 23:29:26', 'ant006Measurements.csv', 'VSWR', 8, 'UPLOADED');

-- --------------------------------------------------------

--
-- Table structure for table `file_image_measurement`
--

CREATE TABLE `file_image_measurement` (
  `file_image_measurement_id` int(11) NOT NULL,
  `antenna_code` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `determinant` double NOT NULL,
  `value` double NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `file_image_measurement`
--

INSERT INTO `file_image_measurement` (`file_image_measurement_id`, `antenna_code`, `determinant`, `value`) VALUES
(1, 'ANT001', 100, 9),
(2, 'ANT001', 110, 6),
(3, 'ANT001', 120, 0),
(4, 'ANT001', 130, 3),
(5, 'ANT001', 140, 0),
(6, 'ANT001', 150, 3),
(7, 'ANT001', 160, 6),
(8, 'ANT004', 100, 6),
(9, 'ANT004', 110, 3),
(10, 'ANT004', 120, 0),
(11, 'ANT004', 130, 3),
(12, 'ANT004', 140, 9),
(13, 'ANT004', 150, 6),
(14, 'ANT004', 160, 0),
(15, 'ANT003', 100, 0),
(16, 'ANT003', 110, 3),
(17, 'ANT003', 120, 1),
(18, 'ANT003', 130, 2),
(19, 'ANT003', 140, 5),
(20, 'ANT003', 150, 8),
(21, 'ANT003', 160, 0),
(22, 'ANT007', 100, 0),
(23, 'ANT007', 110, 3),
(24, 'ANT007', 120, 1),
(25, 'ANT007', 130, 2),
(26, 'ANT007', 140, 5),
(27, 'ANT007', 150, 8),
(28, 'ANT007', 160, 0);

-- --------------------------------------------------------

--
-- Table structure for table `measurement`
--

CREATE TABLE `measurement` (
  `measurement_id` int(11) NOT NULL,
  `date_created` datetime NOT NULL,
  `description` varchar(2048) COLLATE utf8mb4_unicode_ci NOT NULL,
  `measurement_code` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `measurement`
--

INSERT INTO `measurement` (`measurement_id`, `date_created`, `description`, `measurement_code`, `name`) VALUES
(1, '2020-12-29 23:28:40', 'Responds according to frequency.', 'GAIN', 'Gain Measurement'),
(2, '2020-12-29 23:28:40', 'Responds according to frequency.', 'HPOLAR', 'Horizontal Polar'),
(3, '2020-12-29 23:28:40', 'Responds according to frequency.', 'VPOLAR', 'Vertical Polar'),
(4, '2020-12-29 23:28:40', 'Responds according to frequency.', 'VSWR', 'Voltage Standing Wave Ratio');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `antenna`
--
ALTER TABLE `antenna`
  ADD PRIMARY KEY (`antenna_id`),
  ADD UNIQUE KEY `UK_eg81icvbea8h10hbvksjp6d3` (`antenna_code`),
  ADD UNIQUE KEY `antenna_id` (`antenna_id`),
  ADD KEY `FKsfeyh03f4nohag4jehidpeurf` (`main_antenna_code`),
  ADD KEY `FKr80ujgqkqumvq5ay8ly146se7` (`antenna_type_code`);

--
-- Indexes for table `antenna_type`
--
ALTER TABLE `antenna_type`
  ADD PRIMARY KEY (`antenna_type_id`),
  ADD UNIQUE KEY `UK_eurq01tlopdi86s1latevclol` (`antenna_type_code`),
  ADD UNIQUE KEY `antenna_type_id` (`antenna_type_id`);

--
-- Indexes for table `audit`
--
ALTER TABLE `audit`
  ADD PRIMARY KEY (`audit_id`);

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`data_id`),
  ADD UNIQUE KEY `data_id` (`data_id`),
  ADD KEY `FK6kbxq7cwdrarquwvptb7lmkol` (`antenna_code`),
  ADD KEY `FK75nat7w9vmynf4chg1xpqqf64` (`measurement_code`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `file_image`
--
ALTER TABLE `file_image`
  ADD PRIMARY KEY (`file_id`),
  ADD UNIQUE KEY `UK_kcxvx8gcahc6x5purpbsavix3` (`antenna_code`),
  ADD UNIQUE KEY `file_id` (`file_id`),
  ADD KEY `FK7xptyxt460ptr7p7cd7xs07lt` (`antenna_type_code`),
  ADD KEY `FK4s4m64mgx9srh9bcfk6t2s71u` (`measurement_code`);

--
-- Indexes for table `file_image_measurement`
--
ALTER TABLE `file_image_measurement`
  ADD PRIMARY KEY (`file_image_measurement_id`),
  ADD UNIQUE KEY `file_image_measurement_id` (`file_image_measurement_id`),
  ADD KEY `FKlq1duhprsrdsdti4f541do1fl` (`antenna_code`);

--
-- Indexes for table `measurement`
--
ALTER TABLE `measurement`
  ADD PRIMARY KEY (`measurement_id`),
  ADD UNIQUE KEY `UK_kfq9jq91uddofoeiv4wpty8s1` (`measurement_code`),
  ADD UNIQUE KEY `measurement_id` (`measurement_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `antenna`
--
ALTER TABLE `antenna`
  MODIFY `antenna_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `antenna_type`
--
ALTER TABLE `antenna_type`
  MODIFY `antenna_type_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `audit`
--
ALTER TABLE `audit`
  MODIFY `audit_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `data`
--
ALTER TABLE `data`
  MODIFY `data_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `file_image`
--
ALTER TABLE `file_image`
  MODIFY `file_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `file_image_measurement`
--
ALTER TABLE `file_image_measurement`
  MODIFY `file_image_measurement_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `measurement`
--
ALTER TABLE `measurement`
  MODIFY `measurement_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
