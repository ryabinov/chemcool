create table if not exists chemistry_lesson_event
(
    event_id                     varchar(255) not null primary key,
    event_author                 varchar(255) not null,
    event_occurring_context      varchar(255) not null,
    event_occurring_context_time timestamp    not null,
    event_payload                jsonb        not null
);

create table if not exists chemistry_lesson_page
(
    theory_id          varchar(255) not null primary key,
    theory_name        varchar(255) not null,
    theory_description varchar(10000),
    theory_chapter     integer      not null,
    theory_references  integer      not null

);

create table if not exists chemistry_theory_event(
    event_id varchar(255) not null primary key,
    event_author varchar(255) not null,
    event_occurring_context varchar(255) not null ,
    event_occurring_context_time timestamp not null,
    event_type varchar(255) not null,
    version varchar(255) not null,
    event_entity_id varchar(255) not null,
    event_payload jsonb not null
);

create table if not exists chemistry_theory_page (
    theory_id varchar(255) not null primary key,
    theory_name varchar(255) not null ,
    theory_description varchar(10000),
    theory_chapter  integer not null,
    theory_references integer not null
);

create table if not exists chemistry_single_select_task
(
    id                     varchar(255) not null
        constraint chemistry_single_select_task_pkey
            primary key,
    chapter_id             integer,
    correct_answer         varchar(255),
    description            varchar(10000),
    incorrect_answer_one   varchar(255),
    incorrect_answer_two   varchar(255),
    incorrect_answer_three varchar(255),
    incorrect_answer_four  varchar(255),
    reference_id           integer
);

