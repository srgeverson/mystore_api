create table empresas_usuarios_responsaveis (
  empresas_id bigint not null,
  usuarios_id bigint not null,
  
  primary key (empresas_id, usuarios_id)
) engine=InnoDB default charset=utf8;