-- Сценарий использования – "Авторизация"

-- Переменные для "честности запроса"
WITH my_constants (usr_login, usr_password_hash)
         as (values ('your_aksioma', '8BeNwoHhivE2zbcrhHnX9vqcAZWP1MnV'))

SELECT id
FROM auth
WHERE login = (SELECT usr_login FROM my_constants)
  AND password_hash = (SELECT usr_password_hash FROM my_constants);

-- Сценарий использования – "Добавление задачи"

-- Переменные для "честности запроса"
WITH my_constants (usr_id, usr_date_start, usr_date_end)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49', '2022-10-21', '2022-10-25'))

SELECT date, SUM(minutes_amount)
FROM watcher
WHERE person_id = (SELECT usr_id::uuid FROM my_constants)
  AND date BETWEEN (SELECT usr_date_start::date FROM my_constants) AND (SELECT usr_date_end::date FROM my_constants)
GROUP BY date;

SELECT p.name
FROM person
         LEFT JOIN person_to_project ptp on person.id = ptp.person_id
         LEFT JOIN project p on ptp.project_id = p.id;

-- Переменные для "честности запроса"
WITH my_constants (usr_id, usr_date, usr_project_name, usr_time, usr_comment)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49', '2022-10-22', 'Buhinder', 360,
                     'Сделала авторизацию X2'))

INSERT
INTO watcher (person_id, date, project_id, minutes_amount, comment)
SELECT (SELECT usr_id::uuid FROM my_constants),
       (SELECT usr_date::date FROM my_constants),
       project.id,
       (SELECT usr_time FROM my_constants),
       (SELECT usr_comment FROM my_constants)
FROM project
WHERE name = (SELECT usr_project_name FROM my_constants);

-- Переменные для "честности запроса"
WITH my_constants (usr_id, usr_date_start, usr_date_end)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49', '2022-10-21', '2022-10-25'))

SELECT date, SUM(minutes_amount)
FROM watcher
WHERE person_id = (SELECT usr_id::uuid FROM my_constants)
  AND date BETWEEN (SELECT usr_date_start::date FROM my_constants) AND (SELECT usr_date_end::date FROM my_constants)
GROUP BY date;

-- Сценарий использования – "Просмотр задач"

-- Переменные для "честности запроса"
WITH my_constants (usr_id, usr_date_start, usr_date_end)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49', '2022-10-21', '2022-10-25'))

SELECT date, SUM(minutes_amount)
FROM watcher
WHERE person_id = (SELECT usr_id::uuid FROM my_constants)
  AND date BETWEEN (SELECT usr_date_start::date FROM my_constants) AND (SELECT usr_date_end::date FROM my_constants)
GROUP BY date;

-- Переменные для "честности запроса"
WITH my_constants (usr_id, usr_date) as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49',
                                                 '2022-10-22'))

SELECT p.name, watcher.minutes_amount, watcher.comment
FROM watcher
    LEFT JOIN project p on watcher.project_id = p.id
WHERE person_id = (SELECT usr_id::uuid FROM my_constants)
  AND date = (SELECT usr_date::date FROM my_constants);

-- Переменные для "честности запроса"
WITH my_constants (usr_id, usr_date_start, usr_date_end)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49', '2022-10-21', '2022-10-25'))

SELECT date, SUM(minutes_amount)
FROM watcher
WHERE person_id = (SELECT usr_id::uuid FROM my_constants)
  AND date BETWEEN (SELECT usr_date_start::date FROM my_constants) AND (SELECT usr_date_end::date FROM my_constants)
GROUP BY date;

-- Сценарий использования – "Изменение задачи"

-- для проверки
SELECT *
FROM watcher;

-- Переменные для "честности запроса"
WITH my_constants (usr_watcher_id, usr_time, usr_comment)
         as (values ('6aa884fe-d01b-4e30-b64a-c71d16ddb308', 480, 'Создал бд для справок'))

UPDATE watcher
SET minutes_amount = (SELECT usr_time FROM my_constants),
    comment        = (SELECT usr_comment FROM my_constants)
WHERE id = (SELECT usr_watcher_id::uuid FROM my_constants);
SELECT *
FROM watcher;

-- Сценарий использования – "Удаление задачи"

-- для проверки
SELECT *
FROM watcher;

-- Переменные для "честности запроса"
WITH my_constants (usr_watcher_id) as (values ('f6221d65-af6e-4c4c-b071-bb85936ae25f'))

