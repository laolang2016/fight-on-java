create table gen_info(
	id bigint(20) primary key auto_increment,
	table_name varchar(255) comment '表名称',
	table_comment varchar(500) comment '表描述',
	tpl_type varchar(20) comment '模板类型. crud:增删改查 , tree:树表操作',
	gen_type varchar(20) comment '生成类型. zip: 压缩包 , select_path: 自定义路径',
	package_name varchar(255) comment '包名',
	module_name varchar(255) comment '业务模块名',
	class_name varchar(255) comment '实体类名称',
	function_name varchar(255) comment '功能名称',
	function_author varchar(255) comment '功能作者名',
	create_by varchar(150) comment '创建人',
	create_time datetime comment '创建时间',
	update_by varchar(150) comment '更新人',
	update_time datetime comment '更新时间',
	remark varchar(200) comment '备注',
	deleted int(10) comment '逻辑删除',
	version int(10) comment '乐观锁'
)engine = innodb default charset = utf8 comment '代码生成信息表';
