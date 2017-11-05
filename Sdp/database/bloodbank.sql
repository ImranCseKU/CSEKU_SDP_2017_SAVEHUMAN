-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 05, 2017 at 05:22 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bloodbank`
--

-- --------------------------------------------------------

--
-- Table structure for table `donor`
--

CREATE TABLE `donor` (
  `id` int(11) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `discipline` varchar(100) DEFAULT NULL,
  `bloodgroup` varchar(100) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(1000) NOT NULL,
  `district` varchar(100) NOT NULL,
  `data` date DEFAULT NULL,
  `times` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `donor`
--

INSERT INTO `donor` (`id`, `name`, `discipline`, `bloodgroup`, `mobile`, `email`, `password`, `district`, `data`, `times`) VALUES
(222, 'Shoron2', 'CSE', 'AB+', '01912814844', 'shoron@gmail.com', '222', 'KHULNA', '2010-02-02', 0),
(150205, 'Mahmud', 'CSE', 'AB+', '01521479677', 'abdulahalmahmud@gmail.com', '150205', 'RAJSHAHI', '2017-06-11', 0),
(150208, 'raihan', 'CSE', 'O+', '01912814844', 'raihan150208@gmail.com', '150208', 'FARIDPUR', '2016-10-10', 0),
(150220, 'Sayed', 'CSE', 'B+', '01684577131', 'aaaa@gmail.com', '150220', 'DHAKA', '2016-05-03', 0),
(150225, 'shish', 'CSE', 'AB-', '01778310227', 'shish150225@gmail.com', '150225', 'KUSHTIA', '2016-06-10', 0),
(150226, 'Ratul', 'CSE', 'A+', '01998808427', 'ratul26@gmail.com', '150226', 'KHULNA', '2017-03-03', 0),
(150228, 'Monir', 'CSE', 'A+', '01778310227', 'monir150228@gmail.com', '150228', 'SATKHIRA', '2017-04-25', 0),
(150229, 'Durjoy', 'CSE', 'O+', '01778310227', 'durjoy@gmail.com', '150229', 'KHULNA', '2015-10-12', 0),
(150233, 'shahed', 'CSE', 'B+', '01912814844', 'shoron@gmail.com', 'cse', 'KHULNA', '2017-04-10', 0),
(150237, 'niloy', 'CSE', 'B+', '0191281484', 'shoron@gmail.com', 'cse', 'KHULNA', '2017-06-10', 0),
(150240, 'imran hussain', 'CSE', 'O+', '01998808427', 'imran150240@gmail.com', 'cse', 'CHUADANGA', '2016-10-10', 0),
(151737, 'Tuhin', 'CSE', 'O+', '01778310227', 'tuhin151737@gmail.com', '151737', 'KHULNA', '2016-10-27', 0),
(152020, 'EFAT', 'CSE', 'B-', '01778310227', 'efat@gmail.com', '152020', 'KHULNA', '2017-10-10', 0);

-- --------------------------------------------------------

--
-- Table structure for table `fcm`
--

CREATE TABLE `fcm` (
  `id` int(11) NOT NULL,
  `token` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fcm`
--

INSERT INTO `fcm` (`id`, `token`) VALUES
(15, 'dJRuBKi-I4c:APA91bFGl2DX5aqGgX0hbpFmm6uf_L1wydQ6S0Wcf4oORHsqG5vv1CHw1H0yMNPSOr4phi0Qyi9LZcST17fOuI8P1MuiTh9L-pIVc6ubuT5Mi5YETVo-DABeKEav734oroAwNzN6FcYQ'),
(16, 'dJTT3Kbbjtk:APA91bGWD-uE_S64sA63Lyc6yPFnQPMu3Eiklyto4xLvcpB7PTttvVasWdL_mnXdXoyFjEfDB5blQ30txo0M_phEv3HScnvmwi0ypoIzg2autkGxO4IDBe8Y0Q4Eov1I6eKP-CBh5NUH'),
(17, 'cka7jPmiXAE:APA91bHUrhApOZqOpRcY2iNPAZNBEoP-qkCAy3gAeiVjvO5s_CJLk_5bEeI8Bcg8TkQyVZG3My5mf-hKZp26vpgiQbVo7MBXzN6MuzawTl-wNzd5WE1vwzYoRJVQh9UC_4x8w_B30xyZ');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `donor`
--
ALTER TABLE `donor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `fcm`
--
ALTER TABLE `fcm`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fcm`
--
ALTER TABLE `fcm`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
