drop table if exists test_single_select_task;
drop table if exists test_single_select_task_event;

create table test_single_select_task
(
    id                     varchar(255) not null,
    chapter_id             int4,
    description            varchar(10000),
    reference_id           int4,
    correct_answer         varchar(255),
    incorrect_answer_one   varchar(255),
    incorrect_answer_two   varchar(255),
    incorrect_answer_three varchar(255),
    incorrect_answer_four  varchar(255),
    primary key (id)
);

create table test_single_select_task_event
(
    id                    varchar(255) not null,
    author_id             varchar(255),
    entity_id             varchar(255),
    occuring_context      varchar(255),
    occuring_context_time timestamp,
    payload               jsonb,
    event_type            int4,
    event_version         varchar(255),
    primary key (id)
);

INSERT INTO test_single_select_task (id, chapter_id, description, reference_id, correct_answer,
                                     incorrect_answer_one, incorrect_answer_two, incorrect_answer_three,
                                     incorrect_answer_four)
VALUES ('1', 1, 'description', 4, 'correct_answer', 'incorrect_answer_one', 'incorrect_answer_two',
        'incorrect_answer_three', 'incorrect_answer_four'),
('2', 2, 'another_description', 4, 'correct_answer', 'incorrect_answer_one', 'incorrect_answer_two',
    'incorrect_answer_three', 'incorrect_answer_four')
,
 ('3', 2, 'another_description', 4, 'correct_answer', 'incorrect_answer_one', 'incorrect_answer_two',
        'incorrect_answer_three', 'incorrect_answer_four'),
 ('4', 3, 'another_description', 4, 'correct_answer', 'incorrect_answer_one', 'incorrect_answer_two',
        'incorrect_answer_three', 'incorrect_answer_four'),
 ('5', 3, 'another_description', 3, 'correct_answer', 'incorrect_answer_one', 'incorrect_answer_two',
        'incorrect_answer_three', 'incorrect_answer_four'),
 ('6', 3, 'another_description', 3, 'correct_answer', 'incorrect_answer_one', 'incorrect_answer_two',
        'incorrect_answer_three', 'incorrect_answer_four'),
 ('7', 4, 'another_description', 3, 'correct_answer', 'incorrect_answer_one', 'incorrect_answer_two',
        'incorrect_answer_three', 'incorrect_answer_four'),
 ('8', 4, 'another_description', 2, 'correct_answer', 'incorrect_answer_one', 'incorrect_answer_two',
        'incorrect_answer_three', 'incorrect_answer_four'),
 ('9', 4, 'another_description', 2, 'correct_answer', 'incorrect_answer_one', 'incorrect_answer_two',
        'incorrect_answer_three', 'incorrect_answer_four'),
 ('10', 4, 'another_description', 1, 'correct_answer', 'incorrect_answer_one', 'incorrect_answer_two',
        'incorrect_answer_three', 'incorrect_answer_four');