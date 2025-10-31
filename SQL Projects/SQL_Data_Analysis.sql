CREATE DATABASE SESI13

-- =============================================
-- 1. DIMENSI: CustomerType
-- =============================================
CREATE TABLE CustomerTypeDimension (
    CustomerTypeCode INT PRIMARY KEY IDENTITY(1,1),
    CustomerType_ID CHAR(5),
    CustomerTypeName VARCHAR(50) NOT NULL
);

INSERT INTO CustomerTypeDimension (CustomerType_ID, CustomerTypeName) VALUES
('CT001', 'Regular'),
('CT002', 'Premium'),
('CT003', 'VIP'),
('CT004', 'Student'),
('CT005', 'Corporate'),
('CT006', 'Loyalty Member'),
('CT007', 'Wholesaler'),
('CT008', 'Government'),
('CT009', 'Non-Profit'),
('CT010', 'Reseller');

-- =============================================
-- 2. DIMENSI: Voucher
-- =============================================
CREATE TABLE VoucherDimension (
    VoucherCode INT PRIMARY KEY IDENTITY(1,1),
    Voucher_ID CHAR(5),
    Voucher_Description VARCHAR(50) NOT NULL,
    Voucher_ExpiredDate DATE NOT NULL
);

INSERT INTO VoucherDimension (Voucher_ID, Voucher_Description, Voucher_ExpiredDate) VALUES
('VC001', 'Diskon 10% Pembelian Pertama', '2025-12-31'),
('VC002', 'Gratis Ongkir Jabodetabek', '2025-11-30'),
('VC003', 'Cashback 20.000', '2026-01-15'),
('VC004', 'Potongan Harga 50.000', '2025-12-25'),
('VC005', 'Buy 1 Get 1 Free Coffee', '2025-10-31'),
('VC006', 'Diskon 15% Akhir Tahun', '2025-12-31'),
('VC007', 'Gratis Ongkir Nasional', '2026-03-31'),
('VC008', 'Voucher Ulang Tahun', '2025-11-15'),
('VC009', 'Promo Ramadan', '2026-04-10'),
('VC010', 'Diskon Pelajar 20%', '2026-08-31');

-- =============================================
-- 3. DIMENSI: Staff
-- =============================================
CREATE TABLE StaffDimension (
    StaffCode INT PRIMARY KEY IDENTITY(1,1),
    Staff_ID CHAR(5),
    Staff_Name VARCHAR(50) NOT NULL,
    Staff_DOB DATE NOT NULL,
    Staff_Gender CHAR(1) NOT NULL,
    Staff_Email VARCHAR(50) NOT NULL,
    Staff_Phone VARCHAR(20) NOT NULL
);

INSERT INTO StaffDimension (Staff_ID, Staff_Name, Staff_DOB, Staff_Gender, Staff_Email, Staff_Phone) VALUES
('ST001', 'Budi Santoso', '1995-03-15', 'L', 'budi.s@example.com', '081234567890'),
('ST002', 'Citra Lestari', '1998-08-22', 'P', 'citra.l@example.com', '081234567891'),
('ST003', 'Agus Wijaya', '1992-11-05', 'L', 'agus.w@example.com', '081234567892'),
('ST004', 'Dewi Anggraini', '2000-01-30', 'P', 'dewi.a@example.com', '081234567893'),
('ST005', 'Eko Prasetyo', '1997-07-19', 'L', 'eko.p@example.com', '081234567894'),
('ST006', 'Michael Tan', '1990-05-12', 'L', 'michael.t@example.com', '081234567895'),
('ST007', 'Aisha Rahman', '2001-12-03', 'P', 'aisha.r@example.com', '081234567896'),
('ST008', 'Rajiv Patel', '1988-09-22', 'L', 'rajiv.p@example.com', '081234567897'),
('ST009', 'Lina Wong', '1994-02-14', 'P', 'lina.w@example.com', '081234567898'),
('ST010', 'Kenji Sato', '1996-07-30', 'L', 'kenji.s@example.com', '081234567899');

-- =============================================
-- 4. DIMENSI: Branch
-- =============================================
CREATE TABLE BranchDimension (
    BranchCode INT PRIMARY KEY IDENTITY(1,1),
    Branch_ID CHAR(5),
    Branch_Name VARCHAR(50) NOT NULL,
    Branch_Country VARCHAR(50) NOT NULL,
    Branch_Address VARCHAR(50) NOT NULL
);

