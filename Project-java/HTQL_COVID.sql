create database `htql_covid`;
use `htql_covid`;

/* ================================================================================== */

create table `account`
(
    `username` varchar(12) not null primary key,
    `password` varchar(100) not null default '',
    `role` varchar(8) not null default 'user',
    `status` bool default true
);

alter table `account`
    add constraint C_ROLE
        check (`role` in ('admin','manager','user'));


create table `NGUOI_LIEN_QUAN`
(
    ten nvarchar(35) not null,
    cmnd varchar(12) not null primary key,
    namsinh int not null,
    diachi nvarchar(70),
    trangthai varchar(2) not null,
    idnoiquanly int default 0,
    ghino float(15,2) default 0,
    nguonlay varchar(12) default null
);

alter table `NGUOI_LIEN_QUAN`
    add constraint C_status
        check (trangthai in ('OK','F0','F1','F2','F3','F4'));


create table `NOI_QUAN_LY`
(
    id int not null primary key auto_increment,
    ten nvarchar(40) not null,
    succhua int not null,
    soluongtiep int default 0
);

alter table `NOI_QUAN_LY` auto_increment = 20;

create table `NHU_PHAM`
(
    id int not null primary key auto_increment,
    ten nvarchar(50) not null,
    muchan int,
    hsd int default 10,
    gia float(15,2) not null
);

alter table `NHU_PHAM` auto_increment = 10;

create table `LICH_SU_CHUYEN_TRANG_THAI`
(
    id int not null primary key auto_increment,
    nguoiquanly varchar(12),
    doituong varchar(12) not null,
    trangthaicu varchar(2) default null,
    trangthaimoi varchar(2) default null,
    noiquanlycu int default null,
    noiquanlymoi int default null,
    thoigian timestamp default current_timestamp
);

create table `LICH_SU_MUA`
(
    id int not null primary key auto_increment,
    nguoimua varchar(12) not null,
    idgoinhupham int not null,
    soluong int not null,
    thanhtien float(15,2),
    thoigian timestamp default current_timestamp
);

create table `LICH_SU_HOAT_DONG`
(
    username varchar(12) not null,
    hanhdong varchar(8) not null,
    tb varchar(30) not null,
    msg nvarchar(100) not null,
    thoigian timestamp default current_timestamp
);

alter table `LICH_SU_HOAT_DONG`
    add constraint C_hanhdong
        check (hanhdong in ('them','xoa','cap nhat'));

create table `LICH_SU_THANH_TOAN`
(
    cmnd varchar(12) not null,
    sotien float(15,2),
	thoigian timestamp default current_timestamp
);


create table `chuyen_trang_thai`
(
    cmnd varchar(12) not null,
    trangthaimoi varchar(3),
    trangthaicu varchar(3),
    noiquanlymoi varchar(255),
    noiquanlycu varchar(255),
    nguoiquanly varchar(12)
);

create table `Quy_dinh_muc_han` (
	id int not null primary key auto_increment,
    cmnd varchar(12),
    idsp int,
    `limit` int,
    thoigian date
);


update Quy_dinh_muc_han set thoigian = '2022-01-07' where cmnd = '123456789001' and idsp = 10;

insert into `chuyen_trang_thai` values("123321222", "F0", "F1", null, null, "manager");

create table `ThongKeTrangThai`(
                                   id int not null primary key auto_increment,
                                   trangthai varchar(2),
                                   soluong int,
                                   thoigian timestamp default current_timestamp
);

insert into `ThongKeTrangThai`(trangthai,soluong) value("F0",0);
insert into `ThongKeTrangThai`(trangthai,soluong) value("F1",0);
insert into `ThongKeTrangThai`(trangthai,soluong) value("F2",0);
insert into `ThongKeTrangThai`(trangthai,soluong) value("F3",0);
insert into `ThongKeTrangThai`(trangthai,soluong) value("F4",0);
insert into `ThongKeTrangThai`(trangthai,soluong) value("OK",0);

-- insert into `ThongKeTrangThai`(trangthai,soluong,thoigian) value("F0",5,"2022-01-01");
-- insert into `ThongKeTrangThai`(trangthai,soluong,thoigian) value("F1",10,"2022-01-01");
-- insert into `ThongKeTrangThai`(trangthai,soluong,thoigian) value("F2",15,"2022-01-01");
-- insert into `ThongKeTrangThai`(trangthai,soluong,thoigian) value("F3",11,"2022-01-01");
-- insert into `ThongKeTrangThai`(trangthai,soluong,thoigian) value("F4",20,"2022-01-01");
-- insert into `ThongKeTrangThai`(trangthai,soluong,thoigian) value("OK",50,"2022-01-01");

