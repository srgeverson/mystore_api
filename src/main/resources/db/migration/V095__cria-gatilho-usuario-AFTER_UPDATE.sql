DROP TRIGGER IF EXISTS `mystore`.`usuarios_AFTER_UPDATE`;

DELIMITER $$
USE `mystore`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mystore`.`usuarios_AFTER_UPDATE` AFTER UPDATE ON `usuarios` FOR EACH ROW
BEGIN
DECLARE tabela CHAR(8) DEFAULT 'usuarios';
DECLARE operacao CHAR(6) DEFAULT 'UPADTE';
	IF NEW.id <> OLD.id THEN
		CALL sp_registra_log(tabela, 'id', NEW.id, operacao, OLD.id, NEW.id);
    END IF;
    IF NEW.nome <> OLD.nome THEN
		CALL sp_registra_log(tabela, 'nome', NEW.id, operacao, OLD.nome, NEW.nome);
    END IF;
	IF NEW.email <> OLD.email THEN
		CALL sp_registra_log(tabela, 'email', NEW.id, operacao, OLD.email, NEW.email);
    END IF;
    IF NEW.senha <> OLD.senha THEN
		CALL sp_registra_log(tabela, 'senha', NEW.id, operacao, OLD.senha, NEW.senha);
    END IF;
	IF NEW.ativo <> OLD.ativo THEN
		CALL sp_registra_log(tabela, 'ativo', NEW.id, operacao, OLD.ativo, NEW.ativo);
    END IF;
    IF NEW.codigo_acesso <> OLD.codigo_acesso THEN
		CALL sp_registra_log(tabela, 'codigo_acesso', NEW.id, operacao, OLD.codigo_acesso, NEW.codigo_acesso);
    END IF;
	IF NEW.data_cadastro <> OLD.data_cadastro THEN
		CALL sp_registra_log(tabela, 'data_cadastro', NEW.id, operacao, OLD.data_cadastro, NEW.data_cadastro);
    END IF;
    IF NEW.data_ultimo_acesso <> OLD.data_ultimo_acesso THEN
		CALL sp_registra_log(tabela, 'data_ultimo_acesso', NEW.id, operacao, OLD.data_ultimo_acesso, NEW.data_ultimo_acesso);
    END IF;
END$$
DELIMITER ;
