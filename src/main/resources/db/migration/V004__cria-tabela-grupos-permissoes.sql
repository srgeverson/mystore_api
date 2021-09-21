CREATE TABLE grupos_permissoes (
	grupos_id BIGINT NOT NULL,
	permissoes_id BIGINT NOT NULL,
	PRIMARY KEY (grupos_id, permissoes_id)
) engine=InnoDB default charset=utf8;