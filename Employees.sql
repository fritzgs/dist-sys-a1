-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 09, 2018 at 11:33 PM
-- Server version: 10.1.35-MariaDB
-- PHP Version: 7.2.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Assignment1`
--

-- --------------------------------------------------------

--
-- Table structure for table `Employees`
--

CREATE TABLE `Employees` (
  `ID` int(11) NOT NULL,
  `Name` varchar(62) NOT NULL,
  `Department` varchar(30) NOT NULL,
  `Manager` varchar(62) DEFAULT NULL,
  `ManagerID` int(11) DEFAULT NULL,
  `Location` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Employees`
--

INSERT INTO `Employees` (`ID`, `Name`, `Department`, `Manager`, `ManagerID`, `Location`) VALUES
(1, 'John Doe', 'Business', NULL, NULL, 'Dublin'),
(2, 'Geraldine Patton', 'Human Resources', 'John Doe', 1, 'Dublin'),
(3, 'Issac Kendall', 'Engineering', 'John Doe', 1, 'Dublin'),
(4, 'Trystan Roman', 'IT', 'John Doe', 1, 'Waterford'),
(5, 'Samara Le', 'Engineering', 'Issac Kendall', 3, 'Waterford'),
(6, 'Paolo Hope', 'Human Resources', 'Geraldine Patton', 2, 'Waterford'),
(7, 'Karim Avila', 'IT', 'Trystan Roman', 4, 'Waterford'),
(8, 'Alanna Sharpe', 'Engineering', 'Issac Kendall', 3, 'Waterford'),
(9, 'Marni Farley', 'Engineering', 'Samara Le', 5, 'Waterford'),
(10, 'Autumn Goddard', 'Business', 'John Doe', 1, 'Dublin'),
(11, 'Zavier Macleod', 'Engineering', 'Samara Le', 5, 'Waterford');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
