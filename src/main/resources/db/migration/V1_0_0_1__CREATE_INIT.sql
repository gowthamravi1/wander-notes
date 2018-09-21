--This table is used for adding new user.
CREATE TABLE auth_user
(
  id bigint(20) AUTO_INCREMENT NOT NULL,
  name varchar(50) NOT NULL,
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE(email)
);

CREATE TABLE notes
(
  id bigint(20) AUTO_INCREMENT NOT NULL,
  title text NOT NULL,
  description text NOT NULL,
  created_at datetime NOT NULL,
  updated_at datetime NOT NULL,
  user_id bigint(20) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE(id),
  FOREIGN KEY (user_id) REFERENCES auth_user(id) ON DELETE CASCADE
);