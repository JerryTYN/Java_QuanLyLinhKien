create database QLLK
go
 use QLLK
 create table nhanvien
(
	manv varchar(20) primary key,
	hotennv nvarchar(30) ,
	gioitinh bit ,
	diachi nvarchar(30),
	sdt nvarchar(30),
	email Nvarchar(30) ,
	quanlyvien  bit,
)
go
create table khachhang
(
	makh varchar(20)  primary key,
	tenKh nvarchar(30) ,
	gioitinh bit ,
	diachi nvarchar(30),
	sdt nvarchar(30),
	email Nvarchar(30),	
)
go
create table hoadon
(
	mahd varchar(20) primary key,
	manv varchar(20) constraint FK_NV_HD foreign key references nhanvien(manv) on delete cascade,
	makh varchar(20) constraint FK_KH_HD foreign key references khachhang(makh) on delete cascade,
	ngaylap datetime,
)
select * from hoadon
go
create table linhkien
(
	malk varchar(20) primary key,
	tenlk nvarchar(50),
	loailk nvarchar(30),
	thuonghieu nvarchar(30),
	nuocsanxuat nvarchar(30),
	soluongton int,
	dongia money,
	baohanh int,
)

select * from linhkien
go
create table CT_hoadon
(
	mahd varchar(20) constraint FK_CTHD_HD foreign key references hoadon(mahd) on delete cascade,
	malk varchar(20)  constraint FK_LK_HD foreign key references linhkien(malk) on delete cascade ,
	soluong int,
	dongia money,
)
go
--thêm khách hàng vào bảng khách hàng
insert into khachhang values
	('KH01',  N'Hoàng Đình Chiến', 1, N'phường 4 , quận Gò vấp', '0386934153', null),
	('KH02', N'Hoàng Kim Thoa', 0, N'phường 4 , quận Gò vấp', '0394875005', 'hoangkimthoa@gmail.com'),
	('KH03', N'Vũ Quang Khải', 1, N'phường 5 , quận 3' , '0386974153',null),
	('KH04',  N'Nông Vĩnh Kha', 1, N'phường 15 , quận 9', '0376902153', 'kha123@gmail.com'),
	('KH05', N'Nguyễn Thị Đào', 0, N'phường 16 , quận 7' , '0356909853', null)
go
select * from khachhang
--thêm nhân viên vào bảng nhân viên
insert into nhanvien  values
	('NV001', N'Nguyễn đặng hoàng thi', 1,N'phường 4 , quận Gò vấp','0386934153', 'hoangthi123@gmail.com',1),
	('NV002', N'Bùi Sĩ Sơn ', 1,N'phường 16 , quận Tân Bình ','0396934106','sison123@gmail.com', 1),
	('NV003', N'Võ Thành Nhớ ',1,N'phường 4 , quận 7 ', '0397548106','thanhnho123@gmail.com',0),
	('NV004', N'Nguyễn Hoàng Quân', 1,N'phường 8 , quận 3 ','0360048181', 'hoangquan123@gmail.com',0)
go
select * from nhanvien 
--Thêm thông tin linh kiện 
insert into linhkien values
('LK013',' GIGABYTE B560M D2V','Mainboard','GIGABYTE','China',20,2490000,2),
('LK023',' GIGABYTE W480 VISION D','Mainboard','GIGABYTE','China',10 ,9290000,3),
('LK033','HDD 5TB Seagate Expansion Portable STEA5000402',N'Ổ cứng ','Seagate','China ',20 ,5050000,0),
('LK011','Asrock Radeon RX 6700 XT Challenger D 12GB',N'VGA - Card màn hình ','Radeon','America',20 ,23999000,3),
('LK001','CPU Intel Core i9-10940X',N'CPU - Bộ vi xử lý ','Intel','America',20 ,23999000,0),
('LK002','RAM desktop DDR4 Micron ECC 16GB/2133Mhz',N'RAM - Bộ nhớ trong ','ECC Registered','Korea ',20 ,1399000,0)


go

--thêm vào bảng hóa đơn
insert into hoadon  values
('HD01','NV001','KH01', GETDATE() - 10),
('HD02','NV003','KH02', GETDATE() - 5),
('HD03','NV002','KH03', GETDATE() - 14),
('HD04','NV004','KH04', GETDATE() - 20),
('HD05','NV001','KH05', GETDATE() - 30)
go

select * from hoadon
--thêm vào chi tiết hóa đơn 
insert into CT_hoadon  values
('HD01','LK013',2,5000000),
('HD01','LK002',1,1399000),
('HD02','LK023',1,9290000),
('HD03','LK033',2,1000000),
('HD03','LK011',1,23999000),
('HD03','LK001',2,23999000),
('HD04','LK013',1,2490000)
go

select * from [dbo].[CT_hoadon]
use master
go
create login nv001
with password = '0000',
default_database = [QLLK]

create login nv002 
with password = '0000',
default_database = [QLLK]

create login nv003
with password = '0000',
default_database = [QLLK]

create login nv004
with password = '0000',
default_database = [QLLK]
go
use QLLK

create user nv001
for login nv001

create user nv002
for login nv002

create user nv003
for login nv003

create user nv004
for login nv004
go

grant insert, select, delete, update
to nv001

grant insert, select, delete, update
to nv002

grant insert, select, delete, update
to nv003

grant insert, select, delete, update
to nv004
go

deny insert, update, delete
on NhanVien
to nv003 , nv004
go

select * from KhachHang a join HoaDon b on a.makh = b.maKH join NhanVien c on b.maNV = c.manv

create function ThongKeTheoThang(@thang int, @nam int)
returns @return_value table (maNV varchar(20), tenNV nvarchar(50), solkDaBan int, doanhThu money)
as
	begin
		insert into @return_value
			select a.manv, a.hotennv, SUM(c.soluong), SUM(soLuong * donGia) from nhanvien a join hoadon b on a.manv = b.maNV join CT_hoadon c on b.maHD = c.maHD
			where YEAR(ngaylap) = @nam and MONTH(ngaylap) = @thang
			group by a.manv, a.hotennv
		return
	end
go
create function ThongKeTheoNam(@nam int)
returns @return_value table (maNV varchar(20), tenNV nvarchar(50), solkDaBan int, doanhThu money)
as
	begin
		insert into @return_value
			select a.manv, a.hotennv, SUM(c.soLuong), SUM(soLuong * donGia) from nhanvien a join hoadon b on a.manv = b.maNV join CT_hoadon c on b.maHD = c.maHD
			where YEAR(ngayLap) = @nam
			group by a.manv, a.hotennv
		return
	end
go

select * from linhkien