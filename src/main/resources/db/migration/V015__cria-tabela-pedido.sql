create table pedidos (
  id bigint not null auto_increment,
  subtotal decimal(10,2) not null,
  taxa_frete decimal(10,2) null,
  valor_total decimal(10,2) not null,
  empresas_id bigint not null,
  clientes_id bigint not null,
  formas_pagamentos_id bigint not null,
  status varchar(10) not null,
  data_criacao datetime not null,
  data_confirmacao datetime null,
  data_cancelamento datetime null,
  data_entrega datetime null,
  primary key (id)
) engine=InnoDB default charset=utf8;
alter table pedidos add codigo varchar(36) not null after id;
update pedidos set codigo = uuid();
alter table pedidos add constraint uk_pedidos_codigo unique (codigo);