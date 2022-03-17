CREATE TABLE empresas_formas_pagamentos (
	empresas_id BIGINT NOT NULL,
	formas_pagamentos_id BIGINT NOT NULL,
	PRIMARY KEY (empresas_id, formas_pagamentos_id)
) engine=InnoDB default charset=utf8;