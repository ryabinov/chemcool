drop table if exists test_matching_task cascade;
drop table if exists test_matching_task_event;
drop table if exists test_couples_for_matching;

create table test_matching_task
(
    task_id      varchar(255) not null,
    chapter_id   int4,
    description  varchar(10000),
    reference_id int4,
    primary key (task_id)
);

create table test_matching_task_event
(
    chemistry_matching_task_event_id                     varchar(255) not null,
    chemistry_matching_task_event_author_id              varchar(255),
    chemistry_matching_task_event_entity_id              varchar(255),
    chemistry_matching_task_event_occurring_context      varchar(255),
    chemistry_matching_task_event_occurring_context_time timestamp,
    chemistry_matching_task_event_payload                jsonb,
    chemistry_matching_task_event_type                   varchar(255),
    version                                              varchar(255),
    primary key (chemistry_matching_task_event_id)
);

create table test_couples_for_matching
(
    couple_id    bigint not null,
    left_couple  varchar(255),
    right_couple varchar(255),
    task_id      varchar(255)
        constraint task_id
            references test_matching_task,
    primary key (couple_id)

);

INSERT INTO test_matching_task (task_id, chapter_id, description, reference_id)
VALUES ('1', 1, 'task1', 4),
       ('2', 2, 'task2', 4),
       ('3', 2, 'task3', 4),
       ('4', 3, 'task4', 4),
       ('5', 3, 'task5', 3),
       ('6', 3, 'task6', 3),
       ('7', 4, 'task7', 3),
       ('8', 4, 'task8', 2),
       ('9', 4, 'task9', 2),
       ('10', 4, 'task10', 1);

INSERT INTO test_couples_for_matching (couple_id, left_couple, right_couple, task_id)
VALUES (1, 'He', 'Гелий', '1'),
       (2, 'H', 'Водород', '2'),
       (3, 'O', 'Кислород', '4'),
       (4, 'C', 'Углерод', '8');