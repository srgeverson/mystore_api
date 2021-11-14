SET SQL_SAFE_UPDATES = 0;
set foreign_key_checks = 0;

lock tables cidades write, clientes write, empresas write, estados write, empresas_formas_pagamentos write,
empresas_usuarios_responsaveis write, enderecos write, formas_pagamentos write, grupos write, 
grupos_permissoes write, oauth_client_details write, permissoes write, produtos write, usuarios write, usuarios_grupos write;

delete from cidades;
delete from clientes;
delete from empresas;
delete from empresas_formas_pagamentos;
delete from empresas_usuarios_responsaveis;
delete from enderecos;
delete from estados;
delete from formas_pagamentos;
#delete from foto_produto;
delete from grupos;
delete from grupos_permissoes;
#delete from itens_pedidos;
delete from oauth_client_details;
delete from permissoes;
#delete from pedidos;
delete from produtos;
delete from usuarios;
delete from usuarios_grupos;

set foreign_key_checks = 1;

alter table cidades auto_increment = 1;
alter table empresas auto_increment = 1;
alter table enderecos auto_increment = 1;
alter table estados auto_increment = 1;
alter table formas_pagamentos auto_increment = 1;
alter table grupos auto_increment = 1;
alter table oauth_client_details auto_increment = 1;
alter table permissoes auto_increment = 1;
#alter table pedidos auto_increment = 1;
alter table produtos auto_increment = 1;
alter table usuarios auto_increment = 1;

insert into estados (id, nome) values (1, 'Ceará');

insert into cidades (id, nome, estados_id) values (1, 'Fortaleza', 1);
insert into cidades (id, nome, estados_id) values (2, 'Caucaia', 1);
insert into cidades (id, nome, estados_id) values (3, 'Eusebio', 1);

insert into enderecos (id, logradouro, numero, bairro, cep, cidades_id) values (1, 'Rua', 'S/N', NULL, NULL , 1);

insert into empresas (id, nome,  data_cadastro, ativo, enderecos_id) values (1, 'PrimeMultimarcas', utc_timestamp, true, 1);
insert into empresas (id, nome, data_cadastro, ativo, enderecos_id) values (2, 'Nilson Limpeza', utc_timestamp, true, 1);
insert into empresas (id, nome, data_cadastro,  ativo, enderecos_id) values (3, 'Granfinamodas', utc_timestamp, true, 1);

insert into produtos (id, nome, descricao, preco_compra, preco_venda, preco_custo, ativo, empresas_id) values (1, "Amaciante Azul", "Roupas macias e cheirosas", 0, 0, 0, true,1);

insert into formas_pagamentos (id, descricao, data_atualizacao) values (1, 'Cartão de crédito', utc_timestamp);
insert into formas_pagamentos (id, descricao, data_atualizacao) values (2, 'Cartão de débito', utc_timestamp);
insert into formas_pagamentos (id, descricao, data_atualizacao) values (3, 'Dinheiro', utc_timestamp);
insert into formas_pagamentos (id, descricao, data_atualizacao) values (4, 'Fiado', utc_timestamp);

insert into permissoes (id, nome, descricao, ativo) values (1, 'CONSULTAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite consultar usuários, grupos e permissões', true);
insert into permissoes (id, nome, descricao, ativo) values (2, 'EDITAR_CIDADES', 'Permite criar ou editar cidades', true);
insert into permissoes (id, nome, descricao, ativo) values (3, 'EDITAR_CLIENTES', 'Permite criar ou editar clientes', true);
insert into permissoes (id, nome, descricao, ativo) values (4, 'EDITAR_EMPRESAS', 'Permite criar, editar ou gerenciar empresas', true);
insert into permissoes (id, nome, descricao, ativo) values (5, 'EDITAR_ESTADOS', 'Permite criar ou editar estados', true);
insert into permissoes (id, nome, descricao, ativo) values (6, 'EDITAR_FORMAS_PAGAMENTO', 'Permite criar ou editar formas de pagamento', true);
insert into permissoes (id, nome, descricao, ativo) values (7, 'EDITAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite criar ou editar usuários, grupos e permissões', true);
#insert into permissoes (id, nome, descricao, ativo) values (7, 'CONSULTAR_PEDIDOS', 'Permite consultar pedidos', true);
#insert into permissoes (id, nome, descricao, ativo) values (8, 'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos', true);
#insert into permissoes (id, nome, descricao, ativo) values (9, 'GERAR_RELATORIOS', 'Permite gerar relatórios', true);

insert into empresas_formas_pagamentos (empresas_id, formas_pagamentos_id) values (1, 1);

insert into grupos (id, nome) values (1, 'TI'), (2, 'Dono'), (3, 'Vendedor'), (4, 'Auxiliar');

insert into grupos_permissoes (grupos_id, permissoes_id) values (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6);

insert into usuarios (id, nome, email, senha, ativo, data_ultimo_acesso, data_cadastro) values
(1, 'Administrador', 'paulistensetecnologia@gmail.com', '$2a$12$tVlLLpPEEGKGK9UoMIXiFOQguudMDPGvautJgqp/jzBEdWxTzpi2u', true, utc_timestamp, utc_timestamp),
(2, 'Anderson', 'anderson@gmail.com', '$2a$12$tVlLLpPEEGKGK9UoMIXiFOQguudMDPGvautJgqp/jzBEdWxTzpi2u', true, utc_timestamp, utc_timestamp),
(3, 'Nilson', 'nilson@hotmail.com', '$2a$12$tVlLLpPEEGKGK9UoMIXiFOQguudMDPGvautJgqp/jzBEdWxTzpi2u', true, utc_timestamp, utc_timestamp),
(4, 'Vera', 'vera@icloud.com', '$2a$12$tVlLLpPEEGKGK9UoMIXiFOQguudMDPGvautJgqp/jzBEdWxTzpi2u', true, utc_timestamp, utc_timestamp);

insert into usuarios_grupos (usuarios_id, grupos_id) values (1, 1), (2, 2);

insert into oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, autoapprove)
values ('mystore-web', null, '$2a$12$1RCnMPrhtq1coLaRbrG8zOwvViirmOYscHezhW9quozc1TGVO/EI2','READ,WRITE', 'password', null, null, 60 * 60 * 6, 60 * 24 * 60 * 60, null);

insert into oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, autoapprove)
values ('mystore-app', null, '$2a$12$1RCnMPrhtq1coLaRbrG8zOwvViirmOYscHezhW9quozc1TGVO/EI2','READ,WRITE', 'password', null, null, 60 * 60 * 6, 60 * 24 * 60 * 60, null);

insert into oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, autoapprove)
values ('mystore-manager', null, '$2a$12$1RCnMPrhtq1coLaRbrG8zOwvViirmOYscHezhW9quozc1TGVO/EI2', 'READ,WRITE', 'client_credentials', null, 'RECUPERAR_SENHA', null, null, null);

unlock tables;