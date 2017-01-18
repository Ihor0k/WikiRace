#Table: users
CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  email    VARCHAR(255) NOT NULL UNIQUE
);

#Table: pages
CREATE TABLE pages (
  id          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title       VARCHAR(255) NOT NULL,
  url         VARCHAR(255) NOT NULL,
  page_name   VARCHAR(255) NOT NULL,
  description VARCHAR(1023)
);

#Table: games
CREATE TABLE games (
  id         INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
  start_page INT           NOT NULL,
  end_page   INT           NOT NULL,
  pages_list VARCHAR(2047) NOT NULL,
  user_id    INT           NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (start_page) REFERENCES pages (id),
  FOREIGN KEY (end_page) REFERENCES pages (id)
);