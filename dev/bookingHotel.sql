CREATE DATABASE BookingHotel;
go

USE BookingHotel;
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
VALUES(N'queen room', 2),
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
CREATE TABLE tbl_BookingInfo
(
	bookingInfoId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	userId int FOREIGN KEY REFERENCES tbl_User(userId),
	roomId int FOREIGN KEY REFERENCES tbl_Room(roomId),
	startDate Date,
	endDate Date,
	numberOfDay int,
	status int,
	total float
);
GO

INSERt INTO tbl_BookingInfo(userId, roomId, startDate, endDate, numberOfDay, status, total)
VALUES (1, 100, N'2021-04-20', N'2021-04-28', 8, 1, 800),
	   (1, 101, N'2021-03-21', N'2021-03-25', 4, 1, 600),
	   (2, 108, N'2021-08-20', N'2021-08-22', 2, -1, 400),
	   (1, 107, N'2021-05-10', N'2021-05-16', 6, 0, 0),
	   (1, 105, N'2021-06-01', N'2021-06-07', 7, 0, 0),
	   (2, 102, N'2021-04-20', N'2021-04-22', 2, 1, 320);
GO

/*---------------------------------------------------------------------------*/
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
    