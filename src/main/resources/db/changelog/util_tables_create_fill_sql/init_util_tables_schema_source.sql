BEGIN;
drop table if exists command_state_dependency cascade;
drop table if exists card_draft cascade;
drop table if exists current_condition cascade;
drop table if exists States cascade;
drop table if exists Commands cascade;
drop table if exists command_state_message_history cascade;

create table Commands
(
    id      bigint       not null
        primary key,
    name varchar(255) not null
        constraint uk_sj9rerwuj7q00a7oy76efiehv
            unique
);

create table States
(
    id    bigint       not null
        primary key,
    name varchar(255) not null
        constraint uk_rdb7pnbo5e3l4vc5bkpk5q6t1
            unique
);

create table command_state_dependency
(
    id              bigint not null
        primary key,
    base_id          bigint
        constraint fkerjv4ayfkhw840r0tkpi3ounm
            references commands,
    command_id       bigint
        constraint fk3swo3tv5uw84gt0h15o42y8s3
            references commands,
    current_state_id  bigint
        constraint fkd4jr0wuyapa5sgd4vr19g7v9f
            references states,
    next_state_id     bigint
        constraint fkf7j5gs40ihsgf9i9h4dtc4e9h
            references states,
    previous_state_id bigint
        constraint fkahgr74c14r91lcfw431eyk5ts
            references states
);

create table current_condition
(
    id        bigint not null
        primary key,
    command_id bigint not null
        constraint uk_d6bvyng0m5wooggruq1kygv3c
            unique
        constraint fk94aseiloyag45c28srlpspwte
            references commands,
    state_id   bigint
        constraint fk7d9h2c3f6ml9lugnofv91qehu
            references states
);

create table card_draft
(
    id      bigint not null
        primary key,
    name    varchar(255),
    balance numeric(38, 2),
    status  varchar(30)
);

create table command_state_message_history
(
    id        bigint not null
        primary key,
    message   varchar(255),
    timestamp timestamp default current_timestamp
);
END;