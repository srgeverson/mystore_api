CREATE TABLE usuarios (
	id BIGINT NOT NULL auto_increment,
	nome VARCHAR(80) NOT NULL,
	email VARCHAR(255) NOT NULL,
	senha VARCHAR(255) NOT NULL,
	data_cadastro datetime NOT NULL,
	PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;