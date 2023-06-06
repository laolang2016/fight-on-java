create table dept(
	id bigint(20) primary key auto_increment,
	name varchar(100) comment '部门名称',
	parent_id bigint(20) comment '上级部门id',
	manager_user_id bigint(20) comment '管理员id',
	sort int(11) comment '排序值',
	create_by varchar(150)  comment '创建人',
	create_time datetime  comment '创建时间',
	update_by varchar(150)  comment '更新人',
	update_time datetime  comment '更新时间',
	remark varchar(200) comment '备注',
	deleted int(10)  comment '逻辑删除',
	version int(10)  comment '乐观锁'
)engine = innodb default charset = utf8 comment '部门表';

INSERT INTO mybatis_study.dept
(id, name, parent_id, manager_user_id, sort, create_by, create_time, update_by, update_time, remark, deleted, version)
VALUES(1, '开发部', 0, 1, 1, '1', '2023-06-06 13:23:59', NULL, '2023-06-06 13:23:59', NULL, 0, 1);