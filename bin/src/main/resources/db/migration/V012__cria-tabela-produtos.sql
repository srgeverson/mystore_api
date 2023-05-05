CREATE TABLE produtos (
	id BIGINT NOT NULL auto_increment,
	empresas_id BIGINT NOT NULL,
	nome VARCHAR(80) NOT NULL,
	descricao TEXT NOT NULL,
	preco decimal(10,2) NOT NULL,
	ativo TINYINT(1) NOT NULL,
	PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;