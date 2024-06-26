-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 26, 2024 at 12:54 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.0.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projectdad`
--

-- --------------------------------------------------------

--
-- Table structure for table `order_list`
--

CREATE TABLE `order_list` (
  `orderID` int(11) NOT NULL,
  `customerName` varchar(255) NOT NULL,
  `phoneNum` varchar(20) NOT NULL,
  `address` varchar(255) NOT NULL,
  `itemOrdered` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `totalPrice` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `order_list`
--

INSERT INTO `order_list` (`orderID`, `customerName`, `phoneNum`, `address`, `itemOrdered`, `status`, `totalPrice`) VALUES
(1, 'Balqis', '011555665', 'Damansara', 'Dettol - 2, Flavettes - 1', 'PACKING', '69.70'),
(2, 'Akmal', '001999654', 'Puchong', 'Darlie - 1, Rejoice - 2', 'DELIVERED', '37.70'),
(3, 'Hakim', '017889965', 'Serkat', 'Lifebuoy - 2, Darlie - 1, Pantene - 3', 'DELIVERED', '94.40'),
(4, 'Akmal', '0119965', 'Bangsar', 'Systema - 2', 'PACKING', '21.80'),
(5, 'Akmal', '01172731088', 'Puchong', 'Dove - 1, Colgate - 3, Sunsilk - 2, Redoxon - 1', 'TRANSIT', '146.30'),
(6, 'Hakim', '011222555', 'Pontian', 'Lifebuoy - 1, Colgate - 2', 'DELIVERED', '45.70'),
(7, 'Balqis', '12345', 'Pontian', 'Pantene - 1', 'TRANSIT', '16.90'),
(8, 'Akmal', '123456', 'Puchong', 'Lifebuoy - 1, Colgate - 2, Rejoice - 3', 'PACKING', '90.40');

-- --------------------------------------------------------

--
-- Table structure for table `product_list`
--

CREATE TABLE `product_list` (
  `productID` int(5) NOT NULL,
  `productCategory` varchar(255) NOT NULL,
  `productName` varchar(255) NOT NULL,
  `quantity` int(5) NOT NULL,
  `price` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `product_list`
--

INSERT INTO `product_list` (`productID`, `productCategory`, `productName`, `quantity`, `price`) VALUES
(1, 'Body Wash', 'Antabax', 9, '19.90'),
(2, 'Body Wash', 'Lifebuoy', 8, '17.90'),
(3, 'Body Wash', 'Dove', 22, '22.90'),
(4, 'Toothpaste', 'Systema', 34, '10.90'),
(5, 'Toothpaste', 'Colgate', 9, '13.90'),
(6, 'Toothpaste', 'Darlie', 2, '7.90'),
(7, 'Shampoo', 'Rejoice', 24, '14.90'),
(8, 'Shampoo', 'Pantene', 3, '16.90'),
(9, 'Shampoo', 'Sunsilk', 11, '24.90'),
(10, 'Vitamin', 'Flavettes', 27, '29.90'),
(11, 'Vitamin', 'Berocca', 33, '34.90'),
(12, 'Vitamin', 'Redoxon', 26, '31.90');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(10) NOT NULL,
  `username` varchar(255) NOT NULL,
  `passwd` varchar(255) NOT NULL,
  `role` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `username`, `passwd`, `role`) VALUES
(1, 'min', 'min', 0),
(2, 'Akmal', '111', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `order_list`
--
ALTER TABLE `order_list`
  ADD PRIMARY KEY (`orderID`);

--
-- Indexes for table `product_list`
--
ALTER TABLE `product_list`
  ADD PRIMARY KEY (`productID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `order_list`
--
ALTER TABLE `order_list`
  MODIFY `orderID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `product_list`
--
ALTER TABLE `product_list`
  MODIFY `productID` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
