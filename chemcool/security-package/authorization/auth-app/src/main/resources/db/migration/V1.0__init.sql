create table if not exists users_event
(
    event_id                     varchar(255) not null primary key,
    event_author_id              varchar(255),
    event_entity_id              varchar(255),
    event_version                VARCHAR(255),
    event_occurring_context      varchar(255),
    event_occurring_context_time timestamp,
    event_payload                jsonb,
    event_type                   varchar(255)
);

create table if not exists users
(
    id                   varchar(255) not null primary key,
    email                varchar(255),
    password             VARCHAR(255),
    name                 varchar(255),
    surname              varchar(255),
    role                 varchar(255),
    phone                varchar(255),
    account_type         varchar(255),
    gender               varchar(255),
    image_url            varchar(255),
    provider             varchar(255),
    provider_id          varchar(255),
    birthday             date,
    enabled              boolean,
    verification_code    varchar(255),
    reset_password_token varchar(255)
);