/* ================================================================================== */

alter table `NGUOI_LIEN_QUAN` add
    constraint fk_nguon foreign key (nguonlay) references NGUOI_LIEN_QUAN(cmnd);

alter table `LICH_SU_CHUYEN_TRANG_THAI`	add
    constraint fk_nguoiquanly foreign key (nguoiquanly) references `account`(`username`);
alter table `LICH_SU_CHUYEN_TRANG_THAI`	add
    constraint fk_doituong foreign key (doituong) references NGUOI_LIEN_QUAN(cmnd);

alter table `LICH_SU_MUA` add
    constraint fk_nguoimua foreign key (nguoimua) references NGUOI_LIEN_QUAN(cmnd);

alter table `LICH_SU_HOAT_DONG` add
    constraint fk_acc foreign key (username) references `account`(`username`);

alter table `LICH_SU_THANH_TOAN` add
    constraint fk_cmnd_ foreign key (cmnd) references NGUOI_LIEN_QUAN(cmnd);


/* ================================================================================== */


create table TAI_KHOAN_GIAO_DICH
(
    tk varchar(12) not null primary key,
    sodu float(15,2)
    );

create table LICH_SU_GIAO_DICH
(
    id int not null primary key auto_increment,
    thoigian timestamp default current_timestamp,
    tk_gui varchar(12),
    tk_nhan varchar(12),
    sotien float(15,2),
	foreign key (tk_gui) references TAI_KHOAN_GIAO_DICH(tk),
    foreign key (tk_nhan) references TAI_KHOAN_GIAO_DICH(tk)
);


/* ================================================================================== */

DELIMITER $$
CREATE PROCEDURE `proc_TaoQuanLy` (_username nvarchar(12), _pass varchar(100), _quanly varchar(12))
BEGIN
	declare _msg nvarchar(100);
	IF ((select 'username' from htql_covid.`account` WHERE 'username' = _username) IS NULL) THEN
		insert into htql_covid.`account` (`username`,`password`,`role`)
        values (_username, _pass, 'manager');

        set _msg = concat("Thêm quản lý mới: ",_username);
INSERT INTO htql_covid.LICH_SU_HOAT_DONG (username, hanhdong, tb, msg)
VALUES	(_quanly, "them", "nguoi_lien_quan", _msg);
select 1;
ELSE
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Tai khoan da ton tai";
END IF;
END$$
DELIMITER ;



DELIMITER $$
CREATE PROCEDURE `proc_ThemNguoi` (_ten nvarchar(35), _cmnd varchar(12), _namsinh int, _diachi nvarchar(70), _trangthai varchar(2), _noiquanly int, _nguonlay varchar(12) , _quanly varchar(12))
BEGIN
	DECLARE _id int;
	DECLARE _soluong int;
    DECLARE _msg nvarchar(100);
    DECLARE thoigian timestamp;
    SET thoigian = current_timestamp;
	IF ((select 'username' from htql_covid.`account` WHERE 'username' = _cmnd) IS NULL) THEN
		INSERT INTO htql_covid.`nguoi_lien_quan` (ten,cmnd, namsinh, diachi,trangthai, idnoiquanly,nguonlay)
		VALUES
		(_ten, _cmnd, _namsinh, _diachi, _trangthai, _noiquanly,_nguonlay);

insert into htql_covid.`account` (`username`)
values (_cmnd);
select soluongtiep into _soluong from htql_covid.noi_quan_ly where id = _noiquanly;

insert into htql_covid.tai_khoan_giao_dich (tk,sodu)
    value (_cmnd, 1000000000);

update htql_covid.noi_quan_ly set soluongtiep = _soluong + 1 where id = _noiquanly;

set _msg = concat("Thêm người mới: (",_ten,", ",_cmnd,")");
INSERT INTO htql_covid.LICH_SU_HOAT_DONG (username, hanhdong, tb, msg)
VALUES	(_quanly, "them", "nguoi_lien_quan", _msg);

