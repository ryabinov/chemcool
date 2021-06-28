drop table if exists test_chemistry_fixed_answer;
drop table if exists test_chemistry_fixed_answer_event;

create table test_chemistry_fixed_answer
(
    task_id varchar(255) not null primary key,
    description VARCHAR(10000),
    right_answer VARCHAR(255),
    chapter_id int,
    reference_id int
    );

create table test_chemistry_fixed_answer_event (
    event_uuid VARCHAR(255) not null primary key,
    event_author_id VARCHAR(255) not null,
    event_occuring_comtext VARCHAR(255) not null,
    event_occuring_context_time TIMESTAMP not null,
    event_type VARCHAR(255) not null,
    event_version VARCHAR(255),
    event_entity_id VARCHAR(255),
    event_payload jsonb not null
    );

DELETE FROM test_chemistry_fixed_answer;

INSERT INTO test_chemistry_fixed_answer
(task_id, chapter_id, description, reference_id, right_answer)
VALUES (1, 1, 111, 1, 111),
       (2, 2, 222, 2, 222),
       (3, 3, 333, 3, 333),
       (4, 4, 444, 4, 444);

