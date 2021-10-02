CREATE TABLE usuarios_grupos (
	usuarios_id BIGINT NOT NULL,
	grupos_id BIGINT NOT NULL,
	PRIMARY KEY (usuarios_id, grupos_id)
) engine=InnoDB default charset=utf8;