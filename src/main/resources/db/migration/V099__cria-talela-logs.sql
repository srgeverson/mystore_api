CREATE TABLE logs (
	id BIGINT NOT NULL AUTO_INCREMENT,
    tabela_id BIGINT,
    tabela_nome VARCHAR(50) NOT NULL,
    tabela_coluna VARCHAR(100),
    operacao VARCHAR(6),
    valor_anterior TEXT,
    valor_atual TEXT,
    data_operacao DATETIME DEFAULT NULL,
    usuarios_id BIGINT,
	PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;
