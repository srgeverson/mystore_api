CREATE TABLE empresas (
	id BIGINT NOT NULL auto_increment,
	nome VARCHAR(80) NOT NULL,
	cpf_cnpj VARCHAR(30),
	data_atualizacao DATETIME,
	data_cadastro DATETIME NOT NULL,
	ativo TINYINT(1) NOT NULL,
	enderecos_id BIGINT,
    telefone VARCHAR(20),
	primary key (id)
) engine=InnoDB default charset=utf8;