CREATE TABLE estados (
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(80) NOT NULL,
    codigo_uf INT,
    uf CHAR(2),
    ativo TINYINT(1),
    regioes_id INT,
    versao BIGINT NOT NULL,
	PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;