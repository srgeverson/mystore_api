CREATE TABLE logs (
	id BIGINT NOT NULL AUTO_INCREMENT,
    tabela_nome VARCHAR(50) NOT NULL,
    tabela_coluna VARCHAR(100),
    tabela_id BIGINT,
    operacao VARCHAR(6),
    data_operacao DATETIME DEFAULT NULL,
    valor_anterior TEXT,
    valor_atual TEXT,
    usuarios_id BIGINT,
	PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;
