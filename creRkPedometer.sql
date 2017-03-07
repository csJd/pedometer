create database pedometer;
USE pedometer;

create table users (
  id       int primary key auto_increment,
  username TEXT,
  account  TEXT,
  password TEXT
);

create table sport_records (
  id         int primary key auto_increment,
  uid        int,
  distance   double,
  time       int,
  speed      double,
  start_time DATE,
  stop_time  DATE,
  step_count int,
    foreign key(uid) references users(id)
);
