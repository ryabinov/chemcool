create table if not exists users
(
    id                   varchar(255) not null
        constraint users_pkey
            primary key,
    name                 varchar(255),
    surname              varchar(255),
    gender               varchar(255),
    phone                varchar(255),
    email                varchar(255)
        constraint uk_user_email
            unique,
    password             varchar(255),
    image_url            varchar(255),
    provider             varchar(255),
    provider_id          varchar(255),
    role                 varchar(255),
    type                 varchar(255),
    birthday             date,
    enabled              boolean,
    verification_code    varchar(255),
    reset_password_token varchar(255)
);

create table if not exists users_event
(
    event_id               varchar(255) not null
        constraint users_event_pkey
            primary key,
    author_id              varchar(255),
    entity_id              varchar(255),
    event_type             varchar(255),
    event_version          varchar(255),
    occurring_context      varchar(255),
    occurring_context_time timestamp,
    payload                jsonb
);