Authors
Workshop 2 Tuesday 6pm-9pm
Group 2:

Carol Alonso Diaz
Katherine Lee
Jialan Guo
Pyae Phyo Zaw
Lorin Chanel Legaspi Raba

Instructions

Ensure Java 8 is installed on your device.

Open Netbeans on your device and select ‘Open Project’ under the File tab.

Navigate to where you have extracted the ISDAssignment1 Netbeans project folder and press ‘Open Project’.

Open DBeaver, making sure that your MySQL username and password are correct.

Run the SQL code under the subheading SQL in DBeaver.

Open DB.java under the ‘uts.isd.model.dao’ folder in Netbeans. Make sure that the database name, username and password correspond to what is in DBeaver.

Right click on the project in Netbeans and select ‘Clean and Build’. Wait for the build process to finish.

Right click on the project again and select ‘Run’. It may take some time for this to happen, as the Glassfish server will need to be started.

You have now run our IotBay Web Application successfully.



SQL
Online User Access Management
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `userEmail` varchar(50) NOT NULL,
  `userPassword` varchar(20) NOT NULL,
  `userFullName` varchar(50) NOT NULL,
  `userPhone` varchar(10) NOT NULL,
  `userAddress` varchar(50) NOT NULL,
  `userDOB` date NOT NULL,
  `userGender` varchar(1) NOT NULL,
  `userType` varchar(1) NOT NULL DEFAULT 'C',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `users_unique_1` (`userEmail`)
);

INSERT INTO users
(userID, userEmail, userPassword, userFullName, userPhone, userAddress, userDOB, userGender, userType)
VALUES(1, 'apples@gmail.com', '1721Iphone', 'Susan Brown', '0400000000', '7 Rooney St, Hurstville, NSW, 2193', '1971-05-02', 'F', 'C')
,(2, 'admin@iotbay.com', 'logmein', 'The Admin', '0411111111', '8 Nettle Rd, Campsie, NSW, 2284', '2000-12-24', 'F', 'S')
,(3, 'layla@winx.com', 'winxclub', 'Layla Houssain', '0422222222', '42 Pixie Ave, Domino, QLD, 7023', '1992-07-21', 'F', 'C')
,(4, 'employee@iotbay.com', 'paymepls', 'Employee WorksaLot', '0433333333', '19 Money Dr, Linton, VIC, 2024', '2001-04-09', 'M', 'S')
,(5, 'riverview@sims.com', 'electronicarts', 'Nick Alto', '0444444444', '5 Riverwood Rd, West Ryde, NT, 6253', '1965-11-11', 'M', 'C')
,(6, 'TSwift@gmail.com', 'poetsdepartment', 'Taylor Swift', '0455555555', '22 Grammys St, Hollywood, ACT, 0809', '1989-12-13', 'F', 'C')
,(7, 'ARamanujan@iotbay.com', 'numb3rs', 'Amita Ramanujan', '0466666666', '17 Federal Dr, Chippendale, NSW, 2000', '1982-06-06', 'F', 'S')
,(8, 'SakamotoR@phantom.com', 'Persona5', 'Ryuji Sakamoto', '0477777777', '15 Shujin St, Jefferson, SA, 4021', '2002-09-29', 'M', 'C')
,(9, 'ChidiA@hotmail.com', 'theg00dplace', 'Chidi Anagonye', '0488888888', '5 Florida Rd, Wolli Creek, NSW, 2905', '1990-03-17', 'M', 'C')
,(10, 'HighHarper@live.com', 'abs0Lut3', 'Jaheira Harper', '0499999999', '40 Baldur Ave, Gate, WA, 9043', '1954-01-19', 'F', 'C')
,(11, 'HiraiMomo@twice.com', 'feelSpecial', 'Momo Hirai', '0412345678', '2 Ingleburn Rd, Glenfield, TAS, 1921', '1996-11-09', 'F', 'C')
,(12, 'AlecH@leverageinc.com', 'thehacker', 'Alec Hardison', '0423456789', '17/4 Oregon St, Narwee, QLD, 2214', '1997-07-12', 'M', 'C')
,(13, 'AHotchner@gmail.com', 'crim1nalM1nds', 'Aaron Hotchner', '0412222222', '11/6 Harrison St, Panania, NSW, 7812', '1982-12-09', 'M', 'C')
,(14, 'SabCarpenter@hotmail.com', 'imA$inger', 'Sabrina Carpenter', '0413333333', '9 Florida Rd, Wolli Creek, NSW, 2905', '1999-05-11', 'F', 'C')
,(15, 'NatashaR@marvel.com', 'blackwidow', 'Natasha Romanoff', '0414444444', '21 Liberty Dr, Darlinghurst, NSW, 2010', '1954-07-09', 'F', 'C')
,(16, 'JKirk@enterprise.com', 'exploreSNW', 'James Tiberius Kirk', '0415555555', '9 Greendale Rd, Wallacia, TAS, 1941', '2003-02-24', 'M', 'C')
,(17, 'CharlesE@calsci.com', 'consultant', 'Charles Eppes', '0416666666', '15 Professor Ave, Mahren, WA, 1002', '1996-10-19', 'M', 'C')
,(18, 'AlinaB@gmail.com', 'coffebeans', 'Alina Bell', '0417777777', '30 Greenwich St, Panania, NSW, 7812', '1962-10-19', 'F', 'C')
,(19, 'cosrx@wcosmetics.com', 'snail99', 'Snail Mucin', '0418888888', '29 Rutledge St, Narwee, QLD, 2214 ', '2003-02-24', 'M', 'C')
,(20, 'PLeith@gbbo.com', 'readysetbake', 'Prue Leith', '0419999999', '20 England St, Shaftsbury, TAS, 1072', '1950-08-09', 'F', 'C')
,(21, 'deleteTest@gmail.com', 'delete', 'Delete Dee', '0412131232', '8 Delete Ave, Delete, NSW, 2101', '1982-10-10', 'M', 'S')
,(30, 'test3@testemail.com', 'test', 'Tester', '0444444444', 'Ray St', '1994-02-02', 'F', 'C');

