CREATE TABLE clientes (
	id BIGINT NOT NULL auto_increment,
	apelido_nome_fantazia VARCHAR(100) NOT NULL,
    nome_razao_social VARCHAR(100),
    cpf_cnpj VARCHAR(20),
	email VARCHAR(100),
    telefone VARCHAR(14),
    celular VARCHAR(15),
	data_cadastro datetime NOT NULL,
    ativo TINYINT(1) NOT NULL,
    enderecos_id BIGINT,
    empresas_id BIGINT NOT NULL,
	PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;