
CREATE DATABASE IF NOT EXISTS dam2;
USE dam2;
CREATE TABLE estudiante (
 id CHAR(9) PRIMARY KEY,
 nombre VARCHAR(50) NOT NULL,
 apellidos VARCHAR(100) NOT NULL,
 edad INT NOT NULL,
 email VARCHAR(100) NOT NULL
);

INSERT INTO estudiante VALUES ('11111111A','Draco','Malfoy',25,'draco@hogwarts.com');
INSERT INTO estudiante VALUES ('22222222B','Hermione','Granger',23,'hermione@hogwarts.com');
INSERT INTO estudiante VALUES ('33333333C','Harry','Potter',20,'harry@hogwarts.com');
INSERT INTO estudiante VALUES ('44444444D','Ron','Weasley',22,'ron@hogwarts.com');
