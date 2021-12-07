DELETE
FROM task;
DELETE
FROM employee_role;
DELETE
FROM employee;
DELETE
FROM department;
DELETE
FROM company;

ALTER SEQUENCE global_seq RESTART WITH 100000;


INSERT INTO company (name, physical_address, legal_address, head)
VALUES ('Сибирская геологическая компания', '660077, Красноярский край, г. Красноярск, ул. Весны, д. 5, оф. 122',
        '660077, Красноярский край, г. Красноярск, ул. Весны, д. 5, оф. 122', 'Стрельцов Александр Владимирович');

INSERT INTO department (name, contact, head, company_id)
VALUES ('Байкитская партия', '1-69', 'Атонов Сергей Борисович', 100000),
       ('Восточно-Саянская партия', '1-55', 'Крылов Михаил Николаевич', 100000),
       ('Приенисейская партия', '1-23', 'Лягушев Глеб Петрович', 100000),
       ('Геохимическая партия', '1-43', 'Петрова Мария Сергеевна', 100000),
       ('Отдел информационных технологий', '1-49', 'Строев Анатолий Глебович', 100000),
       ('Геофизическая партия', '1-13', 'Иванов Александр Иванович', 100000);

INSERT INTO employee (last_name, first_name, patronymic, login, password, position, company_id, department_id)
VALUES ('Спесивцев', 'Сергей', 'Андреевич', 'spesivcev-sa', '{noop}12345', 'Геолог', 100000, 100001),
       ('Плеханов', 'Андрей', 'Дмитриевич', 'plehanov-ad', '{noop}11111', 'Ведущий геолог', 100000, 100001),
       ('Антонов', 'Сергей', 'Борисович', 'antonov-sb', '{noop}22222', 'Начальник партии', 100000, 100001),
       ('Прохоров', 'Антон', 'Николаевич', 'prohorov-an', '{noop}33333', 'Главный геолог партии', 100000, 100001),
       ('Прозорова', 'Юлия', 'Евгеньевна', 'prozorova-ye', '{noop}44444', 'Геолог 1 категории', 100000, 100001),
       ('Панова', 'Мария', 'Александровна', 'panova-ma', '{noop}55555', 'Геолог 2 категории', 100000, 100001),

       ('Евгеньев', 'Олег', 'Сергеевич', 'evgenev-os', '{noop}aAaaa', 'Геолог 2 категории', 100000, 100002),
       ('Крылов', 'Михаил', 'Николаевич', 'krylov-mn', '{noop}123123', 'Начальник партии', 100000, 100002),
       ('Храмова', 'Алина', 'Сергеевна', 'hramova-as', '{noop}12Abv12', 'Геолог 1 категории', 100000, 100002),
       ('Дмитриев', 'Алексей', 'Иванович', 'dmitriev-ai', '{noop}54244', 'Ведущий геолог', 100000, 100002),
       ('Михайлова', 'Нина', 'Александровна', 'mihaylova-na', '{noop}mih124', 'Техник-геолог', 100000, 100002),

       ('Строев', 'Анатолий', 'Глебович', 'stroev-ag', '{noop}15265', 'Начальник отдела', 100000, 100005),
       ('Сержантов', 'Василий', 'Федорович', 'serzhantov-vf', '{noop}vasfed', 'Специалист 1 категории', 100000, 100005),
       ('Внуков', 'Матвей', 'Александрович', 'vnukov-ma', '{noop}1234556', 'Специалист по КБ', 100000, 100005);

INSERT INTO employee_role (employee_id, role)
VALUES (100007, 'USER'),
       (100008, 'USER'),
       (100009, 'USER'),
       (100009, 'MANAGER'),
       (100010, 'USER'),
       (100011, 'USER'),
       (100012, 'USER'),

       (100013, 'USER'),
       (100014, 'USER'),
       (100014, 'MANAGER'),
       (100015, 'USER'),
       (100016, 'USER'),
       (100017, 'USER'),

       (100018, 'ADMIN'),
       (100018, 'USER'),
       (100018, 'MANAGER'),
       (100019, 'ADMIN'),
       (100019, 'USER'),
       (100020, 'ADMIN'),
       (100020, 'USER');

INSERT INTO task (title, author_id, performer_id, deadline, description, performed, accepted)
VALUES ('Литохимия для квартального отчета по Узюпскому объекту', 100009, 100007, '2021-12-30 10:00:00',
        'Необходимо подготовить разноску по участкам Троицкий и Успенский', false, false),
       ('Глава "Бурение" в проект по Кизасскому объекту', 100009, 100008, '2021-12-15 12:00:00',
        'Составить ГТН, написать текстовую часть', true, false),
       ('Заявки на ТМЦ на полевой сезон', 100009, 100010, '2021-12-20 15:00:00',
        'Сформировать заявки на покупку ТМЦ для Кизасского и Узюпского объектов, утвердить в отделе МТС', false, false),

       ('Правки по геологической карте 25-го масштаба', 100014, 100015, '2021-12-25 16:00:00',
        'Исправить: условные обозначения; линии разломов; привести к масштабу', false, false),
       ('Внешний контроль литохимических проб', 100014, 100013, '2021-12-20 17:00:00',
        'Отобрать и отправить на внешний контроль 350 проб по участку Семеновскому', false, false);
