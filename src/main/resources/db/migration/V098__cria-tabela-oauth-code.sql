CREATE TABLE oauth_code(
	code VARCHAR(256),
    authentication BLOB
) engine=InnoDB default charset=utf8;