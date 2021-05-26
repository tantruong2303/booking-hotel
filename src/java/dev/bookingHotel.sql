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

CREATE TABLE tbl_RoomType
(
	roomTypeId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	name varchar(50),
	numOfPeople int,
)
go

CREATE TABLE tbl_Room
(
	roomId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	price float,
	description varchar(250),
	isDisable bit,
	imageUrl varchar(250),
	roomTypeId int FOREIGN KEY REFERENCES tbl_RoomType(roomTypeId)
);
go



CREATE TABLE tbl_BookingInfo
(
	bookingInfoId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	userId int FOREIGN KEY REFERENCES tbl_User(userId),
	roomId int FOREIGN KEY REFERENCES tbl_Room(roomId),
	startDate date,
	endDate date,
	numberOfDay int,
	total float
);
go

CREATE TABLE tbl_Review
(
	reviewId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	userId int FOREIGN KEY REFERENCES tbl_User(userId),
	roomId int FOREIGN KEY REFERENCES tbl_Room(roomId),
	createDate date,
	message varchar(500)
)
go

INSERT INTO tbl_RoomType
	(name, numOfPeople)
VALUES('Tom B. Erichsen', 2);
INSERT INTO tbl_RoomType
	(name, numOfPeople)
VALUES('luxuryroom', 2);
GO


INSERT INTO tbl_Room (description,imageUrl,isDisable,price,roomTypeId)VALUES('Hello', '/url', 0, 123, 1);