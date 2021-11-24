create table itens_pedido (
  id bigint not null auto_increment,
  quantidade smallint(6) not null,
  preco_unitario decimal(10,2) not null,
  preco_total decimal(10,2) not null,
  observacao varchar(255) null,
  pedidos_id bigint not null,
  produtos_id bigint not null,
  primary key (id),
  unique key uk_item_pedido_produto (pedidos_id, produtos_id)
) engine=InnoDB default charset=utf8;