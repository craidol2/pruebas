USE SRCEIBLOGIC;
CREATE TABLE candidate_experiences (
    id_candidate_experience INT PRIMARY KEY,
    id_candidate INT NOT NULL,
    company VARCHAR(100) NOT NULL,
    job VARCHAR(100) NOT NULL,
    description VARCHAR(4000) NOT NULL,
    salary INT NOT NULL,
    begin_date DATE NOT NULL,
    end_date DATE,
    insert_date DATE NOT NULL,
    modify_date DATE,
    CONSTRAINT fk_id_candidate FOREIGN KEY (id_candidate) REFERENCES candidates(id_candidate)
);