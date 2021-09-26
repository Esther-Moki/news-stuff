SET MODE PostgreSQL;
--
--CREATE DATABASE news_information;
--\c news_information

CREATE TABLE IF NOT EXISTS departments (
      id int PRIMARY KEY auto_increment,
      name VARCHAR,
      description VARCHAR,
      employees VARCHAR
);

CREATE TABLE IF NOT EXISTS news (
      id int PRIMARY KEY auto_increment,
      content VARCHAR,
      departmentsid INTEGER,
      usersid INTEGER
);

CREATE TABLE IF NOT EXISTS users (
      id int PRIMARY KEY auto_increment,
      username VARCHAR,
      position VARCHAR,
      role VARCHAR,
      departmentsid INTEGER
);
CREATE TABLE IF NOT EXISTS departments_users (
      id int PRIMARY KEY auto_increment,
      usersid INTEGER,
      departmentsid INTEGER
);
CREATE TABLE IF NOT EXISTS departments_news (
      id int PRIMARY KEY auto_increment,
      newsid INTEGER,
      departmentsid INTEGER
);
--
--CREATE DATABASE news_information_test WITH TEMPLATE news_information;
