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

CREATE TABLE honors (
  id INT PRIMARY KEY AUTO_INCREMENT
);

CREATE TABLE honor_users (
  id  INT PRIMARY KEY AUTO_INCREMENT,
  uid INT,
  hid INT,
  FOREIGN KEY (uid) REFERENCES users (id),
  FOREIGN KEY (hid) REFERENCES honors (id)
);

INSERT INTO honors VALUES (1);
INSERT INTO honors VALUES (2);
INSERT INTO honors VALUES (3);
INSERT INTO honors VALUES (4);
INSERT INTO honors VALUES (5);
INSERT INTO honors VALUES (6);
