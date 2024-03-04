ALTER SESSION SET container = XEPDB1;
CREATE TABLE PUBLICATIONS.USERS (
    id VARCHAR2(36) PRIMARY KEY,
    name VARCHAR2(50) NOT NULL,
    email VARCHAR2(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

INSERT INTO PUBLICATIONS.USERS (name, email,created_at, updated_at) VALUES ('Diego Armando', 'diegovillamizar231@gmail.com', SYSTIMESTAMP, null);