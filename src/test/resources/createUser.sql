-- Auth_user test table for store user details.

CREATE TABLE IF NOT EXISTS auth_user
(
  id bigint(20) AUTO_INCREMENT NOT NULL,
  name varchar(50) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE(email)
);