SET MODE PostgreSQL;

CREATE DATABASE newsinformation;
\c newsinformation

CREATE TABLE IF NOT EXISTS departments (
 id SERIAL PRIMARY KEY,
 name VARCHAR,
 description VARCHAR,
 employees VARCHAR
);
CREATE TABLE IF NOT EXISTS news (
  id SERIAL PRIMARY KEY,
  content VARCHAR,
  departmentsId INTEGER,
  usersId INTEGER
);

CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY,
  username VARCHAR,
  position VARCHAR,
  role VARCHAR,
  departmentsid INTEGER
);
CREATE TABLE IF NOT EXISTS departments_users (
  id SERIAL PRIMARY KEY,,
 usersid INTEGER,
 departmetsid INTEGER
);
CREATE TABLE IF NOT EXISTS departments_news (
  id SERIAL PRIMARY KEY,,
 newsid INTEGER,
 departmetsid INTEGER
);

CREATE DATABASE newsinformation_test WITH TEMPLATE newsinformation;





