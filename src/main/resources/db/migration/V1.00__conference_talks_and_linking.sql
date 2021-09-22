CREATE TABLE conference
(
    id                BIGSERIAL primary key,
    name              varchar(255) not null,
    participant_number integer      not null,
    date              date         not null unique
);

CREATE TYPE talkType AS ENUM ('WORKSHOP', 'MASTER_CLASS', 'TALK');

CREATE TABLE talk
(
    id      BIGSERIAL primary key,
    name    varchar(255) not null,
    description varchar(255) not null,
    orator varchar(255) not null,
    type talkType not null
);

CREATE TABLE planned_talks
(
    conference_id BIGINT NOT NULL,
    talk_id       BIGINT NOT NULL,
    primary key (conference_id, talk_id),
    constraint fk_conference_id foreign key (conference_id) references conference (id),
    constraint fk_talk_id foreign key (talk_id) references talk (id)
);
