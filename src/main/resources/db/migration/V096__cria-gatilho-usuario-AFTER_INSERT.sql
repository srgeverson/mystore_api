DROP TRIGGER IF EXISTS `mystore`.`usuarios_AFTER_INSERT`;

DELIMITER $$
USE `mystore`$$
CREATE DEFINER=`root`@`localhost` TRIGGER `usuarios_AFTER_INSERT` AFTER INSERT ON `usuarios` FOR EACH ROW BEGIN
DECLARE tabela CHAR(8) DEFAULT 'usuarios';
DECLARE operacao CHAR(6) DEFAULT 'INSERT';
	IF NEW.id IS NOT NULL THEN
		CALL sp_registra_log(tabela, 'id', NEW.id, operacao, NULL, NEW.id);
    END IF;
    IF NEW.nome IS NOT NULL THEN
		CALL sp_registra_log(tabela, 'nome', NEW.id, operacao, NULL, NEW.nome);
    END IF;
	IF NEW.email IS NOT NULL THEN
		CALL sp_registra_log(tabela, 'email', NEW.id, operacao, NULL, NEW.email);
    END IF;
    IF NEW.email IS NOT NULL THEN
		CALL sp_registra_log(tabela, 'senha', NEW.id, operacao, NULL, NEW.senha);
    END IF;
    IF NEW.codigo_acesso IS NOT NULL THEN
		CALL sp_registra_log(tabela, 'codigo_acesso', NEW.id, operacao, NULL, NEW.codigo_acesso);
    END IF;
	IF NEW.data_cadastro IS NOT NULL THEN
		CALL sp_registra_log(tabela, 'data_cadastro', NEW.id, operacao, NULL, NEW.data_cadastro);
    END IF;
    IF NEW.data_ultimo_acesso IS NOT NULL THEN
		CALL sp_registra_log(tabela, 'data_ultimo_acesso', NEW.id, operacao, NULL, NEW.data_ultimo_acesso);
    END IF;
END$$
DELIMITER ;
