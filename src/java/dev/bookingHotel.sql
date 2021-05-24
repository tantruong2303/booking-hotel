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
VALUES ('binhtan', '123456789Aa', 'TRUONG BINH TAN', 'binhtan@gmail.com', '0987654321', 0);

INSERT INTO tbl_User (username, password, fullName, email, phone, role) 
VALUES ('binhtan1', '123456789Aa', 'TRUONG BINH TAN', 'binhtan@gmail.com', '0987654321', 1);

INSERT INTO tbl_User (username, password, fullName, email, phone, role) 
VALUES ('chihai', '123456789Aa', 'CAO CHI HAI', 'chihai@gmail.com', '0987654321', 0);

INSERT INTO tbl_User (username, password, fullName, email, phone, role) 
VALUES ('chihai1', '123456789Aa', 'CAO CHI HAI', 'chihai@gmail.com', '0987654321', 1);

INSERT INTO tbl_User (username, password, fullName, email, phone, role) 
VALUES ('vinhnhan', '123456789Aa', 'PHAM VINH NHAN', 'chihai@gmail.com', '0987654321', 0);

INSERT INTO tbl_User (username, password, fullName, email, phone, role) 
VALUES ('vinhnhan1', '123456789Aa', 'PHAM VINH NHAN', 'chihai@gmail.com', '0987654321', 1);
