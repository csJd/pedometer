CREATE DATABASE pedometer;
USE pedometer;

CREATE TABLE users (
  id       INT PRIMARY KEY AUTO_INCREMENT,
  username TEXT,
  account  TEXT,
  password TEXT
);

CREATE TABLE sport_records (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  uid        INT,
  distance   DOUBLE,
  time       INT,
  speed      DOUBLE,
  start_time DATE,
  stop_time  DATE,
  step_count INT,
  FOREIGN KEY (uid) REFERENCES users (id)
);

CREATE TABLE user_relatives (
  id   INT PRIMARY KEY AUTO_INCREMENT,
  uid1 INT,
  uid2 INT,
  FOREIGN KEY (uid1) REFERENCES users (id),
  FOREIGN KEY (uid2) REFERENCES users (id)
);
