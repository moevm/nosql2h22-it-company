DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1
                      FROM pg_type t
                               LEFT JOIN pg_namespace n ON t.typnamespace = n.oid
                      WHERE t.typname = 'sex'
                        AND n.nspname = 'public')
        THEN
            CREATE TYPE sex AS ENUM (
                'MALE',
                'FEMALE'
                );
        END IF;
    END
$$;

DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1
                      FROM pg_type t
                               LEFT JOIN pg_namespace n ON t.typnamespace = n.oid
                      WHERE t.typname = 'person_status'
                        AND n.nspname = 'public')
        THEN
            CREATE TYPE person_status AS ENUM (
                'WORKING',
                'ON_HOLIDAY',
                'SEEK_LEAVE',
                'NOT_WORKING'
                );
        END IF;
    END
$$;

DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1
                      FROM pg_type t
                               LEFT JOIN pg_namespace n ON t.typnamespace = n.oid
                      WHERE t.typname = 'document_status'
                        AND n.nspname = 'public')
        THEN
            CREATE TYPE document_status AS ENUM (
                'ORDERED',
                'IN_PROGRESS',
                'DONE',
                'CANCELED'
                );
        END IF;
    END
$$;

DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1
                      FROM pg_type t
                               LEFT JOIN pg_namespace n ON t.typnamespace = n.oid
                      WHERE t.typname = 'position'
                        AND n.nspname = 'public')
        THEN
            CREATE TYPE usr_position AS ENUM (
                'JUNIOR_BACKEND_DEVELOPER',
                'MIDDLE_BACKEND_DEVELOPER',
                'SENIOR_BACKEND_DEVELOPER',
                'JUNIOR_FRONTEND_DEVELOPER',
                'MIDDLE_FRONTEND_DEVELOPER',
                'SENIOR_FRONTEND_DEVELOPER',
                'JUNIOR_QA_DEVELOPER',
                'MIDDLE_QA_DEVELOPER',
                'SENIOR_QA_DEVELOPER',
                'JUNIOR_DEVOPS_DEVELOPER',
                'MIDDLE_DEVOPS_DEVELOPER',
                'SENIOR_DEVOPS_DEVELOPER',
                'HR',
                'PM',
                'ADMINISTRATION',
                'OFFICE_MANAGER'
                );
        END IF;
    END
$$;

DO
$$
    BEGIN
        IF NOT EXISTS(SELECT 1
                      FROM pg_type t
                               LEFT JOIN pg_namespace n ON t.typnamespace = n.oid
                      WHERE t.typname = 'document_type'
                        AND n.nspname = 'public')
        THEN
            CREATE TYPE document_type AS ENUM (
                'INCOME_STATEMENT',
                'WORK_STATEMENT'
                );
        END IF;
    END
$$;

CREATE TABLE IF NOT EXISTS auth
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    login         TEXT NOT NULL,
    password_hash TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS nationality
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nationality TEXT NOT NULL

);

CREATE TABLE IF NOT EXISTS office
(
    id      UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name    TEXT NOT NULL,
    address TEXT
);

CREATE TABLE IF NOT EXISTS project
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS person
(
    id                    UUID PRIMARY KEY
        CONSTRAINT auth_id_fk REFERENCES auth,
    name                  TEXT          NOT NULL,
    surname               TEXT          NOT NULL,
    patronymic            TEXT,
    sex                   SEX           NOT NULL,
    birthday              DATE          NOT NULL,
    first_work_day        DATE          NOT NULL,
    position              USR_POSITION  NOT NULL,
    status                PERSON_STATUS NOT NULL,
    phone_number          TEXT          NOT NULL,
    email                 TEXT          NOT NULL,
    job_time_start        TEXT          NOT NULL DEFAULT '09:00',
    job_time_end          TEXT          NOT NULL DEFAULT '18:00',
    office_id             UUID          NOT NULL
        CONSTRAINT office_id_person_fk REFERENCES office,
    nationality_id        UUID          NOT NULL
        CONSTRAINT nationality_id_person_fk REFERENCES nationality,
    passport_number       TEXT          NOT NULL,
    passport_issued_place TEXT          NOT NULL,
    passport_issued_date  DATE          NOT NULL,
    address               TEXT          NOT NULL,
    salary                BIGINT        NOT NULL,
    comment               TEXT          NULL
);

