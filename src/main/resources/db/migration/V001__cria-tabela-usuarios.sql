CREATE TABLE usuarios (
	id BIGINT NOT NULL auto_increment,
	nome VARCHAR(80) NOT NULL,
	email VARCHAR(255) NOT NULL,
	senha VARCHAR(255),
    ativo TINYINT(1),
    codigo_acesso VARCHAR(10),
	data_cadastro datetime NOT NULL,
    data_ultimo_acesso datetime,
	PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;