CREATE DATABASE BookingHotel;
USE BookingHotel;

CREATE TABLE tbl_User (
	userId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	username varchar(50) NOT NULL,
	password varchar(50) NOT NULL,
	fullName varchar(50) NOT NULL,
	email varchar(50),
	phone varchar(20),
	role int
);

CREATE TABLE tbl_Room (
	roomId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	price float,
	numOfPeople int
);

INSERT INTO tbl_Room (price, numOfPeople) VALUES (100, 2);
INSERT INTO tbl_Room (price, numOfPeople) VALUES (110, 2);
INSERT INTO tbl_Room (price, numOfPeople) VALUES (105, 2);
INSERT INTO tbl_Room (price, numOfPeople) VALUES (130, 2);
INSERT INTO tbl_Room (price, numOfPeople) VALUES (200, 4);
INSERT INTO tbl_Room (price, numOfPeople) VALUES (220, 4);
INSERT INTO tbl_Room (price, numOfPeople) VALUES (210, 4);
INSERT INTO tbl_Room (price, numOfPeople) VALUES (230, 4);
INSERT INTO tbl_Room (price, numOfPeople) VALUES (420, 8);
INSERT INTO tbl_Room (price, numOfPeople) VALUES (410, 8);
INSERT INTO tbl_Room (price, numOfPeople) VALUES (450, 8);
INSERT INTO tbl_Room (price, numOfPeople) VALUES (430, 8);

CREATE TABLE tbl_BookingInfo (
	bookingInfoId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	userId int FOREIGN KEY REFERENCES tbl_User(userId),
	roomId int FOREIGN KEY REFERENCES tbl_Room(roomId),
	startDate date,
	endDate date,
	numberOfDay int,
	total float
);

CREATE TABLE tbl_Review (
	reviewId INT IDENTITY(1,1) NOT NULL PRIMARY KEY CLUSTERED,
	userId int FOREIGN KEY REFERENCES tbl_User(userId),
	roomId int FOREIGN KEY REFERENCES tbl_Room(roomId),
	createDate date,
	message varchar(500)
)


