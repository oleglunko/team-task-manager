DROP TABLE IF EXISTS task;
DROP TABLE IF EXISTS employee_role;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;
DROP TABLE IF EXISTS company;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE IF NOT EXISTS company
(
    id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name             VARCHAR NOT NULL,
    physical_address VARCHAR NOT NULL,
    legal_address    VARCHAR NOT NULL,
    head             VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS department
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name       VARCHAR                         NOT NULL,
    contact    VARCHAR                         NOT NULL,
    head       VARCHAR                         NOT NULL,
    company_id INTEGER REFERENCES company (id) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    last_name     VARCHAR                                              NOT NULL,
    first_name    VARCHAR                                              NOT NULL,
    patronymic    VARCHAR,
    position      VARCHAR                                              NOT NULL,
    company_id    INTEGER REFERENCES company (id) ON DELETE CASCADE    NOT NULL,
    department_id INTEGER REFERENCES department (id) ON DELETE CASCADE NOT NULL
);

CREATE TABLE IF NOT EXISTS employee_role
(
    employee_id INTEGER REFERENCES employee (id) ON DELETE CASCADE NOT NULL,
    role        VARCHAR,
    CONSTRAINT employee_role_idx UNIQUE (employee_id, role)
);

CREATE TABLE IF NOT EXISTS task
(
    id           INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    title        VARCHAR                                           NOT NULL,
    author_id    BIGINT REFERENCES employee (id) ON DELETE CASCADE NOT NULL,
    performer_id BIGINT REFERENCES employee (id) ON DELETE CASCADE,
    deadline     TIMESTAMP                                         NOT NULL,
    description  VARCHAR                                           NOT NULL
)






