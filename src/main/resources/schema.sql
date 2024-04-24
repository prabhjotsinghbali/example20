CREATE TABLE contact_msg(
contact_id int AUTO_INCREMENT PRIMARY KEY,
name varchar(100) NOT NULL,
mobile_no varchar(10) NOT NULL,
email varchar(100) NOT NULL,
subject varchar(100) NOT NULL,
message varchar(100) NOT NULL,
status varchar(10) NOT NULL,
created_at TIMESTAMP NOT NULL,
created_by varchar(100) NOT NULL,
updated_at TIMESTAMP DEFAULT NULL,
updated_by varchar(100) DEFAULT NULL);
