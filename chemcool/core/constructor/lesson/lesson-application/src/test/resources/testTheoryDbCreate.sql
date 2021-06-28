drop table if exists chemistry_theory_page;
drop table if exists chemistry_theory_event;

create table chemistry_theory_page
(
    theory_id          varchar(255) not null,
    theory_chapter     int4,
    theory_description varchar(10000),
    theory_references  int4,
    theory_name        varchar(255),
    primary key (theory_id)
);

create table chemistry_theory_event
(
    event_id                    varchar(255) not null,
    event_author                varchar(255),
    event_entity_id             varchar(255),
    event_occuring_context      varchar(255),
    event_occuring_context_time timestamp,
    event_payload               jsonb,
    event_type                  int4,
    version                     varchar(255),
    primary key (event_id)
);

INSERT INTO chemistry_theory_page (theory_id, theory_chapter, theory_description, theory_references, theory_name)
VALUES ('1', 1, 'theory_description1', 4, 'theory_name1');
INSERT INTO chemistry_theory_page (theory_id, theory_chapter, theory_description, theory_references, theory_name)
VALUES ('2', 2, 'theory_description2', 4, 'theory_name2');
INSERT INTO chemistry_theory_page (theory_id, theory_chapter, theory_description, theory_references, theory_name)
VALUES ('3', 2, 'theory_description3', 4, 'theory_name3');
INSERT INTO chemistry_theory_page (theory_id, theory_chapter, theory_description, theory_references, theory_name)
VALUES ('4', 3, 'theory_description4', 4, 'theory_name4');
INSERT INTO chemistry_theory_page (theory_id, theory_chapter, theory_description, theory_references, theory_name)
VALUES ('5', 3, 'theory_description5', 3, 'theory_name5');
INSERT INTO chemistry_theory_page (theory_id, theory_chapter, theory_description, theory_references, theory_name)
VALUES ('6', 3, 'theory_description6', 3, 'theory_name6');
INSERT INTO chemistry_theory_page (theory_id, theory_chapter, theory_description, theory_references, theory_name)
VALUES ('7', 4, 'theory_description7', 3, 'theory_name7');
INSERT INTO chemistry_theory_page (theory_id, theory_chapter, theory_description, theory_references, theory_name)
VALUES ('8', 4, 'theory_description8', 2, 'theory_name8');
INSERT INTO chemistry_theory_page (theory_id, theory_chapter, theory_description, theory_references, theory_name)
VALUES ('9', 4, 'theory_description9', 2, 'theory_name9');
INSERT INTO chemistry_theory_page (theory_id, theory_chapter, theory_description, theory_references, theory_name)
VALUES ('10', 4, 'theory_description10', 1, 'theory_name10');
