CREATE TABLE cidades (
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(80) NOT NULL,
	estados_id BIGINT,
	PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;