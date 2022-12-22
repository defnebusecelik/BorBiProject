-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/


SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `borbi`

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `company`
--

CREATE TABLE `company` (
  `companyId` int(11) NOT NULL,
  `companyName` varchar(100) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `airline`
--

CREATE TABLE `airline` (
  `airlineId` int(11) NOT NULL,
  `airlineCountryId` int(11) NOT NULL,
  `airlineCityId` int(11) NOT NULL,
  `airlineName` varchar(100) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `city`
--

CREATE TABLE `city` (
  `airlineCityId` int(11) NOT NULL,
  `airlineCityName` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `country`
--

CREATE TABLE `country` (
  `airlineCountryId` int(11) NOT NULL,
  `airlineCountryName` varchar(500) CHARACTER SET utf8 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `aboutUser`
--

CREATE TABLE `aboutUser` (
  `userId` int(11) NOT NULL,
  `userFirstName` varchar(100) DEFAULT NULL,
  `userLastName` varchar(100) DEFAULT NULL,
  `userMail` varchar(100) DEFAULT NULL,
  `userPassword` varchar(100) DEFAULT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Tablo döküm verisi `aboutUser`
--

INSERT INTO `aboutUser` (`userId`, `userFirstName`, `userLastName`, `userMail`, `userPassword`) VALUES
(1, 'Defne', 'Çelik', 'defne@gmail.com', '123456'),
(2, 'Ecrin', 'Çelik', 'ecrin@gmail.com', '654321');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `message`
--

CREATE TABLE `message` (
  `messageId` int(11) NOT NULL,
  `messageName` varchar(500) NOT NULL,
  `messageMail` varchar(500) NOT NULL,
  `messageAbout` varchar(500) NOT NULL,
  `messageContent` text NOT NULL,
  `messageDate` timestamp NOT NULL DEFAULT current_timestamp(),
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `reservation`
--

CREATE TABLE `reservation` (
  `reservationId` int(11) NOT NULL,
  `reservaitonDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `flightId` int(11) NOT NULL,
  `customerId` int(11) NOT NULL,
  `passengerNameRecord` varchar(11) NOT NULL DEFAULT '45645',
  `customerName` varchar(500) NOT NULL,
  `customerLastName` varchar(500) NOT NULL,
  `customerMail` varchar(500) NOT NULL,
  `customerPhone` varchar(11) NOT NULL,
  `versionNumber` int(5) NOT NULL,
  `flightDate` varchar(20) NOT NULL,
  `ticketPayment` decimal(6,2) NOT NULL,
  `seatNumber` varchar(10) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `aboutFlight`
--

CREATE TABLE `aboutFlight` (
  `companyId` int(11) NOT NULL
  `planeId` int(11) NOT NULL,
  `companyName` varchar(500) NOT NULL,
  `seat` int(10) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `flight`
--

CREATE TABLE `flight` (
  `flightId` int(11) NOT NULL,
  `flightDate` date NOT NULL,
  `flightHour` time NOT NULL,
  `flightTime` varchar(50) NOT NULL,
  `comnpanyId` int(11) NOT NULL,
  `planeId` int(11) NOT NULL,
  `ticketPayment` decimal(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dökümü yapılmış tablolar için indeksler
--
-- Tablo için indeksler `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`companyId`);

--
-- Tablo için indeksler `airline`
--
ALTER TABLE `airline`
  ADD PRIMARY KEY (`flightId`);

--
-- Tablo için indeksler `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`airlineCityId`);

--
-- Tablo için indeksler  `country`
--
ALTER TABLE `country`
  ADD PRIMARY KEY (`cairlineCountryId`);

--
-- Tablo için indeksler `aboutUser`
--
ALTER TABLE `aboutUser`
  ADD PRIMARY KEY (`userId`);

--
-- Tablo için indeksler `reservasion`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservationId`);

--
-- Tablo için indeksler `aboutFlight`
--
ALTER TABLE `aboutFlight`
  ADD PRIMARY KEY (`planeId`);

--
-- Tablo için indeksler `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`flightId`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `company`
--
ALTER TABLE `company`
  MODIFY `companyId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- Tablo için AUTO_INCREMENT değeri `airline`
--
ALTER TABLE `airline`
  MODIFY `airlineId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- Tablo için AUTO_INCREMENT değeri `city`
--
ALTER TABLE `city`
  MODIFY `airlineCityId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Tablo için AUTO_INCREMENT değeri `country`
--
ALTER TABLE `country`
  MODIFY `airlineCountryId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Tablo için AUTO_INCREMENT değeri `aboutUser`
--
ALTER TABLE `aboutUser`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- Tablo için AUTO_INCREMENT değeri `message`
--
ALTER TABLE `message`
  MODIFY `messageId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Tablo için AUTO_INCREMENT değeri `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Tablo için AUTO_INCREMENT değeri `aboutFlight`
--
ALTER TABLE `aboutFlight`
  MODIFY `planeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Tablo için AUTO_INCREMENT değeri `flight`
--
ALTER TABLE `flight`
  MODIFY `flightId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
COMMIT;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