INSERT INTO BranchDimension (Branch_ID, Branch_Name, Branch_Country, Branch_Address) VALUES
('BR001', 'Jakarta Pusat', 'Indonesia', 'Jl. Sudirman No. 1'),
('BR002', 'Bandung Kota', 'Indonesia', 'Jl. Asia Afrika No. 10'),
('BR003', 'Surabaya Selatan', 'Indonesia', 'Jl. Darmo No. 25'),
('BR004', 'Yogyakarta', 'Indonesia', 'Jl. Malioboro No. 50'),
('BR005', 'Bali Denpasar', 'Indonesia', 'Jl. Sunset Road No. 100'),
('BR006', 'Singapore Central', 'Singapore', 'Orchard Road No. 22'),
('BR007', 'Kuala Lumpur', 'Malaysia', 'Jalan Bukit Bintang No. 8'),
('BR008', 'Bangkok Siam', 'Thailand', 'Siam Square Soi 5'),
('BR009', 'Manila Bay', 'Philippines', 'Roxas Blvd No. 101'),
('BR010', 'Ho Chi Minh City', 'Vietnam', 'Nguyen Hue St No. 45');

-- =============================================
-- 5. DIMENSI: Payment
-- =============================================
CREATE TABLE PaymentDimension (
    PaymentCode INT PRIMARY KEY IDENTITY(1,1),
    Payment_ID CHAR(5),
    Payment_Method VARCHAR(50) NOT NULL,
    Payment_Date DATE NOT NULL,
    Payment_Status VARCHAR(50) NOT NULL
);

INSERT INTO PaymentDimension (Payment_ID, Payment_Method, Payment_Date, Payment_Status) VALUES
('PY001', 'Credit Card', '2025-10-08', 'Completed'),
('PY002', 'E-Wallet', '2025-10-08', 'Completed'),
('PY003', 'Bank Transfer', '2025-10-07', 'Completed'),
('PY004', 'Cash', '2025-10-07', 'Completed'),
('PY005', 'Debit Card', '2025-10-06', 'Completed'),
('PY006', 'PayPal', '2025-09-15', 'Completed'),
('PY007', 'OVO', '2025-11-20', 'Completed'),
('PY008', 'Gopay', '2026-01-05', 'Pending'),
('PY009', 'ShopeePay', '2025-12-12', 'Completed'),
('PY010', 'Apple Pay', '2026-02-14', 'Completed');

-- =============================================
-- 6. DIMENSI: Supplier
-- =============================================
CREATE TABLE SupplierDimension (
    SupplierCode INT PRIMARY KEY IDENTITY(1,1),
    Supplier_ID CHAR(5),
    Supplier_Name VARCHAR(50) NOT NULL,
    Supplier_Address VARCHAR(50) NOT NULL,
    Supplier_CP VARCHAR(50) NOT NULL
);

INSERT INTO SupplierDimension (Supplier_ID, Supplier_Name, Supplier_Address, Supplier_CP) VALUES
('SP001', 'PT Maju Jaya', 'Jakarta', 'Rina'),
('SP002', 'CV Sinar Abadi', 'Bandung', 'Andi'),
('SP003', 'UD Berkah Selalu', 'Surabaya', 'Siti'),
('SP004', 'PT Cipta Kreasi', 'Yogyakarta', 'Dodi'),
('SP005', 'Global Goods Inc.', 'Tangerang', 'Brian'),
('SP006', 'Asia Tech Distributors', 'Singapore', 'Mei Ling'),
('SP007', 'Global Fashion Co.', 'Kuala Lumpur', 'Ahmad'),
('SP008', 'Thai Agro Products', 'Bangkok', 'Niran'),
('SP009', 'Manila Book House', 'Manila', 'Luis'),
('SP010', 'Hanoi Stationery Ltd', 'Hanoi', 'Minh');

-- =============================================
-- 7. DIMENSI: StockCategory
-- =============================================
CREATE TABLE StockCategoryDimension (
    CategoryCode INT PRIMARY KEY IDENTITY(1,1),
    Category_ID CHAR(5),
    Category_Name VARCHAR(50) NOT NULL
);

