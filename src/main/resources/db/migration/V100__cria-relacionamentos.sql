ALTER TABLE cidades ADD CONSTRAINT fk_cidades_estados_id
FOREIGN KEY (estados_id) REFERENCES estados (id);

ALTER TABLE clientes ADD CONSTRAINT fk_clientes_enderecos_id
FOREIGN KEY (enderecos_id) REFERENCES enderecos (id);

ALTER TABLE clientes ADD CONSTRAINT fk_clientes_empresas_id
FOREIGN KEY (empresas_id) REFERENCES empresas (id);

ALTER TABLE empresas ADD CONSTRAINT fk_empresas_enderecos_id
FOREIGN KEY (enderecos_id) REFERENCES enderecos (id);

ALTER TABLE empresas_formas_pagamentos ADD CONSTRAINT fk_empresas_formas_pagamentos_empresas_id
FOREIGN KEY (empresas_id) REFERENCES empresas (id);

ALTER TABLE empresas_formas_pagamentos ADD CONSTRAINT fk_empresas_formas_pagamentos_formas_pagamentos_id
FOREIGN KEY (formas_pagamentos_id) REFERENCES formas_pagamentos (id);

alter table empresas_usuarios_responsaveis add constraint fk_empresas_usuarios_responsaveis_empresas_id
foreign key (empresas_id) references empresas (id);

alter table empresas_usuarios_responsaveis add constraint fk_empresas_usuarios_responsaveis_usuarios_id
foreign key (usuarios_id) references usuarios (id);

ALTER TABLE enderecos ADD CONSTRAINT fk_empresas_cidades_id
FOREIGN KEY (cidades_id) REFERENCES cidades (id);

ALTER TABLE grupos_permissoes ADD CONSTRAINT fk_grupos_permissoes_grupos_id
FOREIGN KEY (grupos_id) REFERENCES grupos (id);

ALTER TABLE grupos_permissoes ADD CONSTRAINT fk_grupos_permissoes_permissoes_id
FOREIGN KEY (permissoes_id) REFERENCES permissoes (id);

ALTER TABLE produtos ADD CONSTRAINT fk_produtos_empresas_id
FOREIGN KEY (empresas_id) REFERENCES empresas (id);

ALTER TABLE usuarios_grupos ADD CONSTRAINT fk_usuarios_grupos_grupos_id
FOREIGN KEY (grupos_id) REFERENCES grupos (id);

ALTER TABLE usuarios_grupos ADD CONSTRAINT fk_usuarios_grupos_usuarios_id
FOREIGN KEY (usuarios_id) REFERENCES usuarios (id);