CREATE TABLE IF NOT EXISTS watcher
(
    id             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    person_id      UUID NOT NULL
        CONSTRAINT person_id_watcher_fk REFERENCES person,
    date           DATE NOT NULL,
    project_id     UUID NOT NULL
        CONSTRAINT project_id_watcher_fk REFERENCES project,
    minutes_amount INT  NOT NULL,
    comment        TEXT
);

CREATE TABLE IF NOT EXISTS document
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type       DOCUMENT_TYPE   NOT NULL,
    person_id  UUID            NOT NULL
        CONSTRAINT person_id_document_fk REFERENCES person,
    order_date DATE            NOT NULL,
    status     DOCUMENT_STATUS NOT NULL
);

CREATE TABLE IF NOT EXISTS person_to_project
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    person_id  UUID NOT NULL
        CONSTRAINT person_id_project_fk REFERENCES person,
    project_id UUID NOT NULL
        CONSTRAINT project_id_person_fk REFERENCES project
);

INSERT INTO auth
VALUES ('78a1f37d-9043-4783-a5f5-3a353d9bff49', 'your_aksioma', '8BeNwoHhivE2zbcrhHnX9vqcAZWP1MnV'),
       ('b45afca5-6e10-4968-98a4-d45d5a6bba60', 'waiddew', 'ndlXbmW/PZqxZ6wDhHyBwn6PKSPc6AKn'),
       ('0fb7403e-0f9b-449c-8636-85df3ffdc92b', 'alelambin', 'm5C09li7Qk/4ifl7IwPXxY96j/YNQZq4');

INSERT INTO nationality
VALUES ('f7d00710-1f66-46d8-a3de-b0e4536774a1', 'BELARUS'),
       ('a5a47416-95ee-4839-aa77-c3e9e06403e3', 'RUSSIA');

INSERT INTO office
VALUES ('bd711817-57a0-430d-8553-15dd4e3d7de0', 'Офис Витебск', 'Улица Чапаева, дом 34'),
       ('5cdd0c6d-2f9c-477b-8d26-db429dcf11a2', 'Офис Ставрополь', 'Улица Людникова, дом 12'),
       ('6b79d640-88fa-46cb-bd72-dbb28b335faf', 'Офис Тобольск', 'Улица Фрунзе, дом 23');

INSERT INTO project
VALUES ('23248722-990a-4758-a613-10fae7c83eba', 'Buhinder'),
       ('f44aeeb6-be71-40e8-a1b6-77b88c3586ae', 'Госуслуги'),
       ('e996e43c-1ea6-4584-a256-4eab67c14437', 'Курс крипты'),
       ('7ca65223-c48a-4da2-b725-73972e6a530c', 'Отпуск'),
       ('c6d4cf2e-88b6-4557-9c0d-bb45396aa0b6', 'Больничный');