DELETE
FROM watcher
WHERE id = (SELECT usr_watcher_id::uuid FROM my_constants);

-- для проверки
SELECT *
FROM watcher;

-- Сценарий использования – "Просмотр дней отпуска"

-- Переменные для "честности запроса"
WITH my_constants (usr_activity, start_date, end_date) as (values ('Отпуск', '2022-10-21', '2022-10-25'))

SELECT watcher.date
FROM watcher
         JOIN project p on p.id = watcher.project_id
WHERE p.name = (SELECT usr_activity FROM my_constants)
  AND watcher.date BETWEEN (SELECT start_date::date FROM my_constants)
      AND (SELECT end_date::date FROM my_constants);

-- Сценарий использования – "Просмотр больничных дней"

-- Переменные для "честности запроса"
WITH my_constants (usr_activity, start_date, end_date) as (values ('Больничный', '2022-10-21', '2022-10-25'))

SELECT watcher.date
FROM watcher
         JOIN project p on p.id = watcher.project_id
WHERE p.name = (SELECT usr_activity FROM my_constants)
  AND watcher.date BETWEEN (SELECT start_date::date FROM my_constants)
    AND (SELECT end_date::date FROM my_constants);

-- Сценарий использования – "Изменение своих контактов"

-- Переменные для "честности запроса"
WITH my_constants (usr_id)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49'))

SELECT *
FROM person
WHERE id = (SELECT usr_id::uuid FROM my_constants);

-- Переменные для "честности запроса"
WITH my_constants (usr_id, usr_number, usr_email)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49', '79179842361', 'kate6575@gmail.com'))

UPDATE person
SET email        = (SELECT usr_email FROM my_constants),
    phone_number = (SELECT phone_number FROM my_constants)
WHERE id = (SELECT usr_id::uuid FROM my_constants);

-- Переменные для "честности запроса"
WITH my_constants (usr_id)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49'))

SELECT *
FROM person
WHERE id = (SELECT usr_id::uuid FROM my_constants);

-- Сценарий использования – "Поиск сотрудника"

-- Переменные для "честности запроса"
WITH my_constants (name_pattern)
         as (values ('%а%'))

SELECT id, name, position
FROM person
WHERE name ILIKE (SELECT name_pattern FROM my_constants);

-- Сценарий использования – "Расширенный поиск сотрудника"

-- Переменные для "честности запроса"
WITH my_constants (name_pattern, surname_pattern, patronymic_pattern, age_min, age_max, sex,
                   position_pattern, email_pattern, number_pattern)
         as (values ('%а%', '%а%', '%а%', 18, 30, 'FEMALE', '%a%', '%a%', '%7%'))

SELECT id, name, position
FROM person
WHERE name ILIKE (SELECT name_pattern FROM my_constants)
  AND surname ILIKE (SELECT surname_pattern FROM my_constants)
  AND patronymic ILIKE (SELECT patronymic_pattern FROM my_constants)
  AND DATE_PART('year', AGE(now(), birthday)) BETWEEN (SELECT age_min FROM my_constants)
    AND (SELECT age_max FROM my_constants)
  AND sex = (SELECT sex::sex FROM my_constants)
  AND position::text ILIKE (SELECT position_pattern FROM my_constants)
  AND email ILIKE (SELECT email_pattern FROM my_constants)
  AND phone_number ILIKE (SELECT number_pattern FROM my_constants);

-- Сценарий использования – "Заказ справки"

-- Переменные для "честности запроса"
WITH my_constants (usr_id)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49'))
-- для проверки
SELECT *
FROM document
WHERE person_id = (SELECT usr_id::uuid FROM my_constants);

-- Переменные для "честности запроса"
WITH my_constants (usr_id, doc_type)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49', 'WORK_STATEMENT'))

INSERT
INTO document(type, person_id, order_date, status)
VALUES ((SELECT doc_type::document_type FROM my_constants), (SELECT usr_id::uuid FROM my_constants),
        CURRENT_DATE, 'ORDERED');

-- Переменные для "честности запроса"
WITH my_constants (usr_id)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49'))
-- для проверки
SELECT *
FROM document
WHERE person_id = (SELECT usr_id::uuid FROM my_constants);


-- Сценарий использования – "Просмотр заказанных справок"

-- Переменные для "честности запроса"
WITH my_constants (usr_id)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49'))
-- для проверки
SELECT *
FROM document
WHERE person_id = (SELECT usr_id::uuid FROM my_constants);

