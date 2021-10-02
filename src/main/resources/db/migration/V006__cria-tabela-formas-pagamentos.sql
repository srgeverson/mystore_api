CREATE TABLE formas_pagamentos (
	id BIGINT NOT NULL AUTO_INCREMENT,
	descricao varchar(60) NOT NULL,
    data_atualizacao DATETIME,
    ativo TINYINT(1),
	PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;