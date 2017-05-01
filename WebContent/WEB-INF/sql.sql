create database repairCar;
use repairCar;

create table t_admin (
	adminId varchar(32) primary key,
	adminName varchar(32),
	adminPwd varchar(18)
) engine=innoDB default charset=utf8;
