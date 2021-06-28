drop table if exists chemistry_equations;
drop table if exists chemistry_equations_event;

create table chemistry_equations
(
    task_id      varchar(255) not null,
    chapter_id   int4,
    description  varchar(10000),
    reference_id int4,
    right_answer varchar(255),
    primary key (task_id)
);

create table chemistry_equations_event
(
    event_id                    varchar(255) not null,
    event_author_id             varchar(255),
    event_entity_id             varchar(255),
    event_occuring_context      varchar(255),
    event_occuring_context_time timestamp,
    event_payload               jsonb,
    event_type                  int4,
    event_version               varchar(255),
    primary key (event_id)
);

INSERT INTO chemistry_equations (task_id, chapter_id, description, reference_id, right_answer)
VALUES ('1', 1, 'task1', 4, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4');
INSERT INTO chemistry_equations (task_id, chapter_id, description, reference_id, right_answer)
VALUES ('2', 2, 'task2', 4, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4');
INSERT INTO chemistry_equations (task_id, chapter_id, description, reference_id, right_answer)
VALUES ('3', 2, 'task3', 4, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4');
INSERT INTO chemistry_equations (task_id, chapter_id, description, reference_id, right_answer)
VALUES ('4', 3, 'task4', 4, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4');
INSERT INTO chemistry_equations (task_id, chapter_id, description, reference_id, right_answer)
VALUES ('5', 3, 'task5', 3, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4');
INSERT INTO chemistry_equations (task_id, chapter_id, description, reference_id, right_answer)
VALUES ('6', 3, 'task6', 3, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4');
INSERT INTO chemistry_equations (task_id, chapter_id, description, reference_id, right_answer)
VALUES ('7', 4, 'task7', 3, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4');
INSERT INTO chemistry_equations (task_id, chapter_id, description, reference_id, right_answer)
VALUES ('8', 4, 'task8', 2, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4');
INSERT INTO chemistry_equations (task_id, chapter_id, description, reference_id, right_answer)
VALUES ('9', 4, 'task9', 2, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4');
INSERT INTO chemistry_equations (task_id, chapter_id, description, reference_id, right_answer)
VALUES ('10', 4, 'task10', 1, 'CuSO4+2NaOH→Cu(OH)2↓+Na2SO4');