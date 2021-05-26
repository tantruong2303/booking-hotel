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
	numOfPeople int,
        isDisable bit,
        imageUrl varchar(250)
);

INSERT INTO tbl_Room (price, numOfPeople, isDisable, imageUrl) VALUES (100, 2, 0, '');
INSERT INTO tbl_Room (price, numOfPeople, isDisable, imageUrl) VALUES (200, 2, 0, '');
INSERT INTO tbl_Room (price, numOfPeople, isDisable, imageUrl) VALUES (300, 4, 0, '');
INSERT INTO tbl_Room (price, numOfPeople, isDisable, imageUrl) VALUES (300, 4, 0, '');
INSERT INTO tbl_Room (price, numOfPeople, isDisable, imageUrl) VALUES (400, 4, 0, '');
INSERT INTO tbl_Room (price, numOfPeople, isDisable, imageUrl) VALUES (800, 8, 0, '');
INSERT INTO tbl_Room (price, numOfPeople, isDisable, imageUrl) VALUES (140, 2, 1, '');
INSERT INTO tbl_Room (price, numOfPeople, isDisable, imageUrl) VALUES (160, 2, 0, '');
INSERT INTO tbl_Room (price, numOfPeople, isDisable, imageUrl) VALUES (280, 4, 0, '');
INSERT INTO tbl_Room (price, numOfPeople, isDisable, imageUrl) VALUES (300, 4, 1, '');
INSERT INTO tbl_Room (price, numOfPeople, isDisable, imageUrl) VALUES (410, 4, 0, '');

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