select soluong into _soluong from htql_covid.`ThongKeTrangThai` WHERE trangthai = _trangthai order by thoigian desc limit 1;
IF ((select id from htql_covid.`ThongKeTrangThai` WHERE DATE(thoigian) = CURDATE() AND trangthai = _trangthai) IS NULL) THEN
select soluong into _soluong from htql_covid.`ThongKeTrangThai` WHERE trangthai = "F0" order by thoigian desc limit 1;
insert into `ThongKeTrangThai`(trangthai,soluong) value("F0",_soluong);
select soluong into _soluong from htql_covid.`ThongKeTrangThai` WHERE trangthai = "F1" order by thoigian desc limit 1;
insert into `ThongKeTrangThai`(trangthai,soluong) value("F1",_soluong);
select soluong into _soluong from htql_covid.`ThongKeTrangThai` WHERE trangthai = "F2" order by thoigian desc limit 1;
insert into `ThongKeTrangThai`(trangthai,soluong) value("F2",_soluong);
select soluong into _soluong from htql_covid.`ThongKeTrangThai` WHERE trangthai = "F3" order by thoigian desc limit 1;
insert into `ThongKeTrangThai`(trangthai,soluong) value("F3",_soluong);
select soluong into _soluong from htql_covid.`ThongKeTrangThai` WHERE trangthai = "F4" order by thoigian desc limit 1;
insert into `ThongKeTrangThai`(trangthai,soluong) value("F4",_soluong);
select soluong into _soluong from htql_covid.`ThongKeTrangThai` WHERE trangthai = "OK" order by thoigian desc limit 1;
insert into `ThongKeTrangThai`(trangthai,soluong) value("OK",_soluong);
END IF;
select soluong,id into _soluong,_id from htql_covid.`ThongKeTrangThai` WHERE trangthai = _trangthai order by thoigian desc limit 1;
update htql_covid.`ThongKeTrangThai` set soluong = _soluong + 1 WHERE id = _id;
select 1;

ELSE
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Nguoi nay da co trong danh sach";
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `proc_ChuyenTrangThai` (_doituong varchar(12),  _trangthaimoi varchar(2), _quanly varchar(12))
BEGIN
	DECLARE _id int;
	DECLARE _soluong int;
	declare _trangthaicu varchar(2);
    declare _msg  nvarchar(100);
	IF ((select cmnd from htql_covid.nguoi_lien_quan WHERE cmnd = _doituong) IS NOT NULL) THEN
select trangthai into _trangthaicu from htql_covid.nguoi_lien_quan WHERE cmnd = _doituong;
update htql_covid.nguoi_lien_quan set trangthai = _trangthaimoi where cmnd = _doituong;

INSERT INTO htql_covid.lich_su_chuyen_trang_thai (nguoiquanly, doituong, trangthaicu, trangthaimoi)
VALUES	(_quanly, _doituong, _trangthaicu, _trangthaimoi);

set _msg = concat(_doituong," chuyển từ ",_trangthaicu," sang ",_trangthaimoi);
INSERT INTO htql_covid.LICH_SU_HOAT_DONG (username, hanhdong, tb, msg)
VALUES	(_quanly, "cap nhat", "nguoi_lien_quan", _msg);

IF ((select id from htql_covid.`ThongKeTrangThai` WHERE DATE(thoigian) = CURDATE() AND trangthai = _trangthaimoi) IS NULL) THEN
select soluong into _soluong from htql_covid.`ThongKeTrangThai` WHERE trangthai = "F0" order by thoigian desc limit 1;
insert into `ThongKeTrangThai`(trangthai,soluong) value("F0",_soluong);
select soluong into _soluong from htql_covid.`ThongKeTrangThai` WHERE trangthai = "F1" order by thoigian desc limit 1;
insert into `ThongKeTrangThai`(trangthai,soluong) value("F1",_soluong);
select soluong into _soluong from htql_covid.`ThongKeTrangThai` WHERE trangthai = "F2" order by thoigian desc limit 1;
insert into `ThongKeTrangThai`(trangthai,soluong) value("F2",_soluong);
select soluong into _soluong from htql_covid.`ThongKeTrangThai` WHERE trangthai = "F3" order by thoigian desc limit 1;
insert into `ThongKeTrangThai`(trangthai,soluong) value("F3",_soluong);
select soluong into _soluong from htql_covid.`ThongKeTrangThai` WHERE trangthai = "F4" order by thoigian desc limit 1;
insert into `ThongKeTrangThai`(trangthai,soluong) value("F4",_soluong);
select soluong into _soluong from htql_covid.`ThongKeTrangThai` WHERE trangthai = "OK" order by thoigian desc limit 1;
insert into `ThongKeTrangThai`(trangthai,soluong) value("OK",_soluong);

