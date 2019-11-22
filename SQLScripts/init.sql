CREATE DATABASE  crudapp;
USE crudapp;

CREATE TABLE user
(
    id         int(11) NOT NULL auto_increment,
    first_name varchar(20) default null,
    last_name  varchar(20) default null,
    username   varchar(20) default null,
    password   varchar(20) default null,
    unique (username),
    primary key (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE stuffs
(
    stuff_id    int(11) NOT NULL ,
    name        varchar(200) DEFAULT NULL,
    description text,
    quantity    int(11)      DEFAULT NULL,
    user_id int(11) NOT NULL,
    primary key (stuff_id),
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
