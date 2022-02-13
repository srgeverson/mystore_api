DELIMITER $$
USE `mystore`$$
CREATE DEFINER = CURRENT_USER PROCEDURE `mystore`.`sp_registra_log`(
IN TABELA VARCHAR (50),
IN COLUNA VARCHAR (100),
IN ID BIGINT,
IN OPERACAO VARCHAR(6),
IN VALOR_ANTERIOR TEXT,
IN VALOR_ATUAL TEXT
)
BEGIN
#
INSERT INTO logs
		(tabela_nome, tabela_coluna, tabela_id, operacao, data_operacao, valor_anterior, valor_atual) 
	VALUES
		(TABELA, COLUNA, ID, OPERACAO, utc_timestamp, VALOR_ANTERIOR, VALOR_ATUAL);
END$$

DELIMITER ;
;

