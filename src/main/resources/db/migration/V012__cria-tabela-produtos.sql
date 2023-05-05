CREATE TABLE produtos (
	id BIGINT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(80) NOT NULL,
	descricao TEXT,
	preco_compra DECIMAL(10,2),
    preco_venda DECIMAL(10,2),
    preco_custo DECIMAL(10,2),
	ativo TINYINT(1) NOT NULL,
	empresas_id BIGINT NOT NULL,
	PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;