-- Сценарий использования – "Просмотр списанных часов другим пользователем"

-- Переменные для "честности запроса"
WITH my_constants (other_usr_id, usr_date_start, usr_date_end, name_pattern)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49', '2022-10-21', '2022-10-25'))

SELECT *
FROM watcher
WHERE person_id = (SELECT other_usr_id::uuid FROM my_constants)
  AND date BETWEEN (SELECT usr_date_start::date FROM my_constants) AND (SELECT usr_date_end::date FROM my_constants);

-- Сценарий использования – "Добавление сотрудника"

-- для проверки
SELECT *
FROM person;

WITH my_constants_auth (id, login, password_hash)
         as (values ('c48fcdb6-4bed-435b-9d1b-6947ccb77644'::uuid, 'lelen',
                     'm5C09li7Qk/4ifl7IwPXxY96j/YNQZq4'))


INSERT
INTO auth (id, login, password_hash)
SELECT *
FROM my_constants_auth;
WITH my_constants_person (id, usr_name, usr_surname, usr_patronymic, usr_sex, usr_birthday,
                          usr_first_work_day,
                          usr_position, usr_status, usr_phone_number, usr_email, usr_job_time_start,
                          usr_job_time_end, usr_office_id, usr_nationality_id, usr_passport_number,
                          usr_passport_issued_place, usr_passport_issued_date, usr_address,
                          usr_salary,
                          usr_comment)
         as (values ('c48fcdb6-4bed-435b-9d1b-6947ccb77644'::uuid, 'Елена', 'Иванова', 'Алексеевна',
                     'FEMALE'::sex,
                     '2001-10-14'::date, '2022-07-14'::date,
                     'JUNIOR_BACKEND_DEVELOPER'::usr_position, 'WORKING'::person_status,
                     '79117236438', 'lelens@gmail.com', '9:00', '18:00',
                     'bd711817-57a0-430d-8553-15dd4e3d7de0'::uuid,
                     'f7d00710-1f66-46d8-a3de-b0e4536774a1'::uuid,
                     'BM3478689', 'Октябрьский РОВД', '2015-10-14'::date,
                     'Улица Торжковская, дом 15',
                     1000000, 'Молодой специалист'))

INSERT
INTO person (id, name, surname, patronymic, sex, birthday, first_work_day, position, status,
             phone_number, email, job_time_start, job_time_end, office_id, nationality_id,
             passport_number, passport_issued_place, passport_issued_date, address, salary, comment)
SELECT *
FROM my_constants_person;

-- для проверки
SELECT *
FROM person;

-- Сценарий использования – "Изменение информации о сотруднике"

WITH my_constants (usr_id)
         as (values ('c48fcdb6-4bed-435b-9d1b-6947ccb77644'))

-- для проверки
SELECT *
FROM person
WHERE id = (SELECT usr_id::uuid FROM my_constants);

-- Переменные для "честности запроса"
WITH my_constants (usr_id, usr_name, usr_surname, usr_patronymic, usr_sex, usr_birthday,
                   usr_first_work_day,
                   usr_position, usr_status, usr_phone_number, usr_email, usr_job_time_start,
                   usr_job_time_end, usr_office_id, usr_nationality_id, usr_passport_number,
                   usr_passport_issued_place, usr_passport_issued_date, usr_address, usr_salary,
                   usr_comment)
         as (values ('c48fcdb6-4bed-435b-9d1b-6947ccb77644'::uuid, 'Лена', 'Иванова', 'Алексеевна',
                     'FEMALE'::sex,
                     '2001-10-14'::date, '2022-07-14'::date,
                     'JUNIOR_BACKEND_DEVELOPER'::usr_position, 'WORKING'::person_status,
                     '79117236438', 'lelens@gmail.com', '9:00', '18:00',
                     'bd711817-57a0-430d-8553-15dd4e3d7de0'::uuid,
                     'f7d00710-1f66-46d8-a3de-b0e4536774a1'::uuid,
                     'BM3478689', 'Октябрьский РОВД', '2015-10-14'::date,
                     'Улица Торжковская, дом 15',
                     1000000, 'Молодой специалист'))

