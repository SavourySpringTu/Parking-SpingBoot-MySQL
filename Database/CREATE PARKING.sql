drop database parking;
CREATE DATABASE parking;
USE parking;

USE parking;
CREATE TABLE Employee(
	id CHAR(10) PRIMARY KEY NOT NULL,
    name CHAR(20),
    password CHAR(20)
);
CREATE TABLE Revenue(
	id int PRIMARY KEY NOT NULL,
    time Date,
    total int
);
CREATE TABLE Positions(
	id CHAR(10) PRIMARY KEY NOT NULL,
    status BOOLEAN
);
CREATE TABLE Ticket(
	id CHAR(10) PRIMARY KEY NOT NULL,
    time TIME,
    ticket_type BOOLEAN,
    price INT,
    status BOOLEAN,
    id_employee CHAR(10),
    number_car INT,
	id_revenue int,
    id_position CHAR(10),
    FOREIGN KEY (id_employee) REFERENCES Employee (id),
    FOREIGN KEY (id_revenue) REFERENCES Revenue (id),
    FOREIGN KEY (id_position) REFERENCES Positions (id)
);

INSERT INTO Employee(id,name,password) VALUES ("NV01","Alice","123");
INSERT INTO Employee(id,name,password) VALUES ("NV02","Tex","123");

INSERT INTO Positions(id,status) VALUES ("P01",0);
INSERT INTO Positions(id,status) VALUES ("P03",0);
INSERT INTO Positions(id,status) VALUES ("P02",0);
INSERT INTO Positions(id,status) VALUES ("P04",0);

INSERT INTO Revenue(id,time,total) VALUES (1,"2023-06-03",5000);

INSERT INTO Ticket(id,time,ticket_type,price,status,id_employee,number_car,id_revenue,id_position) VALUES ("T001","06:00:00",0,5000,0,"NV01",123456,1,"P01");