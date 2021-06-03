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
<<<<<<< HEAD

CREATE TABLE tbl_RoomType
(
	roomTypeId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	name varchar(50),
	numOfPeople int,
)
go

CREATE TABLE tbl_Room
(
	roomId INT  NOT NULL PRIMARY KEY CLUSTERED,
	price float,
	description varchar(250),
	state int,
=======

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
>>>>>>> 43f17b2 (done add room)
	imageUrl varchar(250),
	roomTypeId int FOREIGN KEY REFERENCES tbl_RoomType(roomTypeId)
);
go
<<<<<<< HEAD



=======



>>>>>>> 43f17b2 (done add room)
CREATE TABLE tbl_BookingInfo
(
	bookingInfoId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	userId int FOREIGN KEY REFERENCES tbl_User(userId),
	roomId int FOREIGN KEY REFERENCES tbl_Room(roomId),
	startDate varchar(20),
	endDate varchar(20),
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
<<<<<<< HEAD
VALUES('queen room', 2);

INSERT INTO tbl_RoomType
	(name, numOfPeople)
VALUES('king room', 3);
GO

=======
VALUES('Tom B. Erichsen', 2);
INSERT INTO tbl_RoomType
	(name, numOfPeople)
VALUES('luxuryroom', 2);
GO
>>>>>>> 43f17b2 (done add room)

INSERT INTO tbl_RoomType
	(name, numOfPeople)
VALUES('luxury room', 2);
GO

INSERT INTO tbl_Room (description,imageUrl,isDisable,price,roomTypeId)VALUES('Hello', '/url', 0, 123, 1);