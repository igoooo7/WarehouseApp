/*
CREATE DATABASE db_warehouse;
*/

USE db_warehouse;
 
/*
SHOW DATABASES;
SELECT database();
*/

DROP TABLE orders;
DROP TABLE parts;
DROP TABLE users;
DROP TABLE areas;
DROP TABLE job_titles;
DROP TABLE manufacturers;
DROP TABLE part_groups;
DROP TABLE projects;
DROP TABLE racks;
DROP TABLE shelfs;
DROP TABLE units;
DROP TABLE user_rights;

CREATE TABLE job_titles
(
job_title_id INT NOT NULL AUTO_INCREMENT,
job_title VARCHAR(100) NOT NULL UNIQUE,
PRIMARY KEY(job_title_id)
);

CREATE TABLE user_rights
(
user_rights_id INT NOT NULL AUTO_INCREMENT,
user_rights VARCHAR(50) NOT NULL UNIQUE,
PRIMARY KEY(user_rights_id)
);

CREATE TABLE manufacturers
(
manufacturer_id INT NOT NULL AUTO_INCREMENT,
manufacturer VARCHAR(100) NOT NULL UNIQUE,
PRIMARY KEY (manufacturer_id)
);

CREATE TABLE areas
(
area_id INT NOT NULL AUTO_INCREMENT,
area VARCHAR(100) NOT NULL UNIQUE,
PRIMARY KEY(area_id)
);

CREATE TABLE racks
(
rack_id INT NOT NULL AUTO_INCREMENT,
rack INT NOT NULL UNIQUE,
PRIMARY KEY(rack_id)
);

CREATE TABLE shelfs
(
shelf_id INT NOT NULL AUTO_INCREMENT,
shelf INT NOT NULL UNIQUE,
PRIMARY KEY(shelf_id)
);

CREATE TABLE part_groups
(
part_group_id INT NOT NULL AUTO_INCREMENT,
part_group VARCHAR(100) NOT NULL UNIQUE,
PRIMARY KEY(part_group_id)
);

CREATE TABLE units
(
unit_id INT NOT NULL AUTO_INCREMENT,
unit VARCHAR(50) NOT NULL UNIQUE,
PRIMARY KEY(unit_id)
);

CREATE TABLE users
(
user_id INT NOT NULL AUTO_INCREMENT,
user_rights_id INT NOT NULL,
job_title_id INT NOT NULL,
user_name VARCHAR(50) NOT NULL UNIQUE,
user_password NVARCHAR(25),
user_email NVARCHAR(50),
first_name VARCHAR(25) DEFAULT '',
last_name VARCHAR(25) DEFAULT '',
PRIMARY KEY(user_id),
FOREIGN KEY(user_rights_id) REFERENCES user_rights(user_rights_id),
FOREIGN KEY(job_title_id) REFERENCES job_titles(job_title_id)
);

CREATE TABLE parts
(
part_id INT NOT NULL AUTO_INCREMENT,
user_id INT NOT NULL,
manufacturer_id INT NOT NULL,
area_id INT NOT NULL,
rack_id INT NOT NULL,
shelf_id INT NOT NULL,
part_group_id INT NOT NULL,
unit_id INT NOT NULL,
order_code VARCHAR(255) NOT NULL,
product_code VARCHAR(255),
part_name VARCHAR(255),
description VARCHAR(255),
quantity_min INT NOT NULL,
CHECK(quantity_min >= 0),
quantity_max INT NOT NULL,
CHECK(quantity_max >= quantity_min),
creation_date DATETIME DEFAULT '1999-01-1 00:00:00',
last_change_date DATETIME DEFAULT '1999-01-1 00:00:00',
image LONGBLOB DEFAULT NULL,
PRIMARY KEY(part_id),
FOREIGN KEY(user_id) REFERENCES users(user_id),
FOREIGN KEY(manufacturer_id) REFERENCES manufacturers(manufacturer_id),
FOREIGN KEY(area_id) REFERENCES areas(area_id),
FOREIGN KEY(rack_id) REFERENCES racks(rack_id),
FOREIGN KEY(shelf_id) REFERENCES shelfs(shelf_id),
FOREIGN KEY(part_group_id) REFERENCES part_groups(part_group_id),
FOREIGN KEY(unit_id) REFERENCES units(unit_id)
);
ALTER TABLE parts ADD UNIQUE (manufacturer_id , order_code);

CREATE TABLE projects
(
project_id INT NOT NULL AUTO_INCREMENT,
project_name VARCHAR(100) NOT NULL UNIQUE,
PRIMARY KEY(project_id)
);

CREATE TABLE orders
(
order_id INT NOT NULL AUTO_INCREMENT,
part_id INT NOT NULL,
user_id INT NOT NULL,
part_count INT NOT NULL,
project_id INT NOT NULL,
order_date DATETIME,
PRIMARY KEY(order_id),
FOREIGN KEY(part_id) REFERENCES parts(part_id),
FOREIGN KEY(user_id) REFERENCES users(user_id),
FOREIGN KEY(project_id) REFERENCES projects(project_id)
);









