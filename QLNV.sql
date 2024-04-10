USE [QLNV]
GO

/****** Object:  Table [dbo].[employees]    Script Date: 4/10/2024 2:44:15 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[employees](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fullName] [nvarchar](255) NULL,
	[birthDay] [date] NULL,
	[phone] [nvarchar](20) NULL,
	[email] [nvarchar](255) NULL,
	[employee_type] [nvarchar](50) NULL,
	[expInYear] [int] NULL,
	[proSkill] [nvarchar](255) NULL,
	[graduation_date] [date] NULL,
	[graduation_rank] [nvarchar](50) NULL,
	[education] [nvarchar](255) NULL,
	[majors] [nvarchar](255) NULL,
	[semester] [nvarchar](50) NULL,
	[university_name] [nvarchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


