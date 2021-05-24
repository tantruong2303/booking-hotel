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

INSERT INTO tbl_User (username, password, fullName, email, phone, role) 
VALUES ('user', '123456789Aa', 'USER', 'user@gmail.com', '0987654321', 0);

INSERT INTO tbl_User (username, password, fullName, email, phone, role) 
VALUES ('partner', '123456789Aa', 'PARTNER', 'partner@gmail.com', '0987654321', 1);
