USE [master]
GO
/****** Object:  Database [BookingHotel]    Script Date: 6/2/2021 10:05:39 PM ******/
CREATE DATABASE [BookingHotel]
GO
ALTER DATABASE [BookingHotel] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BookingHotel].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BookingHotel] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BookingHotel] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BookingHotel] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BookingHotel] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BookingHotel] SET ARITHABORT OFF 
GO
ALTER DATABASE [BookingHotel] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BookingHotel] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BookingHotel] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BookingHotel] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BookingHotel] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BookingHotel] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BookingHotel] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BookingHotel] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BookingHotel] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BookingHotel] SET  ENABLE_BROKER 
GO
ALTER DATABASE [BookingHotel] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BookingHotel] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BookingHotel] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BookingHotel] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BookingHotel] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BookingHotel] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BookingHotel] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BookingHotel] SET RECOVERY FULL 
GO
ALTER DATABASE [BookingHotel] SET  MULTI_USER 
GO
ALTER DATABASE [BookingHotel] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BookingHotel] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BookingHotel] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BookingHotel] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [BookingHotel] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [BookingHotel] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'BookingHotel', N'ON'
GO
ALTER DATABASE [BookingHotel] SET QUERY_STORE = OFF
GO
USE [BookingHotel]
GO
/****** Object:  User [user]    Script Date: 6/2/2021 10:05:39 PM ******/
CREATE USER [user] WITHOUT LOGIN WITH DEFAULT_SCHEMA=[dbo]
GO
/****** Object:  User [hello]    Script Date: 6/2/2021 10:05:39 PM ******/
CREATE USER [hello] FOR LOGIN [hello] WITH DEFAULT_SCHEMA=[hello]
GO
/****** Object:  Schema [hello]    Script Date: 6/2/2021 10:05:39 PM ******/
CREATE SCHEMA [hello]
GO
/****** Object:  Table [dbo].[tbl_BookingInfo]    Script Date: 6/2/2021 10:05:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_BookingInfo](
	[bookingInfoId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NULL,
	[roomId] [int] NULL,
	[startDate] [varchar](20) NULL,
	[endDate] [varchar](20) NULL,
	[numberOfDay] [int] NULL,
	[status] [int] NULL,
	[total] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[bookingInfoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Review]    Script Date: 6/2/2021 10:05:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Review](
	[reviewId] [int] IDENTITY(1,1) NOT NULL,
	[userId] [int] NULL,
	[roomId] [int] NULL,
	[createDate] [varchar](20) NULL,
	[rate] [int] NULL,
	[message] [varchar](500) NULL,
PRIMARY KEY CLUSTERED 
(
	[reviewId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Room]    Script Date: 6/2/2021 10:05:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Room](
	[roomId] [int] IDENTITY(1,1) NOT NULL,
	[price] [float] NULL,
	[description] [varchar](250) NULL,
	[state] [int] NULL,
	[imageUrl] [varchar](250) NULL,
	[roomTypeId] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[roomId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_RoomType]    Script Date: 6/2/2021 10:05:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_RoomType](
	[roomTypeId] [int] IDENTITY(1,1) NOT NULL,
	[name] [varchar](50) NULL,
	[numOfPeople] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[roomTypeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_User]    Script Date: 6/2/2021 10:05:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_User](
	[userId] [int] IDENTITY(1,1) NOT NULL,
	[username] [varchar](50) NOT NULL,
	[password] [varchar](50) NOT NULL,
	[fullName] [varchar](50) NOT NULL,
	[email] [varchar](50) NULL,
	[phone] [varchar](20) NULL,
	[role] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[userId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tbl_RoomType] ON 
GO
INSERT [dbo].[tbl_RoomType] ([roomTypeId], [name], [numOfPeople]) VALUES (4, N'Single', 1)
GO
INSERT [dbo].[tbl_RoomType] ([roomTypeId], [name], [numOfPeople]) VALUES (5, N'Double', 2)
GO
INSERT [dbo].[tbl_RoomType] ([roomTypeId], [name], [numOfPeople]) VALUES (6, N'Triple', 3)
GO
INSERT [dbo].[tbl_RoomType] ([roomTypeId], [name], [numOfPeople]) VALUES (7, N'Queen', 2)
GO
INSERT [dbo].[tbl_RoomType] ([roomTypeId], [name], [numOfPeople]) VALUES (8, N'King', 3)
GO
INSERT [dbo].[tbl_RoomType] ([roomTypeId], [name], [numOfPeople]) VALUES (9, N'Twin', 4)
GO
SET IDENTITY_INSERT [dbo].[tbl_RoomType] OFF
GO
SET IDENTITY_INSERT [dbo].[tbl_User] ON 
GO
INSERT [dbo].[tbl_User] ([userId], [username], [password], [fullName], [email], [phone], [role]) VALUES (1, N'heaty566', N'MNO', N'Pham Vinh Nhan', N'nhanpvse150046@fpt.edu.vn', N'0918703954', 0)
GO
INSERT [dbo].[tbl_User] ([userId], [username], [password], [fullName], [email], [phone], [role]) VALUES (2, N'taicltv86', N'MNO', N'Pham Vinh Nhan (K15 HCM)', N'nhanpvse150046@fpt.edu.vn', N'0918703954', 1)
GO
INSERT [dbo].[tbl_User] ([userId], [username], [password], [fullName], [email], [phone], [role]) VALUES (3, N'taicltv86sa', N'MNO', N'Pham Vinh Nhan', N'nhanpvse150046@fpt.edu.vn', N'0901212099', 0)
GO
SET IDENTITY_INSERT [dbo].[tbl_User] OFF
GO
ALTER TABLE [dbo].[tbl_BookingInfo]  WITH CHECK ADD FOREIGN KEY([roomId])
REFERENCES [dbo].[tbl_Room] ([roomId])
GO
ALTER TABLE [dbo].[tbl_BookingInfo]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[tbl_User] ([userId])
GO
ALTER TABLE [dbo].[tbl_Review]  WITH CHECK ADD FOREIGN KEY([roomId])
REFERENCES [dbo].[tbl_Room] ([roomId])
GO
ALTER TABLE [dbo].[tbl_Review]  WITH CHECK ADD FOREIGN KEY([userId])
REFERENCES [dbo].[tbl_User] ([userId])
GO
ALTER TABLE [dbo].[tbl_Room]  WITH CHECK ADD FOREIGN KEY([roomTypeId])
REFERENCES [dbo].[tbl_RoomType] ([roomTypeId])
GO
USE [master]
GO
ALTER DATABASE [BookingHotel] SET  READ_WRITE 
GO
