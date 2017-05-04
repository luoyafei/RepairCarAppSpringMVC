create database repairCar;
use repairCar;

create table t_user (
	userId varchar(32) primary key,
	userName varchar(32),
	userPwd varchar(18),
	userType varchar(2),				--0:普通用户，1:维修人员
	createDate datetime default now()
) engine=innoDB default charset=utf8;

