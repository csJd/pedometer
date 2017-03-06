create database pedometer;
USE pedometer;

create table users (
	id int primary key auto_increment,
	username varchar(40),
	account varchar(40),
	password varchar(40)
);

create table sport_records (
  id         int primary key auto_increment,
  uid        int,
  distance   double,
  time       int,
  speed      double,
  start_time TEXT,
  stop_time  TEXT,
  step_count int,
    foreign key(uid) references users(id)
);
