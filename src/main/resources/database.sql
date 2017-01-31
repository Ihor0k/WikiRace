#Table: users
CREATE TABLE users (
  id       INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL
);

#Table: pages
CREATE TABLE pages (
  id          INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title       VARCHAR(4095) NOT NULL,
  url         VARCHAR(4095) NOT NULL,
  page_name   VARCHAR(4095) NOT NULL,
  description TEXT
);

#Table: games
CREATE TABLE games (
  id         INT  NOT NULL AUTO_INCREMENT PRIMARY KEY,
  start_page INT  NOT NULL,
  end_page   INT  NOT NULL,
  pages_list TEXT NOT NULL,
  user_id    INT  NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (start_page) REFERENCES pages (id),
  FOREIGN KEY (end_page) REFERENCES pages (id)
);