INSERT INTO person
VALUES ('78a1f37d-9043-4783-a5f5-3a353d9bff49', 'Екатерина', 'Аксёнова', 'Александровна', 'FEMALE',
        '2001-11-14', '2022-04-14', 'JUNIOR_BACKEND_DEVELOPER', 'WORKING', '79112886438',
        'kate.kate6575@gmail.com', '11:00', '20:00', 'bd711817-57a0-430d-8553-15dd4e3d7de0',
        'f7d00710-1f66-46d8-a3de-b0e4536774a1', 'BM5653789', 'Октябрьский РОВД', '2015-10-14',
        'Улица Торжковская, дом 15', 1000000, 'Устала'),
       ('b45afca5-6e10-4968-98a4-d45d5a6bba60', 'Владимир', 'Арутюнян', 'Валерьевич', 'MALE',
        '2001-02-25', '2022-03-10', 'SENIOR_BACKEND_DEVELOPER', 'WORKING', '79615673412',
        'waiddew123@gmail.com', '11;00', '20:00', '5cdd0c6d-2f9c-477b-8d26-db429dcf11a2',
        'a5a47416-95ee-4839-aa77-c3e9e06403e3', '95653789', 'Ставропольский РОВД', '2015-11-14',
        'Улица Торжковская, дом 15', 2000000, 'Мало спит'),
       ('0fb7403e-0f9b-449c-8636-85df3ffdc92b', 'Алексей', 'Ламбин', 'Владимирович', 'MALE',
        '2001-10-18', '2022-04-25', 'MIDDLE_BACKEND_DEVELOPER', 'WORKING', '79116753412',
        'alelambin@gmail.com', '10:00', '19:00', '6b79d640-88fa-46cb-bd72-dbb28b335faf',
        'a5a47416-95ee-4839-aa77-c3e9e06403e3', '34253789', 'Тобольский РОВД', '2015-12-14',
        'Улица Торжковская, дом 15', 3000000, 'Продуктивный');

INSERT INTO watcher
VALUES ('fd7e1b4f-70ad-4abc-83fb-cacc0c24fccd', '78a1f37d-9043-4783-a5f5-3a353d9bff49',
        '2022-10-22', '23248722-990a-4758-a613-10fae7c83eba', 360, 'Сделала авторизацию'),
       ('6aa884fe-d01b-4e30-b64a-c71d16ddb308', 'b45afca5-6e10-4968-98a4-d45d5a6bba60',
        '2022-10-22', 'f44aeeb6-be71-40e8-a1b6-77b88c3586ae', 480,
        'Создал бд для справок о доходах'),
       ('f6221d65-af6e-4c4c-b071-bb85936ae25f', '0fb7403e-0f9b-449c-8636-85df3ffdc92b',
        '2022-10-22', 'e996e43c-1ea6-4584-a256-4eab67c14437', 420, 'Добавил генератор задач'),
       ('2a68cb88-a361-49ec-9e16-68ec388c96ea', '0fb7403e-0f9b-449c-8636-85df3ffdc92b',
        '2022-10-23', '7ca65223-c48a-4da2-b725-73972e6a530c', 1440, ''),
       ('fd7e1b4f-70ad-4abc-83fb-cacc0c24fcce', '78a1f37d-9043-4783-a5f5-3a353d9bff49',
        '2022-10-24', 'c6d4cf2e-88b6-4557-9c0d-bb45396aa0b6', 1440, '');

INSERT INTO document
VALUES ('703d1140-96b8-4543-bf17-3d393c41f332', 'INCOME_STATEMENT', '78a1f37d-9043-4783-a5f5-3a353d9bff49', '2022-10-20', 'IN_PROGRESS'),
       ('32eb3c33-21d5-4c5f-8ce8-8a076afb5e89', 'WORK_STATEMENT', 'b45afca5-6e10-4968-98a4-d45d5a6bba60', '2022-10-10', 'DONE'),
       ('00a27a89-87fc-4114-bce8-d59cfce7c0e7', 'WORK_STATEMENT', '0fb7403e-0f9b-449c-8636-85df3ffdc92b', '2022-10-26', 'ORDERED');

INSERT INTO person_to_project
VALUES ('de6a65ce-68bd-4522-90fd-a33d26fd1df1', '78a1f37d-9043-4783-a5f5-3a353d9bff49',
        '23248722-990a-4758-a613-10fae7c83eba'),
       ('282eb67f-5093-4f14-b5a5-aa893acfcb4f', 'b45afca5-6e10-4968-98a4-d45d5a6bba60',
        'f44aeeb6-be71-40e8-a1b6-77b88c3586ae'),
       ('15959650-e3a6-4c01-8e02-d688c448cf56', '0fb7403e-0f9b-449c-8636-85df3ffdc92b',
        'e996e43c-1ea6-4584-a256-4eab67c14437');
