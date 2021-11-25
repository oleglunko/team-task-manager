DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS employee_role;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS company;

CREATE TABLE IF NOT EXISTS company
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR NOT NULL,
    physical_address VARCHAR NOT NULL,
    legal_address    VARCHAR NOT NULL,
    head             VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS department
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR                         NOT NULL,
    contact    VARCHAR                         NOT NULL,
    head       VARCHAR                         NOT NULL,
    company_id INTEGER REFERENCES company (id) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee
(
    id            SERIAL PRIMARY KEY,
    last_name     VARCHAR                            NOT NULL,
    first_name    VARCHAR                            NOT NULL,
    patronymic    VARCHAR,
    position      VARCHAR                            NOT NULL,
    company_id    INTEGER REFERENCES company (id)    NOT NULL,
    department_id INTEGER REFERENCES department (id) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee_role
(
    employee_id INTEGER REFERENCES employee (id) ON DELETE CASCADE NOT NULL,
    role        VARCHAR,
    CONSTRAINT employee_role_idx UNIQUE (employee_id, role)
);

CREATE TABLE IF NOT EXISTS task
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR                         NOT NULL,
    author_id    BIGINT REFERENCES employee (id) NOT NULL,
    performer_id BIGINT REFERENCES employee (id),
    deadline     TIMESTAMP                       NOT NULL,
    description  VARCHAR                         NOT NULL
)