INSERT INTO StockCategoryDimension (Category_ID, Category_Name) VALUES
('CA001', 'Elektronik'),
('CA002', 'Pakaian'),
('CA003', 'Makanan & Minuman'),
('CA004', 'Buku'),
('CA005', 'Alat Tulis Kantor'),
('CA006', 'Olahraga & Outdoor'),
('CA007', 'Kecantikan'),
('CA008', 'Mainan Anak'),
('CA009', 'Elektronik Rumah Tangga'),
('CA010', 'Perhiasan');

-- =============================================
-- 8. DIMENSI: Customer
-- =============================================
CREATE TABLE CustomerDimension (
    CustomerCode INT PRIMARY KEY IDENTITY(1,1),
    Customer_ID CHAR(5),
    CustomerType_ID CHAR(5) NOT NULL,
    Customer_Name VARCHAR(50) NOT NULL,
    Customer_DOB DATE NOT NULL,
    Customer_Gender CHAR(1) NOT NULL,
    Customer_Email VARCHAR(50) NOT NULL,
    Customer_Phone VARCHAR(20) NOT NULL
);

INSERT INTO CustomerDimension (Customer_ID, CustomerType_ID, Customer_Name, Customer_DOB, Customer_Gender, Customer_Email, Customer_Phone) VALUES
('CU001', 'CT001', 'Rian Ardianto', '1996-04-10', 'L', 'rian.a@email.com', '085711112222'),
('CU002', 'CT002', 'Susi Susanti', '1999-09-01', 'P', 'susi.s@email.com', '085722223333'),
('CU003', 'CT001', 'Tono Martono', '1993-02-25', 'L', 'tono.m@email.com', '085733334444'),
('CU004', 'CT004', 'Maharani Putri', '2002-06-12', 'P', 'maharani.p@email.com', '085744445555'),
('CU005', 'CT005', 'PT Sejahtera', '2010-10-10', 'L', 'contact@sejahtera.com', '021500600'),
('CU006', 'CT002', 'Aditya Wijaya', '1985-11-20', 'L', 'aditya.w@email.com', '081344445555'),
('CU007', 'CT006', 'Nadia Putri', '2003-04-05', 'P', 'nadia.p@email.com', '081455556666'),
('CU008', 'CT003', 'Richard Lee', '1978-08-12', 'L', 'richard.lee@email.com', '+6598765432'),
('CU009', 'CT004', 'Fajar Ramadhan', '2004-01-30', 'L', 'fajar.r@email.com', '081566667777'),
('CU010', 'CT007', 'CV Maju Bersama', '2008-06-15', 'L', 'info@majubersama.co.id', '0217890123');

-- =============================================
-- 9. DIMENSI: Review
-- =============================================
CREATE TABLE ReviewDimension (
    ReviewCode INT PRIMARY KEY IDENTITY(1,1),
    Review_ID CHAR(5),
    Customer_ID CHAR(5) NOT NULL,
    Review_Description VARCHAR(50) NOT NULL
);

INSERT INTO ReviewDimension (Review_ID, Customer_ID, Review_Description) VALUES
('RV001', 'CU001', 'Pelayanan sangat baik dan cepat!'),
('RV002', 'CU002', 'Produknya original dan berkualitas.'),
('RV003', 'CU001', 'Aplikasi mudah digunakan.'),
('RV004', 'CU004', 'Ada diskon untuk mahasiswa, terima kasih!'),
('RV005', 'CU002', 'Pengiriman tepat waktu.'),
('RV006', 'CU006', 'Pelayanan cepat meski akhir pekan!'),
('RV007', 'CU007', 'Diskon pelajar sangat membantu.'),
('RV008', 'CU008', 'Pengiriman internasional lancar.'),
('RV009', 'CU009', 'Aplikasi sering error, tolong diperbaiki.'),
('RV010', 'CU010', 'Kerja sama B2B sangat profesional.');