END IF;
select soluong,id into _soluong,_id from htql_covid.`ThongKeTrangThai` WHERE trangthai = _trangthaimoi order by thoigian desc limit 1;
update htql_covid.`ThongKeTrangThai` set soluong = _soluong + 1 WHERE id = _id;
select soluong,id into _soluong,_id from htql_covid.`ThongKeTrangThai` WHERE trangthai = _trangthaicu order by thoigian desc limit 1;
update htql_covid.`ThongKeTrangThai` set soluong = _soluong - 1 WHERE id = _id;
select 1;

ELSE
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Khong tim thay nguoi nay";
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `proc_ChuyenNoiDieuTri` (_doituong varchar(12),  _noidieutrimoi int, _quanly varchar(12))
BEGIN
	declare _noidieutricu varchar(2);
    declare _msg  nvarchar(100);
	IF ((select soluongtiep from htql_covid.noi_quan_ly where soluongtiep + 1 <= succhua and id = _noidieutrimoi) is not null) THEN
select idnoiquanly into _noidieutricu from htql_covid.nguoi_lien_quan WHERE cmnd = _doituong;

update htql_covid.noi_quan_ly set soluongtiep = soluongtiep - 1 where id = _noidieutricu;
update htql_covid.nguoi_lien_quan set idnoiquanly = _noidieutrimoi where cmnd = _doituong;
update htql_covid.noi_quan_ly set soluongtiep = soluongtiep + 1 where id = _noidieutrimoi;

INSERT INTO htql_covid.lich_su_chuyen_trang_thai (nguoiquanly, doituong, noiquanlycu, noiquanlymoi)
VALUES	(_quanly, _doituong, _noidieutricu, _noidieutrimoi);

set _msg = concat(_doituong," chuyển từ nơi điều trị: ",_noidieutricu," sang nơi điều trị: ",_noidieutrimoi);
INSERT INTO htql_covid.LICH_SU_HOAT_DONG (username, hanhdong, tb, msg)
VALUES	(_quanly, "cap nhat", "nguoi_lien_quan", _msg);
select 1;

ELSE
		SIGNAL SQLSTATE "45000" SET MESSAGE_TEXT = "Khong tim thay nguoi nay";
END IF;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `proc_ThemNhuYeuPham` (_ten nvarchar(50), _muchan int,_hsd int, _gia float(15,2), _quanly varchar(12))
BEGIN
	declare _id int;
    declare _msg varchar(100);
insert into htql_covid.nhu_pham (ten, muchan, hsd, gia)
values (_ten, _muchan, _hsd, _gia);

select id into _id from htql_covid.nhu_pham ORDER BY id DESC LIMIT 1;
set _msg = concat("Thêm sản phẩm mới: (", _id, ", ", _ten, ")");
INSERT INTO htql_covid.LICH_SU_HOAT_DONG (username, hanhdong, tb, msg)
VALUES	(_quanly, "them", "nhu_pham", _msg);
select 1;

END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `proc_XoaNhuYeuPham` (_id int, _quanly varchar(12))
BEGIN
	declare _msg nvarchar(100);
    declare _tensp nvarchar(50);
select ten into _tensp from htql_covid.nhu_pham where id = _id;

delete from htql_covid.nhu_pham
where id = _id;

set _msg = concat("Xóa sản phẩm: (", _id, ", ", _tensp, ")");
INSERT INTO htql_covid.LICH_SU_HOAT_DONG (username, hanhdong, tb, msg)
VALUES	(_quanly, "xoa", "nhu_pham", _msg);
select 1;

END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `proc_CapNhatNhuYeuPham` (_id int, _ten nvarchar(50), _muchan int,_hsd int, _gia float(15,2), _quanly varchar(12))
BEGIN
    declare _msg varchar(100);

update htql_covid.nhu_pham
set ten = _ten, muchan = _muchan, hsd = _hsd, gia = _gia
where id = _id;

set _msg = concat("Cập nhật sản phẩm: (", _id, ", ", _ten, ")");
INSERT INTO htql_covid.LICH_SU_HOAT_DONG (username, hanhdong, tb, msg)
VALUES	(_quanly, "cap nhat", "nhu_pham", _msg);
select 1;

END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `proc_ThemNoiQuanLy` (_ten nvarchar(40), _succhua int, _SLhientai int)
BEGIN
	declare _id int;
    declare _msg varchar(100);
