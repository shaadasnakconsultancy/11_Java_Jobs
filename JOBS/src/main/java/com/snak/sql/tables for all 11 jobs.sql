-->>>>>start<<<<<<<<PL_BudgetDomestic 
USE [FinanceDashboard]
GO

/****** Object:  Table [dbo].[PL_BudgetDomestic]    Script Date: 07-04-2026 09:33:34 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[PL_BudgetDomestic](
	[SNO] [int] IDENTITY(1,1) NOT NULL,
	[FY] [varchar](100) NULL,
	[dataType] [varchar](100) NULL,
	[Market] [varchar](100) NULL,
	[Country] [varchar](100) NULL,
	[Xcode] [varchar](100) NULL,
	[Model Code] [varchar](100) NULL,
	[Export Code] [varchar](100) NULL,
	[Trade Type] [varchar](100) NULL,
	[Order Number] [varchar](100) NULL,
	[Month] [varchar](100) NULL,
	[Sales units] [varchar](100) NULL,
	[Currency] [varchar](100) NULL,
	[Original Currency] [varchar](100) NULL,
	[Sales Amounts（INR)Gross] [varchar](100) NULL,
	[Sales Amounts Sales Rebate] [varchar](100) NULL,
	[Sales Amounts Insurance Recovery] [varchar](100) NULL,
	[Sales Amounts Freight Recovery] [varchar](100) NULL,
	[Sales Amounts（INR)] [varchar](100) NULL,
	[Variable Factory Cost] [varchar](100) NULL,
	[Variable Factory Cost Materials & Components] [varchar](100) NULL,
	[Variable Factory Cost labor expenses] [varchar](100) NULL,
	[Variable Factory Cost Fuel] [varchar](100) NULL,
	[Variable Factory Cost Submaterial] [varchar](100) NULL,
	[Variable Factory Cost Royalty] [varchar](100) NULL,
	[Variable Factory Cost Others] [varchar](100) NULL,
	[Variable Factory Cost Total] [varchar](100) NULL,
	[Variable Selling Cost] [varchar](100) NULL,
	[Variable Selling Cost Commodity Tax] [varchar](100) NULL,
	[Variable Selling Cost Transportation] [varchar](100) NULL,
	[Variable Selling Cost Packing Expenses] [varchar](100) NULL,
	[Variable Selling Cost Sales Rebate] [varchar](100) NULL,
	[Variable Selling Cost  Sales Commission] [varchar](100) NULL,
	[Variable Selling Cost  Others] [varchar](100) NULL,
	[Variable Selling Cost  Total] [varchar](100) NULL,
	[Variable Cost Tale] [varchar](100) NULL,
	[Variable Cost Tale Marginal Profit] [varchar](100) NULL,
	[Variable Cost Tale Ratio] [varchar](100) NULL,
	[Fixed Factory Cost] [varchar](100) NULL,
	[Fixed Factory Cost Indirect Labour Cost] [varchar](100) NULL,
	[Fixed Factory Cost Depreciation (Dies & Jigs)] [varchar](100) NULL,
	[Fixed Factory Cost Depreciation (Others)] [varchar](100) NULL,
	[Fixed Factory Cost Consumable Cost] [varchar](100) NULL,
	[Fixed Factory Cost Supplementary Dept. Cost] [varchar](100) NULL,
	[Fixed Factory Cost Others] [varchar](100) NULL,
	[Fixed Factory Cost Total] [varchar](100) NULL,
	[Fixed Factory Cost Contribution Profit by Model] [varchar](100) NULL,
	[Fixed Factory Cost Ratio] [varchar](100) NULL,
	[Selling Expenses] [varchar](100) NULL,
	[Selling Expenses Marketing Expenses (Fixed)] [varchar](100) NULL,
	[Selling Expenses Sales Dept. Expenses] [varchar](100) NULL,
	[Selling Expenses Others] [varchar](100) NULL,
	[Selling Expenses Total] [varchar](100) NULL,
	[Selling Expenses Operating Contribution Profit] [varchar](100) NULL,
	[Selling Expenses Ratio] [varchar](100) NULL,
	[Administrative Expenses] [varchar](100) NULL,
	[Administrative Expenses General & AdExpenses] [varchar](100) NULL,
	[Administrative Expenses Warranty Expenses] [varchar](100) NULL,
	[Administrative Expenses Others] [varchar](100) NULL,
	[Administrative Expenses Total] [varchar](100) NULL,
	[Administrative Expenses Operating Profit] [varchar](100) NULL,
	[Administrative Expenses Ratio] [varchar](100) NULL,
	[Component Cost] [varchar](100) NULL,
	[Component Cost KD from Suzuki] [varchar](100) NULL,
	[Component Cost Import from Other] [varchar](100) NULL,
	[Component Cost Domestic Vendors] [varchar](100) NULL,
	[Component Cost MSIL Engine Price] [varchar](100) NULL,
	[Component Cost Subcontractors Cost] [varchar](100) NULL,
	[Component Cost Conversion Cost] [varchar](100) NULL,
	[Component Cost Others] [varchar](100) NULL,
	[Component Cost Total] [varchar](100) NULL,
 CONSTRAINT [PK_PL_BudgetDomestic_Sno] PRIMARY KEY CLUSTERED 
(
	[SNO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


-->>>>>end<<<<<<<<PL_BudgetDomestic 
-->>>>>start<<<<<<<<PL_BudgetExport 
USE [FinanceDashboard]
GO

/****** Object:  Table [dbo].[PL_BudgetExport]    Script Date: 07-04-2026 09:35:48 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[PL_BudgetExport](
	[SNO] [int] IDENTITY(1,1) NOT NULL,
	[FY] [varchar](100) NULL,
	[dataType] [varchar](100) NULL,
	[Market] [varchar](100) NULL,
	[Country] [varchar](100) NULL,
	[Trade Country] [varchar](100) NULL,
	[Xcode] [varchar](100) NULL,
	[Model Code] [varchar](100) NULL,
	[Export Code] [varchar](100) NULL,
	[Trade Type] [varchar](100) NULL,
	[Order Number] [varchar](100) NULL,
	[Month] [varchar](100) NULL,
	[Sales units] [varchar](100) NULL,
	[Currency] [varchar](100) NULL,
	[Original Currency] [varchar](100) NULL,
	[Sales Amounts（INR)Gross] [varchar](100) NULL,
	[Sales Amounts Sales Rebate] [varchar](100) NULL,
	[Sales Amounts Insurance Recovery] [varchar](100) NULL,
	[Sales Amounts Freight Recovery] [varchar](100) NULL,
	[Sales Amounts（INR)] [varchar](100) NULL,
	[Variable Factory Cost] [varchar](100) NULL,
	[Variable Factory Cost Materials & Components] [varchar](100) NULL,
	[Variable Factory Cost labor expenses] [varchar](100) NULL,
	[Variable Factory Cost Fuel] [varchar](100) NULL,
	[Variable Factory Cost Submaterial] [varchar](100) NULL,
	[Variable Factory Cost Royalty] [varchar](100) NULL,
	[Variable Factory Cost Others] [varchar](100) NULL,
	[Variable Factory Cost Total] [varchar](100) NULL,
	[Variable Selling Cost] [varchar](100) NULL,
	[Variable Selling Cost Commodity Tax] [varchar](100) NULL,
	[Variable Selling Cost Transportation] [varchar](100) NULL,
	[Variable Selling Cost Packing Expenses] [varchar](100) NULL,
	[Variable Selling Cost Sales Rebate] [varchar](100) NULL,
	[Variable Selling Cost  Sales Commission] [varchar](100) NULL,
	[Variable Selling Cost  Others] [varchar](100) NULL,
	[Variable Selling Cost  Total] [varchar](100) NULL,
	[Variable Cost Tale] [varchar](100) NULL,
	[Variable Cost Tale Marginal Profit] [varchar](100) NULL,
	[Variable Cost Tale Ratio] [varchar](100) NULL,
	[Fixed Factory Cost] [varchar](100) NULL,
	[Fixed Factory Cost Indirect Labour Cost] [varchar](100) NULL,
	[Fixed Factory Cost Depreciation (Dies & Jigs)] [varchar](100) NULL,
	[Fixed Factory Cost Depreciation (Others)] [varchar](100) NULL,
	[Fixed Factory Cost Consumable Cost] [varchar](100) NULL,
	[Fixed Factory Cost Supplementary Dept. Cost] [varchar](100) NULL,
	[Fixed Factory Cost Others] [varchar](100) NULL,
	[Fixed Factory Cost Total] [varchar](100) NULL,
	[Fixed Factory Cost Contribution Profit by Model] [varchar](100) NULL,
	[Fixed Factory Cost Ratio] [varchar](100) NULL,
	[Selling Expenses] [varchar](100) NULL,
	[Selling Expenses Marketing Expenses (Fixed)] [varchar](100) NULL,
	[Selling Expenses Sales Dept. Expenses] [varchar](100) NULL,
	[Selling Expenses Others] [varchar](100) NULL,
	[Selling Expenses Total] [varchar](100) NULL,
	[Selling Expenses Operating Contribution Profit] [varchar](100) NULL,
	[Selling Expenses Ratio] [varchar](100) NULL,
	[Administrative Expenses] [varchar](100) NULL,
	[Administrative Expenses General & AdExpenses] [varchar](100) NULL,
	[Administrative Expenses Warranty Expenses] [varchar](100) NULL,
	[Administrative Expenses Others] [varchar](100) NULL,
	[Administrative Expenses Total] [varchar](100) NULL,
	[Administrative Expenses Operating Profit] [varchar](100) NULL,
	[Administrative Expenses Ratio] [varchar](100) NULL,
	[Component Cost] [varchar](100) NULL,
	[Component Cost KD from Suzuki] [varchar](100) NULL,
	[Component Cost Import from Other] [varchar](100) NULL,
	[Component Cost Domestic Vendors] [varchar](100) NULL,
	[Component Cost MSIL Engine Price] [varchar](100) NULL,
	[Component Cost Subcontractors Cost] [varchar](100) NULL,
	[Component Cost Conversion Cost] [varchar](100) NULL,
	[Component Cost Others] [varchar](100) NULL,
	[Component Cost Total] [varchar](100) NULL,
 CONSTRAINT [PK_PL_BudgetExport_Sno] PRIMARY KEY CLUSTERED 
(
	[SNO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO




-->>>>>end<<<<<<<<PL_BudgetExport
-->>>>>start<<<<<<<<PL_ResultDep
USE [FinanceDashboard]
GO

/****** Object:  Table [dbo].[PL_ResultDep]    Script Date: 07-04-2026 09:37:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[PL_ResultDep](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Sno] [varchar](50) NULL,
	[FY] [varchar](20) NULL,
	[DataType] [varchar](50) NULL,
	[Country] [varchar](50) NULL,
	[1st_half] [varchar](50) NULL,
	[2nd_half] [varchar](50) NULL,
	[Model] [varchar](50) NULL,
	[Group] [varchar](50) NULL,
	[Code] [varchar](50) NULL,
	[DEP] [varchar](100) NULL,
	[Apr] [varchar](50) NULL,
	[May] [varchar](50) NULL,
	[Jun] [varchar](50) NULL,
	[Jul] [varchar](50) NULL,
	[Aug] [varchar](50) NULL,
	[Sep] [varchar](50) NULL,
	[Oct] [varchar](50) NULL,
	[Nov] [varchar](50) NULL,
	[Dec] [varchar](50) NULL,
	[Jan] [varchar](50) NULL,
	[Feb] [varchar](50) NULL,
	[Mar] [varchar](50) NULL,
	[Current Month] [varchar](50) NULL,
	[Accum. Depreciation] [varchar](50) NULL,
	[InsertedDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[PL_ResultDep] ADD  DEFAULT (getdate()) FOR [InsertedDate]
GO



-->>>>>end<<<<<<<<PL_ResultDep
-->>>>>start<<<<<<<<PL_ResultExpense
USE [FinanceDashboard]
GO

/****** Object:  Table [dbo].[PL_ResultExpense]    Script Date: 07-04-2026 09:38:47 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[PL_ResultExpense](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Sno] [varchar](50) NULL,
	[FY] [varchar](20) NULL,
	[DataType] [varchar](50) NULL,
	[Model] [varchar](50) NULL,
	[Country] [varchar](50) NULL,
	[1st_half] [varchar](50) NULL,
	[2nd_half] [varchar](50) NULL,
	[Model Description] [varchar](255) NULL,
	[Group] [varchar](50) NULL,
	[Category] [varchar](50) NULL,
	[Monthly] [varchar](50) NULL,
	[Accumlated] [varchar](50) NULL,
	[Last_month] [varchar](50) NULL,
	[InsertedDate] [datetime] NULL,
	[Month] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[PL_ResultExpense] ADD  DEFAULT (getdate()) FOR [InsertedDate]
GO



-->>>>>end<<<<<<<<PL_ResultExpense
-->>>>>start<<<<<<<<PL_ResultParts
USE [FinanceDashboard]
GO

/****** Object:  Table [dbo].[PL_ResultParts]    Script Date: 07-04-2026 09:40:16 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[PL_ResultParts](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Sno] [varchar](50) NULL,
	[FY] [varchar](20) NULL,
	[DataType] [varchar](50) NULL,
	[Country] [varchar](50) NULL,
	[1st_half] [varchar](50) NULL,
	[2nd_half] [varchar](50) NULL,
	[Model] [varchar](50) NULL,
	[Group] [varchar](50) NULL,
	[Code] [varchar](50) NULL,
	[Sale code] [varchar](100) NULL,
	[Production code] [varchar](100) NULL,
	[Current Month Result Unit] [varchar](50) NULL,
	[CateGory] [varchar](50) NULL,
	[Apr] [varchar](50) NULL,
	[May] [varchar](50) NULL,
	[Jun] [varchar](50) NULL,
	[Jul] [varchar](50) NULL,
	[Aug] [varchar](50) NULL,
	[Sep] [varchar](50) NULL,
	[Oct] [varchar](50) NULL,
	[Nov] [varchar](50) NULL,
	[Dec] [varchar](50) NULL,
	[Jan] [varchar](50) NULL,
	[Feb] [varchar](50) NULL,
	[Mar] [varchar](50) NULL,
	[CUMonth] [varchar](50) NULL,
	[LastMonth] [varchar](50) NULL,
	[InsertedDate] [datetime] NULL,
	[Average] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[PL_ResultParts] ADD  DEFAULT (getdate()) FOR [InsertedDate]
GO



-->>>>>end<<<<<<<<PL_ResultParts
-->>>>>start<<<<<<<<PL_ResultPrice
USE [FinanceDashboard]
GO

/****** Object:  Table [dbo].[PL_ResultPrice]    Script Date: 07-04-2026 09:41:05 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[PL_ResultPrice](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[SNO] [varchar](50) NULL,
	[FY] [varchar](20) NULL,
	[DataType] [varchar](50) NULL,
	[Country] [varchar](50) NULL,
	[1st_half] [varchar](50) NULL,
	[2nd_half] [varchar](50) NULL,
	[Model] [varchar](50) NULL,
	[Group] [varchar](50) NULL,
	[category] [varchar](50) NULL,
	[Apr] [varchar](50) NULL,
	[May] [varchar](50) NULL,
	[Jun] [varchar](50) NULL,
	[Jul] [varchar](50) NULL,
	[Aug] [varchar](50) NULL,
	[Sep] [varchar](50) NULL,
	[Oct] [varchar](50) NULL,
	[Nov] [varchar](50) NULL,
	[Dec] [varchar](50) NULL,
	[Jan] [varchar](50) NULL,
	[Feb] [varchar](50) NULL,
	[Mar] [varchar](50) NULL,
	[Monthly] [varchar](50) NULL,
	[Average] [varchar](50) NULL,
	[LastMonth] [varchar](50) NULL,
	[InsertedDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[PL_ResultPrice] ADD  DEFAULT (getdate()) FOR [InsertedDate]
GO



-->>>>>end<<<<<<<<PL_ResultPrice
-->>>>>start<<<<<<<<PL_ResultProAss
USE [FinanceDashboard]
GO

/****** Object:  Table [dbo].[PL_ResultProAss]    Script Date: 07-04-2026 09:41:48 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[PL_ResultProAss](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Sno] [varchar](50) NULL,
	[FY] [varchar](20) NULL,
	[DataType] [varchar](50) NULL,
	[Country] [varchar](50) NULL,
	[1st_half] [varchar](50) NULL,
	[2nd_half] [varchar](50) NULL,
	[Model] [varchar](50) NULL,
	[Group] [varchar](50) NULL,
	[SpecCode] [varchar](50) NULL,
	[Model Group Description] [varchar](100) NULL,
	[CateGory] [varchar](50) NULL,
	[Apr] [varchar](50) NULL,
	[May] [varchar](50) NULL,
	[Jun] [varchar](50) NULL,
	[Jul] [varchar](50) NULL,
	[Aug] [varchar](50) NULL,
	[Sep] [varchar](50) NULL,
	[Oct] [varchar](50) NULL,
	[Nov] [varchar](50) NULL,
	[Dec] [varchar](50) NULL,
	[Jan] [varchar](50) NULL,
	[Feb] [varchar](50) NULL,
	[Mar] [varchar](50) NULL,
	[Monthly] [varchar](50) NULL,
	[InsertedDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[PL_ResultProAss] ADD  DEFAULT (getdate()) FOR [InsertedDate]
GO



-->>>>>end<<<<<<<<PL_ResultProAss
-->>>>>start<<<<<<<<PL_ResultRoyalty
USE [FinanceDashboard]
GO

/****** Object:  Table [dbo].[PL_ResultRoyalty]    Script Date: 07-04-2026 09:42:28 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[PL_ResultRoyalty](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Sno] [varchar](50) NULL,
	[FY] [varchar](20) NULL,
	[DataType] [varchar](50) NULL,
	[Model Description] [varchar](255) NULL,
	[Country] [varchar](50) NULL,
	[1st_half] [varchar](50) NULL,
	[2nd_half] [varchar](50) NULL,
	[Model] [varchar](50) NULL,
	[Group] [varchar](50) NULL,
	[Category] [varchar](50) NULL,
	[Apr] [varchar](50) NULL,
	[May] [varchar](50) NULL,
	[Jun] [varchar](50) NULL,
	[Jul] [varchar](50) NULL,
	[Aug] [varchar](50) NULL,
	[Sep] [varchar](50) NULL,
	[Oct] [varchar](50) NULL,
	[Nov] [varchar](50) NULL,
	[Dec] [varchar](50) NULL,
	[Jan] [varchar](50) NULL,
	[Feb] [varchar](50) NULL,
	[Mar] [varchar](50) NULL,
	[Royality %age] [varchar](50) NULL,
	[Monthly] [varchar](50) NULL,
	[LastMonth] [varchar](50) NULL,
	[InsertedDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[PL_ResultRoyalty] ADD  DEFAULT (getdate()) FOR [InsertedDate]
GO



-->>>>>end<<<<<<<<PL_ResultRoyalty
-->>>>>start<<<<<<<<PL_ResultTransPacking
USE [FinanceDashboard]
GO

/****** Object:  Table [dbo].[PL_ResultTransPacking]    Script Date: 07-04-2026 09:43:16 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[PL_ResultTransPacking](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[Sno] [varchar](50) NULL,
	[FY] [varchar](20) NULL,
	[DataType] [varchar](50) NULL,
	[Country] [varchar](50) NULL,
	[1st_half] [varchar](50) NULL,
	[2nd_half] [varchar](50) NULL,
	[Model] [varchar](50) NULL,
	[Model Decription] [varchar](255) NULL,
	[Group] [varchar](50) NULL,
	[Category] [varchar](50) NULL,
	[Apr] [varchar](50) NULL,
	[May] [varchar](50) NULL,
	[Jun] [varchar](50) NULL,
	[Jul] [varchar](50) NULL,
	[Aug] [varchar](50) NULL,
	[Sep] [varchar](50) NULL,
	[Oct] [varchar](50) NULL,
	[Nov] [varchar](50) NULL,
	[Dec] [varchar](50) NULL,
	[Jan] [varchar](50) NULL,
	[Feb] [varchar](50) NULL,
	[Mar] [varchar](50) NULL,
	[ThisMonth] [varchar](50) NULL,
	[Average] [varchar](50) NULL,
	[LastMonth] [varchar](50) NULL,
	[InsertedDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[PL_ResultTransPacking] ADD  DEFAULT (getdate()) FOR [InsertedDate]
GO



-->>>>>end<<<<<<<<PL_ResultTransPacking
-->>>>>start<<<<<<<<PL_ResultUnits
USE [FinanceDashboard]
GO

/****** Object:  Table [dbo].[PL_ResultUnits]    Script Date: 07-04-2026 10:15:32 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[PL_ResultUnits](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[SNO] [varchar](50) NULL,
	[FY] [varchar](20) NULL,
	[DataType] [varchar](50) NULL,
	[Country] [varchar](50) NULL,
	[First_half] [varchar](50) NULL,
	[Second_half] [varchar](50) NULL,
	[Model] [varchar](50) NULL,
	[Group] [varchar](50) NULL,
	[Raw_Label] [varchar](200) NULL,
	[Base_Model] [varchar](100) NULL,
	[Vehicle_Type] [varchar](50) NULL,
	[X_Model_Code] [varchar](50) NULL,
	[Apr] [varchar](50) NULL,
	[May] [varchar](50) NULL,
	[Jun] [varchar](50) NULL,
	[Jul] [varchar](50) NULL,
	[Aug] [varchar](50) NULL,
	[Sep] [varchar](50) NULL,
	[Oct] [varchar](50) NULL,
	[Nov] [varchar](50) NULL,
	[Dec] [varchar](50) NULL,
	[Jan] [varchar](50) NULL,
	[Feb] [varchar](50) NULL,
	[Mar] [varchar](50) NULL,
	[Monthly] [varchar](50) NULL,
	[ACCUMULATED] [varchar](50) NULL,
	[LastMonth] [varchar](50) NULL,
	[InsertedDate] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[PL_ResultUnits] ADD  DEFAULT (getdate()) FOR [InsertedDate]
GO



-->>>>>end<<<<<<<<PL_ResultUnits
-->>>>>start<<<<<<<<FundFlow
USE [FinanceDashboard]
GO

/****** Object:  Table [dbo].[FundFlow]    Script Date: 07-04-2026 10:18:52 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[FundFlow](
	[SNO] [int] IDENTITY(1,1) NOT NULL,
	[Date] [varchar](50) NULL,
	[Day] [varchar](50) NULL,
	[Month] [varchar](50) NULL,
	[FY] [varchar](50) NULL,
	[DataType] [varchar](50) NULL,
	[Category] [varchar](50) NULL,
	[ICICI_Investment_Payment (Capital)] [varchar](100) NULL,
	[ICICI_For_Expenses_Payment_INR] [varchar](100) NULL,
	[ICICI_For_Dealer_Received_INR] [varchar](100) NULL,
	[USD] [varchar](100) NULL,
	[INR] [varchar](100) NULL,
	[YEN] [varchar](100) NULL,
	[JPY] [varchar](100) NULL,
	[EURO] [varchar](100) NULL,
 CONSTRAINT [PK_FundFlow_Sno] PRIMARY KEY CLUSTERED 
(
	[SNO] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO



-->>>>>end<<<<<<<<FundFlow
 





