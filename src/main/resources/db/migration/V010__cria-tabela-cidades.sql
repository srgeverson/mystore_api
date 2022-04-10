CREATE TABLE cidades (
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(80) NOT NULL,
    codigo_municipio INT,
    ativo TINYINT(1),
	estados_id BIGINT,
	PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;