insert into htql_covid.noi_quan_ly (ten, succhua, soluongtiep)
values (_ten, _succhua, _SLhientai);

select id into _id from htql_covid.noi_quan_ly ORDER BY id DESC LIMIT 1;
set _msg = concat("Thêm nơi quản lý mới: (", _id, ", ", _ten, ")");
INSERT INTO htql_covid.LICH_SU_HOAT_DONG (username, hanhdong, tb, msg)
VALUES	("admin", "them", "noi_quan_ly", _msg);
select 1;

END$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE `proc_MuaNhuPham` (_nguoimua varchar(12), _idsp int, _soluong int)
BEGIN
	declare _ghino float(15,2);
    declare _thanhtien float(15,2);
    declare _gia float(15,2);
    declare _msg varchar(100);
select ghino into _ghino from htql_covid.nguoi_lien_quan where cmnd = _nguoimua;
select gia into _gia from htql_covid.nhu_pham where id = _idsp;
set _thanhtien = _gia * _soluong;

update htql_covid.nguoi_lien_quan set ghino = _ghino + _thanhtien
where cmnd = _nguoimua;

insert into htql_covid.lich_su_mua (nguoimua, idgoinhupham,soluong,thanhtien)
values (_nguoimua, _idsp, _soluong, _thanhtien);

set _msg = concat(_nguoimua, " mua san pham ", _idsp, ", soluong ", _soluong, " tong tien: ", _thanhtien);
INSERT INTO htql_covid.LICH_SU_HOAT_DONG (username, hanhdong, tb, msg)
VALUES	(_nguoimua, "them", "lich_su_mua", _msg);
select 1;

END$$
DELIMITER ;


DELIMITER $$
CREATE PROCEDURE `proc_ThanhToanGiaoDich` (_tknhan varchar(12), _tkgui varchar(12), _sotien float)
BEGIN
declare _sodutkgui float(15,2);
select sodu into _sodutkgui from tai_khoan_giao_dich where tk = _tkgui;
if(_sodutkgui-_sotien>0) then
update tai_khoan_giao_dich set sodu= sodu-_sotien where tk=_tkgui;
update tai_khoan_giao_dich set sodu= sodu+_sotien where tk=_tknhan;
insert into lich_su_giao_dich (tk_gui,tk_nhan,sotien) values(_tknhan,_tkgui,_sotien);
select 1;
else
select 0;
end if;
END$$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE `proc_ThanhToanGhiNo` (_cmnd varchar(12), _sotien float)
BEGIN
declare _msg varchar(100);
update nguoi_lien_quan set ghino=ghino-_sotien where cmnd=_cmnd;
insert into lich_su_thanh_toan (cmnd,sotien) values (_cmnd,_sotien);

set _msg = concat(_cmnd, " thanh toan ghi no voi so tien: ", _sotien);
INSERT INTO htql_covid.LICH_SU_HOAT_DONG (username, hanhdong, tb, msg)
VALUES	(_cmnd, "them", "lich_su_thanh_toan", _msg);
END$$
DELIMITER ;

-- call proc_ThanhToanGiaoDich ("123456789003","admin",150000);
-- call proc_ThanhToanGhiNo ("123456789003",150000);
update nguoi_lien_quan set ghino=1000000 where cmnd="123456789001";

/* ================================================================================== */
insert into htql_covid.`account` (`username`,`password`,`role`) values ('admin','','admin');

call htql_covid.`proc_ThemNoiQuanLy` ('Bệnh viện Phạm Ngọc Thạch', 1000, 0);
call htql_covid.`proc_ThemNoiQuanLy` ('Bệnh viện Nhiệt Đới TP. HCM', 1000, 0);
call htql_covid.`proc_ThemNoiQuanLy` ('Bệnh viện Trưng Vương', 1000, 0);
call htql_covid.`proc_ThemNoiQuanLy` ('Bệnh viện Đa Khoa Gò Vấp', 1000, 0);
call htql_covid.`proc_ThemNoiQuanLy` ('Bệnh viện Đa Khoa khu vực Thủ Đức', 1000, 0);