-- =============================================
-- 10. DIMENSI: Stock
-- =============================================
CREATE TABLE StockDimension (
    StockCode INT PRIMARY KEY IDENTITY(1,1),
    Stock_ID CHAR(5),
    Category_ID CHAR(5) NOT NULL,
    Supplier_ID CHAR(5) NOT NULL,
    Description VARCHAR(50) NOT NULL,
    Unit_Price FLOAT NOT NULL,
    Unit_Quantity INT NOT NULL,
    Unit_Status VARCHAR(50) NOT NULL
);

INSERT INTO StockDimension (Stock_ID, Category_ID, Supplier_ID, Description, Unit_Price, Unit_Quantity, Unit_Status) VALUES
('SK001', 'CA001', 'SP001', 'Laptop Core i5 14 inch', 12500000, 50, 'Available'),
('SK002', 'CA002', 'SP002', 'Kemeja Katun Pria Lengan Panjang', 250000, 200, 'Available'),
('SK003', 'CA003', 'SP003', 'Kopi Arabika Gayo 250gr', 75000, 150, 'Available'),
('SK004', 'CA004', 'SP004', 'Novel Fiksi "Bumi Manusia"', 120000, 100, 'Available'),
('SK005', 'CA005', 'SP005', 'Paket Pulpen Tinta Gel (Isi 12)', 55000, 300, 'Available'),
('SK006', 'CA006', 'SP006', 'Sepatu Lari Nike Air Zoom', 1850000, 75, 'Available'),
('SK007', 'CA007', 'SP007', 'Serum Wajah Vitamin C 30ml', 320000, 200, 'Available'),
('SK008', 'CA008', 'SP008', 'Lego City Police Station', 890000, 60, 'Available'),
('SK009', 'CA009', 'SP009', 'Rice Cooker Digital 1.8L', 450000, 120, 'Available'),
('SK010', 'CA010', 'SP010', 'Kalung Emas 24K 5gr', 6500000, 25, 'Limited');

CREATE TABLE TimeDimension (
	TIMECODE INT PRIMARY KEY IDENTITY(1,1),
	[DATE] DATE,
	[DAY] INT,
	[MONTH] INT,
	[QUARTER] INT,
	[YEAR] INT
)

CREATE TABLE FILTERTIMESTAMP(
	TABLENAME VARCHAR(50) PRIMARY KEY,
	LASTETL DATETIME
)

INSERT INTO TimeDimension ([DATE], [DAY], [MONTH], [QUARTER], [YEAR])
VALUES
('2025-10-15', 15, 10, 4, 2025),
('2025-10-16', 16, 10, 4, 2025),
('2025-11-20', 20, 11, 4, 2025),
('2026-01-05', 5, 1, 1, 2026),
('2026-04-10', 10, 4, 2, 2026),
('1992-02-25', 25, 2, 1, 1992),
('1992-11-05', 5, 11, 4, 1992),
('1993-02-25', 25, 2, 1, 1993),
('1995-03-15', 15, 3, 1, 1995),
('1996-04-10', 10, 4, 2, 1996),
('1997-07-19', 19, 7, 3, 1997),
('1998-08-22', 22, 8, 3, 1998),
('1999-09-01', 1, 9, 3, 1999),
('2000-01-30', 30, 1, 1, 2000),
('2002-06-12', 12, 6, 2, 2002),
('2010-10-10', 10, 10, 4, 2010),
('2025-10-06', 6, 10, 4, 2025),
('2025-10-07', 7, 10, 4, 2025),
('2025-10-08', 8, 10, 4, 2025),
('2025-10-31', 31, 10, 4, 2025),
('2025-11-30', 30, 11, 4, 2025),
('2025-12-25', 25, 12, 4, 2025),
('2025-12-31', 31, 12, 4, 2025),
('2026-01-15', 15, 1, 1, 2026),
('2026-02-10', 10, 2, 1, 2026),
('2026-03-05', 5, 3, 1, 2026),
('2026-05-10', 10, 5, 2, 2026),
('2026-06-20', 20, 6, 2, 2026),
('2026-07-15', 15, 7, 3, 2026),
('2026-08-12', 12, 8, 3, 2026),
('2026-09-10', 10, 9, 3, 2026),
('2026-10-01', 1, 10, 4, 2026),
('2026-11-11', 11, 11, 4, 2026),
('2026-12-25', 25, 12, 4, 2026),
('2027-01-15', 15, 1, 1, 2027),
('2027-02-20', 20, 2, 1, 2027),
('2027-03-10', 10, 3, 1, 2027),
('2027-04-18', 18, 4, 2, 2027),
('2027-05-30', 30, 5, 2, 2027),
('2027-06-22', 22, 6, 2, 2027),
('2027-07-10', 10, 7, 3, 2027),
('2027-08-25', 25, 8, 3, 2027),
('2027-09-12', 12, 9, 3, 2027),
('2027-10-30', 30, 10, 4, 2027),
('2027-11-20', 20, 11, 4, 2027),
('2027-12-31', 31, 12, 4, 2027);

