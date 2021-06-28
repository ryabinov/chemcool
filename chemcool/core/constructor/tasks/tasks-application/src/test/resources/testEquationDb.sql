drop table if exists test_chemistry_equations;
drop table if exists test_chemistry_equations_event;

create table test_chemistry_equations
(
    task_id      varchar(255) not null,
    chapter_id   int4,
    description  varchar(10000),
    reference_id int4,
    right_answer varchar(255),
    primary key (task_id)
);

create table test_chemistry_equations_event
(
    event_id                    varchar(255) not null,
    event_author_id             varchar(255),
    event_entity_id             varchar(255),
    event_occuring_comtext      varchar(255),
    event_occuring_context_time timestamp,
    event_payload               jsonb,
    event_type                  int4,
    event_version               varchar(255),
    primary key (event_id)
);

INSERT INTO test_chemistry_equations (task_id, chapter_id, description, reference_id, right_answer)
VALUES ('1', 1, 'task1', 4, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4'),
       ('2', 2, 'task2', 4, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4'),
       ('3', 2, 'task3', 4, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4'),
       ('4', 3, 'task4', 4, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4'),
       ('5', 3, 'task5', 3, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4'),
       ('6', 3, 'task6', 3, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4'),
       ('7', 4, 'task7', 3, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4'),
       ('8', 4, 'task8', 2, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4'),
       ('9', 4, 'task9', 2, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4'),
       ('10', 4, 'task10', 1, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4');