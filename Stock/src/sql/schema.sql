-- 数据库初始化脚本

-- 创建数据库 stock(库存)
create database stock;

-- 使用库存表(stock)
use stock;

-- 创建用户表
create table stock_user(
	user_id INT NOT NULL AUTO_INCREMENT COMMENT '库存用户id',
	user_number CHAR(6) NOT NULL COMMENT '库存用户编号',
	user_name VARCHAR(12) NOT NULL COMMENT '库存用户名',
	user_password VARCHAR(18) NOT NULL COMMENT '库存用户密码',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '库存用户入职时间',
	user_level TINYINT NOT NULL COMMENT '库存用户等级 1：管理员 2：采购员 3：车间员',
	user_active TINYINT NOT NULL DEFAULT -1 COMMENT '库存用户的状态 -1：注销   1：在职员工',
	PRIMARY KEY(user_id),
	KEY (create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存用户表';

-- 初始化数据
insert into
	stock_user(user_number,user_name,user_password,user_level,user_active)
values
	('100001','yld','123456',1,1),
	('100002','小明','123456',2,1),
	('100003','小天','123456',3,1);
	
	
-- 库存零件表(parts)
create table stock_parts(
	parts_id INT NOT NULL AUTO_INCREMENT COMMENT '库存零件id',
	parts_number CHAR(8) NOT NULL COMMENT '库存零件编号',
	parts_material VARCHAR(12) NOT NULL COMMENT '库存零件名字',
	parts_total INT NOT NULL DEFAULT 0 COMMENT '库存总量',
	parts_remain INT NOT NULL DEFAULT 0 COMMENT '库存剩余',
	parts_used INT NOT NULL DEFAULT 0 COMMENT '库存已用',
	parts_limit INT NOT NULL DEFAULT 0 COMMENT '库存临界值',
	parts_append INT NOT NULL DEFAULT 0 COMMENT '库存追加',
	parts_default_append INT NOT NULL DEFAULT 0 COMMENT '库存默认追加',
	parts_active TINYINT NOT NULL DEFAULT -1 COMMENT '库存零件的状态 -1：停购   1：正常生产',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '库存材料初始添加时间',
	PRIMARY KEY(parts_id),
	KEY (parts_number)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存零件表';

-- 初始化数据
insert into 
	stock_parts(parts_number,parts_material,parts_total,parts_remain,parts_limit,parts_default_append,parts_active)
values
	('10000001','木材',10000,10000,3000,5000,1),
	('10000002','轮子',10000,10000,2000,3000,1),
	('10000003','芯片',10000,10000,3500,6000,1);
	
insert into 
	stock_parts(parts_number,parts_material,parts_total,parts_remain,parts_used,parts_limit,parts_default_append,parts_active)
values
	('10000004','玻璃',10000,3000,7000,1000,1000,0);

insert into 
	stock_parts(parts_number,parts_material,parts_total,parts_remain,parts_used,parts_limit,parts_default_append,parts_active)
values
	('10000005','布料',10000,5000,5000,3000,4500,1);	
	
insert into 
	stock_parts(parts_number,parts_material,parts_total,parts_remain,parts_used,parts_limit,parts_default_append,parts_active)
values
	('10000006','石英',10000,0,10000,3000,4500,0);	
	
	
-- 库存操作表
-- manager 追加
-- buyer 采购
-- employ 提取 还需
create table stock_operate(
	operate_id INT NOT NULL AUTO_INCREMENT COMMENT '库存操作id',
	user_number CHAR(6) NOT NULL COMMENT '库存用户编号',
	user_name VARCHAR(12) NOT NULL COMMENT '库存用户名',
	user_level TINYINT NOT NULL COMMENT '库存用户等级 1：管理员 2：采购员 3：车间员',
	parts_number CHAR(8) NOT NULL COMMENT '库存零件编号',
	parts_material VARCHAR(12) NOT NULL COMMENT '库存零件名字',
	operate_add INT NOT NULL DEFAULT 0 COMMENT 'manager: append , buyer: buy , employ: need',
	operate_take INT NOT NULL DEFAULT -1 COMMENT 'employ: take',
	operate_active TINYINT NOT NULL DEFAULT -1 COMMENT '操作的状态 -1：停止  1：正常',
	create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作初始时间',
	operate_number INT NOT NULL COMMENT '操作的次数,只能操作一次',
	PRIMARY KEY(operate_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='库存操作表';


