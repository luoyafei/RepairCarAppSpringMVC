create database repairCar;
use repairCar;

/**
 * 用户表
 */
create table t_user (
	userId varchar(32) primary key,
	userName varchar(32),
	userPwd varchar(18),
	userType varchar(2),				--0:普通用户，1:维修人员
	createDate datetime default now()
) engine=innoDB default charset=utf8;

/**
 * 订单表
 */
create table t_order (
	orderId varchar(32) primary key,
	userId varchar(32),
	carId varchar(18),
	carOwnerId varchar(32),
	orderTime datetime default now(),
	orderAddr varchar(255),
	orderState varchar(2),
	orderType varchar(2),
	orderRemark varchar(255),
	suspendReason varchar(255),
	suspendRemark varchar(255),
	cancelOrderReason varchar(255),
	deelPlace varchar(255),
	totalMoney varchar(25),
	settlementRemark varchar(255),
	evaluateLevel varchar(1),
	orderEvaluate varchar(255)
) engine=innoDB default charset=utf8;

/**
 * 意见反馈表
 */
create table t_feedBack (
	feedBackId varchar(32) primary key,
	backTime datetime default now(),
	feedContent varchar(255),
	personId varchar(32),
	isHandle varchar(1),
	result varchar(255)
)engine=innoDB default charset=utf8;

/**
 * 日志提交表
 */
create table t_orderLog (
	logId varchar(32) primary key,
	logType varchar(1),
	logContent varchar(255),
	createDate datetime default now(),
	personId varchar(32)
)engine=innoDB default charset=utf8;

/**
 * 设备表
 */
create table t_device (
	deviceId varchar(32) primary key,
	deviceNum varchar(32),
	deviceType varchar(1),
	deviceState varchar(1),
	personId varchar(32),
	inputTime datetime default now()
)engine=innoDB default charset=utf8;
/**
 * 个人库存
 */
create table t_hasDevice (
	upId int primary key auto_increment,
	deviceId varchar(32),
	personId varchar(32),
	createDate datetime default now()
)engine=innoDB default charset=utf8;

/**
 * 领取/退回设备表
 */
create table t_receiveLog (
	indexId varchar(32) primary key,
	deviceId varchar(32) not null,
	operateType varchar(1),
	applyId varchar(32),
	personId varchar(32),
	handleTime datetime default now(),
	remark varchar(255)
)engine=innoDB default charset=utf8;
	