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
	roomId INT NOT NULL PRIMARY KEY CLUSTERED,
	price float,
	description varchar(250),
	status int,
	imageUrl varchar(250),
	roomTypeId int FOREIGN KEY REFERENCES tbl_RoomType(roomTypeId)
);
go



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
go

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

INSERT INTO tbl_RoomType
	(name, numOfPeople)
VALUES('queen room', 2),
	('king room', 3),
	('luxury room', 2);
	GO

INSERT INTO tbl_User
	(username, password,fullName,email,phone,role )
VALUES(N'manager', N'MNOPQ', N'Manager', 'Manager@gmail.com', '0901214034', 1)
GO
    