CREATE TABLE FactSales (
    StockCode INT NOT NULL,
    CustomerCode INT NOT NULL,
    StaffCode INT NOT NULL,
    BranchCode INT NOT NULL,
    PaymentCode INT NOT NULL,
    VoucherCode INT NULL,
    TimeCode INT NOT NULL,
    Quantity INT NOT NULL,
    UnitPrice DECIMAL(12,2) NOT NULL,
    SalesAmount DECIMAL(14,2) NOT NULL,
    FOREIGN KEY (StockCode) REFERENCES StockDimension(StockCode),
    FOREIGN KEY (CustomerCode) REFERENCES CustomerDimension(CustomerCode),
    FOREIGN KEY (StaffCode) REFERENCES StaffDimension(StaffCode),
    FOREIGN KEY (BranchCode) REFERENCES BranchDimension(BranchCode),
    FOREIGN KEY (PaymentCode) REFERENCES PaymentDimension(PaymentCode),
    FOREIGN KEY (VoucherCode) REFERENCES VoucherDimension(VoucherCode),
    FOREIGN KEY (TimeCode) REFERENCES TimeDimension(TimeCode)
);

-- Insert data into FactSales
INSERT INTO FactSales (
    StockCode, CustomerCode, StaffCode, BranchCode, PaymentCode, VoucherCode, TimeCode,
    Quantity, UnitPrice, SalesAmount
) VALUES
('SK001', 'CU001', 'ST001', 'BR001', 'PY001', 'VC001', 'TM014', 1, 12500000.00, 12500000.00),
('SK002', 'CU002', 'ST002', 'BR002', 'PY002', NULL, 'TM014', 3, 250000.00, 750000.00),
('SK003', 'CU004', 'ST003', 'BR001', 'PY004', 'VC010', 'TM013', 2, 75000.00, 150000.00),
('SK006', 'CU006', 'ST005', 'BR003', 'PY005', NULL, 'TM012', 1, 1850000.00, 1850000.00),
('SK007', 'CU007', 'ST004', 'BR001', 'PY002', 'VC008', 'TM014', 2, 320000.00, 640000.00),
('SK005', 'CU003', 'ST006', 'BR004', 'PY003', NULL, 'TM013', 5, 55000.00, 275000.00),
('SK010', 'CU008', 'ST008', 'BR006', 'PY006', 'VC006', 'TM023', 1, 6500000.00, 6500000.00),
('SK008', 'CU009', 'ST007', 'BR005', 'PY007', NULL, 'TM003', 1, 890000.00, 890000.00),
('SK004', 'CU002', 'ST002', 'BR001', 'PY009', 'VC004', 'TM022', 2, 120000.00, 240000.00),
('SK009', 'CU010', 'ST010', 'BR007', 'PY010', NULL, 'TM004', 3, 450000.00, 1350000.00),
('SK001', 'CU001', 'ST001', 'BR001', 'PY001', 'VC001', 'TM024', 2, 12500000.00, 25000000.00),
('SK004', 'CU002', 'ST002', 'BR002', 'PY002', NULL, 'TM025', 1, 120000.00, 120000.00),
('SK006', 'CU003', 'ST003', 'BR003', 'PY003', 'VC002', 'TM026', 1, 1850000.00, 1850000.00),
('SK008', 'CU004', 'ST004', 'BR004', 'PY004', NULL, 'TM027', 2, 890000.00, 1780000.00),
('SK009', 'CU005', 'ST005', 'BR005', 'PY005', 'VC005', 'TM028', 3, 450000.00, 1350000.00),
('SK001', 'CU001', 'ST001', 'BR001', 'PY001', 'VC001', 'TM029', 2, 12500000.00, 25000000.00),
('SK002', 'CU002', 'ST002', 'BR002', 'PY002', NULL, 'TM030', 3, 250000.00, 750000.00),
('SK003', 'CU004', 'ST003', 'BR001', 'PY004', 'VC010', 'TM031', 2, 75000.00, 150000.00),
('SK006', 'CU006', 'ST005', 'BR003', 'PY005', NULL, 'TM032', 1, 1850000.00, 1850000.00),
('SK007', 'CU007', 'ST004', 'BR001', 'PY002', 'VC008', 'TM033', 2, 320000.00, 640000.00),
('SK005', 'CU003', 'ST006', 'BR004', 'PY003', NULL, 'TM034', 5, 55000.00, 275000.00),
('SK010', 'CU008', 'ST008', 'BR006', 'PY006', 'VC006', 'TM035', 1, 6500000.00, 6500000.00),
('SK008', 'CU009', 'ST007', 'BR005', 'PY007', NULL, 'TM036', 1, 890000.00, 890000.00),
('SK004', 'CU002', 'ST002', 'BR001', 'PY009', 'VC004', 'TM037', 2, 120000.00, 240000.00),
('SK009', 'CU010', 'ST010', 'BR007', 'PY010', NULL, 'TM038', 3, 450000.00, 1350000.00);




