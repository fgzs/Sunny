create table user
(
	id int auto_increment
		primary key,
	name varchar(100) null,
	account_id varchar(100) null,
	token char(36) null,
	gmt_create bigint null,
	gmt_create bigint null,
	gmt_modified bigint null
);