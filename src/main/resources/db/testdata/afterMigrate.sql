SET SQL_SAFE_UPDATES = 0;
set foreign_key_checks = 0;

delete from cidades;
delete from empresas;
delete from empresas_formas_pagamentos;
#delete from empresas_usuarios_responsaveis;
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
#delete from produtos;
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
#alter table produtos auto_increment = 1;
alter table usuarios auto_increment = 1;

insert into estados (id, nome) values (1, 'Ceará');

insert into cidades (id, nome, estados_id) values (1, 'Fortaleza', 1);
insert into cidades (id, nome, estados_id) values (2, 'Caucaia', 1);
insert into cidades (id, nome, estados_id) values (3, 'Eusebio', 1);

insert into enderecos (id, logradouro, numero, bairro, cep, cidades_id) values (1, 'Rua', 'S/N', NULL, NULL , 1);

insert into empresas (id, nome,  data_cadastro, ativo, enderecos_id) values (1, 'PrimeMultimarcas', utc_timestamp, true, 1);
insert into empresas (id, nome, data_cadastro, ativo, enderecos_id) values (2, 'Nilson Limpeza', utc_timestamp, true, 1);
insert into empresas (id, nome, data_cadastro,  ativo, enderecos_id) values (3, 'Granfinamodas', utc_timestamp, true, 1);


insert into formas_pagamentos (id, descricao) values (1, 'Cartão de crédito');
insert into formas_pagamentos (id, descricao) values (2, 'Cartão de débito');
insert into formas_pagamentos (id, descricao) values (3, 'Dinheiro');
insert into formas_pagamentos (id, descricao) values (4, 'Fiado');

insert into permissoes (id, nome, descricao) values (1, 'GERENCIAR_USUARIOS_GRUPOS_PERMISSOES', 'Permite consultar usuários, grupos e permissões');
insert into permissoes (id, nome, descricao) values (2, 'GERENCIAR_EMPRESAS', 'Permite gerenciar pedidos');
insert into permissoes (id, nome, descricao) values (3, 'GERENCIAR_CIDADES', 'Permite criar ou editar cidades');
insert into permissoes (id, nome, descricao) values (4, 'GERENCIAR_ESTADOS', 'Permite criar ou editar estados');
insert into permissoes (id, nome, descricao) values (5, 'GERENCIAR_PEDIDOS', 'Controla os pedidos emitidos');

insert into empresas_formas_pagamentos (empresas_id, formas_pagamentos_id) values (1, 1);

insert into grupos (id, nome) values (1, 'TI'), (2, 'Dono'), (3, 'Vendedor'), (4, 'Auxiliar');

insert into grupos_permissoes (grupos_id, permissoes_id) values (1, 1), (2, 5);

insert into usuarios (id, nome, email, senha, ativo, data_ultimo_acesso, data_cadastro) values
(1, 'Administrador', 'mystore@gmail.com', '$2a$12$tVlLLpPEEGKGK9UoMIXiFOQguudMDPGvautJgqp/jzBEdWxTzpi2u', true, utc_timestamp, utc_timestamp),
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