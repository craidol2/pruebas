USE SRCEIBLOGIC;
CREATE TABLE candidates (
    id_candidate INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(150) NOT NULL,
    birthdate DATE NOT NULL,
    email VARCHAR(250) NOT NULL,
    insert_date DATE NOT NULL,
    modify_date DATE
);