call htql_covid.`proc_ThemNguoi` ('Kiều Nhật Hùng','123456789001',1974,'Thành phố Hồ Chí Minh, Quận 1, Phường Bến Nghé','F1',20, null ,"admin");
call htql_covid.`proc_ThemNguoi` ('Vương Hiếu Phong','123456789002',1984,'Thành phố Hồ Chí Minh, Huyện Củ Chi, Xã Trung Lập Thượng','F0',20,null,"admin");
call htql_covid.`proc_ThemNguoi` ('Bùi Tấn Tài','123456789003',1967,'Thành phố Hồ Chí Minh, Quận Bình Thạnh, Phường 13','F1',20,'123456789002',"admin");
call htql_covid.`proc_ThemNguoi` ('Nguyễn Huy Vũ','123456789004',1982,'Thành phố Hồ Chí Minh, Quận 11, Phường 14','F0',21, null,"admin");
call htql_covid.`proc_ThemNguoi` ('Nguyễn Hữu Chiến','123456789005',1988,'Thành phố Hồ Chí Minh, Huyện Củ Chi, Xã Phạm Văn Cội','F0',21,null,"admin");
call htql_covid.`proc_ThemNguoi` ('Bùi Tiến Ðức','123456789006',1994,'Thành phố Hồ Chí Minh, Quận 10, Phường 10','F0',21,null,"admin");
call htql_covid.`proc_ThemNguoi` ('Nguyễn Ngọc Tuấn','123456789007',1994,'Thành phố Hồ Chí Minh, Quận 3, Phường 10','F0',22,null,"admin");
call htql_covid.`proc_ThemNguoi` ('Đỗ Kim Huyền','123456789008',1998,'Thành phố Hồ Chí Minh, Quận 5, Phường 02','F0',22,null,"admin");
call htql_covid.`proc_ThemNguoi` ('Quyền Duy Cường','123456789009',1960,'Thành phố Hồ Chí Minh, Quận 4, Phường 14','F1',22,'123456789008',"admin");
call htql_covid.`proc_ThemNguoi` ('Nguyễn Nguyên','123456789010',1977,'Thành phố Hồ Chí Minh, Quận Bình Thạnh, Phường 05','F2',22,'123456789003',"admin");

call htql_covid.`proc_ThemNhuYeuPham` ('Bánh mì', 10, 1,10000,'admin');
call htql_covid.`proc_ThemNhuYeuPham` ('Gạo', 10, 1,15000,'admin');
call htql_covid.`proc_ThemNhuYeuPham` ('Khẩu trang kháng khuẩn', 10, 1, 3500,'admin');
call htql_covid.`proc_ThemNhuYeuPham` ('Nước sát khuẩn', 10, 1,50000,'admin');

call htql_covid.`proc_ChuyenTrangThai` ('123456789001','F0','admin');
call htql_covid.`proc_ChuyenNoiDieuTri` ('123456789001', 21, 'admin');
call htql_covid.`proc_ChuyenTrangThai` ('123456789002', 'OK', 'admin');
call htql_covid.`proc_ChuyenTrangThai` ('123456789003', 'F4', 'admin');
call htql_covid.`proc_ChuyenTrangThai` ('123456789004', 'OK', 'admin');

call htql_covid.`proc_MuaNhuPham` ('123456789008', 10, 2);
call htql_covid.`proc_MuaNhuPham` ('123456789008', 11, 10);
call htql_covid.`proc_MuaNhuPham` ('123456789008', 12, 5);
call htql_covid.`proc_MuaNhuPham` ('123456789008', 13, 1);

call htql_covid.`proc_TaoQuanLy` ('manager','manager','admin');

insert into `NGUOI_LIEN_QUAN`(ten,cmnd, namsinh, diachi,trangthai, idnoiquanly,ghino)
values('abc', '123456789011',1974,'Thành phố Hồ Chí Minh, Quận 1, Phường Bến Nghé','F1',20, 100000 );
insert into `NGUOI_LIEN_QUAN`(ten,cmnd, namsinh, diachi,trangthai, idnoiquanly,ghino)
values('def','123456789012',1974,'Thành phố Hồ Chí Minh, Quận 1, Phường Bến Nghé','F1',20, 2000000);

update htql_covid.`account` set `role` = 'manager' where `username` = 'admin';

select * from htql_covid.`account`;
-- select * from htql_covid.noi_quan_ly;
-- select * from htql_covid.nguoi_lien_quan;
-- select * from htql_covid.nhu_pham;
-- select * from htql_covid.lich_su_chuyen_trang_thai;
-- select * from htql_covid.lich_su_hoat_dong;
-- select * from htql_covid.lich_su_mua;
-- select * from htql_covid.`ThongKeTrangThai`;
