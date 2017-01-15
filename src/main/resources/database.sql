#Table: users
CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT,
  email    VARCHAR(255) NOT NULL,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;

#Table: games
CREATE TABLE games (
  id         INT NOT NULL AUTO_INCREMENT,
  start_page INT NOT NULL,
  end_page   INT NOT NULL,
  user_id    INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (start_page) REFERENCES pages (id),
  FOREIGN KEY (end_page) REFERENCES pages (id)
)
  ENGINE = InnoDB;

#Table: pages
CREATE TABLE pages (
  id    INT          NOT NULL AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL
)
  ENGINE = InnoDB;