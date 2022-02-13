DELIMITER $$
USE `mystore`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mystore`.`usuarios_AFTER_INSERT` AFTER INSERT ON `usuarios` FOR EACH ROW
BEGIN
DECLARE tabela VARCHAR(100) DEFAULT 'usuarios';
DECLARE concluido INT DEFAULT FALSE;
DECLARE nome_coluna VARCHAR(100);
DECLARE valor TEXT;
DECLARE todas_colunas CURSOR FOR SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = tabela ORDER BY ordinal_position;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET concluido = TRUE;
OPEN todas_colunas;

the_loop: LOOP

	FETCH todas_colunas INTO nome_coluna;
    
	IF concluido THEN
      LEAVE the_loop;
    END IF;
	
	set valor = (SELECT CONCAT('NEW.',(SELECT nome_coluna)));

	CALL sp_registra_log(tabela, nome_coluna, NEW.id, 'INSERT', NULL, valor);

END LOOP the_loop;
CLOSE todas_colunas;
END$$
DELIMITER ;