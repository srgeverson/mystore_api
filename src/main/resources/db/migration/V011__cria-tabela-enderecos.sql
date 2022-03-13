CREATE TABLE enderecos (
	id BIGINT NOT NULL AUTO_INCREMENT,
	logradouro VARCHAR(100),
	numero VARCHAR(20),
	complemento VARCHAR(60),
	bairro VARCHAR(60),
	cep VARCHAR(10),
	cidades_id BIGINT,
	PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;