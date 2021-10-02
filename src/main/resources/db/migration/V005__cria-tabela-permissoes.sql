CREATE TABLE permissoes (
	id BIGINT NOT NULL auto_increment,
	descricao VARCHAR(60) NOT NULL,
	nome VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;