UPDATE person
SET name                  = (SELECT usr_name FROM my_constants),
    surname               = (SELECT usr_surname FROM my_constants),
    patronymic            = (SELECT usr_patronymic FROM my_constants),
    sex                   = (SELECT usr_sex FROM my_constants),
    birthday              = (SELECT usr_birthday FROM my_constants),
    first_work_day        = (SELECT usr_first_work_day FROM my_constants),
    position              = (SELECT usr_position FROM my_constants),
    status                = (SELECT usr_status FROM my_constants),
    phone_number          = (SELECT usr_phone_number FROM my_constants),
    email                 = (SELECT usr_email FROM my_constants),
    job_time_start        = (SELECT usr_job_time_start FROM my_constants),
    job_time_end          = (SELECT usr_job_time_end FROM my_constants),
    office_id             = (SELECT usr_office_id FROM my_constants),
    nationality_id        = (SELECT usr_nationality_id FROM my_constants),
    passport_number       = (SELECT usr_passport_number FROM my_constants),
    passport_issued_place = (SELECT usr_passport_issued_place FROM my_constants),
    passport_issued_date  = (SELECT usr_passport_issued_date FROM my_constants),
    address               = (SELECT usr_address FROM my_constants),
    salary                = (SELECT usr_salary FROM my_constants),
    comment               = (SELECT usr_comment FROM my_constants)
WHERE id = (SELECT usr_id::uuid FROM my_constants);

-- Переменные для "честности запроса"
WITH my_constants (usr_id)
         as (values ('c48fcdb6-4bed-435b-9d1b-6947ccb77644'))

-- для проверки
SELECT *
FROM person
WHERE id = (SELECT usr_id::uuid FROM my_constants);

-- Сценарий использования – "Удаление сотрудника"

-- Переменные для "честности запроса"
WITH my_constants (usr_id)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49'))

-- для проверки
SELECT status
FROM person
WHERE id = (SELECT usr_id::uuid FROM my_constants);

-- Переменные для "честности запроса"
WITH my_constants (usr_id)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49'))

UPDATE person
SET status = 'NOT_WORKING'
WHERE id = (SELECT usr_id::uuid FROM my_constants);

-- Переменные для "честности запроса"
WITH my_constants (usr_id)
         as (values ('78a1f37d-9043-4783-a5f5-3a353d9bff49'))

-- для проверки
SELECT status
FROM person
WHERE id = (SELECT usr_id::uuid FROM my_constants);

-- Сценарий использования – "Просмотр всех заказанных справок"

SELECT *
FROM person
         LEFT JOIN document d on person.id = d.person_id
         LEFT JOIN nationality n on person.nationality_id = n.id
         LEFT JOIN office o on person.office_id = o.id
WHERE d.status NOT IN ('DONE', 'CANCELED');

-- Сценарий использования – "Изменение статуса заказа"

-- Переменные для "честности запроса"
WITH my_constants (doc_id)
         as (values ('703d1140-96b8-4543-bf17-3d393c41f332'))

-- для проверки
SELECT *
FROM document
WHERE id = (SELECT doc_id::uuid FROM my_constants);

-- Переменные для "честности запроса"
WITH my_constants (doc_id, new_status)
         as (values ('703d1140-96b8-4543-bf17-3d393c41f332', 'CANCELED'))

UPDATE document
SET status = (SELECT new_status::document_status FROM my_constants)
WHERE id = (SELECT doc_id::uuid FROM my_constants);

-- Переменные для "честности запроса"
WITH my_constants (doc_id)
         as (values ('703d1140-96b8-4543-bf17-3d393c41f332'))

-- для проверки
SELECT *
FROM document
WHERE id = (SELECT doc_id::uuid FROM my_constants);

-- Сценарий использования – "Массовый импорт / экспорт данных"

SELECT *
FROM person
         LEFT JOIN document d on person.id = d.person_id
         LEFT JOIN nationality n on person.nationality_id = n.id
         LEFT JOIN office o on person.office_id = o.id;

SELECT *
FROM person
         LEFT JOIN auth a on person.id = a.id
         LEFT JOIN nationality n on person.nationality_id = n.id
         LEFT JOIN office o on person.office_id = o.id;

SELECT *
FROM person
         LEFT JOIN nationality n on person.nationality_id = n.id
         LEFT JOIN office o on person.office_id = o.id
         LEFT JOIN person_to_project ptp on person.id = ptp.person_id
         LEFT JOIN project p on ptp.project_id = p.id;

SELECT *
FROM person
         LEFT JOIN watcher w on person.id = w.person_id
         LEFT JOIN nationality n on person.nationality_id = n.id
         LEFT JOIN office o on person.office_id = o.id;

