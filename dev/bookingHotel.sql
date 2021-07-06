CREATE DATABASE PRJ301_SE08D_BookingHotel;
go

USE PRJ301_SE08D_BookingHotel;
go

CREATE TABLE tbl_User
(
	userId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	username varchar(50) NOT NULL,
	password varchar(50) NOT NULL,
	fullName varchar(50) NOT NULL,
	email varchar(50),
	phone varchar(20),
	role int
);
go

INSERT INTO tbl_User(username,password,fullName,email,phone,role) 
VALUES(N'customer', N'MNOPQ', N'Nguyen Van A', N'aaabbbccc@gmail.com', N'0862334008', 0),
      (N'customer2', N'MNOPQ', N'Nguyen Van C', N'dddeeeccc@gmail.com', N'0862334000', 0),
      (N'manager', N'MNOPQ', N'Nguyen Van B', N'manager@gmail.com', N'0862334009', 1);
GO

/*---------------------------------------------------------------------------*/
CREATE TABLE tbl_RoomType
(
	roomTypeId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	name varchar(50),
	numOfPeople int,
)
go

INSERT INTO tbl_RoomType(name, numOfPeople)
VALUES
	(N'double room', 4),
	(N'double room', 1),
	(N'twin room', 1),
	(N'twin room', 2),
	(N'single room', 1),
	(N'queen room', 2),
	  (N'king room', 3),
	  (N'luxury room', 2);
GO

/*---------------------------------------------------------------------------*/
CREATE TABLE tbl_Room
(
	roomId INT NOT NULL PRIMARY KEY CLUSTERED,
	price float,
	description varchar(250),
	status int,
	imageUrl varchar(250),
	roomTypeId int FOREIGN KEY REFERENCES tbl_RoomType(roomTypeId)
);
GO

INSERT INTO tbl_Room(roomId, price, description, status, imageUrl, roomTypeId)
VALUES (100, 100, N'Free wifi, bedroom, food, drink', 1, N'images\photo1.jpg', 1),
       (101, 150, N'Free wifi, bedroom, food, drink', 1, N'images\photo2.jpg', 2),
	   (102, 160, N'Free wifi, bedroom, food, drink', 0, N'images\photo3.jpg', 3),
	   (103, 100, N'Free wifi, bedroom, food, drink', 1, N'images\photo4.jpg', 1),
	   (104, 130, N'Free wifi, bedroom, food, drink', 1, N'images\photo5.jpg', 2),
	   (105, 120, N'Free wifi, bedroom, food, drink', 1, N'images\photo6.jpg', 3),
	   (106, 130, N'Free wifi, bedroom, food, drink', 0, N'images\photo7.jpg', 2),
	   (107, 160, N'Free wifi, bedroom, food, drink', 1, N'images\photo8.jpg', 3),
	   (108, 200, N'Free wifi, bedroom, food, drink', 1, N'images\photo9.jpg', 3),
	   (109, 180, N'Free wifi, bedroom, food, drink', 1, N'images\photo10.jpg', 1);

/*---------------------------------------------------------------------------*/

CREATE TABLE tbl_Order
(
    orderId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
    userId int FOREIGN KEY REFERENCES tbl_User(userId) ON DELETE CASCADE,
    createDate DATE,
	total float,
)
GO

/*---------------------------------------------------------------------------*/

CREATE TABLE tbl_BookingInfo
(
	bookingInfoId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	orderId int FOREIGN KEY REFERENCES tbl_Order(orderId) ON DELETE CASCADE,
	roomId int FOREIGN KEY REFERENCES tbl_Room(roomId) ON DELETE CASCADE,
	startDate Date,
	endDate Date,
	status int,
	roomPrice float,
);
GO

CREATE TABLE tbl_Review
(
	reviewId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	userId int FOREIGN KEY REFERENCES tbl_User(userId),
	roomId int FOREIGN KEY REFERENCES tbl_Room(roomId),
	createDate varchar(20),
	rate int,
	message varchar(500)
)
go

INSERT INTO tbl_Review(userId, roomId, createDate, rate, message)
VALUES (1, 100, N'2021-04-28', 5, N'Good service'),
       (2, 102, N'2021-04-22', 4, N'ok');
    