CREATE TABLE `accesslogs` (
 `accessLogID` int NOT NULL AUTO_INCREMENT,
 `userID` int NOT NULL,
 `accessLogDate` date NOT NULL DEFAULT (curdate()),
 `loginTime` time NOT NULL DEFAULT (now()),
 `logoutTime` time DEFAULT NULL,
 PRIMARY KEY (`accessLogID`),
 KEY `accesslogs_fk` (`userID`),
 CONSTRAINT `accesslogs_fk` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO accesslogs
(accessLogID, userID, accessLogDate, loginTime, logoutTime)
VALUES(1, 2, '2017-08-27', '04:30:05', '11:47:33'),(2, 18, '2018-01-26', '07:56:37', '08:16:24'),(3, 14, '2019-01-12', '06:09:23', '09:52:17'),(4, 14, '2019-02-25', '07:26:50', '13:07:39'),(5, 2, '2019-09-18', '13:51:58', '04:02:59'),(6, 16, '2019-09-23', '03:17:53', '07:16:43'),(7, 15, '2020-12-02', '14:00:19', '17:54:10'),(8, 16, '2021-02-04', '22:58:28', '10:08:41'),(9, 20, '2021-10-13', '08:05:31', '02:12:50'),(10, 7, '2022-02-24', '15:57:31', '11:22:35'),(11, 10, '2022-05-20', '05:45:51', '13:10:37'),(12, 5, '2022-06-23', '07:50:42', '11:15:31'),(13, 20, '2022-07-05', '15:07:04', '03:10:02'),(14, 18, '2023-01-06', '13:57:30', '22:50:13'),(15, 14, '2023-02-15', '20:52:24', '11:57:01'), (16, 9, '2023-02-24', '15:16:09', '06:02:26'), (17, 6, '2023-06-30', '09:34:58', '15:09:02'),(18, 10, '2023-10-14', '05:35:55', '16:04:24'),(19, 7, '2024-02-18', '02:26:51', '20:51:47'),(20, 7, '2024-04-12', '22:22:07', '17:27:20'),(21, 3, '2024-05-03', '19:52:26', NULL);

Device Collection Management
DROP TABLE IF EXISTS `Product`; 

CREATE TABLE `Product` (
  `ProductID` int NOT NULL AUTO_INCREMENT,
  `ProductName` varchar(100) NOT NULL,
  `ProductType` varchar(100) NOT NULL,
  `ProductUnitPrice` double NOT NULL,
  `ProductDetails` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ProductInStock` int NOT NULL,
  `ProductImg` varchar(100),
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO Product (ProductID, ProductName, ProductType, ProductUnitPrice, ProductDetails, ProductInStock, ProductImg)
VALUES(1,'DS18B20', 'Temperature / Humidity / Air Pressure / Gas', 4, 'DS18B20 measures temperature range of -55°C to +125°C.', 0, 'DS18B20.webp'),
(2,'DHT22', 'Temperature / Humidity / Air Pressure / Gas', 4, 'DHT22 measure all humidity ranges from 0-100% with an accuracy of 2%.', 50, 'DHT22.webp'),
(3,'Moisture Sensor', 'Temperature / Humidity / Air Pressure / Gas', 4.5, 'Moisture Sensor measures moisture by installing it in the ground, the reading value on raspberry pi requires MCP3008.', 50, 'Moisture Sensor.webp'),
(4,'Capacitive ground moisture sensor', 'Temperature / Humidity / Air Pressure / Gas', 6, 'This type of moisture sensor is erosion resistant and has high precision in moisture measuring.', 50,'Capacitive ground moisture sensor.webp'),
(5,'BMP180 Barometer', 'Temperature / Humidity / Air Pressure / Gas', 5.5, 'BMP180 Barometer measures air pressure and is controlled via I2C on the Raspberry Pi.', 50,'BMP180 Barometer.webp'),
(6,'MQ-2 Gas Sensor', 'Temperature / Humidity / Air Pressure / Gas', 2, 'The MQ gas sensors can detect different gases at room temperature. It can recognise methane, butane, LPG and smoke. These sensors can be very hot and they should not be touched directly. These modules also work analogically with 5V, you need also a MCP3008 as well as a 3.3V-5V TTL to read the signals.', 50, 'MQ-2 Gas Sensor.webp'),
(7,'PIR Motion Sensor', 'Motion', 2, 'PIR Motion Sensor Sends a signal when a moving object is close to the sensor. Can be used in various building projects, such as activating the smart home system when guests approach the entry.', 50, 'PIR Motion Sensor.webp'),
(8,'HC-SR04 ultrasonic sensor', 'Motion', 3, 'HC-SR04 measuring the time elapsed between transmitting and receiving an ultrasound signal.', 50, 'HC-SR04 ultrasonic sensor.webp'),
(9,'Magnetic Switch / Reed Relay', 'Motion', 2.5, 'Magnetic Switch automatically switches on when the magnet is near, and measures voltage passes through it.', 50, 'Magnetic Switch : Reed Relay.webp'),
(10,'GP2Y0A02YK', 'Motion', 10, 'A highly accurate distance meter, the module is limited to a range of 20-150cm', 50, 'GP2Y0A02YK.webp'),
(11,'RFID-RC522 – Inductive RFID card reader', 'Motion', 4.5, 'The RFID-RC522 is a card reader for check cards, it reads when a card approaches within a few centimetres of it.', 50, 'RFID-RC522 – Inductive RFID card reader.webp'),
(12,'GPS NEO-6M Module', 'Navigation Module', 10, 'The most widely used, well-known GPS receiver. It is compatible with the Raspberry Pi packages minicom and gpsd, which makes reading the coordinates very easy.', 50, 'GPS NEO-6M Module.webp'),
(13,'USB GPS Receiver', 'Navigation Module', 15, 'These USB GPSs are the same accurate satellite navigation modules that are compatible with Windows, Linux and Mac. It requires no additional connection.', 50, 'USB GPS Receiver.webp'),
(14,'MPU-6050 Gyroscope', 'Navigation Module', 3.5, 'MPU-6050 Gyroscope detects the rotation along the three axes, and can be used in the robot arm to detect the motion angle.', 50, 'MPU-6050 Gyroscope.webp'),
(15,'HMC5883L / GY-271 Compass', 'Navigation Module', 3.5, 'The directional display of this type of compass can be read digitally and in-unit radians.', 50, 'HMC5883L : GY-271 Compass.webp'),
(16,'DS1307RTC', 'Navigation Module', 2.5, 'A real-time clock (RTC) module initialises and saves the current time even if the power supply and internet are not present.', 50,'DS1307 RTC.webp'),
(17,'433 MHz Set', 'Raspberry Pi Sensors – Wireless / Infrared (IR) / Bluetooth', 2.5, 'The set includes 433 MHz transmitter and receiver that transmit signals via radio.', 50, '433 MHz Set.webp'),
(18,'2.4 GHz NRF24L01+ Modul', 'Raspberry Pi Sensors – Wireless / Infrared (IR) / Bluetooth', 3.5, 'An advanced 2.4 GHz frequency module that supports larger data transmission at once.', 50, '2.4 GHz NRF24L01.webp'),
(19,'Radio controlled outlets / Power sockets', 'Raspberry Pi Sensors – Wireless / Infrared (IR) / Bluetooth', 12.5, 'Work with 433 MHz radio signals. By reading the codes of the remote control with a receiver on the Raspberry Pi, one can switch these radio sockets individually.', 50, 'Radio controlled outlets : Power sockets.webp'),
(20,'Si4703 Radio receiver', 'Raspberry Pi Sensors – Wireless / Infrared (IR) / Bluetooth', 10, 'The Si470x module offers the option to upgrade the Pi to a radio receiver, which can be very interesting in Car PCs or Raspberry Pi Jukeboxes.', 50, 'Si4703 Radio receiver.webp'),
(21,'Bluetooth Adapter', 'Raspberry Pi Sensors – Wireless / Infrared (IR) / Bluetooth', 8.5, 'This Bluetooth adapter provides users with the opportunity to integrate Bluetooth modules into their Raspberry Pi.', 50, 'Bluetooth Adapter.webp');

Shipment Management
CREATE TABLE `Shipment` (
 `ShipmentID` int NOT NULL AUTO_INCREMENT,
 `ShipmentAddress` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
 `ShipmentMethod` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
 `ShipmentStatus` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
 `ShipmentDate` datetime NOT NULL,
 `UserID` int DEFAULT NULL,
 PRIMARY KEY (`ShipmentID`),
 KEY `Shipment_Customer_FK` (`UserID`),
 CONSTRAINT `Shipment_users_FK` FOREIGN KEY (`UserID`) REFERENCES `users` (`userID`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




INSERT INTO Shipment(ShipmentAddress, ShipmentMethod , ShipmentStatus, ShipmentDate, UserID)
VALUES('24 Shirley Street, Maudsland QLD 4210', 'Express', 'Dispatched', '2024-05-07', 19),
('60 Ashton Road, North Bannister WA 6390', 'Express', 'Pending', '2024-05-04', 17),
('44 Florabunda Lane, Theresa Park NSW 2570', 'Express', 'Pending', '2024-05-07', 1),
('81 Ferry Avenue, Svensson Heights QLD 4670', 'Standard', 'On Route', '2024-04-30', 19),
('4 Myrtle Street, Whorouly VIC 3735', 'Standard', 'Delivered', '2024-04-10', 3),
('47 Spencer Street, Alexandra Headland QLD 4570', 'Express', 'Delivered', '2024-03-25', 19),
('48 Throne Street, Marlee NSW 2429', 'Express', 'On Route', '2024-04-27', 15),
('3 Kaesler Road, Beachport SA 5280', 'Standard', 'Delivered', '2024-04-13', 8),
('73 Shannon Court, Dawson SA 5422', 'Standard', 'Delivered', '2024-03-20', 8),
('86 Cecil Street, Pennant Hills NSW 2120', 'Standard', 'Delivered', '2024-02-28', 1),
('18 South Molle Boulevard, Wills QLD 4829','Standard', 'On Route', '2024-05-01', 3),
('62 Hillsdale Road, Eidsvold West QLD 4627', 'Standard', 'Pending', '2024-05-03', 3),
('31 Lewin Street, Brookdale NSW 2656', 'Express', 'Delivered', '2024-04-25', 14),
('20 Zipfs Road, Riverview QLD 4303', 'Express', 'On Route', '2024-05-02', 12),
('52 Black Point Drive, Cleve SA 5640', 'Standard', 'Pending', '2024-05-02', 8),
('48 Brown Street, Chatswood West NSW 2067', 'Standard', 'Delivered', '2024-02-24', 15),
('80 Corny Court, Bookabie SA 5690', 'Express', 'On Route', '2024-05-09', 8),
('16 Edward Bennett Drive, Burwood Heights NSW 2136', 'Standard', 'Delivered', '2024-05-07', 9),
('41 Acheron Road, Hazelwood North VIC 3840', 'Express', 'Delivered', '2024-01-18', 3),
('5 Friar John Way, Karnup WA 6176', 'Express', 'On Route', '2024-05-07', 17),
('51 Walters Street, Upper Ryans Creek VIC 3673', 'Express', 'Delivered', '2024-01-12',3),
('43 Kerma Crescent, Lithgow NSW 2790', 'Standard', 'Delivered', '2024-02-23', 9),
('93 Capper Street, Dundarrah QLD 4625', 'Express', 'On Route', '2024-05-02', 12),
('71 Normans Road, Parkwood VIC 3315', 'Express', 'Pending', '2024-05-10', 3);


INSERT INTO IoTBayTest.Shipment(ShipmentID, ShipmentAddress, ShipmentMethod , ShipmentStatus, ShipmentDate, UserID)
VALUES(38, '79 Ultimo St, Sydney NSW 2000', 'Standard', 'Pending', '2024-05-16', null),
(50, '89 Ultimo St, Sydney NSW 2000', 'Standard', 'Pending', '2024-04-17', 10);










Payment Table


CREATE TABLE `payment` (
 `PaymentID` int NOT NULL AUTO_INCREMENT,
 `PaymentMethod` varchar(20) NOT NULL,
 `ExpiryDate` date NOT NULL,
 `PaymentCVC` int NOT NULL,
 `PaymentCardNumber` int NOT NULL,
 `UserID` int NULL,
 PRIMARY KEY (`PaymentID`),
 KEY `payment_users_FK` (`UserID`),
 CONSTRAINT `payment_users_FK` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
Insert records into Payment Table
INSERT INTO payment
(PaymentID, PaymentMethod, ExpiryDate, PaymentCVC, PaymentCardNumber , UserID)
VALUES(1, 'Paypal', '2026-07-09', 123, 222333444, 1),
(2, 'Visa', '2024-02-01', 891, 688877767, 1),
(3, 'Paypal', '2028-09-01', 179, 889668256, 2),
(4, 'Google Pay', '2027-01-10', 154, 660394842, 3),
(5, 'Visa', '2026-11-12', 910, 100378322, 4),
(6, 'Mastercard', '2030-01-12', 659, 786190112, 5),
(7, 'Paypal', '2027-09-23', 397, 979337463, 6),
(8, 'Visa', '2029-10-27', 678, 434638119, 8),
(9, 'Mastercard', '2028-03-02', 443, 621773785, 8),
(10, 'Paypal', '2029-08-20', 890, 275875674, 9),
(11, 'Paypal', '2025-12-28', 998, 500865768, 10),
(12, 'Visa', '2029-08-20', 642, 763840462, 11),
(13, 'Google Pay', '2021-01-12', 463, 628361726, 13),
(14, 'Paypal', '2024-07-24', 269, 837526936, 14),
(15, 'Visa', '2030-01-02', 823, 556439756, 14),
(16, 'Mastercard', '2034-03-15', 342, 438793247, 16),
(17, 'Mastercard', '2025-12-23', 144, 887337875, 18),
(18, 'Google Pay', '2025-03-09', 345, 234143234, 18),
(19, 'Paypal', '2032-01-22', 233, 826591873, 19),
(20, 'Visa', '2024-11-11', 154, 572978789, 20),
(21, 'Mastercard', '2026-02-13', 990, 347893777, 30),
(22, 'Visa', '2027-07-15', 198, 123786566, 15);

-- IoTBay.`Order` definition
CREATE TABLE `Order` (
 `OrderID` int NOT NULL AUTO_INCREMENT,
 `OrderDate` date DEFAULT NULL,
 `OrderStatus` varchar(20) DEFAULT NULL,
 `TotalAmount` double DEFAULT NULL,
 `ShipmentID` int DEFAULT NULL,
 `UserID` int DEFAULT NULL,
 PRIMARY KEY (`OrderID`),
 KEY `Order_Shipment_FK` (`ShipmentID`),
 KEY `Order_users_FK` (`UserID`),
 CONSTRAINT `Order_Shipment_FK` FOREIGN KEY (`ShipmentID`) REFERENCES `Shipment` (`ShipmentID`) ON DELETE SET NULL ON UPDATE CASCADE,
 CONSTRAINT `Order_users_FK` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE SET NULL ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=5071 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Order` (OrderID, OrderDate, OrderStatus, TotalAmount, ShipmentID, UserID)
VALUES (1, '2024-05-05', 'Processing', 10, 3, 1),
(2,'2024-02-10','Completed',13,10,1),
(3,'2024-03-11','Completed',15,5,3),
(4,'2024-03-24','Completed',12.5,11,3),
(5,'2024-03-24','Processing',2,12,3),
(6,'2024-01-01','Completed',10,19,3),
(7,'2024-01-04','Completed',6,21,3),
(8,'2024-04-11','Processing',4.5,24,3),
(9,'2024-03-30','Completed',8,8,8),
(10,'2024-03-02','Completed',2.5,9,8),
(11,'2024-04-06','Processing',2,15,8),
(12,'2024-05-07','Processing',3,17,8),
(13,'2024-05-01','Completed',4,18,9),
(14,'2024-02-12','Completed',4,22,9),
(15,'2024-03-03','Completed',20,13,14),
(16,'2024-04-21','Completed',3.5,1,19),
(17,'2024-04-29','Completed',4.5,4,19),
(18,'2024-03-19','Completed',12,6,19),
(19,'2024-04-27','Completed',8,7,15),
(20,'2024-02-17','Completed',4.5,16,15);


-- IoTBay.OrderLineItem definition
CREATE TABLE `OrderLineItem` (
 `ProductID` int NOT NULL,
 `OrderID` int NOT NULL,
 `ProductQuantity` int DEFAULT NULL,
 `SubTotal` double DEFAULT NULL,
 PRIMARY KEY (`ProductID`,`OrderID`),
 KEY `OrderLineItem_Order_FK` (`OrderID`),
 CONSTRAINT `OrderLineItem_Order_FK` FOREIGN KEY (`OrderID`) REFERENCES `Order` (`OrderID`) ON DELETE CASCADE ON UPDATE RESTRICT,
 CONSTRAINT `OrderLineItem_Product_FK` FOREIGN KEY (`ProductID`) REFERENCES `Product` (`ProductID`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO OrderLineItem (ProductID,OrderID,ProductQuantity,SubTotal)
VALUES (1,1,1,4), (4,1,1,6),
(2,2,1,4), (3,2,2,9),
(5,3,2,11), (6,3,2,4),
(1,4,1,4), (2,4,1,4), (3,4,1,4.5),
(7,5,1,2),
(12,6,1,10),
(8,7,2,6),
(3,8,1,4.5),
(1,9,1,2),(2,9,1,2),
(9,10,1,2.5),
(7,11,1,2),
(8,12,1,3),
(1,13,1,4),
(2,14,1,4),
(20,15,2,20),
(18,16,1,3.5),
(11,17,1,4.5),
(1,18,3,4),
(1,19,2,8),
(3,20,1,4.5);


CREATE TABLE `Order_Payment` (
 `PaymentID` int NOT NULL,
 `OrderID` int NOT NULL,
 `PaymentDate` date DEFAULT NULL,
 `PaymentTime` time DEFAULT NULL,
 PRIMARY KEY (`PaymentID`,`OrderID`),
 KEY `Order_Payment_Order_FK` (`OrderID`),
 CONSTRAINT `Order_Payment_Order_FK` FOREIGN KEY (`OrderID`) REFERENCES `Order` (`OrderID`),
 CONSTRAINT `Order_Payment_Payment_FK` FOREIGN KEY (`PaymentID`) REFERENCES `Payment` (`PaymentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO Order_Payment (PaymentID, OrderID, PaymentDate, PaymentTime)
VALUES (1, 1, '2024-05-05','20:40:21'),
(2,2,'2024-02-10','01:22:11'),
(4,3,'2024-03-11','15:21:45'),
(4,4,'2024-03-24','12:11:00'),
(4,5,'2024-03-24','13:20:14'),
(4,6,'2024-01-01','09:45:45'),
(4,7,'2024-01-04','10:45:20'),
(4,8,'2024-04-11','15:14:12'),
(9,9,'2024-03-30','12:12:12'),
(8,10,'2024-03-02','08:30:00'),
(8,11,'2024-04-06','09:22:22'),
(8,12,'2024-05-07','07:11:11'),
(10,13,'2024-05-01','11:11:11'),
(10,14,'2024-02-12','21:25:59'),
(14,15,'2024-03-03','00:20:11'),
(19,16,'2024-04-21','10:12:45'),
(19,17,'2024-04-29','12:30:00'),
(19,18,'2024-03-19','23:17:11'),
(22,19,'2024-04-27','21:20:00'),
(22,20,'2024-02-17','04:45:07');

 	 
 