-- 1. Compare number of repeat customers by year
WITH CustomerYearlyTransactions AS (
    SELECT 
        f.CustomerCode, 
		t.[YEAR], 
		COUNT(*) AS OrderCount
    FROM FactSales f
    JOIN TimeDimension t ON f.TimeCode = t.TimeCode
    GROUP BY f.CustomerCode, t.[YEAR]
),
RepeatCustomers AS (
    SELECT 
        c1.[YEAR], COUNT(DISTINCT c1.CustomerCode) AS RepeatCustomerCount
    FROM CustomerYearlyTransactions c1
    WHERE EXISTS (
        SELECT 1
        FROM CustomerYearlyTransactions c2
        WHERE c2.CustomerCode = c1.CustomerCode
          AND c2.[YEAR] < c1.[YEAR]
    )
    GROUP BY c1.[YEAR]
)
SELECT 
    [YEAR],
    RepeatCustomerCount
FROM RepeatCustomers
ORDER BY [YEAR];



-- 2. Monthly sales amount: new vs returning customers
WITH FirstTransaction AS (
    SELECT 
        f.CustomerCode,
        MIN(t.[DATE]) AS FirstOrderDate
    FROM FactSales f
    JOIN TimeDimension t ON f.TimeCode = t.TimeCode
    GROUP BY f.CustomerCode
),
MonthlySales AS (
    SELECT 
        t.[YEAR],
        t.[MONTH],
        f.CustomerCode,
        SUM(f.SalesAmount) AS MonthlySalesAmount,
        CASE 
            WHEN MIN(t.[DATE]) = ft.FirstOrderDate THEN 'New'
            ELSE 'Returning'
        END AS CustomerType
    FROM FactSales f
    JOIN TimeDimension t ON f.TimeCode = t.TimeCode
    JOIN FirstTransaction ft ON f.CustomerCode = ft.CustomerCode
    GROUP BY t.[YEAR], t.[MONTH], f.CustomerCode, ft.FirstOrderDate
)
SELECT 
    [YEAR],
    [MONTH],
    CustomerType,
    SUM(MonthlySalesAmount) AS TotalSalesAmount
FROM MonthlySales
GROUP BY [YEAR], [MONTH], CustomerType
ORDER BY [YEAR], [MONTH], CustomerType;



-- 3. Average number of orders per customer per year
WITH OrdersPerCustomerPerYear AS (
    SELECT 
        t.[YEAR],
        f.CustomerCode,
        COUNT(*) AS OrderCount
    FROM FactSales f
    JOIN TimeDimension t ON f.TimeCode = t.TimeCode
    GROUP BY t.[YEAR], f.CustomerCode
)
SELECT 
    [YEAR],
    AVG(CAST(OrderCount AS DECIMAL(10,2))) AS AvgOrdersPerCustomer
FROM OrdersPerCustomerPerYear
GROUP BY [YEAR]
ORDER BY [YEAR];



