ALTER SESSION SET container = XEPDB1;
CREATE TABLE PUBLICATIONS.PROMISSORY_NOTES (
    id NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1) PRIMARY KEY,
    id_investment NUMBER NOT NULL,
    url VARCHAR2(200) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    FOREIGN KEY (id_investment) REFERENCES PUBLICATIONS.INVESTMENTS(id),
    UNIQUE (id_investment)
);